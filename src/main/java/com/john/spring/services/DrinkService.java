package com.john.spring.services;

import com.john.spring.dto.DrinkDto;

import java.util.List;

public interface DrinkService {

    DrinkDto addDrink(DrinkDto drink);
    DrinkDto getDrinkById(Integer drinkId);
    DrinkDto updateDrinkDto(DrinkDto drink, Integer drinkId);
    void deleteDrink(Integer drinkId);
    List<DrinkDto> listAllDrinks();
}
