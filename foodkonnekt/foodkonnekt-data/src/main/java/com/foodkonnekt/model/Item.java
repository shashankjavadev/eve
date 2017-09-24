package com.foodkonnekt.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "item")
@XmlRootElement(name = "Item")
@Audited
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic
    private Boolean isHidden;

    @Column(name = "pos_item_id")
    private String posItemId;
    @Basic
    @Column(length = 100)
    private String name;
    
    @Basic
    @Column(length = 150)
    private String description;

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Transient
	private String menuOrder;

	public String getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(String menuOrder) {
		this.menuOrder = menuOrder;
	}

	@Basic
    private Double price;

    @Basic
    @Column(length = 100)
    private String priceType;

    @Basic
    @Column(length = 100)
    private String unitName;

    @Basic
    private Boolean isRevenue;

    @Basic
    private Boolean isDefaultTaxRates;

    @Basic
    private Date modifiedTime;

    @ManyToMany(mappedBy = "items", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(cascade = { CascadeType.REMOVE })
    @JoinTable(name = "item_taxes", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = { @JoinColumn(name = "tax_rate_id") })
    private Set<TaxRates> taxes = new HashSet<TaxRates>();

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ItemModifierGroup> itemModifierGroups;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<OrderItem> orderItems;
    
    @OneToMany(mappedBy = "item", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemTiming> itemTimings;

    public List<ItemTiming> getItemTimings() {
		return itemTimings;
	}

	public void setItemTimings(List<ItemTiming> itemTimings) {
		this.itemTimings = itemTimings;
	}

	@Transient
    private String categoriesName;
    
    @Transient
    private String itemModifierGroupsIds;
    
    @Transient
    private String itemModifierGroupsStatusIds;
    
    @Transient
    private String days;
    
    @Transient
    private String startTime;
    
    @Transient
    private String endTime;
    
    public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Transient
    private String itemModifiersStatusIds;
    
    
    public String getItemModifiersStatusIds() {
		return itemModifiersStatusIds;
	}

	public void setItemModifiersStatusIds(String itemModifiersStatusIds) {
		this.itemModifiersStatusIds = itemModifiersStatusIds;
	}

	public String getItemModifierGroupsStatusIds() {
		return itemModifierGroupsStatusIds;
	}

	public void setItemModifierGroupsStatusIds(String itemModifierGroupsStatusIds) {
		this.itemModifierGroupsStatusIds = itemModifierGroupsStatusIds;
	}

	@Transient
    private String modifierLimits;
    
    @Column(name = "allow_modifier_limit")
    private Integer allowModifierLimit;
    
    @Column(name = "allow_item_timing")
    private Integer allowItemTimings;
    
    public Integer getAllowItemTimings() {
		return allowItemTimings;
	}

	public void setAllowItemTimings(Integer allowItemTimings) {
		this.allowItemTimings = allowItemTimings;
	}

	@Column(name = "allow_modifier_group_order")
    private Integer allowModifierGroupOrder;


    public Integer getAllowModifierGroupOrder() {
		return allowModifierGroupOrder;
	}

	public void setAllowModifierGroupOrder(Integer allowModifierGroupOrder) {
		this.allowModifierGroupOrder = allowModifierGroupOrder;
	}

	public Integer getAllowModifierLimit() {
		return allowModifierLimit;
	}

	public void setAllowModifierLimit(Integer allowModifierLimit) {
		this.allowModifierLimit = allowModifierLimit;
	}

	public String getItemModifierGroupsIds() {
		return itemModifierGroupsIds;
	}

	public void setItemModifierGroupsIds(String itemModifierGroupsIds) {
		this.itemModifierGroupsIds = itemModifierGroupsIds;
	}

	public String getModifierLimits() {
		return modifierLimits;
	}

	

	public void setModifierLimits(String modifierLimits) {
		this.modifierLimits = modifierLimits;
	}

	@Transient
    private String modifierGroups;

    @Transient
    private List<String> extras;

    @Transient
    private List<String> categoryList;

    @Column(name = "item_status")
    private Integer itemStatus;

    @Column(name = "modifiers_limit")
    private Integer modifiersLimit;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<ItemModifierGroup> getItemModifierGroups() {
        return itemModifierGroups;
    }

    public void setItemModifierGroups(List<ItemModifierGroup> itemModifierGroups) {
        this.itemModifierGroups = itemModifierGroups;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Boolean getIsRevenue() {
        return isRevenue;
    }

    public void setIsRevenue(Boolean isRevenue) {
        this.isRevenue = isRevenue;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getIsDefaultTaxRates() {
        return isDefaultTaxRates;
    }

    public void setIsDefaultTaxRates(Boolean isDefaultTaxRates) {
        this.isDefaultTaxRates = isDefaultTaxRates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    @Override
    public boolean equals(Object obj) {
    	try{
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Item other = (Item) obj;
        if (categories == null) {
            if (other.categories != null)
                return false;
        } else if (other!=null && categories!=null && other.categories!=null &&  !categories.equals(other.categories))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (isDefaultTaxRates == null) {
            if (other.isDefaultTaxRates != null)
                return false;
        } else if (!isDefaultTaxRates.equals(other.isDefaultTaxRates))
            return false;
        if (isHidden == null) {
            if (other.isHidden != null)
                return false;
        } else if (!isHidden.equals(other.isHidden))
            return false;
        if (isRevenue == null) {
            if (other.isRevenue != null)
                return false;
        } else if (!isRevenue.equals(other.isRevenue))
            return false;
        if (modifiedTime == null) {
            if (other.modifiedTime != null)
                return false;
        } else if (!modifiedTime.equals(other.modifiedTime))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (price == null) {
            if (other.price != null)
                return false;
        } else if (!price.equals(other.price))
            return false;
        if (priceType == null) {
            if (other.priceType != null)
                return false;
        } else if (!priceType.equals(other.priceType))
            return false;
        if (unitName == null) {
            if (other.unitName != null)
                return false;
        } else if (!unitName.equals(other.unitName))
            return false;
        return true;
    	}catch(Exception ex){
    		 return false;	
    	}
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public String getPosItemId() {
        return posItemId;
    }

    public void setPosItemId(String posItemId) {
        this.posItemId = posItemId;
    }

    public Set<TaxRates> getTaxes() {
        return taxes;
    }

    public void setTaxes(Set<TaxRates> taxes) {
        this.taxes = taxes;
    }

    /**
     * @return the categoriesName
     */
    public String getCategoriesName() {
        return categoriesName;
    }

    /**
     * @param categoriesName
     *            the categoriesName to set
     */
    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    /**
     * @return the modifierGroups
     */
    public String getModifierGroups() {
        return modifierGroups;
    }

    /**
     * @param modifierGroups
     *            the modifierGroups to set
     */
    public void setModifierGroups(String modifierGroups) {
        this.modifierGroups = modifierGroups;
    }

    public List<String> getExtras() {
        return extras;
    }

    public void setExtras(List<String> extras) {
        this.extras = extras;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public Integer getModifiersLimit() {
        return modifiersLimit;
    }

    public void setModifiersLimit(Integer modifiersLimit) {
        this.modifiersLimit = modifiersLimit;
    }

}
