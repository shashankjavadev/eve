package com.foodkonnekt.service;

public interface CreateInventoryService {

    /**
     * Create new inventory and save into database
     * 
     * @param merchantId
     * @param objectType
     * @param objectId
     * @param actionType
     */
    public void createInventoryService(String merchantId, String objectType, String objectId, String actionType);

}
