package com.foodkonnekt.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;


@Entity
@Table(name="ordertype")
@XmlRootElement(name="OrderType")
@Audited
public class OrderType implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	
	@Basic
	@Column(length=100)
	private String label;
	
	
	@Basic
	@Column(length=100,name="isTaxable")
	private boolean taxable;
	
	@Basic
	private boolean isHidden;
	
	
	public boolean getIsHidden() {
		return isHidden;
	}

	public void setIsHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}


	@Basic
	@Column(length=100)
	private String filterCategories;
	
	
	@Basic
	private Double minOrderAmount;
	
	
	@Basic
	private Double maxOrderAmount;
	
	@Basic
	private Double fee;
	
	
	
	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	


	@Basic
	@Column(name="averageOrderTime")
	private Double avgOrderTime;
	
	
	@Basic
	private String hoursAvailable;
	
	@Column(name="pos_ordertype_id")
	private String posOrderTypeId;
	
	public String getPosOrderTypeId() {
		return posOrderTypeId;
	}

	public void setPosOrderTypeId(String posOrderTypeId) {
		this.posOrderTypeId = posOrderTypeId;
	}


	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public boolean getTaxable() {
		return taxable;
	}


	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}


	public String getFilterCategories() {
		return filterCategories;
	}


	public void setFilterCategories(String filterCategories) {
		this.filterCategories = filterCategories;
	}


	public Double getMinOrderAmount() {
		return minOrderAmount;
	}


	public void setMinOrderAmount(Double minOrderAmount) {
		this.minOrderAmount = minOrderAmount;
	}


	public Double getMaxOrderAmount() {
		return maxOrderAmount;
	}


	public void setMaxOrderAmount(Double maxOrderAmount) {
		this.maxOrderAmount = maxOrderAmount;
	}


	


	public String getHoursAvailable() {
		return hoursAvailable;
	}


	public void setHoursAvailable(String hoursAvailable) {
		this.hoursAvailable = hoursAvailable;
	}


	


	


	@Override
	public String toString() {
		return "OrderType [id=" + id + ", label=" + label + ", taxable="
				+ taxable + ", filterCategories=" + filterCategories
				+ ", minOrderAmount=" + minOrderAmount + ", maxOrderAmount="
				+ maxOrderAmount + ", avgOrderAmount=" + avgOrderTime
				+ ", hoursAvailable=" + hoursAvailable + "]";
	}

	public Double getAvgOrderTime() {
		return avgOrderTime;
	}

	public void setAvgOrderTime(Double avgOrderTime) {
		this.avgOrderTime = avgOrderTime;
	}
	
		
}
