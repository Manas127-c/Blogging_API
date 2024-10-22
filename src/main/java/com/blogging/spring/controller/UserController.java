package com.blogging.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.spring.payload.UserDTO;
import com.blogging.spring.services.UserService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	//use @RequestBody for get value from the client
	//use @Valid for validate user data
	//use @PathVariable to get the value from url path that define ("/{data}").
	
	@Autowired
	 UserService uService;
	
	@PostMapping("/insert")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO userDTO2= uService.createUser(userDTO);
		return new ResponseEntity<>(userDTO2,HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable Integer userId){
		UserDTO userDTO2=uService.updateUser(userDTO, userId);
		return new ResponseEntity<>(userDTO2,HttpStatus.ACCEPTED);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer userId){
		uService.deleteUser(userId);
		return new ResponseEntity<>(userId+" id user deleted",HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
		UserDTO userDTO=uService.getUser(userId);
		return new ResponseEntity<>(userDTO,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		List<UserDTO> userDTOs=uService.getUsers();
		return new ResponseEntity<>(userDTOs,HttpStatus.OK);
	}
}
