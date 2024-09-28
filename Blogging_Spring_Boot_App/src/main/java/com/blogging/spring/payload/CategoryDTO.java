package com.blogging.spring.payload;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Component
public class CategoryDTO {
	
	
	private int categoryId;
	
	@NotEmpty(message = "Category Title needed")
	@Size(min = 3,message = "Category title must be above 3 charater")
	private String categoryTitle;
	
	@NotEmpty(message = "Category Description needed")
	@Size(min = 10,message = "Category description must be above 10 charater")
	private String categoryDescription;
	
	public CategoryDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	
}
