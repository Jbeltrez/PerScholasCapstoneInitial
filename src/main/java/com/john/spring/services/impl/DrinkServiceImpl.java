package com.john.spring.services.impl;

import com.john.spring.converter.TempConverter;
import com.john.spring.dto.CategoryDto;
import com.john.spring.dto.DrinkDto;
import com.john.spring.entity.CategoryEntity;
import com.john.spring.entity.DrinkEntity;
import com.john.spring.exceptions.InstanceUndefinedException;
import com.john.spring.repository.CategoryRepository;
import com.john.spring.repository.DrinkRepository;
import com.john.spring.services.CategoryService;
import com.john.spring.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class DrinkServiceImpl implements DrinkService {

	@Autowired
	private DrinkRepository drinkRepository;

	@Autowired
	private TempConverter tempConverter;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	@Override
	@Transactional
	public DrinkDto addDrink(DrinkDto drink) {
		DrinkEntity drinkEntity = tempConverter.drinkDtoToEntity(drink);
		DrinkEntity savedDrink = drinkRepository.save(drinkEntity);
		return tempConverter.drinkEntityToDto(savedDrink);
	}

	@Override
	@Transactional(readOnly = true)
	public DrinkDto getDrinkById(Integer drinkId) {

		Error error = new Error("The drink has not been found");
		DrinkEntity drinkEntity = drinkRepository.findById(drinkId)
				.orElseThrow(() -> new InstanceUndefinedException(error));
		return tempConverter.drinkEntityToDto(drinkEntity);
	}

	@Override
	@Transactional
	public DrinkDto updateDrink(DrinkDto drink, Integer drinkId) {
		DrinkDto currentDrink = getDrinkById(drinkId);
		drink.setDrinkId(currentDrink.getDrinkId());
		Optional<List<Integer>> menusIdsOptional = Optional.of(drink.getMenusids());
		Optional<List<Integer>> categoriesIdsOptional = Optional.ofNullable(drink.getCategoriesids());
		menusIdsOptional.ifPresent((menusIds) -> drink.setMenusids(menusIds));
		categoriesIdsOptional.ifPresent((categoriesIds) -> drink.setCategoriesids(categoriesIds));
		DrinkEntity drinkEntity = tempConverter.drinkDtoToEntity(drink);
		DrinkEntity updatedDrink = drinkRepository.saveAndFlush(drinkEntity);
		return tempConverter.drinkEntityToDto(updatedDrink);
	}

	@Override
	@Transactional
	public void deleteDrink(Integer drinkId) {
		getDrinkById(drinkId);
		drinkRepository.deleteById(drinkId);
		drinkRepository.flush();
	}

	@Override
	@Transactional(readOnly = true)
	public List<DrinkDto> listAllDrinks() {

		List<DrinkEntity> allDrinks = drinkRepository.findAll();

		List<DrinkDto> returnValue = new ArrayList<>();
		allDrinks.forEach((drinkEntity -> {
			DrinkDto drinkDto = tempConverter.drinkEntityToDto(drinkEntity);
			returnValue.add(drinkDto);
		}));
		return returnValue;

	}

	@Override
	@Transactional
	public void addCategory(Integer drinkId, Integer categoryId) {
		DrinkDto drink = getDrinkById(drinkId);
		CategoryDto category = categoryService.getCategoryById(categoryId);

		List<DrinkEntity> allDrinks = drinkRepository.findAllByCategoryId(categoryId);
		List<CategoryEntity> allCategories = categoryRepository.findAllByDrinkId(drinkId);
		DrinkEntity drinkEntity = tempConverter.drinkDtoToEntity(drink);
		CategoryEntity categoryEntity = tempConverter.categoryDtoToEntity(category);
		allDrinks.add(drinkEntity);
		categoryEntity.setDrinks(allDrinks);
		categoryEntity = categoryRepository.saveAndFlush(categoryEntity);

		allCategories.add(categoryEntity);
		drinkEntity.setCategories(allCategories);
		drinkRepository.saveAndFlush(drinkEntity);

	}

	@Override
	@Transactional
	public void addMenu(Integer drinkId, Integer menuId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DrinkDto> listAllByCategoryId(Integer categoryId) {

		List<DrinkEntity> allDrinks = drinkRepository.findAllByCategoryId(categoryId);

		List<DrinkDto> returnValue = new ArrayList<>();
		allDrinks.forEach((drinkEntity -> {
			DrinkDto drinkDto = tempConverter.drinkEntityToDto(drinkEntity);
			returnValue.add(drinkDto);
		}));
		return returnValue;
	}

}
