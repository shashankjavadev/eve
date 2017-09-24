package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "modifiergroup_modifiers")
public class ModifierModifierGroupDto {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modifiergroup_id", referencedColumnName = "id")
    private ModifierGroup modifierGroup;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modifiers_id", referencedColumnName = "id")
    private Modifiers modifiers;

    public ModifierGroup getModifierGroup() {
        return modifierGroup;
    }

    public void setModifierGroup(ModifierGroup modifierGroup) {
        this.modifierGroup = modifierGroup;
    }

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
    }

}
