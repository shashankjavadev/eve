package com.foodkonnekt.clover.vo;

public class ItemModifiersVO {
	
	private Integer id;
	private int modifierId;
	private String modifierNmae;
	private String modifierGroupName;
	public String getModifierGroupName() {
		return modifierGroupName;
	}
	public void setModifierGroupName(String modifierGroupName) {
		this.modifierGroupName = modifierGroupName;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private int modifierStatus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getModifierId() {
		return modifierId;
	}
	public void setModifierId(int modifierId) {
		this.modifierId = modifierId;
	}
	public String getModifierNmae() {
		return modifierNmae;
	}
	public void setModifierNmae(String modifierNmae) {
		this.modifierNmae = modifierNmae;
	}
	public int getModifierStatus() {
		return modifierStatus;
	}
	public void setModifierStatus(int modifierStatus) {
		this.modifierStatus = modifierStatus;
	}

}
