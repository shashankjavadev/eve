package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.PizzaSize;

public interface PizzaSizeRepository extends JpaRepository<PizzaSize, Integer> {

	PizzaSize findByPosPizzaSizeIdAndMerchantId(String posPizzaSizeId,Integer merchantId);
}
