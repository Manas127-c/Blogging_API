package com.blogging.spring.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//2.Create JWTauthenticationEntryPoint(c)implements AuthenticationEntryPoint(i)
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{
	
	/*
	 * 1.Add dependency(io.jsonwebtoken) 
	 * 2.Create JWTauthenticationEntryPoint(c)implements AuthenticationEntryPoint(i) 
	 * 3.Create JWTTokenHelper(c) 
	 * 4.Create JwtAuthenticationFilter extends OnceRequestFilter 
	 *   ->Get jwt token from request
	 *   ->Validate token 
	 *   ->Get user from token 
	 *   ->Load user associated with token
	 *   ->Set spring security 
	 * 5.Create JwtAuthResponse 
	 * 6.Configure JWT in spring security config. 
	 * 7.Create login api to return token 
	 * 8.Test the application
	 */
	
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Access Denied");
		
	}
	
	
	

}
