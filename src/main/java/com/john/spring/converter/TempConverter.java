package com.john.spring.converter;

import com.john.spring.dto.CategoryDto;
import com.john.spring.dto.DrinkDto;
import com.john.spring.entity.CategoryEntity;
import com.john.spring.entity.DrinkEntity;
import com.john.spring.entity.MenuEntity;
import com.john.spring.repository.CategoryRepository;
import com.john.spring.repository.MenuRepository;
import jdk.jfr.Category;
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
    private MenuRepository menuRepository;
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
        returnValue.setMenus(menus);
        return returnValue;
    }

    public CategoryDto categoryEntityToDto(CategoryEntity categoryEntity) {

        CategoryDto returnValue = mapper.map(categoryEntity, CategoryDto.class);
        Optional<List<DrinkEntity>> drinksOptional = Optional.ofNullable(categoryEntity.getDrinks());
        List<Integer> drinksIds = new ArrayList<>();

        if (drinksOptional.isPresent()) {
            List<DrinkEntity> drinks = drinksOptional.get();

        }


    }


}
