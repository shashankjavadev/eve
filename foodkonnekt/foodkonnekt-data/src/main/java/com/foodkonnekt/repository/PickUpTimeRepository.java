package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.PickUpTime;

public interface PickUpTimeRepository extends JpaRepository<PickUpTime, Integer> {

	public PickUpTime findByMerchantId(Integer merchantId);
}
