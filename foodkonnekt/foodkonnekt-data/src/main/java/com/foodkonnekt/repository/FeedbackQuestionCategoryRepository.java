package com.foodkonnekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodkonnekt.model.FeedbackQuestionCategory;

public interface FeedbackQuestionCategoryRepository extends JpaRepository<FeedbackQuestionCategory, Integer>{

}
