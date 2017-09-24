package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "pizzatemplate")
public class PizzaTemplate {
	
	 @Id
	    @GeneratedValue(strategy = IDENTITY)
	    @Column(name = "id", unique = true, nullable = false)
	    private Integer id;
	 
	    private String description;
	    
	    private int active;
	    
	    private String posPizzaTemplateId;
	    
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
	    private Merchant merchant;
	    
	    public Merchant getMerchant() {
			return merchant;
		}

		public void setMerchant(Merchant merchant) {
			this.merchant = merchant;
		}

		public int getActive() {
			return active;
		}

		public String getPosPizzaTemplateId() {
			return posPizzaTemplateId;
		}

		public void setPosPizzaTemplateId(String posPizzaTemplateId) {
			this.posPizzaTemplateId = posPizzaTemplateId;
		}

		public Set<PizzaSize> getPizzaSizes() {
			return pizzaSizes;
		}

		public void setPizzaSizes(Set<PizzaSize> pizzaSizes) {
			this.pizzaSizes = pizzaSizes;
		}

		@LazyCollection(LazyCollectionOption.FALSE)
		@ManyToMany(mappedBy = "pizzaTemplates")
	    private Set<PizzaSize> pizzaSizes = new HashSet<PizzaSize>();
	    
	    @LazyCollection(LazyCollectionOption.FALSE)
	    @ManyToOne
	    @JoinColumn(name = "category_id", referencedColumnName = "id")
	    private Category category;
	    
	    
	    @OneToMany(mappedBy = "pizzaTemplate",fetch = FetchType.EAGER)
	    private List<PizzaTemplateSize> pizzaTemplateSizes;

		public List<PizzaTemplateSize> getPizzaTemplateSizes() {
			return pizzaTemplateSizes;
		}

		public void setPizzaTemplateSizes(List<PizzaTemplateSize> pizzaTemplateSizes) {
			this.pizzaTemplateSizes = pizzaTemplateSizes;
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

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

}
