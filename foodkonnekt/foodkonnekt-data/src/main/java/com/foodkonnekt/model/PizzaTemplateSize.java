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
@Table(name = "pizzatemplatesize")
public class PizzaTemplateSize {
	
	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
 
    private String description;
    
    private String posPizzaTemplateSizeId;
    
    public String getPosPizzaTemplateSizeId() {
		return posPizzaTemplateSizeId;
	}

	public void setPosPizzaTemplateSizeId(String posPizzaTemplateSizeId) {
		this.posPizzaTemplateSizeId = posPizzaTemplateSizeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public PizzaSize getPizzaSize() {
		return pizzaSize;
	}

	public void setPizzaSize(PizzaSize pizzaSize) {
		this.pizzaSize = pizzaSize;
	}

	public PizzaTemplate getPizzaTemplate() {
		return pizzaTemplate;
	}

	public void setPizzaTemplate(PizzaTemplate pizzaTemplate) {
		this.pizzaTemplate = pizzaTemplate;
	}

	private double price;
    
    private int active;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizzasize_id", referencedColumnName = "id")
    private PizzaSize pizzaSize;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pizzatemplate_id", referencedColumnName = "id")
    private PizzaTemplate pizzaTemplate;

    
    
    
    

}
