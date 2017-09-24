package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.OrderDiscount;

public interface OrderDiscountRepository extends JpaRepository<OrderDiscount, Integer> {

	List<OrderDiscount> findByCustomerIdAndCouponCode(Integer customerId,String couponCode);
}
