package com.blogging.spring.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.spring.payload.CommentDTO;
import com.blogging.spring.services.CommentService;

import jakarta.validation.Valid;


@RestController
//@RequestMapping("api/")
public class CommentController {
	
	@Autowired
	private CommentService cService;
	
	@PostMapping("api/post/{postId}/user/{userId}/comment")
	public ResponseEntity<CommentDTO> createComment(@Valid @RequestBody CommentDTO cDto, @PathVariable Integer postId,
			@PathVariable Integer userId) {
		CommentDTO cDto2=this.cService.createComment(cDto, postId, userId);
		return new ResponseEntity<CommentDTO>(cDto2,HttpStatus.CREATED);
	}
	
	@PutMapping("api/comment/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(@RequestBody CommentDTO cDto,@PathVariable Integer commentId){
		CommentDTO cDto2=this.cService.updateComment(cDto, commentId);
		return new ResponseEntity<CommentDTO>(cDto2,HttpStatus.OK);
	}
	
	@GetMapping("api/comments")
	public ResponseEntity<List<CommentDTO>> getAllComment(){
		List<CommentDTO> commentDTOs= this.cService.getAllComments();
		return new ResponseEntity<List<CommentDTO>>(commentDTOs,HttpStatus.OK);
	}
	
	@GetMapping("api/user/{userId}/comment/{cId}")
	public ResponseEntity<CommentDTO> getCommentByUser(@PathVariable Integer userId,@PathVariable Integer cId){
		CommentDTO cDto=this.cService.getAllCommentsByUserId(userId, cId);
		return new ResponseEntity<CommentDTO>(cDto,HttpStatus.OK);
	}
	
	@GetMapping("api/post/{postId}/comments")
	public ResponseEntity<Set<CommentDTO>> getAllCommentByPost(@PathVariable Integer postId){
		Set<CommentDTO> cDtos=this.cService.getAllCommentsByPostId(postId);
		return new ResponseEntity<Set<CommentDTO>>(cDtos,HttpStatus.OK);
	}
	
	@DeleteMapping("api/comment/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer commentId){
		String message=this.cService.deleteComment(commentId);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
}
