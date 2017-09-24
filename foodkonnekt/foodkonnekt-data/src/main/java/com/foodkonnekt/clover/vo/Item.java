package com.foodkonnekt.clover.vo;

import java.util.List;

public class Item {
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	private String itemid;
	private String amount;
	private String price;
	private List<ModifierVO> modifier;

	public String getItemid() {
		return itemid;
	}

	public void setItemid(String itemid) {
		this.itemid = itemid;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<ModifierVO> getModifier() {
		return modifier;
	}

	public void setModifier(List<ModifierVO> modifier) {
		this.modifier = modifier;
	}
	

}
