package com.blogging.spring.services.implement;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.spring.entities.Comment;
import com.blogging.spring.entities.Post;
import com.blogging.spring.entities.User;
import com.blogging.spring.exception.ResourceNotFoundException;
import com.blogging.spring.payload.CommentDTO;
import com.blogging.spring.repository.CommentRepository;
import com.blogging.spring.repository.PostRepository;
import com.blogging.spring.repository.UserRepository;
import com.blogging.spring.services.CommentService;

@Service
public class CommentServiceImplement implements CommentService {

	@Autowired
	private CommentRepository cRepository;

	@Autowired
	UserRepository uRepository;

	@Autowired
	PostRepository pRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDTO createComment(CommentDTO cDto, Integer postId, Integer userId) {
		User user = uRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Post post = pRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		Comment comment = this.mapper.map(cDto, Comment.class);
		comment.setcDate(new Date());
		comment.setPost(post);
		comment.setUser(user);
		Comment comment2 = this.cRepository.save(comment);
		return this.mapper.map(comment2, CommentDTO.class);
	}

	@Override
	public List<CommentDTO> getAllComments() {
		List<Comment> comments = this.cRepository.findAll();
		List<CommentDTO> cDtos = comments.stream().map((e) -> this.mapper.map(e, CommentDTO.class))
				.collect(Collectors.toList());
		return cDtos;
	}

	@Override
	public CommentDTO updateComment(CommentDTO cDto, Integer commentId) {
		Comment comment = this.cRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", commentId));
		comment.setcContent(cDto.getcContent());
		comment.setcDate(new Date());
		Comment updateComment = this.cRepository.save(comment);
		return this.mapper.map(updateComment, CommentDTO.class);
	}

	@Override
	public CommentDTO getAllCommentsByUserId(Integer userId, Integer cId) {
		User user = uRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Comment comments = this.cRepository.findByUserAndCId(user, cId);
		return this.mapper.map(comments, CommentDTO.class);
	}

	@Override
	public Set<CommentDTO> getAllCommentsByPostId(Integer postId) {
		Post post = pRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		Set<Comment> comments = this.cRepository.findByPost(post);
		Set<CommentDTO> cDtos = comments.stream().map((e) -> this.mapper.map(e, CommentDTO.class))
				.collect(Collectors.toSet());
		return cDtos;
	}

	@Override
	public String deleteComment(Integer commentId) {
		Comment comment = this.cRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
		this.cRepository.delete(comment);
		return "Comment Id " + commentId + " Deleted.";
	}

}
