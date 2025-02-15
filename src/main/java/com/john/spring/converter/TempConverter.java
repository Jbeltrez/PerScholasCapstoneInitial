package com.john.spring.converter;

import com.john.spring.dto.CategoryDto;
import com.john.spring.dto.DrinkDto;
import com.john.spring.dto.MenuDto;
import com.john.spring.entity.CategoryEntity;
import com.john.spring.entity.DrinkEntity;
import com.john.spring.entity.MenuEntity;
import com.john.spring.repository.CategoryRepository;
import com.john.spring.repository.DrinkRepository;
import com.john.spring.repository.MenuRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TempConverter {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private DrinkRepository drinkRepository;

	@Autowired
	private MenuRepository menuRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public DrinkDto drinkEntityToDto(DrinkEntity drinkEntity) {

		DrinkDto returnValue = mapper.map(drinkEntity, DrinkDto.class);
		Optional<List<MenuEntity>> menusOptional = Optional.ofNullable(drinkEntity.getMenus());
		List<Integer> menusIds = new ArrayList<>();
		if (menusOptional.isPresent()) {
			List<MenuEntity> menus = menusOptional.get();
			for (MenuEntity menu : menus) {
				menusIds.add(menu.getMenuId());
			}
		}
		returnValue.setMenusids(menusIds);
		Optional<List<CategoryEntity>> categoriesOptional = Optional.ofNullable(drinkEntity.getCategories());
		List<Integer> categoryIds = new ArrayList<>();
		if (categoriesOptional.isPresent()) {
			categoriesOptional.get().forEach((categoryEntity) -> {
				categoryIds.add(categoryEntity.getCategoryId());
			});
		}
		returnValue.setCategoriesids(categoryIds);
		return returnValue;
	}

	public DrinkEntity drinkDtoToEntity(DrinkDto drinkDto) {

		DrinkEntity returnValue = mapper.map(drinkDto, DrinkEntity.class);
		Optional<List<Integer>> menusIdsOptional = Optional.ofNullable(drinkDto.getMenusids());
		List<MenuEntity> menus = new ArrayList<>();
		if (menusIdsOptional.isPresent()) {
			List<Integer> menusIds = menusIdsOptional.get();
			for (Integer menuId : menusIds) {
				Optional<MenuEntity> menuOptional = menuRepository.findById(menuId);
				if (menuOptional.isPresent()) {
					menus.add(menuOptional.get());
				}
			}
		}
		Optional<List<Integer>> categorityIdsOptional = Optional.ofNullable(drinkDto.getCategoriesids());
		List<CategoryEntity> categories = new ArrayList<>();
		if (categorityIdsOptional.isPresent()) {
			categorityIdsOptional.get().forEach((categoryId) -> {
				Optional<CategoryEntity> categoryOptional = categoryRepository.findById(categoryId);
				if (categoryOptional.isPresent()) {
					categories.add(categoryOptional.get());
				}
			});
		}
		returnValue.setMenus(menus);
		returnValue.setCategories(categories);
		return returnValue;
	}

	public CategoryDto categoryEntityToDto(CategoryEntity categoryEntity) {

		CategoryDto returnValue = mapper.map(categoryEntity, CategoryDto.class);
		Optional<List<DrinkEntity>> drinksOptional = Optional.ofNullable(categoryEntity.getDrinks());
		List<Integer> drinksIds = new ArrayList<>();

		if (drinksOptional.isPresent()) {
			List<DrinkEntity> drinks = drinksOptional.get();
			for (DrinkEntity drink : drinks) {
				drinksIds.add(drink.getDrinkId());
			}
		}
		returnValue.setDrinkIds(drinksIds);
		return returnValue;
	}

	public CategoryEntity categoryDtoToEntity(CategoryDto categoryDto) {

		CategoryEntity returnValue = mapper.map(categoryDto, CategoryEntity.class);
		Optional<List<Integer>> drinksIdsOptional = Optional.ofNullable(categoryDto.getDrinkIds());
		List<DrinkEntity> drinks = new ArrayList<>();

		if (drinksIdsOptional.isPresent()) {
			List<Integer> drinkIds = drinksIdsOptional.get();
			for (Integer drinkId : drinkIds) {
				DrinkEntity drinkEntity = drinkRepository.findById(drinkId).orElse(null);
				if (drinkEntity != null) {
					drinks.add(drinkEntity);
				}
			}
		}
		returnValue.setDrinks(drinks);
		return returnValue;
	}

	public MenuEntity menuDtoToEntity(MenuDto menuDto) {

		MenuEntity returnValue = mapper.map(menuDto, MenuEntity.class);
		Optional<List<Integer>> drinksIdsOptional = Optional.ofNullable(menuDto.getDrinksIds());
		List<DrinkEntity> drinks = new ArrayList<>();

		if (drinksIdsOptional.isPresent()) {
			drinksIdsOptional.get().forEach((drinkId) -> {
				DrinkEntity drinkEntity = drinkRepository.findById(drinkId).orElse(null);

				if (drinkEntity != null) {
					drinks.add(drinkEntity);
				}
			});
		}
		returnValue.setDrinks(drinks);
		return returnValue;
	}

	public MenuDto menuEntityToDto(MenuEntity menuEntity) {
		MenuDto returnValue = mapper.map(menuEntity, MenuDto.class);
		Optional<List<DrinkEntity>> drinksOptional = Optional.ofNullable(menuEntity.getDrinks());
		List<Integer> drinksIds = new ArrayList<>();

		if (drinksOptional.isPresent()) {
			drinksOptional.get().forEach((drinkEntity) -> {
				drinksIds.add(drinkEntity.getDrinkId());
			});
		}
		returnValue.setDrinksIds(drinksIds);
		return returnValue;
	}
}
