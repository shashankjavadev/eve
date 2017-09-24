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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "pos_merchant_id")
    private String posMerchantId;

    @Column(name = "name")
    private String name;
    
    @Column(name = "employeePosId")
    private String employeePosId;
    
    
    public String getEmployeePosId() {
		return employeePosId;
	}

	public void setEmployeePosId(String employeePosId) {
		this.employeePosId = employeePosId;
	}

	@Column(name = "allow_future_order")
    private Integer allowFutureOrder;
	
	@Column(name = "active_customer_feedback")
    private Integer activeCustomerFeedback=0;
	
	public Integer getActiveCustomerFeedback() {
		return activeCustomerFeedback;
	}

	public void setActiveCustomerFeedback(Integer activeCustomerFeedback) {
		this.activeCustomerFeedback = activeCustomerFeedback;
	}

	@Column(name = "future_days_ahead")
    private Integer futureDaysAhead=10;
	
	

    public Integer getFutureDaysAhead() {
		return futureDaysAhead;
	}

	public void setFutureDaysAhead(Integer futureDaysAhead) {
		this.futureDaysAhead = futureDaysAhead;
	}

	public Integer getAllowFutureOrder() {
		return allowFutureOrder;
	}

	public void setAllowFutureOrder(Integer allowFutureOrder) {
		this.allowFutureOrder = allowFutureOrder;
	}

	@Column(name = "phone_number")
    private String phoneNumber;

    @Transient
    private String address;
    
    @Transient
    private Integer totalOrders;
    
    @Transient
    private Double totalOrdersPrice;
    
    public Double getTotalOrdersPrice() {
		return totalOrdersPrice;
	}

	public void setTotalOrdersPrice(Double totalOrdersPrice) {
		this.totalOrdersPrice = totalOrdersPrice;
	}

	@Transient
    private String status;
    
    
    
    

    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(Integer totalOrders) {
		this.totalOrders = totalOrders;
	}

	@Column(name = "storeId")
    private String storeId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Item> items;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Address> addresses;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<PaymentMode> paymentModes;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<OpeningClosingDay> openingClosingDays;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Vouchar> vouchars;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<OrderType> orderTypes;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<MerchantSubscription> merchantSubscriptions;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Modifiers> modifiers;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ModifierGroup> modifierGroups;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<OrderR> orderRs;

    @OneToMany(mappedBy = "merchant", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<TaxRates> taxRates;
    
    @OneToOne(mappedBy="merchant" ,cascade = CascadeType.ALL)
    private MerchantLogin merchantLogin;
    
    @OneToOne(mappedBy="merchant" ,cascade = CascadeType.ALL)
    private SocialMediaLinks socialMediaLinks;
    
    
   
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "time_zone_id", referencedColumnName = "id")
    private TimeZone timeZone;

    public TimeZone getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(TimeZone timeZone) {
		this.timeZone = timeZone;
	}

	public SocialMediaLinks getSocialMediaLinks() {
		return socialMediaLinks;
	}

	public void setSocialMediaLinks(SocialMediaLinks socialMediaLinks) {
		this.socialMediaLinks = socialMediaLinks;
	}

	public MerchantLogin getMerchantLogin() {
		return merchantLogin;
	}

	public void setMerchantLogin(MerchantLogin merchantLogin) {
		this.merchantLogin = merchantLogin;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id", referencedColumnName = "id")
    private Vendor owner;

    @Column(name = "merchant_logo")
    private String merchantLogo;

    @Column(name = "accessToken")
    private String accessToken;

    @Column(name = "is_install")
    private Integer isInstall;
    
    @Column(name = "merchant_uid")
    private String merchantUid;

    @Column(name = "allow_multiple_koupon")
    private Integer allowMultipleKoupon;
    
    
    public Integer getAllowMultipleKoupon() {
		return allowMultipleKoupon;
	}

	public void setAllowMultipleKoupon(Integer allowMultipleKoupon) {
		this.allowMultipleKoupon = allowMultipleKoupon;
	}

	public String getMerchantUid() {
		return merchantUid;
	}

	public void setMerchantUid(String merchantUid) {
		this.merchantUid = merchantUid;
	}

	public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<PaymentMode> getPaymentModes() {
        return paymentModes;
    }

    public void setPaymentModes(List<PaymentMode> paymentModes) {
        this.paymentModes = paymentModes;
    }

    public List<ModifierGroup> getModifierGroups() {
        return modifierGroups;
    }

    public void setModifierGroups(List<ModifierGroup> modifierGroups) {
        this.modifierGroups = modifierGroups;
    }

    public List<OrderR> getOrderRs() {
        return orderRs;
    }

    public void setOrderRs(List<OrderR> orderRs) {
        this.orderRs = orderRs;
    }

    public List<TaxRates> getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(List<TaxRates> taxRates) {
        this.taxRates = taxRates;
    }

    public List<Modifiers> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<Modifiers> modifiers) {
        this.modifiers = modifiers;
    }

    public List<Vouchar> getVouchars() {
        return vouchars;
    }

    public void setVouchars(List<Vouchar> vouchars) {
        this.vouchars = vouchars;
    }

    public List<OrderType> getOrderTypes() {
        return orderTypes;
    }

    public void setOrderTypes(List<OrderType> orderTypes) {
        this.orderTypes = orderTypes;
    }

    public List<MerchantSubscription> getMerchantSubscriptions() {
        return merchantSubscriptions;
    }

    public void setMerchantSubscriptions(List<MerchantSubscription> merchantSubscriptions) {
        this.merchantSubscriptions = merchantSubscriptions;
    }

    public List<OpeningClosingDay> getOpeningClosingDays() {
        return openingClosingDays;
    }

    public void setOpeningClosingDays(List<OpeningClosingDay> openingClosingDays) {
        this.openingClosingDays = openingClosingDays;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPosMerchantId() {
        return posMerchantId;
    }

    public void setPosMerchantId(String posMerchantId) {
        this.posMerchantId = posMerchantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Vendor getOwner() {
        return owner;
    }

    public void setOwner(Vendor owner) {
        this.owner = owner;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getMerchantLogo() {
        return merchantLogo;
    }

    public void setMerchantLogo(String merchantLogo) {
        this.merchantLogo = merchantLogo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIsInstall() {
        return isInstall;
    }

    public void setIsInstall(Integer isInstall) {
        this.isInstall = isInstall;
    }

}
