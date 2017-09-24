package com.foodkonnekt.clover.vo;

public class InventoryItemVo {

    private Integer id;
    private String name;
    private String categoriesName;
    private String modifierGroups;
    private String status;
    private String action;
    private Double price;
    private String menuOrder;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getModifierGroups() {
        return modifierGroups;
    }

    public void setModifierGroups(String modifierGroups) {
        this.modifierGroups = modifierGroups;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
