package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "order_discount")
public class OrderDiscount {

	 @Id
	    @GeneratedValue(strategy = IDENTITY)
	    @Column(name = "id", unique = true, nullable = false)
	    private Integer id;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "order_id", referencedColumnName = "id")
	    private OrderR order;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "customer_id", referencedColumnName = "id")
	    private Customer customer;
	 
	 public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "coupon_code")
	    private String couponCode;
	
	 @Column(name = "date")
	    private String couponDate;
	 
	 @Column(name = "invenotry_type")
	    private String invenotryType;
	 
	 @Column(name = "discount")
	    private Double discount;

	 
	 @Transient
	    private String customerContactNo;
	 
	 
	public String getCustomerContactNo() {
		return customerContactNo;
	}

	public void setCustomerContactNo(String customerContactNo) {
		this.customerContactNo = customerContactNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OrderR getOrder() {
		return order;
	}

	public void setOrder(OrderR order) {
		this.order = order;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getCouponDate() {
		return couponDate;
	}

	public void setCouponDate(String couponDate) {
		this.couponDate = couponDate;
	}

	public String getInvenotryType() {
		return invenotryType;
	}

	public void setInvenotryType(String invenotryType) {
		this.invenotryType = invenotryType;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	 
	 
}
