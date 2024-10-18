package com.blogging.spring.exception;

@SuppressWarnings("serial")
public class UserNameNotFoundException extends RuntimeException{
	String resourceName;
	String fieldName;
	String fieldValue;
	public UserNameNotFoundException(String resourceName, String fieldName, String fieldValue) {
		super(String.format("%s %s email not found is : %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
}
