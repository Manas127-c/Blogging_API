package com.blogging.spring.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImageUploadSevice {
	String uploadImage(String path,MultipartFile image) throws IOException;
	InputStream getResourses(String path,String fileName) throws FileNotFoundException;
}
