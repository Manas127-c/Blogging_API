package com.blogging.spring.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="postdetails")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(name="ptitle")
	private String postTitle;
	
	@Column(name="pcontent")
	private String postContent;
	
	@Column(name="pimage")
	private String postImage;
	
	@Column(name="pdate")
	private Date postAddedDate;
	
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public Date getPostAddedDate() {
		return postAddedDate;
	}

	public void setPostAddedDate(Date postAddedDate) {
		this.postAddedDate = postAddedDate;
	}
}
