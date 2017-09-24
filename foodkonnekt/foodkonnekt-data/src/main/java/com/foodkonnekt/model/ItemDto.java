package com.foodkonnekt.model;

import java.util.List;

public class ItemDto {

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private Integer id;
	private Integer qunatity=0;
	private Integer totalQunatity=0;
	private Double discount =0.0;
	private String discountName ="";
	
    public String getDiscountName() {
		return discountName;
	}

	public void setDiscountName(String discountName) {
		this.discountName = discountName;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Integer getTotalQunatity() {
		return totalQunatity;
	}

	public void setTotalQunatity(Integer totalQunatity) {
		this.totalQunatity = totalQunatity;
	}

	public Integer getQunatity() {
		return qunatity;
	}

	public void setQunatity(Integer qunatity) {
		this.qunatity = qunatity;
	}

	private String itemName;
    private List<ModifierGroupDto> modifierGroupDtos;
    private Double price;
    private Double originalPrice;
    public Double getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	private Double itemModifierPrice=0.0;
    public Double getItemModifierPrice() {
		return itemModifierPrice;
	}

	public void setItemModifierPrice(Double itemModifierPrice) {
		this.itemModifierPrice = itemModifierPrice;
	}

	private String itemPosId;
    private Double itemTax;
    private Integer allowModifierLimit;
    private String itemTaxName;
    private String description;

    public Integer getAllowModifierLimit() {
        return allowModifierLimit;
    }

    public void setAllowModifierLimit(Integer allowModifierLimit) {
        this.allowModifierLimit = allowModifierLimit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public List<ModifierGroupDto> getModifierGroupDtos() {
        return modifierGroupDtos;
    }

    public void setModifierGroupDtos(List<ModifierGroupDto> modifierGroupDtos) {
        this.modifierGroupDtos = modifierGroupDtos;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getItemTax() {
        return itemTax;
    }

    public void setItemTax(Double itemTax) {
        this.itemTax = itemTax;
    }

    public String getItemPosId() {
        return itemPosId;
    }

    /*public String getItemTax() {
        return itemTax;
    }

    public void setItemTax(String itemTax) {
        this.itemTax = itemTax;
    }*/

    public void setItemPosId(String itemPosId) {
        this.itemPosId = itemPosId;
    }

    public String getItemTaxName() {
        return itemTaxName;
    }

    public void setItemTaxName(String itemTaxName) {
        this.itemTaxName = itemTaxName;
    }

}
