package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "customer_feedback_answer")
public class CustomerFeedbackAnswer {
	
	    @Id
	    @GeneratedValue(strategy = IDENTITY)
	    @Column(name = "id", unique = true, nullable = false)
	    private Integer id;
	    
	    @OneToOne 
	    @JoinColumn(name="feedback_question_id") 
		private FeedbackQuestion feedbackQuestion;
	    
	    @Column(name = "answer")
	    private Integer answer;
	    
	    
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "customer_feedback_id", referencedColumnName = "id")
	    private CustomerFeedback customerFeedback;
	    
	    public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public FeedbackQuestion getFeedbackQuestion() {
			return feedbackQuestion;
		}

		public void setFeedbackQuestion(FeedbackQuestion feedbackQuestion) {
			this.feedbackQuestion = feedbackQuestion;
		}

		

		public CustomerFeedback getCustomerFeedback() {
			return customerFeedback;
		}

		public void setCustomerFeedback(CustomerFeedback customerFeedback) {
			this.customerFeedback = customerFeedback;
		}

		public Integer getAnswer() {
			return answer;
		}

		public void setAnswer(Integer answer) {
			this.answer = answer;
		}

		

}
