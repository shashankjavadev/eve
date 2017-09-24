package com.foodkonnekt.service;

import java.util.List;

import com.foodkonnekt.clover.vo.CloverOrderVO;
import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.Merchant;

public interface CloverService {

    /**
     * Save merchant and vendor
     * 
     * @param merchantDetails
     * @param address
     * @param clover
     */
    public Merchant saveMerchant(Clover clover);

    /**
     * Save order type
     * 
     * @param orderTypeDetail
     * @param merchant
     */
    public void saveOrderType(String orderTypeDetail, Merchant merchant,Clover clover);

    /**
     * Save payment mode
     * 
     * @param paymentModeDetail
     * @param merchant
     */
    public void savePaymentMode(String paymentModeDetail, Merchant merchant);

    /**
     * Save tax rate
     * 
     * @param taxRates
     * @param merchant
     */
    public void saveTaxRate(String taxRates, Merchant merchant);

    /**
     * Save opening closing hours
     * 
     * @param merchant
     * @param openingHour
     */
    public void saveOpeningClosing(Merchant merchant, String openingHour);

    /**
     * Save category into database
     * 
     * @param clover
     * @param merchant
     */
    public void saveCategory(Clover clover, Merchant merchant);

    /**
     * Find all category by merchantId
     * 
     * @param merchantId
     * @return List<Category> categories
     */
    public List<Category> getAllCategory(Integer merchantId);

    /**
     * Find items by itemId
     * 
     * @param itemId
     * @param merchant
     * @return
     */
    public Item getItemByItemID(String itemId, Merchant merchant);

    /**
     * Save Item
     * 
     * @param clover
     * @param merchant
     */
    public void saveItem(Clover clover, Merchant merchant);

    /**
     * Create customer on clover
     * 
     * @param customer
     * @param clover
     * @return
     */
    public String createCustomer(Customer customer, Clover clover);
    
    public String createOrderTypeOnClover(String orderType, Clover clover,String systemOrderTypeId);

    /**
     * Place order on clover
     * 
     * @param cloverOrderVO
     * @param clover
     * @return
     */
    public String postOrderOnClover(CloverOrderVO cloverOrderVO, Clover clover);

	public String getMerchantData(String orderId, String merchantId);
    
    
  
}
