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
@Table(name = "item_modifiers_map")
public class ItemModifiers {

   

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    public ModifierGroup getModifierGroup() {
		return modifierGroup;
	}



	public void setModifierGroup(ModifierGroup modifierGroup) {
		this.modifierGroup = modifierGroup;
	}



	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "modifiers_id", referencedColumnName = "id")
    private Modifiers  modifiers;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "modifier_group_id", referencedColumnName = "id")
    private ModifierGroup modifierGroup;
    
    
    
    @Column(name = "modifier_status")
    private Integer modifierStatus;



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Modifiers getModifiers() {
		return modifiers;
	}



	public void setModifiers(Modifiers modifiers) {
		this.modifiers = modifiers;
	}



	public Item getItem() {
		return item;
	}



	public void setItem(Item item) {
		this.item = item;
	}



	public Integer getModifierStatus() {
		return modifierStatus;
	}



	public void setModifierStatus(Integer modifierStatus) {
		this.modifierStatus = modifierStatus;
	}
    
   

	

	

}

