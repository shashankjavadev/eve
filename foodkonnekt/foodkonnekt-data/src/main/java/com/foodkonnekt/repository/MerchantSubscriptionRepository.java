package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.MerchantSubscription;

public interface MerchantSubscriptionRepository extends JpaRepository<MerchantSubscription, Integer> {

    /**
     * Find id by merchantId
     * 
     * @param merchantId
     * @return subScriptionId
     */
    @Query("SELECT ms.id FROM MerchantSubscription ms WHERE ms.merchant.id=:merchantId")
    Integer findByMerchantId(@Param("merchantId") final Integer merchantId);
    
    @Query("SELECT ms FROM MerchantSubscription ms WHERE ms.merchant.id=:merchantId")
    List<MerchantSubscription> findByMId(@Param("merchantId") final Integer merchantId);
    

}
