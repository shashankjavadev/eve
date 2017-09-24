package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
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
@Table(name = "merchant_subscription")
public class MerchantSubscription {

    public List<MerchantOrders> getMerchantOrders() {
		return merchantOrders;
	}

	public void setMerchantOrders(List<MerchantOrders> merchantOrders) {
		this.merchantOrders = merchantOrders;
	}

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
	
	 @Basic
	 private Date billingStartDate;

    public Date getBillingStartDate() {
		return billingStartDate;
	}

	public void setBillingStartDate(Date billingStartDate) {
		this.billingStartDate = billingStartDate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;

    
    @OneToMany(mappedBy = "merchantSubscription", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<MerchantOrders> merchantOrders;
    /**
     * @return the merchant
     */
    public Merchant getMerchant() {
        return merchant;
    }

    /**
     * @param merchant
     *            the merchant to set
     */
    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    /**
     * @return the subscription
     */
    public Subscription getSubscription() {
        return subscription;
    }

    /**
     * @param subscription
     *            the subscription to set
     */
    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
}
