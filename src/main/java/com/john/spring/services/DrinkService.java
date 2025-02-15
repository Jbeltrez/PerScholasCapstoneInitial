package com.john.spring.services;

import com.john.spring.dto.DrinkDto;

import java.util.List;

public interface DrinkService {

	DrinkDto addDrink(DrinkDto drink);

	DrinkDto getDrinkById(Integer drinkId);

	DrinkDto updateDrink(DrinkDto drink, Integer drinkId);

	void deleteDrink(Integer drinkId);

	List<DrinkDto> listAllDrinks();

	void addCategory(Integer drinkId, Integer categoryId);

	void addMenu(Integer drinkId, Integer menuId);

	List<DrinkDto> listAllByCategoryId(Integer categoryId);
}
