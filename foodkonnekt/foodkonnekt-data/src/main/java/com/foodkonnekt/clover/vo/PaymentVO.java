package com.foodkonnekt.clover.vo;

public class PaymentVO {

    private String paymentMethod;
    private String ccType;
    private String ccNumber;
    private String ccExpiration;
    private String ccCode;
    private String instruction;
    private String orderPosId;
    private String total;
    private double tip = 0.0;

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    private String subTotal;
    private String tax;
    private Integer expMonth;
    private Integer expYear;
    private String futureOrderType;
    private String futureDate;
    private String futureTime;
    private String listOfALLDiscounts;

    public String getListOfALLDiscounts() {
		return listOfALLDiscounts;
	}

	public void setListOfALLDiscounts(String listOfALLDiscounts) {
		this.listOfALLDiscounts = listOfALLDiscounts;
	}

	public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcExpiration() {
        return ccExpiration;
    }

    public void setCcExpiration(String ccExpiration) {
        this.ccExpiration = ccExpiration;
    }

    public String getCcCode() {
        return ccCode;
    }

    public void setCcCode(String ccCode) {
        this.ccCode = ccCode;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getOrderPosId() {
        return orderPosId;
    }

    public void setOrderPosId(String orderPosId) {
        this.orderPosId = orderPosId;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getFutureOrderType() {
        return futureOrderType;
    }

    public void setFutureOrderType(String futureOrderType) {
        this.futureOrderType = futureOrderType;
    }

    public String getFutureDate() {
        return futureDate;
    }

    public void setFutureDate(String futureDate) {
        this.futureDate = futureDate;
    }

    public String getFutureTime() {
        return futureTime;
    }

    public void setFutureTime(String futureTime) {
        this.futureTime = futureTime;
    }

}
