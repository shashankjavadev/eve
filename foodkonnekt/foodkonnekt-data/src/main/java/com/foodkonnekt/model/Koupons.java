package com.foodkonnekt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;



public class Koupons {

   
	private Integer kouponId;

	private String name;

	private Date fromDate;

	private Date toDate;

	private String fromTime;

	private String toTime;

	private String recurringDay;

	private String kouponCode;

	private Date validity;

	private Integer kouponType;

	@ManyToOne(fetch = FetchType.EAGER)
	private Vendor vendor;

	private Integer location;

	private Integer role;

	@ManyToOne(fetch = FetchType.EAGER)
	private Merchant merchant;

	private Integer discountLevel;

	private Integer eventCategory;

	private Integer invenotryLevel;

	private Integer offer;

	private Integer personalLevel;

	private Integer locationLevel;

	private Integer customer;
	
	
	private String currentDate;
    private String currentTime;
	

	public Integer getCustomer() {
		return customer;
	}

	public void setCustomer(Integer customer) {
		this.customer = customer;
	}

	public Integer getLocationLevel() {
		return locationLevel;
	}

	public void setLocationLevel(Integer locationLevel) {
		this.locationLevel = locationLevel;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Integer getDiscountLevel() {
		return discountLevel;
	}

	public void setDiscountLevel(Integer discountLevel) {
		this.discountLevel = discountLevel;
	}

	public Integer getEventCategory() {
		return eventCategory;
	}

	public void setEventCategory(Integer eventCategory) {
		this.eventCategory = eventCategory;
	}

	public Integer getInvenotryLevel() {
		return invenotryLevel;
	}

	public void setInvenotryLevel(Integer invenotryLevel) {
		this.invenotryLevel = invenotryLevel;
	}

	public Integer getOffer() {
		return offer;
	}

	public void setOffer(Integer offer) {
		this.offer = offer;
	}

	public Integer getPersonalLevel() {
		return personalLevel;
	}

	public void setPersonalLevel(Integer personalLevel) {
		this.personalLevel = personalLevel;
	}

	@Column(name = "discount")
	private Double discount;

	/**
	 * @return the kouponId
	 */
	public Integer getKouponId() {
		return kouponId;
	}

	/**
	 * @param kouponId
	 *            the kouponId to set
	 */
	public void setKouponId(Integer kouponId) {
		this.kouponId = kouponId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the fromDate
	 */
	public Date getFromDate() {
		return fromDate;
	}

	/**
	 * @param fromDate
	 *            the fromDate to set
	 */
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public Date getToDate() {
		return toDate;
	}

	/**
	 * @param toDate
	 *            the toDate to set
	 */
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	/**
	 * @return the fromTime
	 */
	public String getFromTime() {
		return fromTime;
	}

	/**
	 * @param fromTime
	 *            the fromTime to set
	 */
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}

	/**
	 * @return the recurringDay
	 */
	public String getRecurringDay() {
		return recurringDay;
	}

	/**
	 * @param recurringDay
	 *            the recurringDay to set
	 */
	public void setRecurringDay(String recurringDay) {
		this.recurringDay = recurringDay;
	}

	/**
	 * @return the kouponCode
	 */
	public String getKouponCode() {
		return kouponCode;
	}

	/**
	 * @param kouponCode
	 *            the kouponCode to set
	 */
	public void setKouponCode(String kouponCode) {
		this.kouponCode = kouponCode;
	}

	/**
	 * @return the validity
	 */
	public Date getValidity() {
		return validity;
	}

	/**
	 * @param validity
	 *            the validity to set
	 */
	public void setValidity(Date validity) {
		this.validity = validity;
	}

	/**
	 * @return the kouponType
	 */
	public Integer getKouponType() {
		return kouponType;
	}

	/**
	 * @param kouponType
	 *            the kouponType to set
	 */
	public void setKouponType(Integer kouponType) {
		this.kouponType = kouponType;
	}

	/**
	 * @return the vendor
	 */
	public Vendor getVendor() {
		return vendor;
	}

	/**
	 * @param vendor
	 *            the vendor to set
	 */
	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	/**
	 * @return the location
	 */
	public Integer getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Integer location) {
		this.location = location;
	}

	/**
	 * @return the discount
	 */
	public Double getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	/**
	 * @return the toTime
	 */
	public String getToTime() {
		return toTime;
	}

	/**
	 * @param toTime
	 *            the toTime to set
	 */
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}

	

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}
	
	

}
