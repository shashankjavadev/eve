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
@Table(name = "item_taxes")
public class ItemTax {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tax_rate_id", referencedColumnName = "id")
    private TaxRates taxRates;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private Item item;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaxRates getTaxRates() {
        return taxRates;
    }

    public void setTaxRates(TaxRates taxRates) {
        this.taxRates = taxRates;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
