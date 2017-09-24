package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    /**
     * Find by order id
     * 
     * @param orderId
     * @return
     */
    List<OrderItem> findByOrderId(Integer orderId);

    /**
     * Find by ItemId
     * 
     * @param id
     * @return List<OrderItem>
     */
    List<OrderItem> findByItemId(Integer itemId);

    @Query("SELECT DISTINCT orderItem.item.id FROM OrderItem orderItem where orderItem.order.id=:orderId")
    List<Integer> findDistinctItemIdByOrderId(@Param("orderId") Integer orderId);
}
