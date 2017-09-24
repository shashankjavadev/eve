package com.foodkonnekt.service;

import java.util.Date;
import java.util.List;

import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.CustomerFeedback;
import com.foodkonnekt.model.CustomerFeedbackAnswer;
import com.foodkonnekt.model.FeedbackQuestionCategory;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.Vendor;

public interface KritiqService {
	
	public String checkMerchantExpiration(Merchant merchant);
	
	public boolean verifyOrderFromClover( String orderId, String merchantId);

	public OrderR getAllOrderInfo(Integer customerID, Integer orderId);

	//public List<FeedbackQuestionCategory> getFeedbackQuestionCategorys(String orderType);

	public OrderR validateCustomerAndOrder(String customerId, String orderId);

	public String getOrderDetail(Integer orderId);
	
	public void saveCustomerFeedback(CustomerFeedback customerFeedback);

	public List<CustomerFeedback> findByCustomerIdAndOrderId(String customerId, String orderid);
	public List<CustomerFeedback> findByOrderPosID(String posID);

	public OrderR findByOrderId(String orderid);

	public List<OrderR> multipleOrdersListOfCustomer(Date startDate, Date endDate, int custId);

	public List<FeedbackQuestionCategory> getFeedbackQuestionCategorys(String orderType, boolean pickup_flag,boolean delivery_flag, boolean dineIn_flag);

	public List<FeedbackQuestionCategory> getFeedbackQuestionCategorys();

//	public List<FeedbackQuestionCategory> getFeedbackQuestionCategorysByPosId(Integer id);
	
	public Customer saveWalkInCustomerDetail(Customer customer);

	public OrderR saveOrderDetails(OrderR orderR);

	public Vendor validateVendor(String vendorId);

	public List<Merchant> getMerchantsByVendorId(Integer id);

	public Merchant getMerchantDetailsByPosMerchantId(String merchantPosId);

	public Merchant validateMerchant(String merchantId);

	public Merchant getMerchantDetailsByMerchantId(Integer id);

	public OrderR getOrderDetailsByOrderPosId(String o);

	public List<CustomerFeedback> findByOrderId(Integer id);
	
	public CustomerFeedback getCustomerFeedback(Integer id);

	public Vendor getVendorDetailsByVendorId(Integer id);

	public List<CustomerFeedbackAnswer> getCustomerFeedbackResult(Integer custFeedbackId);

	public FeedbackQuestionCategory getFeedbackQuestionCategory(Integer id);


}
