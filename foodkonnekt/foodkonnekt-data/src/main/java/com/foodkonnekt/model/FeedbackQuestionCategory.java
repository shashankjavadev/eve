package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "feedback_question_category")
public class FeedbackQuestionCategory {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFeedbackQuestionCategory() {
		return feedbackQuestionCategory;
	}

	public void setFeedbackQuestionCategory(String feedbackQuestionCategory) {
		this.feedbackQuestionCategory = feedbackQuestionCategory;
	}

	public List<FeedbackQuestion> getFeedbackQuestions() {
		return feedbackQuestions;
	}

	public void setFeedbackQuestions(List<FeedbackQuestion> feedbackQuestions) {
		this.feedbackQuestions = feedbackQuestions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "feedback_question_category")
	private String feedbackQuestionCategory;

	@OneToMany(mappedBy = "feedbackQuestionCategory", fetch = FetchType.LAZY,cascade = {CascadeType.ALL},orphanRemoval = true)
	private List<FeedbackQuestion> feedbackQuestions;

}
