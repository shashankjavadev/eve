package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.ItemTax;

public interface ItemTaxRepository extends JpaRepository<ItemTax, Integer> {

    /**
     * Find by ItemId
     * 
     * @param itemId
     * @return List<ItemTax>
     */
    List<ItemTax> findByItemId(Integer itemId);
    
    List<ItemTax> findByItemPosItemId(String posItemId);
    
    List<ItemTax> findByTaxRatesId(Integer taxRatesId);

    /**
     * Find by ItemId and TaxRatesId
     * 
     * @param itemId
     * @param taxRatesId
     * @return ItemTax
     */
    List<ItemTax> findByItemIdAndTaxRatesId(Integer itemId, Integer taxRatesId);

}
