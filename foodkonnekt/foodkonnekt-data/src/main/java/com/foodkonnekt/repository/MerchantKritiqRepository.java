package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.MerchantKritiq;

public interface MerchantKritiqRepository extends JpaRepository<MerchantKritiq, Integer>{

	List<MerchantKritiq> findByMerchantId(Integer merchantId);
	
	

}
