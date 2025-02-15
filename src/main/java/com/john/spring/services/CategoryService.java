package com.john.spring.services;

import com.john.spring.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

	CategoryDto getCategoryById(Integer categoryId);

	void deleteCategory(Integer categoryId);

	List<CategoryDto> listAllCategories();

	List<CategoryDto> listAllByDrinkId(Integer drinkId);

	CategoryDto updateCategory(CategoryDto category, Integer categoryId);

}
