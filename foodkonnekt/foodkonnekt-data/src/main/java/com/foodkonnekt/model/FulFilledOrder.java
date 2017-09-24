package com.foodkonnekt.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class FulFilledOrder implements Serializable {

	private String orderID;
	private String orderType;
	private Date orderDate;
	private String pickUpTime;

	private String firstName;

	private String lastName;

	private String emailId;

	private String phoneNumber;

	private Date fullfieldOrder;
	
	
	public Date getFullfieldOrder() {
		return fullfieldOrder;
	}

	public void setFullfieldOrder(Date fullfieldOrder) {
		this.fullfieldOrder = fullfieldOrder;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getPickUpTime() {
		return pickUpTime;
	}

	public void setPickUpTime(String pickUpTime) {
		this.pickUpTime = pickUpTime;
	}

}
