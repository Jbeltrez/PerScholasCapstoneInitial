package com.john.spring.services.impl;

import com.john.spring.converter.TempConverter;
import com.john.spring.dto.MenuDto;
import com.john.spring.entity.MenuEntity;
import com.john.spring.exceptions.InstanceUndefinedException;
import com.john.spring.repository.MenuRepository;
import com.john.spring.services.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private TempConverter tempConverter;

	@Override
	@Transactional
	public MenuDto addMenu(MenuDto menu) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public MenuDto getMenuById(Integer menuId) {
		MenuEntity menuEntity = menuRepository.findById(menuId)
				.orElseThrow(() -> new InstanceUndefinedException(new Error("The menu has not been found")));
		return tempConverter.menuEntityToDto(menuEntity);
	}

	@Override
	@Transactional
	public MenuDto updateMenu(MenuDto menu, Integer menuId) {
		MenuDto currentMenu = getMenuById(menuId);
		menu.setMenuId(currentMenu.getMenuId());
		if (currentMenu.getDrinksIds() != null) {
			menu.setDrinksIds(currentMenu.getDrinksIds());
		} else {
			menu.setDrinksIds(new ArrayList<>());
		}
		MenuEntity updatedMenu = menuRepository.saveAndFlush(tempConverter.menuDtoToEntity(menu));
		return tempConverter.menuEntityToDto(updatedMenu);
	}

	@Override
	@Transactional
	public void deleteMenu(Integer menuId) {
		getMenuById(menuId);
		menuRepository.deleteById(menuId);
		menuRepository.flush();
	}

	@Override
	@Transactional(readOnly = true)
	public List<MenuDto> listAllMenus() {
		List<MenuEntity> allMenus = menuRepository.findAll();
		List<MenuDto> returnValue = new ArrayList<>();
		allMenus.forEach((menuEntity) -> {
			returnValue.add(tempConverter.menuEntityToDto(menuEntity));
		});
		return returnValue;
	}

	@Override
	@Transactional(readOnly = true)
	public List<MenuDto> listAllByDrinkId(Integer drinkId) {
		List<MenuEntity> allMenus = menuRepository.findAllByDrinkId(drinkId);
		List<MenuDto> returnValue = new ArrayList<>();
		allMenus.forEach((menuEntity) -> {
			returnValue.add(tempConverter.menuEntityToDto(menuEntity));
		});
		return returnValue;
	}
}
