package com.blogging.spring.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blogging.spring.entities.Category;
import com.blogging.spring.entities.Post;
import com.blogging.spring.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	Page<Post> findByUser(User user, Pageable pageable);
	Page<Post> findByCategory(Category category, Pageable pageable);
	List<Post> findByPostTitleContaining(String title);
}
