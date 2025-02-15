package com.john.spring.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menus")
public class MenuEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer menuId;

	@Column(nullable = false, length = 50, unique = true)
	private String name;

	@Column(nullable = false, length = 255)
	private String description;

	@Column(nullable = false)
	private byte active;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "menus_drinks", joinColumns = @JoinColumn(name = "menu_id"), inverseJoinColumns = @JoinColumn(name = "drink_id"))
	private List<DrinkEntity> drinks;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte getActive() {
		return active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public List<DrinkEntity> getDrinks() {
		return drinks;
	}

	public void setDrinks(List<DrinkEntity> drinks) {
		this.drinks = drinks;
	}
}
