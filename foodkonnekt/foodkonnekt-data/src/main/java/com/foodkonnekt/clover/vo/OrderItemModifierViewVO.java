package com.foodkonnekt.clover.vo;

import com.foodkonnekt.model.Modifiers;

public class OrderItemModifierViewVO {

    private Integer quantity;
    private Modifiers modifiers;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Modifiers getModifiers() {
        return modifiers;
    }

    public void setModifiers(Modifiers modifiers) {
        this.modifiers = modifiers;
    }

}
