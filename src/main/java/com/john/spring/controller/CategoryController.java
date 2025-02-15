package com.john.spring.controller;

import com.john.spring.dto.CategoryDto;
import com.john.spring.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (value = "/categories")

public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping (value = "/allCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.listAllCategories(), HttpStatus.OK);
    }


    @GetMapping(value = "/allCategories/{drinkId}")
    public ResponseEntity<List<CategoryDto>> getAllByCategoryByDrinkId (@PathVariable("drinkId") Integer drinkId){
        return new ResponseEntity<>(categoryService.listAllByDrinkId(drinkId), HttpStatus.OK);
    }

    @GetMapping(value = "/categoryDetails/{categoryId}")
    public ResponseEntity<CategoryDto> getCategoryDetails (@PathVariable("categoryId") Integer categoryId) {
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

}
