package com.foodkonnekt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.Audited;

import com.foodkonnekt.clover.vo.OrderItemViewVO;

@Entity
@Table(name = "order_r")
@XmlRootElement(name = "OrderR")
@Audited
public class OrderR implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic
    @Column(length = 100)
    private String orderName;

    @Basic
    @Column(length = 100)
    private String orderNote;

    @Basic
    @Column(length = 100)
    private String taxable;

    @Basic
    private Double orderPrice;

    @Basic
    private Double orderDiscount;

    @Basic
    private Double tipAmount = 0.0;

    public Double getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(Double tipAmount) {
        this.tipAmount = tipAmount;
    }

    @Basic
    @Column(length = 100)
    private String orderAvgTime;

    public String getOrderAvgTime() {
        return orderAvgTime;
    }

    public void setOrderAvgTime(String orderAvgTime) {
        this.orderAvgTime = orderAvgTime;
    }

    public Double getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(Double orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    @Basic
    private Integer isDefaults;

    @Basic
    private Date createdOn;

    @Basic
    private Date fulfilled_on;

    public Date getFulfilled_on() {
        return fulfilled_on;
    }

    public void setFulfilled_on(Date fulfilled_on) {
        this.fulfilled_on = fulfilled_on;
    }

    @Basic
    private String orderPosId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @Transient
    private List<ShowOrder> orders;

    @Column(name = "subTotal")
    private String subTotal;

    @Column(name = "tax")
    private String tax;

    @Column(name = "posPaymentId")
    private String posPaymentId;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "order_type")
    private String orderType;

    @Column(name = "convenience_fee")
    private String convenienceFee;

    @Column(name = "delivery_fee")
    private String deliveryFee;

    @Column(name = "is_future_order")
    private Integer isFutureOrder;

    @Transient
    private List<OrderItemViewVO> orderItemViewVOs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public String getTaxable() {
        return taxable;
    }

    public void setTaxable(String taxable) {
        this.taxable = taxable;
    }

    public Integer getIsDefaults() {
        return isDefaults;
    }

    public void setIsDefaults(Integer isDefaults) {
        this.isDefaults = isDefaults;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isDefaults == null) ? 0 : isDefaults.hashCode());
        result = prime * result + ((orderName == null) ? 0 : orderName.hashCode());
        result = prime * result + ((orderNote == null) ? 0 : orderNote.hashCode());
        result = prime * result + ((taxable == null) ? 0 : taxable.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof OrderR)) {
            return false;
        }
        OrderR other = (OrderR) obj;
        if (createdOn == null) {
            if (other.createdOn != null) {
                return false;
            }
        } else if (!createdOn.equals(other.createdOn)) {
            return false;
        }
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (isDefaults == null) {
            if (other.isDefaults != null) {
                return false;
            }
        } else if (!isDefaults.equals(other.isDefaults)) {
            return false;
        }
        if (orderName == null) {
            if (other.orderName != null) {
                return false;
            }
        } else if (!orderName.equals(other.orderName)) {
            return false;
        }
        if (orderNote == null) {
            if (other.orderNote != null) {
                return false;
            }
        } else if (!orderNote.equals(other.orderNote)) {
            return false;
        }
        if (taxable == null) {
            if (other.taxable != null) {
                return false;
            }
        } else if (!taxable.equals(other.taxable)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", orderName=" + orderName + ", orderNote=" + orderNote + ", taxable=" + taxable
                        + ", isDefaults=" + isDefaults + ", createdOn=" + createdOn + "]";
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public List<ShowOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ShowOrder> orders) {
        this.orders = orders;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderPosId() {
        return orderPosId;
    }

    public void setOrderPosId(String orderPosId) {
        this.orderPosId = orderPosId;
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

    public String getPosPaymentId() {
        return posPaymentId;
    }

    public void setPosPaymentId(String posPaymentId) {
        this.posPaymentId = posPaymentId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public List<OrderItemViewVO> getOrderItemViewVOs() {
        return orderItemViewVOs;
    }

    public void setOrderItemViewVOs(List<OrderItemViewVO> orderItemViewVOs) {
        this.orderItemViewVOs = orderItemViewVOs;
    }

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

    public Integer getIsFutureOrder() {
        return isFutureOrder;
    }

    public void setIsFutureOrder(Integer isFutureOrder) {
        this.isFutureOrder = isFutureOrder;
    }

}
