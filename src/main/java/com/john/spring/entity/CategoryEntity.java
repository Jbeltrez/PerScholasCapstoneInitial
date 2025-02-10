package com.john.spring.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table ( name = "categories")

public class CategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Integer categoryId;

    @Column ( nullable = false, length = 40)
    private String name;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable (name = "categories_drinks", joinColumns = @JoinColumn (name = "category_id"), inverseJoinColumns = @JoinColumn (name = "drink_id") )
    private List<DrinkEntity> drinks;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DrinkEntity> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkEntity> drinks) {
        this.drinks = drinks;
    }
}
