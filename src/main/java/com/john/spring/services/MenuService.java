package com.john.spring.services;

import com.john.spring.dto.MenuDto;

import java.util.List;

public interface MenuService {

	MenuDto addMenu(MenuDto menu);

	MenuDto getMenuById(Integer menuId);

	MenuDto updateMenu(MenuDto menu, Integer menuId);

	void deleteMenu(Integer menuId);

	List<MenuDto> listAllMenus();

	List<MenuDto> listAllByDrinkId(Integer drinkId);
}
