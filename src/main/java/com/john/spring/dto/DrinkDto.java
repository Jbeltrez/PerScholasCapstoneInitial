package com.john.spring.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public class DrinkDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer drinkId;

    @NotEmpty
    @Size (min = 3, max = 40 )
    // size annotation creates min max for String length
    private String name;

    @NotEmpty
    @Size (min = 20, max = 200)
    private String description;

    @NotNull
    @DecimalMin(value = "1.0")
    private Float price;


    private List<Integer> menusids;

    private List<Integer> categoriesids;

    public List<Integer> getCategoriesids() {
        return categoriesids;
    }

    public void setCategoriesids(List<Integer> categoriesids) {
        this.categoriesids = categoriesids;
    }

    public List<Integer> getMenusids() {
        return menusids;
    }

    public void setMenusids(List<Integer> menusids) {
        this.menusids = menusids;
    }

    public Integer getDrinkId() {
        return drinkId;
    }

    public void setDrinkId(Integer drinkId) {
        this.drinkId = drinkId;
    }

    public @NotEmpty @Size(min = 3, max = 40) String getName() {
        return name;
    }

    public void setName(@NotEmpty @Size(min = 3, max = 40) String name) {
        this.name = name;
    }

    public @NotEmpty @Size(min = 20, max = 200) String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty @Size(min = 20, max = 200) String description) {
        this.description = description;
    }

    public @NotNull @DecimalMin(value = "1.0") Float getPrice() {
        return price;
    }

    public void setPrice(@NotNull @DecimalMin(value = "1.0") Float price) {
        this.price = price;
    }
}
