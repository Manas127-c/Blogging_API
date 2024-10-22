package com.blogging.spring.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogging.spring.config.AppConstsnts;
import com.blogging.spring.payload.PostDTO;
import com.blogging.spring.payload.PostResponse;
import com.blogging.spring.services.ImageUploadSevice;
import com.blogging.spring.services.PostService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;


@RestController
@RequestMapping("api/")
public class PostController {

	@Autowired
	PostService pService;
	
	@Autowired
	private ImageUploadSevice iSevice;
	
	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDTO> insertPosts(@Valid @RequestBody PostDTO pDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDTO pDto2 = pService.createPost(pDto, userId, categoryId);
		return new ResponseEntity<PostDTO>(pDto2, HttpStatus.CREATED);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePosts(@Valid @RequestBody PostDTO pDto, @PathVariable Integer postId){
		pDto.setPostImage("default.png");
		PostDTO pDto2 = pService.updatePost(pDto, postId);
		return new ResponseEntity<PostDTO>(pDto2, HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("/posts/upload/{postId}")
	public ResponseEntity<PostDTO> updateImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException{
		
		PostDTO pDto=this.pService.getPostById(postId);
		String imageName=this.iSevice.uploadImage(path, image);
		pDto.setPostImage(imageName);
		pDto=this.pService.updatePost(pDto, postId);
		
		return new ResponseEntity<PostDTO>(pDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping(value="post/image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable String imageName,HttpServletResponse response) throws IOException {
		InputStream resorse=this.iSevice.getResourses(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resorse, response.getOutputStream());
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
		String message = pService.deletePost(postId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId) {
		PostDTO pDto = pService.getPostById(postId);
		return new ResponseEntity<PostDTO>(pDto, HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber", defaultValue = AppConstsnts.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstsnts.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstsnts.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDirection", defaultValue = AppConstsnts.SORT_DIRECTION, required = false) String sortDirection) {
		PostResponse pResponse = pService.getAllPost(pageNumber, pageSize, sortBy, sortDirection);
		return new ResponseEntity<>(pResponse, HttpStatus.OK);
	}

	@GetMapping("user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostsByUser(@PathVariable Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstsnts.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstsnts.PAGE_SIZE, required = false) Integer pageSize) {
		PostResponse pDtos = pService.getPostByUser(userId, pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(pDtos, HttpStatus.OK);
	}

	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostsByCategory(@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber", defaultValue = AppConstsnts.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstsnts.PAGE_SIZE, required = false) Integer pageSize) {
		PostResponse pDtos = pService.getPostByCategory(categoryId, pageNumber, pageSize);
		return new ResponseEntity<>(pDtos, HttpStatus.OK);
	}
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDTO>> searchPosts(@PathVariable("keyword") String keyword){
		List<PostDTO> pDtos=this.pService.searchPostByKeyword(keyword);
		return new ResponseEntity<List<PostDTO>>(pDtos,HttpStatus.OK);
	}
}
