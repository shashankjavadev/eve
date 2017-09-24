package com.foodkonnekt.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "pizzasize")
@XmlRootElement(name = "PizzaSize")
@Audited
public class PizzaSize {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	private String description;
	
	private int active;
	
	private String posPizzaSizeId;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;
	
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Set<PizzaTemplate> getPizzaTemplates() {
		return pizzaTemplates;
	}

	public void setPizzaTemplates(Set<PizzaTemplate> pizzaTemplates) {
		this.pizzaTemplates = pizzaTemplates;
	}

	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "pizzasize_pizzatemplate", joinColumns = { @JoinColumn(name = "pizza_size_id") }, inverseJoinColumns = { @JoinColumn(name = "pizzatemplate_id") })
    private Set<PizzaTemplate> pizzaTemplates = new HashSet<PizzaTemplate>();
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(mappedBy = "pizzaSizes",cascade = { CascadeType.ALL })
    private Set<Category> categories = new HashSet<Category>();
	
	@OneToMany(mappedBy = "pizzaSize",fetch = FetchType.EAGER,cascade = { CascadeType.ALL })
    private List<PizzaToppingSize> pizzaToppingSizes;
	
	 

	public List<PizzaToppingSize> getPizzaToppingSizes() {
		return pizzaToppingSizes;
	}

	public void setPizzaToppingSizes(List<PizzaToppingSize> pizzaToppingSizes) {
		this.pizzaToppingSizes = pizzaToppingSizes;
	}

	public int getActive() {
		return active;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public String getPosPizzaSizeId() {
		return posPizzaSizeId;
	}

	public void setPosPizzaSizeId(String posPizzaSizeId) {
		this.posPizzaSizeId = posPizzaSizeId;
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

	public int isActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}
