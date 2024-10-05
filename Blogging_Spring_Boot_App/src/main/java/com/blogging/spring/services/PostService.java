package com.blogging.spring.services;

import java.util.List;

import com.blogging.spring.payload.PostDTO;
import com.blogging.spring.payload.PostResponse;

public interface PostService {
	public PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
	public PostDTO updatePost(PostDTO postDTO,Integer id);
	public String deletePost(Integer id);
	public PostDTO getPostById(Integer id);
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDirection);
	public PostResponse getPostByUser(Integer userId,Integer pageNumber,Integer pageSize);
	public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize);
	public List<PostDTO> searchPostByKeyword(String keyword);
}
