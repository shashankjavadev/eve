package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "feedback_question")
public class FeedbackQuestion {
	
	    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	
	public void setQuestion(String question) {
		this.question = question;
	}

	public FeedbackQuestionCategory getFeedbackQuestionCategory() {
		return feedbackQuestionCategory;
	}

	public void setFeedbackQuestionCategory(FeedbackQuestionCategory feedbackQuestionCategory) {
		this.feedbackQuestionCategory = feedbackQuestionCategory;
	}

		@Id
	    @GeneratedValue(strategy = IDENTITY)
	    @Column(name = "id", unique = true, nullable = false)
	    private Integer id;
	    
	    @Column(name = "question")
	    private String question;
	    
	    @Column(name = "isrequired")
	    private Boolean isRequired;
	    
	    public Boolean getIsRequired() {
			return isRequired;
		}

		public void setIsRequired(Boolean isRequired) {
			this.isRequired = isRequired;
		}

		@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	    @JoinColumn(name = "question_category", referencedColumnName = "id")
	    private FeedbackQuestionCategory feedbackQuestionCategory;

}
