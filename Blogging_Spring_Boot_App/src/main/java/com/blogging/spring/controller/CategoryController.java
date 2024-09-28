package com.blogging.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.blogging.spring.payload.CategoryDTO;
import com.blogging.spring.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/catagories")
public class CategoryController {
	
	@Autowired
	CategoryService cService;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> insertCategory(@Valid @RequestBody CategoryDTO cDto){
		CategoryDTO cDto2=cService.createCategory(cDto);
		return new ResponseEntity<>(cDto2,HttpStatus.CREATED);
	}
	
	@PutMapping("/{cId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO cDto,@PathVariable Integer cId){
		CategoryDTO cDto2=cService.updateCategory(cDto, cId);
		return new ResponseEntity<>(cDto2,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{cId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Integer cId){
		String messgae= cService.deleteCategoryById(cId);
		return new ResponseEntity<String>( messgae, HttpStatus.OK);
	}
	
	@GetMapping("/{cId}")
	public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Integer cId){
		CategoryDTO cDto=cService.getCategoryById(cId);
		return new ResponseEntity<CategoryDTO>(cDto, HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getCategories(){
		List<CategoryDTO> dtos=cService.getAllCategories();
		return new ResponseEntity<List<CategoryDTO>>(dtos, HttpStatus.OK);
	}
}
