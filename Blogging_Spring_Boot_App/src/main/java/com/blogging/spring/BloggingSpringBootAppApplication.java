package com.blogging.spring;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BloggingSpringBootAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BloggingSpringBootAppApplication.class, args);
	}
	
	//create a model mapper bean object
	
	@Bean
	public ModelMapper mapmodel() {
		return new ModelMapper();
	}
}
