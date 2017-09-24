package com.foodkonnekt.model;

public class ModifierDto {

    private Integer id;
    private String modifierName;
    private Double price;
    private String modifierPosId;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getModifierPosId() {
        return modifierPosId;
    }

    public void setModifierPosId(String modifierPosId) {
        this.modifierPosId = modifierPosId;
    }

}
