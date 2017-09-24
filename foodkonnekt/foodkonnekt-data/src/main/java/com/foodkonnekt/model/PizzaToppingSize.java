package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pizzatoppingsize")
public class PizzaToppingSize {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
 
    private double price;
    
    private int active;
    
    private String posPizzaToppingSizeId;
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPosPizzaToppingSizeId() {
		return posPizzaToppingSizeId;
	}

	public void setPosPizzaToppingSizeId(String posPizzaToppingSizeId) {
		this.posPizzaToppingSizeId = posPizzaToppingSizeId;
	}

	public PizzaSize getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(PizzaSize pizzaSize) {
		this.pizzaSize = pizzaSize;
	}

	public PizzaTopping getPizzaTopping() {
		return pizzaTopping;
	}

	public void setPizzaTopping(PizzaTopping pizzaTopping) {
		this.pizzaTopping = pizzaTopping;
	}

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizzasize_id", referencedColumnName = "id")
    private PizzaSize pizzaSize;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizzatopping_id", referencedColumnName = "id")
    private PizzaTopping pizzaTopping;

}
