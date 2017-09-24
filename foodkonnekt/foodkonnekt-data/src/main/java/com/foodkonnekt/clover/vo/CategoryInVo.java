package com.foodkonnekt.clover.vo;

public class CategoryInVo {

    private Integer id;
    private String name;
    private String itemCount;
    private String action;
    private String timing;
    public String getTiming() {
		return timing;
	}

	public void setTiming(String timing) {
		this.timing = timing;
	}

	private String menuOrder;

    public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

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

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

}
