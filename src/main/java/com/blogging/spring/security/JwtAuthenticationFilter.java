package com.blogging.spring.security;

import java.io.IOException;

import javax.naming.MalformedLinkException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//4.Create JwtAuthenticationFilter extends OnceRequestFilter 
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private UserDetailsService uDetailsService;
	
	@Autowired
	private JwtTokenHelper jTokenHelper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String requestPath = request.getRequestURI();
        
        // Skip JWT validation for Swagger endpoints
        if (requestPath.startsWith("/v3/api-docs") || requestPath.startsWith("/swagger-ui") || requestPath.startsWith("/swagger-resources")) {
            filterChain.doFilter(request, response);
            return;
        }
		
		//1.get Jwt token from request
		String requestToken=request.getHeader("Authorization");
		String username=null;
		String token=null;
		if(requestToken!=null && requestToken.startsWith("Bearer")) {
			
			token=requestToken.substring(7);
			try {
				username=this.jTokenHelper.getUserNameFromToken(token);
			}catch (IllegalArgumentException e) {
				System.out.println("Unable to get jwt token");
			}catch (ExpiredJwtException e) {
				System.out.println("Jwt token has expired");
			}catch (MalformedJwtException e) {
				System.out.println("Invalid jwt token");
			}
			
		}else {
			System.out.println("Not Autorized");
		}
		
		//now validate the token
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) 
		{
			UserDetails userDetails=this.uDetailsService.loadUserByUsername(username);
			if(this.jTokenHelper.validateToken(token, userDetails)) {
				//Have to Authenicate
				UsernamePasswordAuthenticationToken uAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				uAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
				SecurityContextHolder.getContext().setAuthentication(uAuthenticationToken);
			}else {
				System.out.println("Invalid jwt token");
			}
		}else {
			System.out.println("UserName is null or Context is not null");
		}
		
		filterChain.doFilter(request, response);
	}

}
