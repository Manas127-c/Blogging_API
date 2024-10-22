package com.blogging.spring.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import com.blogging.spring.entities.Comment;
import com.blogging.spring.entities.Post;
import com.blogging.spring.entities.User;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	Comment findByUserAndCId(User user, Integer cId);
	Set<Comment> findByPost(Post post);
}
