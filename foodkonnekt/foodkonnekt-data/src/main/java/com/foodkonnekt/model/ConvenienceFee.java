package com.foodkonnekt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "convenience_fee")
public class ConvenienceFee {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "convenience_fee")
    private String convenienceFee;

    @Column(name = "is_taxable")
    private Integer isTaxable;

    @Column(name = "merchant_id")
    private Integer merchantId;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "convenience_fee_line_item_posId")
    private String convenienceFeeLineItemPosId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConvenienceFee() {
        return convenienceFee;
    }

    public void setConvenienceFee(String convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    public Integer getIsTaxable() {
        return isTaxable;
    }

    public void setIsTaxable(Integer isTaxable) {
        this.isTaxable = isTaxable;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getConvenienceFeeLineItemPosId() {
        return convenienceFeeLineItemPosId;
    }

    public void setConvenienceFeeLineItemPosId(String convenienceFeeLineItemPosId) {
        this.convenienceFeeLineItemPosId = convenienceFeeLineItemPosId;
    }
}
