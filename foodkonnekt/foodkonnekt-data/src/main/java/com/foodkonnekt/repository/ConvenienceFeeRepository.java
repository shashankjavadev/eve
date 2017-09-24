package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.ConvenienceFee;

public interface ConvenienceFeeRepository extends JpaRepository<ConvenienceFee, Integer> {
	
	public ConvenienceFee findByMerchantId(Integer merchantId);

}
