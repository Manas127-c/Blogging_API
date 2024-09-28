package com.blogging.spring.services;

import java.util.List;

import com.blogging.spring.payload.UserDTO;

//In this we create database operation abstract method

public interface UserService {
	UserDTO createUser(UserDTO user);
	UserDTO updateUser(UserDTO user,Integer id);
	void deleteUser(Integer id);
	UserDTO getUser(Integer id);
	List<UserDTO> getUsers();
}
