package com.john.spring.utils;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.john.spring.entity.CategoryEntity;
import com.john.spring.repository.CategoryRepository;

@Component
public class LoadData {

	private CategoryRepository categoryRepository;

	@Autowired
	private void initialize(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
		loadCategories();
	}

	public void loadCategories() {

		Optional<CategoryEntity> categoryOptional = categoryRepository.findByName("Non-Alcoholic");
		if (categoryOptional.isEmpty()) {
			categoryRepository.save(new CategoryEntity("Non-Alcoholic"));
		}

		categoryOptional = categoryRepository.findByName("Beer");
		if (categoryOptional.isEmpty()) {
			categoryRepository.save(new CategoryEntity("Beer"));
		}

		categoryOptional = categoryRepository.findByName("Wine");
		if (categoryOptional.isEmpty()) {
			categoryRepository.save(new CategoryEntity("Wine"));
		}

		categoryOptional = categoryRepository.findByName("Cocktail");
		if (categoryOptional.isEmpty()) {
			categoryRepository.save(new CategoryEntity("Cocktail"));
		}

		categoryOptional = categoryRepository.findByName("Spirit");
		if (categoryOptional.isEmpty()) {
			categoryRepository.save(new CategoryEntity("Spirit"));
		}

		categoryOptional = categoryRepository.findByName("Shaken");
		if (categoryOptional.isEmpty()) {
			categoryRepository.save(new CategoryEntity("Shaken"));
		}

		categoryOptional = categoryRepository.findByName("Stirred");
		if (categoryOptional.isEmpty()) {
			categoryRepository.save(new CategoryEntity("Stirred"));
		}
	}
}
