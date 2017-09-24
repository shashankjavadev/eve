package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;
import java.util.Map;

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
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_date")
    private String updatedDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id")
    private String emailId;

    @Column(name = "password")
    private String password;

    @Column(name = "phonenumber")
    private String phoneNumber;

    @Column(name = "image")
    private String image;
    
    @Column(name = "customerPosId")
    private String customerPosId;
    
    public String getEmailPosId() {
		return emailPosId;
	}

	public void setEmailPosId(String emailPosId) {
		this.emailPosId = emailPosId;
	}

	public String getPhonePosId() {
		return phonePosId;
	}

	public void setPhonePosId(String phonePosId) {
		this.phonePosId = phonePosId;
	}

	@Column(name = "emailPosId")
    private String emailPosId;
    
    @Column(name = "phonePosId")
    private String phonePosId;
    
    

    public String getCustomerPosId() {
		return customerPosId;
	}

	public void setCustomerPosId(String customerPosId) {
		this.customerPosId = customerPosId;
	}

	@Transient
    private Integer vendorId;

    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private List<Address> addresses;

    @Column(name = "birth_date")
    private String birthDate;

    @Column(name = "anniversary_date")
    private String anniversaryDate;

    @Column(name = "subscribe")
    private String subscribe;

    @Transient
    private Integer checkId;

    @Transient
    private Integer merchantId;

    @Transient
    private String orderType;

    @Column(name = "customer_type")
    private String customerType;

    
    @Transient
    private List<Map<String, Object>> listOfALLDiscounts;

    public List<Map<String, Object>> getListOfALLDiscounts() {
		return listOfALLDiscounts;
	}

	public void setListOfALLDiscounts(List<Map<String, Object>> listOfALLDiscounts) {
		this.listOfALLDiscounts = listOfALLDiscounts;
	}
    
    
    
  

	public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Transient
    private Integer orderCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchantt;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor vendor;

    public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	@Transient
    private String address1;

    @Transient
    private String address2;

    @Transient
    private String city;

    @Transient
    private String state;

    @Transient
    private String zip;

    @Transient
    private String country;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId
     *            the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     *            the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /* *//**
     * @return the vendor
     */
    /*
     * public Vendor getVendor() { return vendor; }
     *//**
     * @param vendor
     *            the vendor to set
     */
    /*
     * public void setVendor(Vendor vendor) { this.vendor = vendor; }
     */

    /**
     * @return the vendorId
     */
    public Integer getVendorId() {
        return vendorId;
    }

    /**
     * @param vendorId
     *            the vendorId to set
     */
    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getAnniversaryDate() {
        return anniversaryDate;
    }

    public void setAnniversaryDate(String anniversaryDate) {
        this.anniversaryDate = anniversaryDate;
    }

    public String getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(String subscribe) {
        this.subscribe = subscribe;
    }

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Merchant getMerchantt() {
        return merchantt;
    }

    public void setMerchantt(Merchant merchantt) {
        this.merchantt = merchantt;
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

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return this.updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    
    
    
    
    
    
    @Transient
	private String merchantUId;
	
	@Transient
	private String vendorUId;

	public String getMerchantUId() {
		return merchantUId;
	}

	public void setMerchantUId(String merchantUId) {
		this.merchantUId = merchantUId;
	}

	public String getVendorUId() {
		return vendorUId;
	}

	public void setVendorUId(String vendorUId) {
		this.vendorUId = vendorUId;
	}
    
    
    
}
