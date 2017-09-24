package com.foodkonnekt.clover.vo;

import java.util.List;



public class PlaceOrderVO {
	
	 //private String orderJson;
     private String orderTotal;  
     private String instruction;  
     private Double discount; 
     
     private List<Item> orderJson; ;
    
	

	public List<Item> getOrderJson() {
		return orderJson;
	}

	public void setOrderJson(List<Item> orderJson) {
		this.orderJson = orderJson;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public String getItemPosIds() {
		return itemPosIds;
	}

	public void setItemPosIds(String itemPosIds) {
		this.itemPosIds = itemPosIds;
	}

	public String getInventoryLevel() {
		return inventoryLevel;
	}

	public void setInventoryLevel(String inventoryLevel) {
		this.inventoryLevel = inventoryLevel;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getConvenienceFee() {
		return convenienceFee;
	}

	public void setConvenienceFee(String convenienceFee) {
		this.convenienceFee = convenienceFee;
	}

	public String getDeliveryItemPrice() {
		return deliveryItemPrice;
	}

	public void setDeliveryItemPrice(String deliveryItemPrice) {
		this.deliveryItemPrice = deliveryItemPrice;
	}

	public String getAvgDeliveryTime() {
		return avgDeliveryTime;
	}

	public void setAvgDeliveryTime(String avgDeliveryTime) {
		this.avgDeliveryTime = avgDeliveryTime;
	}

	public String getItemsForDiscount() {
		return itemsForDiscount;
	}

	public void setItemsForDiscount(String itemsForDiscount) {
		this.itemsForDiscount = itemsForDiscount;
	}

	public String getListOfALLDiscounts() {
		return listOfALLDiscounts;
	}

	public void setListOfALLDiscounts(String listOfALLDiscounts) {
		this.listOfALLDiscounts = listOfALLDiscounts;
	}

	private String discountType; 
	private String itemPosIds; 
	private String inventoryLevel; 
	private String voucherCode ;
    private String orderType;  
    private String convenienceFee;
    private String deliveryItemPrice;  
    private String avgDeliveryTime ; 
    private String itemsForDiscount;  
    private String listOfALLDiscounts;

}
