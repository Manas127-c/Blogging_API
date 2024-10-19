package com.blogging.spring;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BloggingSpringBootAppApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder pEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(BloggingSpringBootAppApplication.class, args);
	}
	
	//create a model mapper bean object
	
	@Bean
	public ModelMapper mapmodel() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.pEncoder.encode("John1010"));
	}
}
