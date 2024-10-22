package com.blogging.spring.payload;

//5. Create JwtAuthResponse.class
public class JwtAuthResponse {
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
