package com.foodkonnekt.foodtronix.service;

import com.foodkonnekt.model.FoodTronix;
import com.foodkonnekt.model.Merchant;

public interface FoodTronixService {

    /**
     * Save merchant and vendor
     * 
     * @param merchantDetails
     * @param address
     * @param clover
     */
    public Merchant saveMerchant(FoodTronix foodTronix);

    public void saveItemProperty(FoodTronix foodTronix,Merchant merchant);
    
    public void saveItemPropertyGroup(FoodTronix foodTronix,Merchant merchant);
    
    public void saveDishCategory(FoodTronix foodTronix,Merchant merchant);
    
    public void savePizzaTemplate(FoodTronix foodTronix,Merchant merchant);
    
    public void savePizzaTemplateSize(FoodTronix foodTronix,Merchant merchant);
    
    public void savePizzaSize(FoodTronix foodTronix,Merchant merchant);
    
    
    
    public void savePizzaTopping(FoodTronix foodTronix,Merchant merchant);
    
    public void savePizzaToppingSize(FoodTronix foodTronix,Merchant merchant);
    
    
    
    
    
    
    
}
