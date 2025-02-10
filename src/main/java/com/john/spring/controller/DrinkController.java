package com.john.spring.controller;

import com.john.spring.dto.DrinkDto;
import com.john.spring.exceptions.DataNotValidatedException;
import com.john.spring.services.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value = "/drinks")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @GetMapping (value = "/allDrinks")
    public ResponseEntity<List<DrinkDto>> listAllDrinks() {
        List<DrinkDto> allDrinks = drinkService.listAllDrinks();
        return new ResponseEntity<>(allDrinks, HttpStatus.OK);
    }

    @PostMapping (value = "/addDrink")
    public ResponseEntity<String> addDrink(@RequestBody @Validated DrinkDto drinkDto, Errors errors) {
        if(errors.hasErrors()) {
            throw new DataNotValidatedException(new Error("Drink not validated"));
        }
        DrinkDto savedDrink = drinkService.addDrink(drinkDto);

        return new ResponseEntity<>("The drink with id: " + savedDrink.getDrinkId() + "has been saved to DB", HttpStatus.OK);
    }

    @GetMapping (value = "/drinkDetails/{drinkId}")
    public ResponseEntity<DrinkDto> getDrinkDetails(@PathVariable ("drinkId") Integer drinkId) {
        DrinkDto drink = drinkService.getDrinkById(drinkId);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @DeleteMapping (value = "/deleteDrink/{drinkId}")
    public ResponseEntity<String> deleteDrink(@PathVariable ("drinkId") Integer drinkId) {
        drinkService.deleteDrink(drinkId);
        return new ResponseEntity<>("Drink with id of: " + drinkId + " has been removed from DB", HttpStatus.OK);
    }

    @PutMapping (value = "/updateDrink/{drinkId}")
    public ResponseEntity<String> updateDrink(@RequestBody @Validated DrinkDto drinkDto, @PathVariable ("drinkId") Integer drinkId, Errors errors) {
        if(errors.hasErrors()) {
            throw new DataNotValidatedException(new Error("Drink not validated"));
        }
        DrinkDto updatedDrink = drinkService.updateDrinkDto(drinkDto, drinkId);
        return  new ResponseEntity<>("Drink with an id of: " + updatedDrink.getDrinkId() + " has been updated", HttpStatus.OK);
    }
}
