package com.foodkonnekt.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.CustomerFeedback;

public interface CustomerFeedbackRepository extends JpaRepository<CustomerFeedback, Integer> {


	@Query("SELECT c FROM CustomerFeedback c WHERE c.customer.id=:customerId AND c.orderR.id=:orderid")
	List<CustomerFeedback> findByCustomerIdAndOrderId(@Param("customerId")Integer customerId, @Param("orderid")Integer orderid);
	
	
	@Query("SELECT c FROM CustomerFeedback c WHERE c.orderR.id=:orderid")
	List<CustomerFeedback> findByOrderId( @Param("orderid")Integer orderid);
	
	CustomerFeedback findById(Integer id);
}
