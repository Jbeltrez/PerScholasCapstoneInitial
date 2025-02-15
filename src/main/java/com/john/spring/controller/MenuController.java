package com.john.spring.controller;

import com.john.spring.dto.CategoryDto;
import com.john.spring.dto.MenuDto;
import com.john.spring.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping (value = "/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping (value = "/allMenus")
    public ResponseEntity<List<MenuDto>> getAllMenus() {
        return new ResponseEntity<>(menuService.listAllMenus(), HttpStatus.OK);

    }

    @GetMapping (value = "/menuDetails/{menuId}")
    public ResponseEntity<MenuDto> getMenuDetails (@PathVariable("menuId") Integer menuId) {
        return new ResponseEntity<>(menuService.getMenuById(menuId), HttpStatus.OK);
    }


}
