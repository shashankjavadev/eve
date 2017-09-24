package com.foodkonnekt.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.foodkonnekt.model.CustomerFeedbackAnswer;

public interface CustomerFeedbackAnswerRepository extends JpaRepository<CustomerFeedbackAnswer, Integer> {

	
	@Query("SELECT c   FROM  CustomerFeedbackAnswer c WHERE c.customerFeedback.id=:customerFeedbackId ")
	List<CustomerFeedbackAnswer> findCustomerFeedbackInfo(@Param("customerFeedbackId")Integer customerFeedbackId);
	
}
