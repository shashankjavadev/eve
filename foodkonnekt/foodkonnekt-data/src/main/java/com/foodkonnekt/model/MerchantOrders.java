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

@Entity
@Table(name = "merchant_orders")
public class MerchantOrders {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_subscription", referencedColumnName = "id")
    private MerchantSubscription merchantSubscription;

    @Column(name = "order_count")
    private Integer orderCount;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    /**
     * @return the merchantSubscription
     */
    public MerchantSubscription getMerchantSubscription() {
        return merchantSubscription;
    }

    /**
     * @param merchantSubscription
     *            the merchantSubscription to set
     */
    public void setMerchantSubscription(MerchantSubscription merchantSubscription) {
        this.merchantSubscription = merchantSubscription;
    }

    /**
     * @return the orderCount
     */
    public Integer getOrderCount() {
        return orderCount;
    }

    /**
     * @param orderCount
     *            the orderCount to set
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
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
