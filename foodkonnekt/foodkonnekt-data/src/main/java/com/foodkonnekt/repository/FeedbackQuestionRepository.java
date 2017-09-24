package com.foodkonnekt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.FeedbackQuestion;

public interface FeedbackQuestionRepository extends JpaRepository<FeedbackQuestion, Integer>{
	
	List<FeedbackQuestion> findByFeedbackQuestionCategoryId(Integer feedbackQuestionCategoryId );

}
