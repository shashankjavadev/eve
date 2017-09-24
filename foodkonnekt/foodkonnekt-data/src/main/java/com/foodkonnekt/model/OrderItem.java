package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "createdOn")
    private Date createdOn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderR order;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;
    
    @OneToMany(mappedBy = "orderItem", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<OrderItemModifier> orderItemModifiers;

    public List<OrderItemModifier> getOrderItemModifiers() {
		return orderItemModifiers;
	}

	public void setOrderItemModifiers(List<OrderItemModifier> orderItemModifiers) {
		this.orderItemModifiers = orderItemModifiers;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public OrderR getOrder() {
        return order;
    }

    public void setOrder(OrderR order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
