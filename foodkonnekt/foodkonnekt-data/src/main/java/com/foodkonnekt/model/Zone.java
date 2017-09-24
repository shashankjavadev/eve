package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "zone")
public class Zone {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Basic
    @Column(name = "zone_name")
    private String zoneName;

    @Basic
    private Date createdOn;

    @Basic
    private Date modifiedOn;

    @Basic
    @Column(name = "delivery_fee")
    private Double deliveryFee;
    
    @Column(name = "zone_status")
    private Integer zoneStatus;

    public Integer getZoneStatus() {
		return zoneStatus;
	}

	public void setZoneStatus(Integer zoneStatus) {
		this.zoneStatus = zoneStatus;
	}

	@Basic
    @Column(name = "zone_distance")
    private Double zoneDistance;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "location_id", referencedColumnName = "id")
    private Address address;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @Basic
    @Column(name = "is_deliveryzone_taxable")
    private Integer isDeliveryZoneTaxable;

    @Basic
    @Column(name = "avg_delivery_time")
    private String avgDeliveryTime;

    @Basic
    @Column(name = "min_dollar_amount")
    private String minDollarAmount;

    @Basic
    @Column(name = "zone_line_item_posid")
    private String deliveryLineItemPosId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public Double getZoneDistance() {
        return zoneDistance;
    }

    public void setZoneDistance(Double zoneDistance) {
        this.zoneDistance = zoneDistance;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Integer getIsDeliveryZoneTaxable() {
        return isDeliveryZoneTaxable;
    }

    public void setIsDeliveryZoneTaxable(Integer isDeliveryZoneTaxable) {
        this.isDeliveryZoneTaxable = isDeliveryZoneTaxable;
    }

    public String getAvgDeliveryTime() {
        return avgDeliveryTime;
    }

    public void setAvgDeliveryTime(String avgDeliveryTime) {
        this.avgDeliveryTime = avgDeliveryTime;
    }

    public String getMinDollarAmount() {
        return minDollarAmount;
    }

    public void setMinDollarAmount(String minDollarAmount) {
        this.minDollarAmount = minDollarAmount;
    }

    public String getDeliveryLineItemPosId() {
        return deliveryLineItemPosId;
    }

    public void setDeliveryLineItemPosId(String deliveryLineItemPosId) {
        this.deliveryLineItemPosId = deliveryLineItemPosId;
    }
  
}
