package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

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
@Table(name = "pizzatopping")
public class PizzaTopping {
	
	    @Id
	    @GeneratedValue(strategy = IDENTITY)
	    @Column(name = "id", unique = true, nullable = false)
	    private Integer id;
	 
	    private String description;
	    
	    private int active;
	    
	    private String posPizzaToppingId;
	    
	    @OneToMany(mappedBy = "pizzaTopping",fetch = FetchType.EAGER)
	    private List<PizzaToppingSize> pizzaToppingSizes;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
	    private Merchant merchant;
		
		public Merchant getMerchant() {
			return merchant;
		}

		public void setMerchant(Merchant merchant) {
			this.merchant = merchant;
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

		public int getActive() {
			return active;
		}

		public void setActive(int active) {
			this.active = active;
		}

		public String getPosPizzaToppingId() {
			return posPizzaToppingId;
		}

		public void setPosPizzaToppingId(String posPizzaToppingId) {
			this.posPizzaToppingId = posPizzaToppingId;
		}

		public List<PizzaToppingSize> getPizzaToppingSizes() {
			return pizzaToppingSizes;
		}

		public void setPizzaToppingSizes(List<PizzaToppingSize> pizzaToppingSizes) {
			this.pizzaToppingSizes = pizzaToppingSizes;
		}
	    
	    

}
