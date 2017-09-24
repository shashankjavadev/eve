package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.OrderItemModifier;

public interface OrderItemModifierRepository extends JpaRepository<OrderItemModifier, Integer> {

    /**
     * Find by order item id
     * 
     * @param orderItemId
     * @return List<OrderItemModifier> orderItemModifiers
     */
    List<OrderItemModifier> findByOrderItemId(Integer orderItemId);

    /**
     * Find by modifierId
     * 
     * @param modifierId
     * @return List<OrderItemModifier>
     */
    List<OrderItemModifier> findByModifiersId(Integer modifierId);

}
