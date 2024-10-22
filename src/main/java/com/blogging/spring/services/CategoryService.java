package com.blogging.spring.services;

import java.util.List;
import com.blogging.spring.payload.CategoryDTO;

public interface CategoryService {
	
	public CategoryDTO createCategory(CategoryDTO cDto);
	public CategoryDTO updateCategory(CategoryDTO cDto,Integer id);
	public String deleteCategoryById(Integer id);
	public CategoryDTO getCategoryById(Integer id);
	public List<CategoryDTO> getAllCategories();
}
