package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "modifiergroup")
public class ModifierGroup {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "showByDefault")
    private Integer showByDefault;

    @Column(name = "pos_modifiergroup_id")
    private String posModifierGroupId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @Transient
    private String modifierNames;

    @Column
    @ElementCollection
    private Set<Modifiers> modifiers = new HashSet<Modifiers>(0);

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "modifiergroup_modifiers", joinColumns = { @JoinColumn(name = "modifiergroup_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "modifier_id", nullable = false, updatable = false) })
    public Set<Modifiers> getModifiers() {
        return modifiers;
    }

    public void setModifiers(Set<Modifiers> modifiers) {
        this.modifiers = modifiers;
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
     * @return the showByDefault
     */
    public Integer getShowByDefault() {
        return showByDefault;
    }

    /**
     * @param showByDefault
     *            the showByDefault to set
     */
    public void setShowByDefault(Integer showByDefault) {
        this.showByDefault = showByDefault;
    }

    /**
     * @return the posModifierGroupId
     */
    public String getPosModifierGroupId() {
        return posModifierGroupId;
    }

    /**
     * @param posModifierGroupId
     *            the posModifierGroupId to set
     */
    public void setPosModifierGroupId(String posModifierGroupId) {
        this.posModifierGroupId = posModifierGroupId;
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
     * @return the modifierNames
     */
    public String getModifierNames() {
        return modifierNames;
    }

    /**
     * @param modifierNames
     *            the modifierNames to set
     */
    public void setModifierNames(String modifierNames) {
        this.modifierNames = modifierNames;
    }
}
