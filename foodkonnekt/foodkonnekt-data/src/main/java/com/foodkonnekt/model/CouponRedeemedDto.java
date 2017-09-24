package com.foodkonnekt.model;

import java.util.List;

public class CouponRedeemedDto {
	
	private String orderId;
	
	private String appliedDate;

	private String merchantUId;
	
	private String vendorUId;

	private List<Koupons> koupons;

	private String customerContactNo;
	
   private String kouponCode;
	
	
	public String getKouponCode() {
	return kouponCode;
}

public void setKouponCode(String kouponCode) {
	this.kouponCode = kouponCode;
}

	public String getCustomerContactNo() {
		return customerContactNo;
	}

	public void setCustomerContactNo(String customerContactNo) {
		this.customerContactNo = customerContactNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(String appliedDate) {
		this.appliedDate = appliedDate;
	}

	public String getMerchantUId() {
		return merchantUId;
	}

	public void setMerchantUId(String merchantUId) {
		this.merchantUId = merchantUId;
	}

	public String getVendorUId() {
		return vendorUId;
	}

	public void setVendorUId(String vendorUId) {
		this.vendorUId = vendorUId;
	}

	public List<Koupons> getKoupons() {
		return koupons;
	}

	public void setKoupons(List<Koupons> koupons) {
		this.koupons = koupons;
	}

	
	
	

}
