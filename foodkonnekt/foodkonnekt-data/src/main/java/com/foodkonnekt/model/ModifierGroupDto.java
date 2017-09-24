package com.foodkonnekt.model;

import java.util.List;

public class ModifierGroupDto {

    private Integer id;
    private String modifierGroupName;
    private List<ModifierDto> modifierDtos;
    private Integer modifiersLimit;
    private Integer isMaxLimit;

    public Integer getIsMaxLimit() {
		return isMaxLimit;
	}

	public void setIsMaxLimit(Integer isMaxLimit) {
		this.isMaxLimit = isMaxLimit;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModifierGroupName() {
        return modifierGroupName;
    }

    public void setModifierGroupName(String modifierGroupName) {
        this.modifierGroupName = modifierGroupName;
    }

    public List<ModifierDto> getModifierDtos() {
        return modifierDtos;
    }

    public void setModifierDtos(List<ModifierDto> modifierDtos) {
        this.modifierDtos = modifierDtos;
    }

    public Integer getModifiersLimit() {
        return modifiersLimit;
    }

    public void setModifiersLimit(Integer modifiersLimit) {
        this.modifiersLimit = modifiersLimit;
    }
}
