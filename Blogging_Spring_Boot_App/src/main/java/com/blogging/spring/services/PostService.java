package com.blogging.spring.services;

import java.util.List;

import com.blogging.spring.payload.PostDTO;

public interface PostService {
	public PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
	public PostDTO updatePost(PostDTO postDTO,Integer id);
	public String deletePost(Integer id);
	public PostDTO getPostById(Integer id);
	public List<PostDTO> getAllPost();
	public List<PostDTO> getPostByUser(Integer userId);
	public List<PostDTO> getPostByCategory(Integer categoryId);
	public List<PostDTO> searchPostByKeyword(String keyword);
}
