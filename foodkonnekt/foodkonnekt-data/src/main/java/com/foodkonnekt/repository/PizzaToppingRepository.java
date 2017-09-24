package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.PizzaTopping;

public interface PizzaToppingRepository extends JpaRepository<PizzaTopping, Integer> {
	
	public PizzaTopping findByMerchantIdAndPosPizzaToppingId(Integer merchantId, String posPizzaToppingId);

}
