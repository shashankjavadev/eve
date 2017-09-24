package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.MerchantOrders;

public interface MerchantOrdersRepository extends JpaRepository<MerchantOrders, Integer> {

    /**
     * Find by merchantSubscriptionId
     * 
     * @param merchantSubscriptionId
     * @return MerchantOrders instance
     */
    MerchantOrders findByMerchantSubscriptionId(Integer merchantSubscriptionId);

}