package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "customer_feedback")
public class CustomerFeedback {
	
	    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustomerComments() {
		return customerComments;
	}

	public void setCustomerComments(String customerComments) {
		this.customerComments = customerComments;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public OrderR getOrderR() {
		return orderR;
	}

	public void setOrderR(OrderR orderR) {
		this.orderR = orderR;
	}

		@Id
	    @GeneratedValue(strategy = IDENTITY)
	    @Column(name = "id", unique = true, nullable = false)
	    private Integer id;
	    
	    @Column(name = "customer_comments")
	    private String customerComments;
	    
	    //@OneToOne (cascade=CascadeType.ALL)
	    @OneToOne
	    @JoinColumn(name="customer_id") 
		private Customer customer;
	    
	    @OneToOne 
	    @JoinColumn(name="order_id") 
		private OrderR orderR;
	    
	    @OneToMany(mappedBy = "customerFeedback", fetch = FetchType.LAZY)
	    private List<CustomerFeedbackAnswer> customerFeedbackAnswers;

		public List<CustomerFeedbackAnswer> getCustomerFeedbackAnswers() {
			return customerFeedbackAnswers;
		}

		public void setCustomerFeedbackAnswers(List<CustomerFeedbackAnswer> customerFeedbackAnswers) {
			this.customerFeedbackAnswers = customerFeedbackAnswers;
		}
		
		@Transient
	    private String anniversaryDate;
		
		@Transient
	    private String anniversaryMonth;
		@Transient
	    private String bdayDate;
		@Transient
	    private String bdayMonth;

		public String getAnniversaryDate() {
			return anniversaryDate;
		}

		public void setAnniversaryDate(String anniversaryDate) {
			this.anniversaryDate = anniversaryDate;
		}

		public String getAnniversaryMonth() {
			return anniversaryMonth;
		}

		public void setAnniversaryMonth(String anniversaryMonth) {
			this.anniversaryMonth = anniversaryMonth;
		}

		public String getBdayDate() {
			return bdayDate;
		}

		public void setBdayDate(String bdayDate) {
			this.bdayDate = bdayDate;
		}

		public String getBdayMonth() {
			return bdayMonth;
		}

		public void setBdayMonth(String bdayMonth) {
			this.bdayMonth = bdayMonth;
		}

		public Date getCreateDate() {
			return createDate;
		}

		public void setCreateDate(Date createDate) {
			this.createDate = createDate;
		}

		 @Column(name = "create_date")
		private Date createDate;

}
