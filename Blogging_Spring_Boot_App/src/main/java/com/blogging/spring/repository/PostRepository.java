package com.blogging.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.spring.entities.Category;
import com.blogging.spring.entities.Post;
import com.blogging.spring.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}
