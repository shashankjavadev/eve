package com.foodkonnekt.model;

import java.util.List;

public class CategoryDto {

    private Integer id;
    private String categoryName;
	private List<ItemDto> itemDtos;
	public int getCategoryStatus() {
		return categoryStatus;
	}

	public void setCategoryStatus(int categoryStatus) {
		this.categoryStatus = categoryStatus;
	}

	private int categoryStatus;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setItemDtos(List<ItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }

}
