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
import javax.persistence.Transient;

@Entity
@Table(name = "vouchar")
public class Vouchar {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "vouchar_code")
    private String voucharCode;

    @Column(name = "type")
    private String type;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "validity")
    private String validity;

    @Column(name = "date")
    private Date date;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "recurring")
    private String recurringR;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @Transient
    private String strDate;
    
    @Transient
    private String frmDate;
    
    @Transient
    private String toDate;

   

	

	public String getFrmDate() {
		return frmDate;
	}

	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
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

    /**
     * @return the voucharCode
     */
    public String getVoucharCode() {
        return voucharCode;
    }

    /**
     * @param voucharCode
     *            the voucharCode to set
     */
    public void setVoucharCode(String voucharCode) {
        this.voucharCode = voucharCode;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the discount
     */
    public Double getDiscount() {
        return discount;
    }

    /**
     * @param discount
     *            the discount to set
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * @return the validity
     */
    public String getValidity() {
        return validity;
    }

    /**
     * @param validity
     *            the validity to set
     */
    public void setValidity(String validity) {
        this.validity = validity;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the fromDate
     */
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate
     *            the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
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
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn
     *            the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

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
     * @return the strDate
     */
    public String getStrDate() {
        return strDate;
    }

    /**
     * @param strDate
     *            the strDate to set
     */
    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getRecurringR() {
        return recurringR;
    }

    public void setRecurringR(String recurringR) {
        this.recurringR = recurringR;
    }
}
