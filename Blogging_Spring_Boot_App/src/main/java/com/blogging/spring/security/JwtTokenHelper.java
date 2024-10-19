package com.blogging.spring.security;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.blogging.spring.config.AppConstsnts;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

//3.Created JwtTokenHelper.class
@Component
public class JwtTokenHelper {
	
	private Key secret=Keys.secretKeyFor(SignatureAlgorithm.HS256);
	
	private String SECRET_KEY="token";
	
	//Retrieve username from jwt token
	public String getUserNameFromToken(String token) {
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	//Retrieve expiration data from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	public <T> T getClaimFromToken(String token,Function<Claims, T> claimsResolver){
		final Claims claims= getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	//for retrieve any information any information from token we will need secret key
	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();
	}
	
	//check if the token expired
	private boolean isTokenExpired(String token) {
		final Date expiration= getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	//generate token for user
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
	 private Key getSigningKey() throws IllegalArgumentException {
	        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
	        if (keyBytes.length < 32) {
	            throw new IllegalArgumentException("The key must be at least 32 bytes (256 bits) long.");
	        }
	        return Keys.hmacShaKeyFor(keyBytes); // Generates a secure key for HS256
	    }
	
	public String doGenerateToken(Map<String, Object> claims, String subject) {
//		return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256) // Use the secure key
//                .compact();
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + AppConstsnts.JWT_TOKEN_VALIDITY* 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,secret).compact();
//		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }
	
	//validate token 
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUserNameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
