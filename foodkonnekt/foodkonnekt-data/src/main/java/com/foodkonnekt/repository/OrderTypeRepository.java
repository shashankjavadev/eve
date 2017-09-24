package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.OrderType;

public interface OrderTypeRepository extends JpaRepository<OrderType, Integer> {

	public OrderType findByMerchantIdAndPosOrderTypeId(Integer merchantId, String posOrderTypeId);
	
	public List<OrderType>  findByMerchantId(Integer merchantId);
	
	public OrderType findByMerchantIdAndLabel(Integer merchantId,String label);

	
	
	
}
