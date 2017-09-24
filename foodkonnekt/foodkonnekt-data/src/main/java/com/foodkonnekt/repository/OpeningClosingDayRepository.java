package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.OpeningClosingDay;

public interface OpeningClosingDayRepository extends JpaRepository<OpeningClosingDay, Integer> {

    /**
     * Find by merchant id
     * 
     * @param merchantId
     * @return
     */
    List<OpeningClosingDay> findByMerchantId(Integer merchantId);
    
    List<OpeningClosingDay> findByMerchantIdAndIsHolidayNot(Integer merchantId,Integer isHoliday);
    
    

    /**
     * 
     * 
     * Find by merchantId and openingClosingPosId
     * 
     * @param merchantId
     * @param openingClosingPosId
     * @return OpeningClosingDay instance
     */
    public OpeningClosingDay findByMerchantIdAndOpeningClosingPosId(Integer merchantId, String openingClosingPosId);

    /**
     * Find by merchantId and Day
     * 
     * @param merchantId
     * @param day
     * @return OpeningClosingDay instance
     */
    public OpeningClosingDay findByMerchantIdAndDay(Integer merchantId, String day);

    /**
     * Find by day and merchantId
     * 
     * @param futureDay
     * @param merchantId
     * @return OpeningClosingDay
     */
    public OpeningClosingDay findByDayAndMerchantId(String futureDay, Integer merchantId);

}
