package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "merchant_kritiq")
public class MerchantKritiq {

	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;
	
	 @Column(name = "create_date")
	    private String createDate;
	 
	 @Column(name = "update_date")
	    private String updateDate;
	 
	 @Column(name = "validity_date")
	    private String validityDate;
	 
	 @Column(name = "active")
	    private Boolean active;
	 
	 @Column(name = "subscrption_type")
	    private String subscrptionType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getSubscrptionType() {
		return subscrptionType;
	}

	public void setSubscrptionType(String subscrptionType) {
		this.subscrptionType = subscrptionType;
	}
	
	
	 
	 
}
