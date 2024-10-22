package com.blogging.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.blogging.spring.payload.CommentDTO;

@Service
public interface CommentService {
	public CommentDTO createComment(CommentDTO cDto, Integer postId, Integer userId);

	public List<CommentDTO> getAllComments();

	public CommentDTO getAllCommentsByUserId(Integer userId, Integer cId);

	public CommentDTO updateComment(CommentDTO cDto, Integer commentId);

	public Set<CommentDTO> getAllCommentsByPostId(Integer postId);
	
	public String deleteComment(Integer commentId);
}
