package com.foodkonnekt.clover.vo;

import java.util.List;

public class OrderItemViewVO {

    private com.foodkonnekt.model.Item item;
    
    private ItemVO itemVO;
    public ItemVO getItemVO() {
		return itemVO;
	}

	public void setItemVO(ItemVO itemVO) {
		this.itemVO = itemVO;
	}

	private Integer quantity;
    private List<OrderItemModifierViewVO> itemModifierViewVOs;

    public com.foodkonnekt.model.Item getItem() {
        return item;
    }

    public void setItem(com.foodkonnekt.model.Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<OrderItemModifierViewVO> getItemModifierViewVOs() {
        return itemModifierViewVOs;
    }

    public void setItemModifierViewVOs(List<OrderItemModifierViewVO> itemModifierViewVOs) {
        this.itemModifierViewVOs = itemModifierViewVOs;
    }

}
