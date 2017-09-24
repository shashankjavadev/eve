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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "opening_closing_day")
public class OpeningClosingDay {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "day")
    private String day;

    @Column(name = "opening_closing_pos_id")
    private String openingClosingPosId;

    @Column(name = "isHoliday")
    private Integer isHoliday;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id",updatable=false)
    private Merchant merchant;

    @OneToMany(mappedBy = "openingClosingDay", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OpeningClosingTime> openingClosingTimes;

    @Transient
    private List<OpeningClosingTime> times;

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
     * @return the day
     */
    public String getDay() {
        return day;
    }

    /**
     * @param day
     *            the day to set
     */
    public void setDay(String day) {
        this.day = day;
    }

    /**
     * @return the openingClosingPosId
     */
    public String getOpeningClosingPosId() {
        return openingClosingPosId;
    }

    /**
     * @param openingClosingPosId
     *            the openingClosingPosId to set
     */
    public void setOpeningClosingPosId(String openingClosingPosId) {
        this.openingClosingPosId = openingClosingPosId;
    }

    /**
     * @return the isHoliday
     */
    public Integer getIsHoliday() {
        return isHoliday;
    }

    /**
     * @param isHoliday
     *            the isHoliday to set
     */
    public void setIsHoliday(Integer isHoliday) {
        this.isHoliday = isHoliday;
    }

    /**
     * @return the merchant
     */
    @JsonIgnore
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

    public List<OpeningClosingTime> getTimes() {
        return times;
    }

    public void setTimes(List<OpeningClosingTime> times) {
        this.times = times;
    }

}
