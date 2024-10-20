package com.blogging.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.spring.payload.JwtAuthRequest;
import com.blogging.spring.payload.JwtAuthResponse;
import com.blogging.spring.payload.UserDTO;
import com.blogging.spring.security.JwtTokenHelper;
import com.blogging.spring.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jHelper;
	
	@Autowired
	private UserDetailsService uService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		this.authentication(request.getUsername(),request.getPassword());
		UserDetails userDetails	= this.uService.loadUserByUsername(request.getUsername());
		String token=this.jHelper.generateToken(userDetails);
		JwtAuthResponse response=new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerNewUser(@RequestBody UserDTO uDto){
		UserDTO uDto2=this.userService.registerUser(uDto);
		return new ResponseEntity<UserDTO>(uDto2,HttpStatus.CREATED);
	}
	
	private void authentication(String username,String password) throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}catch (BadCredentialsException e) {
			throw new  Exception("Invalid username or password");
		}
	}
	
	
}
