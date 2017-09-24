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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "modifiers")
public class Modifiers {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "pos_modifier_id")
    private String posModifierId;

    @ManyToMany(mappedBy = "modifiers", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ModifierGroup> modifierGroup = new HashSet<ModifierGroup>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @Transient
    private Integer itemCount;

    @com.fasterxml.jackson.annotation.JsonIgnore
    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Set<ModifierGroup> getModifierGroup() {
        return modifierGroup;
    }

    public void setModifierGroup(Set<ModifierGroup> modifierGroup) {
        this.modifierGroup = modifierGroup;
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
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the posModifierId
     */
    public String getPosModifierId() {
        return posModifierId;
    }

    /**
     * @param posModifierId
     *            the posModifierId to set
     */
    public void setPosModifierId(String posModifierId) {
        this.posModifierId = posModifierId;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }
}
