package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "item_modifiergroup_map")
public class ItemModifierGroup {

    public Integer getModifierGroupStatus() {
		return modifierGroupStatus;
	}

	public void setModifierGroupStatus(Integer modifierGroupStatus) {
		this.modifierGroupStatus = modifierGroupStatus;
	}

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "modifier_group_id", referencedColumnName = "id")
    private ModifierGroup modifierGroup;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
    
    @Column(name = "modifiers_limit")
    private Integer modifiersLimit;
    
    @Column(name = "modifier_group_status")
    private Integer modifierGroupStatus;
    
    @Column(name = "sortOrder")
    private Integer sortOrder=0;
    
    
    
    @Column(name = "is_max_limit")
    private Integer isMaxLimit=0;
    
   

	public Integer getIsMaxLimit() {
		return isMaxLimit;
	}

	public void setIsMaxLimit(Integer isMaxLimit) {
		this.isMaxLimit = isMaxLimit;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getModifiersLimit() {
		return modifiersLimit;
	}

	public void setModifiersLimit(Integer modifiersLimit) {
		this.modifiersLimit = modifiersLimit;
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
     * @return the modifierGroup
     */
    public ModifierGroup getModifierGroup() {
        return modifierGroup;
    }

    /**
     * @param modifierGroup
     *            the modifierGroup to set
     */
    public void setModifierGroup(ModifierGroup modifierGroup) {
        this.modifierGroup = modifierGroup;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item
     *            the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

}
