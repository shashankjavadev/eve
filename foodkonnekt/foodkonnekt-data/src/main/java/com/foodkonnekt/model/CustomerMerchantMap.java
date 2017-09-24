package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customerMerchant_map")
public class CustomerMerchantMap {
	
	 @Id
	    @GeneratedValue(strategy = IDENTITY)
	    @Column(name = "id", unique = true, nullable = false)
	    private Integer id;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
	    private Merchant merchantt;
	 
	 @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name="customer_id", referencedColumnName = "id") 
		private Customer customer;
	 
	 @Column(name = "customerPosId")
	    private String customerPosId;
	 
	 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Merchant getMerchantt() {
		return merchantt;
	}

	public void setMerchantt(Merchant merchantt) {
		this.merchantt = merchantt;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getCustomerPosId() {
		return customerPosId;
	}

	public void setCustomerPosId(String customerPosId) {
		this.customerPosId = customerPosId;
	}
	 
	 
	 
	 

}
