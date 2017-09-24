package com.foodkonnekt.clover.vo;

import java.util.List;

public class OrderVO {
	public OrderTypeVO getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderTypeVO orderType) {
		this.orderType = orderType;
	}
	private String total;
	private String state;
	private String title;
	private boolean testMode;
	private String note;
	private String currency;
	private boolean taxRemoved;
	private OrderTypeVO orderType; 
	private List<Customer> customers;
	
	private Employee employee;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public boolean isTaxRemoved() {
		return taxRemoved;
	}
	public void setTaxRemoved(boolean taxRemoved) {
		this.taxRemoved = taxRemoved;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isTestMode() {
		return testMode;
	}
	public void setTestMode(boolean testMode) {
		this.testMode = testMode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
