package com.blogging.spring.services.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.blogging.spring.entities.Category;
import com.blogging.spring.exception.ResourceNotFoundException;
import com.blogging.spring.payload.CategoryDTO;
import com.blogging.spring.repository.CategoryRepository;
import com.blogging.spring.services.CategoryService;

@Component
@Service
public class CategoryServiceImplemet implements CategoryService{
	
	@Autowired
	CategoryRepository cRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CategoryDTO createCategory(CategoryDTO cDto) {
		Category category=this.categoryDtoToCategory(cDto);
		Category savedCategory=cRepository.save(category);
		return this.categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO cDto,Integer id) {
		Category category=cRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Id", id));
		
		category.setCategoryTitle(cDto.getCategoryTitle());
		category.setCategoryDescription(cDto.getCategoryDescription());
		
		Category saveCategory=cRepository.save(category);
		
		return this.categoryToCategoryDto(saveCategory);
	}

	@Override
	public String deleteCategoryById(Integer id) {
		Category category=cRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Id", id));
		this.cRepository.delete(category);
		return "Category Id : "+id+" Data Deleted";
	}

	@Override
	public CategoryDTO getCategoryById(Integer id) {
		Category category=cRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Category", "Id", id));
		return this.categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> categories=this.cRepository.findAll();
		List<CategoryDTO> uDtos=categories.stream().map(data->this.categoryToCategoryDto(data)).collect(Collectors.toList());
		return uDtos;
	}
	
	public Category categoryDtoToCategory(CategoryDTO cDto) {
		Category category=this.mapper.map(cDto,Category.class);
		return category;
	}
	
	public CategoryDTO categoryToCategoryDto(Category category) {
		CategoryDTO cDto=this.mapper.map(category,CategoryDTO.class);
		return cDto;
	}

}
