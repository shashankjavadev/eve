package com.foodkonnekt.clover.vo;

import java.util.List;

public class OrderItemVO {
	
	private String unitQty;
	
	private String discountAmount;
	
	private Item item;
	
	private List<Modifications> modifications ;

	public List<Modifications> getModifications() {
		return modifications;
	}

	public void setModifications(List<Modifications> modifications) {
		this.modifications = modifications;
	}

	public String getUnitQty() {
		return unitQty;
	}

	public void setUnitQty(String unitQty) {
		this.unitQty = unitQty;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}
