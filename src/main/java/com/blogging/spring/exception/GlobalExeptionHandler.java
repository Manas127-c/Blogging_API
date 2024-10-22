package com.blogging.spring.exception;


import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogging.spring.payload.ApiResponse;

@RestControllerAdvice
public class GlobalExeptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)// it handles custom class exception
	public ResponseEntity<ApiResponse> resourseNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();// it get current exception class message
		ApiResponse api=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(api,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserNameNotFoundException.class)// it handles custom class exception
	public ResponseEntity<ApiResponse> userNameNotFoundExceptionHandler(UserNameNotFoundException ex){
		String message=ex.getMessage();// it get current exception class message
		ApiResponse api=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(api,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> methodArugment(MethodArgumentNotValidException ex){
		Map<String, String> resp=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fielderror=((FieldError)error).getField();
			String message=error.getDefaultMessage();
			resp.put(fielderror, message);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
}
