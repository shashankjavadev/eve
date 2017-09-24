package com.foodkonnekt.clover.vo;

public class CashVO {

    private String result;

    private String clientCreatedTime;

    private String createdTime;

    private String modifiedTime;

    private Integer offline;

    //private double amount;

    private Long amount;
    
   // private double cashTendered;

    private Long cashTendered;
    
    public Long getCashTendered() {
		return cashTendered;
	}

	public void setCashTendered(Long cashTendered) {
		this.cashTendered = cashTendered;
	}

	private double tipAmount;

    private String taxAmount;

    private Tender tender;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getClientCreatedTime() {
        return clientCreatedTime;
    }

    public void setClientCreatedTime(String clientCreatedTime) {
        this.clientCreatedTime = clientCreatedTime;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Integer getOffline() {
        return offline;
    }

    public void setOffline(Integer offline) {
        this.offline = offline;
    }

//    public double getAmount() {
//        return amount;
//    }

//    public void setAmount(double amount) {
//        this.amount = amount;
//    }

    public void setAmount(Long amount) {
		this.amount = amount;
	}

	/*public double getCashTendered() {
        return cashTendered;
    }

    public void setCashTendered(double cashTendered) {
        this.cashTendered = cashTendered;
    }*/

    public double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(double tipAmount) {
        this.tipAmount = tipAmount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

	public Long getAmount() {
		return amount;
	}

    
    
}
