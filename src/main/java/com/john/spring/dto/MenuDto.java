package com.john.spring.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public class MenuDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Integer> drinksIds;
	private Integer menuId;

	@NotEmpty
	@Size(max = 50, min = 5)
	private String name;

	@NotEmpty
	@Size(max = 255, min = 5)
	private String description;
	private byte active;

	public List<Integer> getDrinksIds() {
		return drinksIds;
	}

	public void setDrinksIds(List<Integer> drinksIds) {
		this.drinksIds = drinksIds;
	}

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
}
