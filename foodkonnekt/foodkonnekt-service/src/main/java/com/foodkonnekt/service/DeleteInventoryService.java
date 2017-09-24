package com.foodkonnekt.service;

public interface DeleteInventoryService {

    /**
     * Delete inventory
     * 
     * @param merchantId
     * @param objectType
     * @param objectId
     * @param actionType
     */
    public void deleteInventory(String merchantId, String objectType, String objectId, String actionType);

}
