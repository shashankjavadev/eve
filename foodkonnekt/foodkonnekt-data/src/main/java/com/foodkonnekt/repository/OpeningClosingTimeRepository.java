package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.OpeningClosingTime;

public interface OpeningClosingTimeRepository extends JpaRepository<OpeningClosingTime, Integer> {

    /**
     * Find by openingClosingId
     * 
     * @param openingClosingDayId
     * @return
     */
    List<OpeningClosingTime> findByOpeningClosingDayId(Integer openingClosingDayId);
    

}
