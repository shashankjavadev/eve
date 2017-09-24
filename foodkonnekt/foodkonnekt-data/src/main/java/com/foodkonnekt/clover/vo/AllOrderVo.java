package com.foodkonnekt.clover.vo;

import java.util.Date;
import java.util.List;

public class AllOrderVo {

	private String DT_RowId;
    public String getDT_RowId() {
		return DT_RowId;
	}

	public void setDT_RowId(String dT_RowId) {
		DT_RowId = dT_RowId;
	}
	
	private List<OrderItemViewVO> orderItemViewVOs;

	public List<OrderItemViewVO> getOrderItemViewVOs() {
		return orderItemViewVOs;
	}

	public void setOrderItemViewVOs(List<OrderItemViewVO> orderItemViewVOs) {
		this.orderItemViewVOs = orderItemViewVOs;
	}

	private Integer id;
    private String firstName;
    private Date createdOn;
    private Double orderPrice;
    private Double tipAmount;
    private Double discount=0.0;
    public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getTipAmount() {
		return tipAmount;
	}

	public void setTipAmount(Double tipAmount) {
		this.tipAmount = tipAmount;
	}

	private String orderType;
    private String orderName;
    private String status;
    private String view;
    private String convenienceFee;
    private String deliveryFee;
    private String tax;
    private String subTotal;

    public String getConvenienceFee() {
		return convenienceFee;
	}

	public void setConvenienceFee(String convenienceFee) {
		this.convenienceFee = convenienceFee;
	}

	public String getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(String deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

}
