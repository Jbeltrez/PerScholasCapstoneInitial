package com.john.spring.services.impl;

import com.john.spring.converter.TempConverter;
import com.john.spring.dto.CategoryDto;
import com.john.spring.entity.CategoryEntity;
import com.john.spring.exceptions.InstanceUndefinedException;
import com.john.spring.repository.CategoryRepository;
import com.john.spring.services.CategoryService;

import com.john.spring.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


	private CategoryRepository categoryRepository;
	private TempConverter tempConverter;

	@Autowired
	private DrinkService drinkService;

	@Autowired
	private void initialize(CategoryRepository categoryRepository, TempConverter tempConverter) {
		this.categoryRepository = categoryRepository;
		this.tempConverter = tempConverter;
	}

	@Override
	@Transactional(readOnly = true)
	public CategoryDto getCategoryById(Integer categoryId) {
		CategoryEntity categoryEntity = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new InstanceUndefinedException(new Error("The category has not been found")));
		return tempConverter.categoryEntityToDto(categoryEntity);
	}

	@Override
	@Transactional
	public void deleteCategory(Integer categoryId) {
		getCategoryById(categoryId);
		categoryRepository.deleteById(categoryId);
		categoryRepository.flush();
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoryDto> listAllCategories() {
		List<CategoryEntity> allCategories = categoryRepository.findAll();
		List<CategoryDto> returnValue = new ArrayList<>();
		allCategories.forEach((categoryEntity) -> {
			returnValue.add(tempConverter.categoryEntityToDto(categoryEntity));
		});
		return returnValue;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CategoryDto> listAllByDrinkId(Integer drinkId) {
		drinkService.getDrinkById(drinkId);
		List<CategoryEntity> allCategories = categoryRepository.findAllByDrinkId(drinkId);
		List<CategoryDto> returnValue = new ArrayList<>();
		allCategories.forEach((categoryEntity) -> {
			returnValue.add(tempConverter.categoryEntityToDto(categoryEntity));
		});
		return returnValue;
	}

	@Override
	@Transactional
	public CategoryDto updateCategory(CategoryDto category, Integer categoryId) {
		CategoryDto currentCategory = getCategoryById(categoryId);
		category.setCategoryId(currentCategory.getCategoryId());
		List<Integer> drinksIds = currentCategory.getDrinkIds();
		if (drinksIds == null) {
			drinksIds = new ArrayList<>();
		}
		category.setDrinkIds(drinksIds);
		CategoryEntity updatedCategory = categoryRepository.saveAndFlush(tempConverter.categoryDtoToEntity(category));

		return tempConverter.categoryEntityToDto(updatedCategory);
	}
}
