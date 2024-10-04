package com.blogging.spring.services.implement;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.blogging.spring.entities.Category;
import com.blogging.spring.entities.Post;
import com.blogging.spring.entities.User;
import com.blogging.spring.exception.ResourceNotFoundException;
import com.blogging.spring.payload.PostDTO;
import com.blogging.spring.payload.PostResponse;
import com.blogging.spring.repository.CategoryRepository;
import com.blogging.spring.repository.PostRepository;
import com.blogging.spring.repository.UserRepository;
import com.blogging.spring.services.PostService;

@Component
@Service
public class PostServiceImplement implements PostService{
	
	@Autowired
	PostRepository pRepository;
	
	@Autowired
	private UserRepository uRepository;
	
	@Autowired
	private CategoryRepository cRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId) {
		
		User user=uRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		Category category=cRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "Id", categoryId));
		
		Post post=this.postDtoToPost(postDTO);
		post.setPostAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savedPost=pRepository.save(post);
		return this.postToPostDto(savedPost);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer id) {
		Post post=pRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "Id", id));
		post.setPostTitle(postDTO.getPostTitle());
		post.setPostContent(postDTO.getPostContent());
		post.setPostImage(postDTO.getPostImage());
		post.setPostAddedDate(new Date());
		Post updatePost=pRepository.save(post);
		return this.postToPostDto(updatePost);
	}

	@Override
	public String deletePost(Integer id) {
		Post post=pRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "Id", id));
		pRepository.delete(post);
		return " Id : "+id+" is deleted";
	}

	@Override
	public PostDTO getPostById(Integer id) {
		Post post=pRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "Id", id));
		return this.postToPostDto(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize) {
		
		Pageable pageable=PageRequest.of(pageNumber,pageSize);//create object as request
		Page<Post> pages=pRepository.findAll(pageable);//get the values as request
		List<Post> posts=pages.getContent();//set the values in list
//		List<Post> posts=pRepository.findAll();
		List<PostDTO> pDtos=posts.stream().map(e->this.postToPostDto(e)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(pDtos);
		postResponse.setPageNumber(pages.getNumber());
		postResponse.setPageSize(pages.getSize());
		postResponse.setTotalElements(pages.getNumberOfElements());
		postResponse.setTotalPages(pages.getTotalPages());
		postResponse.setLastPage(pages.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize) {
		User user=uRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
		Pageable pageable=PageRequest.of(pageNumber,pageSize);
		Page<Post> pages=pRepository.findByUser(user,pageable);
		List<Post> posts=pages.getContent();
		List<PostDTO> pDtos=posts.stream().map(e->this.postToPostDto(e)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(pDtos);
		postResponse.setPageNumber(pages.getNumber());
		postResponse.setPageSize(pages.getSize());
		postResponse.setTotalElements(pages.getNumberOfElements());
		postResponse.setTotalPages(pages.getTotalPages());
		postResponse.setLastPage(pages.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize) {
		Category category=cRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Id", categoryId));
		Pageable pageable=PageRequest.of(pageNumber, pageSize);
		Page<Post> pages=pRepository.findByCategory(category,pageable);
		List<Post> posts=pages.getContent();
		List<PostDTO> pDtos=posts.stream().map(e->this.postToPostDto(e)).collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(pDtos);
		postResponse.setPageNumber(pages.getNumber());
		postResponse.setPageSize(pages.getSize());
		postResponse.setTotalElements(pages.getNumberOfElements());
		postResponse.setTotalPages(pages.getTotalPages());
		postResponse.setLastPage(pages.isLast());
		return postResponse;
	}
	
	@Override
	public List<PostDTO> searchPostByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Post postDtoToPost(PostDTO pDto) {
		Post post=this.mapper.map(pDto, Post.class);
		return post;
	}
	
	public PostDTO postToPostDto(Post post) {
		PostDTO pDto=this.mapper.map(post, PostDTO.class);
		return pDto;
	}

	
}
