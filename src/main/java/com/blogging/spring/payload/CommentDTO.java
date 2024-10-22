package com.blogging.spring.payload;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;

public class CommentDTO {
	private int cId;
	
	@NotEmpty(message = "comment data must not be empty")
	private String cContent;
	
	private Date cDate;
	private PostDTO post;
	private UserDTO user;
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getcContent() {
		return cContent;
	}
	public void setcContent(String cContent) {
		this.cContent = cContent;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
	public PostDTO getPost() {
		return post;
	}
	public void setPost(PostDTO post) {
		this.post = post;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	
}
