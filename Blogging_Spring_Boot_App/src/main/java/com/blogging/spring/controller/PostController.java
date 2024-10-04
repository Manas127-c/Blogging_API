package com.blogging.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.spring.payload.PostDTO;
import com.blogging.spring.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/")
public class PostController {
	
	@Autowired
	PostService pService;
	
	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDTO> insertPosts(@Valid @RequestBody PostDTO pDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDTO pDto2=pService.createPost(pDto, userId, categoryId);
		return new ResponseEntity<PostDTO>(pDto2,HttpStatus.CREATED);
	}
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePosts(@Valid @RequestBody PostDTO pDto,@PathVariable Integer postId){
		PostDTO pDto2=pService.updatePost(pDto, postId);
		return new ResponseEntity<PostDTO>(pDto2,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId){
		String message=pService.deletePost(postId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		PostDTO pDto=pService.getPostById(postId);
		return new ResponseEntity<PostDTO>(pDto,HttpStatus.OK);
	}
	
	@GetMapping("/posts")
	public ResponseEntity<List<PostDTO>> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false) Integer pageSize
			){
		List<PostDTO> pDtos=pService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<List<PostDTO>>(pDtos,HttpStatus.OK);
	}
	
	@GetMapping("user/{userId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByUser(@PathVariable Integer userId){
		List<PostDTO> pDtos=pService.getPostByUser(userId);
		return new ResponseEntity<List<PostDTO>>(pDtos,HttpStatus.OK);
	}
	
	@GetMapping("category/{categoryId}/posts")
	public ResponseEntity<List<PostDTO>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDTO> pDtos=pService.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDTO>>(pDtos,HttpStatus.OK);
	}
}
