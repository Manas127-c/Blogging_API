package com.blogging.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.blogging.spring.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>{
	Optional<User> findByEmail(String email);
}
