package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.PizzaTemplate;

public interface PizzaTemplateRepository extends JpaRepository<PizzaTemplate, Integer>  {
	
	public PizzaTemplate findByMerchantIdAndPosPizzaTemplateId(Integer merchantId, String posPizzaToppingId);

}
