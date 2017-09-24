package com.foodkonnekt.clover.vo;

import java.util.List;

public class CloverOrderVO {
	
	
	private String itemPoSIdsjson;
	
	public String getItemPoSIdsjson() {
		return itemPoSIdsjson;
	}

	public void setItemPoSIdsjson(String itemPoSIdsjson) {
		this.itemPoSIdsjson = itemPoSIdsjson;
	}

	public CloverDiscountVO getCloverDiscountVO() {
		return cloverDiscountVO;
	}

	public void setCloverDiscountVO(CloverDiscountVO cloverDiscountVO) {
		this.cloverDiscountVO = cloverDiscountVO;
	}
	
	

	private CloverDiscountVO cloverDiscountVO;
	
	private OrderVO orderVO;
	
	private List<OrderItemVO> orderItemVOs ;

	public List<OrderItemVO> getOrderItemVOs() {
		return orderItemVOs;
	}

	public void setOrderItemVOs(List<OrderItemVO> orderItemVOs) {
		this.orderItemVOs = orderItemVOs;
	}

	public OrderVO getOrderVO() {
		return orderVO;
	}

	public void setOrderVO(OrderVO orderVO) {
		this.orderVO = orderVO;
	}
	
	private String itemsForDiscount;
	
	private String listOfALLDiscounts;
	
	private Integer  kouponCount;

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

	public Integer getKouponCount() {
		return kouponCount;
	}

	public void setKouponCount(Integer kouponCount) {
		this.kouponCount = kouponCount;
	}

}
