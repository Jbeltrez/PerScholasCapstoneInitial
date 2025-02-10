package com.john.spring.services.impl;

import com.john.spring.converter.TempConverter;
import com.john.spring.dto.DrinkDto;
import com.john.spring.entity.DrinkEntity;
import com.john.spring.exceptions.InstanceUndefinedException;
import com.john.spring.repository.DrinkRepository;
import com.john.spring.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkServiceImpl implements DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private TempConverter tempConverter;

    @Override
    @Transactional
    public DrinkDto addDrink(DrinkDto drink) {
        DrinkEntity drinkEntity = tempConverter.drinkDtoToEntity(drink);
        DrinkEntity savedDrink = drinkRepository.save(drinkEntity);
        return tempConverter.drinkEntityToDto(savedDrink);
    }

    @Override
    @Transactional (readOnly = true)
    public DrinkDto getDrinkById(Integer drinkId) {

        Error error = new Error("The drink has not been found");
        DrinkEntity drinkEntity = drinkRepository.findById(drinkId).orElseThrow(() -> new InstanceUndefinedException(error));
        return tempConverter.drinkEntityToDto(drinkEntity);
    }

    @Override
    @Transactional
    public DrinkDto updateDrinkDto(DrinkDto drink, Integer drinkId) {
        getDrinkById(drinkId);
        drink.setDrinkId(drinkId);
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
    @Transactional (readOnly = true)
    public List<DrinkDto> listAllDrinks() {

        List<DrinkEntity> allDrinks = drinkRepository.findAll();

        List<DrinkDto> returnValue = new ArrayList<>();
        allDrinks.forEach((drinkEntity -> {
            DrinkDto drinkDto = tempConverter.drinkEntityToDto(drinkEntity);
            returnValue.add(drinkDto);
        }));
        return returnValue;

    }


}
