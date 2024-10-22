package com.blogging.spring.services.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogging.spring.services.ImageUploadSevice;

@Service
public class ImageUploadServiceImplementation implements ImageUploadSevice{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		//file name
		String name=file.getOriginalFilename();
		
		String imageUid=UUID.randomUUID().toString();
		String newFileName=imageUid.concat(name.substring(name.lastIndexOf(".")));
		
		//fullpath
		String fullPath=path+ File.separator +newFileName;
		
		//create a folder
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		//file copy
		Files.copy(file.getInputStream(), Paths.get(fullPath));
		
		return newFileName;
	}
	
	@Override
	public InputStream getResourses(String path, String fileName) throws FileNotFoundException {
		String fullPath=path+ File.separator+fileName;
		InputStream iStream=new FileInputStream(fullPath);
		return iStream;
	}
}
