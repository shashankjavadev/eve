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
import javax.persistence.Transient;

@Entity
@Table(name = "location")
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "address3")
    private String address3;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "country")
    private String country;
    
    @Column(name = "addressPosId")
    private String addressPosId;

    public String getAddressPosId() {
		return addressPosId;
	}

	public void setAddressPosId(String addressPosId) {
		this.addressPosId = addressPosId;
	}

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Zone> zones;

    @Transient
    private Integer merchId;
    
    @Transient
    private String custPosId;
   
	public String getCustPosId() {
		return custPosId;
	}

	public void setCustPosId(String custPosId) {
		this.custPosId = custPosId;
	}

	@Transient
    private Double deliveryFee;

    @Transient
    private String deliveryPosId;

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDeliveryPosId() {
        return deliveryPosId;
    }

    public void setDeliveryPosId(String deliveryPosId) {
        this.deliveryPosId = deliveryPosId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getAddress3() {
        return address3;
    }

    public void setAddress3(String address3) {
        this.address3 = address3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @com.fasterxml.jackson.annotation.JsonIgnore
    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    /**
     * @return the customer
     */
    @com.fasterxml.jackson.annotation.JsonIgnore
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer
     *            the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getMerchId() {
        return merchId;
    }

    public void setMerchId(Integer merchId) {
        this.merchId = merchId;
    }

    public List<Zone> getZones() {
        return zones;
    }

    public void setZones(List<Zone> zones) {
        this.zones = zones;
    }
    
    @Transient
    Boolean isDeliveryKoupon;

	public Boolean getIsDeliveryKoupon() {
		return isDeliveryKoupon;
	}

	public void setIsDeliveryKoupon(Boolean isDeliveryKoupon) {
		this.isDeliveryKoupon = isDeliveryKoupon;
	}
    
    
}
