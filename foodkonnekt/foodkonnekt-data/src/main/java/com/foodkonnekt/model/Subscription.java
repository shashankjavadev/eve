package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subscriptions")
public class Subscription {
	
	public List<Merchant> getMerchants() {
		return merchants;
	}

	public void setMerchants(List<Merchant> merchants) {
		this.merchants = merchants;
	}

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@Column(name="subscriptionplan")
	private String subscriptionPlan;
	
	private double price;
	
	
	@Column(name="orderlimit")
	private int orderLimit;
	
	
	@Column(name="metered_id")
	private String meteredId;
	
	@Column(name="metered_price")
	private double meteredPrice;
	
	@Column(name="subscriptionplan_id")
	private String subscriptionPlanId;
	
	@OneToMany(mappedBy = "subscription", fetch = FetchType.EAGER)
    private List<Merchant> merchants;
	

	public String getSubscriptionPlanId() {
		return subscriptionPlanId;
	}

	public void setSubscriptionPlanId(String subscriptionPlanId) {
		this.subscriptionPlanId = subscriptionPlanId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubscriptionPlan() {
		return subscriptionPlan;
	}

	public void setSubscriptionPlan(String subscriptionPlan) {
		this.subscriptionPlan = subscriptionPlan;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrderLimit() {
		return orderLimit;
	}

	public void setOrderLimit(int orderLimit) {
		this.orderLimit = orderLimit;
	}

	public String getMeteredId() {
		return meteredId;
	}

	public void setMeteredId(String meteredId) {
		this.meteredId = meteredId;
	}

	public double getMeteredPrice() {
		return meteredPrice;
	}

	public void setMeteredPrice(double meteredPrice) {
		this.meteredPrice = meteredPrice;
	}

}
