package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taxrate")
public class TaxRates {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "pos_taxrate_id")
    private String posTaxRateId;

    @Column(name = "name")
    private String name;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "isDefault")
    private Integer isDefault;
    
    @ManyToMany(mappedBy = "taxes", fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
    private Set<Item> items = new HashSet<Item>();

    @ManyToOne(fetch = FetchType.EAGER,cascade = { CascadeType.REMOVE })
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

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
     * @return the posTaxRateId
     */
    public String getPosTaxRateId() {
        return posTaxRateId;
    }

    /**
     * @param posTaxRateId
     *            the posTaxRateId to set
     */
    public void setPosTaxRateId(String posTaxRateId) {
        this.posTaxRateId = posTaxRateId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the rate
     */
    public Double getRate() {
        return rate;
    }

    /**
     * @param rate
     *            the rate to set
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }

    /**
     * @return the isDefault
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault
     *            the isDefault to set
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
