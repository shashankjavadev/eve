package com.foodkonnekt.model;

import java.io.Serializable;
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

import org.hibernate.envers.Audited;

@Entity
@Table(name = "category")
@XmlRootElement(name = "Category")
@Audited
public class Category implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Basic
    @Column(length = 100)
    private String name;

    @Basic
    @Column(length = 100)
    private Integer sortOrder=0;

    @Column(name = "pos_category_id")
    private String posCategoryId;

    @Column(name = "item_status")
    private Integer itemStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.REMOVE })
    @JoinColumn(name = "merchant_id", referencedColumnName = "id")
    private Merchant merchant;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "item_category", joinColumns = { @JoinColumn(name = "category_id") }, inverseJoinColumns = { @JoinColumn(name = "item_id") })
    private Set<Item> items = new HashSet<Item>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "pizzasize_category", joinColumns = { @JoinColumn(name = "category_id") }, inverseJoinColumns = { @JoinColumn(name = "pizza_size_id") })
    private Set<PizzaSize> pizzaSizes = new HashSet<PizzaSize>();
    
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CategoryTiming> categoryTimings;
    
    @Column(name = "allow_category_timing")
    private Integer allowCategoryTimings;
    
    public Integer getAllowCategoryTimings() {
		return allowCategoryTimings;
	}

	public void setAllowCategoryTimings(Integer allowCategoryTimings) {
		this.allowCategoryTimings = allowCategoryTimings;
	}

	@Transient
    private Integer itemCount;
	
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

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CategoryTiming> getCategoryTimings() {
		return categoryTimings;
	}

	public void setCategoryTimings(List<CategoryTiming> categoryTimings) {
		this.categoryTimings = categoryTimings;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((sortOrder == null) ? 0 : sortOrder.hashCode());
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
        if (!(obj instanceof Category)) {
            return false;
        }
        Category other = (Category) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (sortOrder == null) {
            if (other.sortOrder != null) {
                return false;
            }
        } else if (!sortOrder.equals(other.sortOrder)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", sortOrder=" + sortOrder + "]";
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Integer getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(Integer itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getPosCategoryId() {
        return posCategoryId;
    }

    public void setPosCategoryId(String posCategoryId) {
        this.posCategoryId = posCategoryId;
    }

    public Set<PizzaSize> getPizzaSizes() {
        return pizzaSizes;
    }

    public void setPizzaSizes(Set<PizzaSize> pizzaSizes) {
        this.pizzaSizes = pizzaSizes;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

}
