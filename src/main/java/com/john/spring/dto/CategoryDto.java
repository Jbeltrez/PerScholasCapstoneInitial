package com.john.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public class CategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer categoryId;

    @NotEmpty
    @Size (max = 40)
    private String name;

    private List<Integer> drinkIds;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public @NotEmpty @Size(max = 40) String getName() {
        return name;
    }

    public void setName(@NotEmpty @Size(max = 40) String name) {
        this.name = name;
    }

    public List<Integer> getDrinkIds() {
        return drinkIds;
    }

    public void setDrinkIds(List<Integer> drinkIds) {
        this.drinkIds = drinkIds;
    }
}
