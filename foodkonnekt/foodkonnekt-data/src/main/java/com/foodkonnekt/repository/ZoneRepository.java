package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.Zone;

public interface ZoneRepository extends JpaRepository<Zone, Integer> {

    /**
     * Find by merchantId
     * 
     * @param merchantId
     * @return
     */
    List<Zone> findByMerchantId(Integer merchantId);
    
    Zone findByMerchantIdAndZoneName(Integer merchantId,String zoneName);
    
   /* @Query("SELECT z from zone z where z.merchant.id =:merchantId AND avg_delivery_time = ( SELECT MAX(avg_delivery_time) FROM zone c where c.merchant.id =:merchantId)")
    Zone findByMerchantIdAndAvgDeliveryTime(@Param("merchantId") Integer merchantId);*/
    
    
    		 @Query(value = "SELECT MAX(avg_delivery_time) FROM zone c WHERE c.merchant_id =:merchantId", nativeQuery = true)
    		String findMaxDeliveryAvgTimeByMerchantId(@Param("merchantId") Integer merchantId);
     /**
     * Find by address id
     * 
     * @param id
     * @return List<Zone>
     */
    List<Zone> findByAddressId(Integer id);

    /**
     * Find zones by merchantId and status
     * 
     * @param merchantId
     * @param zoneStatus
     * @returnList<Zone>
     */
    List<Zone> findByMerchantIdAndZoneStatus(Integer merchantId, int zoneStatus);
}
