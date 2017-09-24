package com.foodkonnekt.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Synchronization;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.OrderItem;
import com.foodkonnekt.model.OrderItemModifier;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.CustomerFeedback;
import com.foodkonnekt.model.CustomerFeedbackAnswer;
import com.foodkonnekt.model.FeedbackQuestion;
import com.foodkonnekt.model.FeedbackQuestionCategory;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.MerchantKritiq;
import com.foodkonnekt.model.MerchantSubscription;
import com.foodkonnekt.model.OrderItem;
import com.foodkonnekt.model.OrderItemModifier;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.Subscription;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.repository.OrderItemModifierRepository;
import com.foodkonnekt.repository.OrderItemRepository;
import com.foodkonnekt.repository.CustomerFeedbackAnswerRepository;
import com.foodkonnekt.repository.CustomerFeedbackRepository;
import com.foodkonnekt.repository.CustomerrRepository;
import com.foodkonnekt.repository.FeedbackQuestionCategoryRepository;
import com.foodkonnekt.repository.FeedbackQuestionRepository;
import com.foodkonnekt.repository.MerchantKritiqRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.MerchantSubscriptionRepository;
import com.foodkonnekt.repository.OrderItemModifierRepository;
import com.foodkonnekt.repository.OrderItemRepository;
import com.foodkonnekt.repository.OrderRepository;
import com.foodkonnekt.repository.SubscriptionRepository;
import com.foodkonnekt.repository.VendorRepository;
import com.foodkonnekt.service.KritiqService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;

@Service
public class KritiqServiceImpl implements KritiqService {

	@Autowired
	private FeedbackQuestionCategoryRepository feedbackQuestionCategoryRepository;

	@Autowired
	private FeedbackQuestionRepository feedbackQuestionRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	
	@Autowired
	private CustomerFeedbackRepository customerFeedbackRepository;
	
	@Autowired
    private SubscriptionRepository  subscriptionRepository;
	
	 @Autowired
	    private MerchantSubscriptionRepository merchantSubscriptionRepository;
	
	 @Autowired
	    private MerchantKritiqRepository merchantKritiqRepository;
	    
	
	@Autowired
	private CustomerrRepository customerrRepository;
	
	@Autowired
	private CustomerFeedbackAnswerRepository customerFeedbackAnswerRepository ;
	
	

	@Autowired
	private OrderItemModifierRepository itemModifierRepository;

	@Autowired
	private VendorRepository vendorRepository;
	@Autowired
	MerchantRepository merchantRepository;
	
	public OrderR getAllOrderInfo(Integer customerID, Integer orderId) {
		// TODO Auto-generated method stub
		OrderR orderRs = orderRepository.findByCustomerIdAndId(customerID, orderId);

		return orderRs;
	}

	public String getOrderDetail(Integer orderId) {
		String orderDetails = "";
		List<OrderItem> orderItems = orderItemRepository.findByOrderId(orderId);
		 for (OrderItem orderItem : orderItems) {
        if (null != orderItem) {
            if (null != orderItem.getItem()) {
                String items = "<tr style='font-weight:600;text-transform:capitalize;font-family:arial'><td width='200px;'>"
                                + orderItem.getItem().getName()
                                + "</td><td width='100px;' style='text-align:center'>"
                                + orderItem.getQuantity()
                                + "</td><td width='100px;' style='text-align:center'>"
                                + "$"
                                + orderItem.getItem().getPrice() + "</td></tr>";
                orderDetails = orderDetails + "" + "<b>" + items + "</b>";
                List<Map<String, String>> extraList = new ArrayList<Map<String, String>>();
                Map<String, String> poduct = new HashMap<String, String>();
                poduct.put("product_id", orderItem.getItem().getPosItemId());
                poduct.put("name", orderItem.getItem().getName());
                poduct.put("price", Double.toString(orderItem.getItem().getPrice()));
                poduct.put("qty", Integer.toString(orderItem.getQuantity()));
                List<OrderItemModifier> itemModifiers = itemModifierRepository
                                .findByOrderItemId(orderItem.getId());
                for (OrderItemModifier itemModifier : itemModifiers) {
                    String modifiers = "<tr style='text-transform:capitalize;font-family:arial;margin-top:5px;font-size:12px'><td width='200px;'>"
                                    + itemModifier.getModifiers().getName()
                                    + "</td><td width='100px;' style='text-align:center'>"
                                    + itemModifier.getQuantity()
                                    + " </td><td width='100px;' style='text-align:center'> "
                                    + "$"
                                    + itemModifier.getModifiers().getPrice() + "</td></tr>";
                    orderDetails = orderDetails + "" + modifiers;
                    Map<String, String> exctra = new HashMap<String, String>();
                    exctra.put("id", itemModifier.getModifiers().getPosModifierId());
                    exctra.put("price", Double.toString(itemModifier.getModifiers().getPrice()));
                    exctra.put("name", itemModifier.getModifiers().getName());
                    exctra.put("qty", Integer.toString(orderItem.getQuantity()));
                    extraList.add(exctra);
                }
               
            }
        }
	}
		 
		 return orderDetails;

	}

//	public List<FeedbackQuestionCategory> getFeedbackQuestionCategorys(String orderType) {
	public List<FeedbackQuestionCategory> getFeedbackQuestionCategorys(String orderType, boolean pickup_flag,boolean delivery_flag, boolean dineIn_flag) {
		// TODO Auto-generated method stub
		
		List<FeedbackQuestionCategory> feedbackQuestionCategoriesResult = feedbackQuestionCategoryRepository.findAll();
		List<FeedbackQuestionCategory> feedbackQuestionCategories= new ArrayList<FeedbackQuestionCategory>();
		for (FeedbackQuestionCategory feedbackQuestionCategory : feedbackQuestionCategoriesResult) {
			
			if(pickup_flag && !delivery_flag  && feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_DELIVERY ){
				continue;
				
			}else if(!pickup_flag && delivery_flag  && feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_PICKUP ){
				continue;
			}else if(!dineIn_flag && (feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_DINE_IN || feedbackQuestionCategory.getId()==IConstant.FEEDBACK_OUR_SERVICES )){
				continue;
			}else{
				feedbackQuestionCategories.add(feedbackQuestionCategory);
			}
			List<FeedbackQuestion> feedbackQuestions = feedbackQuestionRepository
					.findByFeedbackQuestionCategoryId(feedbackQuestionCategory.getId());
			feedbackQuestionCategory.setFeedbackQuestions(feedbackQuestions);
		}
		return feedbackQuestionCategories;
	}

	public OrderR validateCustomerAndOrder(String customerId, String orderId) {
		try {
			Integer custId = Integer.parseInt(EncryptionDecryptionUtil.decryption(customerId));
			Integer ordId = Integer.parseInt(EncryptionDecryptionUtil.decryption(orderId));
			OrderR orderR = orderRepository.findByCustomerIdAndId(custId, ordId);
			if (orderR != null) {
				return orderR;
			} else {
				return null;
			}

		} catch (Exception exception) {
			return null;
		}

	}

	public void saveCustomerFeedback(CustomerFeedback customerFeedback) {
		customerFeedback.setCreateDate(new Date());
		  CustomerFeedback customerFeedbackResult=customerFeedbackRepository.save(customerFeedback);
		  Customer customer= customerFeedback.getCustomer();
		  Customer customerResult = new Customer();
		 
		  boolean merchantFeedback=false;
		  final boolean merchantFeedbackStatus;
		  boolean onlineFeedback=false;
		 // final boolean onlineFeedbackStatus;
		  
		  if(customer!=null && customer.getId()!=null){
			   customerResult=customerrRepository.findOne(customer.getId());
		   if(customer.getEmailId()!=null  && !customer.getEmailId().equals(""))
		   customerResult.setEmailId(customer.getEmailId());
		   if(customer.getPhoneNumber()!=null && !customer.getPhoneNumber().equals(""))
		    customerResult.setPhoneNumber(customer.getPhoneNumber());
		   if(customerFeedback.getBdayDate()!=null && !customerFeedback.getBdayDate().equals("")&& customerFeedback.getBdayMonth()!=null && !customerFeedback.getBdayMonth().equals(""))
		    customerResult.setBirthDate(customerFeedback.getBdayMonth()+","+customerFeedback.getBdayDate());
		   if(customerFeedback.getAnniversaryDate()!=null && !customerFeedback.getAnniversaryDate().equals("")&& customerFeedback.getAnniversaryMonth()!=null && !customerFeedback.getAnniversaryMonth().equals(""))
		    customerResult.setAnniversaryDate(customerFeedback.getAnniversaryMonth()+","+customerFeedback.getAnniversaryDate());
		   customerrRepository.save(customerResult);
		   
		    }
		  
		  int merchantCount=0;
		  final int merchantCountStatus;
		  int supportCount=0;
		  Integer offlineRate = 0;
		  final Integer offlineRateStatus ;
		  Integer onlineRate = 0;
		  List<CustomerFeedbackAnswer> cfaForMerchant = new ArrayList<CustomerFeedbackAnswer>();
		  List<CustomerFeedbackAnswer> cfaForSupport = new ArrayList<CustomerFeedbackAnswer>();
		  
		  for(CustomerFeedbackAnswer customerFeedbackAnswer:customerFeedback.getCustomerFeedbackAnswers()){
		   if(customerFeedbackAnswer.getAnswer()!=null){
		   customerFeedbackAnswer.setCustomerFeedback(customerFeedbackResult);
		   customerFeedbackAnswerRepository.save(customerFeedbackAnswer);
		   FeedbackQuestionCategory feedbackQuestionCategory=customerFeedbackAnswer.getFeedbackQuestion().getFeedbackQuestionCategory();
		   Integer customerRate=customerFeedbackAnswer.getAnswer();
		  /* if((feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_DELIVERY ||feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_PICKUP ||feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_FOOD||feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_DINE_IN) && customerRate<3 ){
		    merchantFeedback=true;
		    cfaForMerchant.add(customerFeedbackAnswer);
		    merchantCount++;
		   }*/
		   
		   if((feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_DELIVERY ||feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_PICKUP ||feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_FOOD||feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_DINE_IN)  ){
			   offlineRate = offlineRate + customerRate;
			     
			    cfaForMerchant.add(customerFeedbackAnswer);
			    merchantCount++;
		   }
		   
		   /*if(feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_ONLINE_ORDER && customerRate<3 ){
		    onlineFeedback=true;
		    cfaForSupport.add(customerFeedbackAnswer);
		    supportCount++;
		   	}*/
		   
		   if(feedbackQuestionCategory.getId()==IConstant.FEEDBACK_CATEGORY_ONLINE_ORDER  ){
			   onlineRate = onlineRate + customerRate;
			   
			    
			    cfaForSupport.add(customerFeedbackAnswer);
			    supportCount++;
			   	}
		   
		   }
		   		  
		  }
		  String email = null;
		  if(customer!= null &&  customer.getMerchantt()!= null && customer.getMerchantt().getOwner()!= null && customer.getMerchantt().getOwner().getEmail()!= null){
			  email = customer.getMerchantt().getOwner().getEmail();
		  }
		  
		  
		  
		  
		  
		  offlineRateStatus=offlineRate;
		  merchantCountStatus=merchantCount;
		  merchantFeedbackStatus=merchantFeedback;
		  final int supportCountStatus=supportCount;
		  final int onlineRateStatus=onlineRate;
		 // onlineFeedbackStatus=onlineFeedback;
		  final String emailForMerchant = email;
		  final String emailForSupport= "support@foodkonnekt.com";
		  final CustomerFeedback feedback=customerFeedback;
		  final Customer customer2=customerResult;
		  
		  final List<CustomerFeedbackAnswer> cfaForMerchantFinal = cfaForMerchant;
		  final List<CustomerFeedbackAnswer> cfaForSupportFinal = cfaForSupport;
		  Runnable inventoryRunnable = new Runnable() {

              public void run() {
            	  if(offlineRateStatus>0 &&   merchantCountStatus>0 && (offlineRateStatus/merchantCountStatus)<3 && emailForMerchant!= null){
            		  
        			  MailSendUtil.thankyouMailForFeedback(feedback, customer2, true);
        			  MailSendUtil.feedbackResultOfClientToMerchantMail(feedback, customer2, offlineRateStatus/merchantCountStatus, cfaForMerchantFinal,emailForMerchant);
        		  }else if(supportCountStatus>0 && onlineRateStatus>0 && (onlineRateStatus/supportCountStatus)<3){
        			 
        			  MailSendUtil.thankyouMailForFeedback(feedback, customer2, true);
        			  MailSendUtil.feedbackResultOfClientToMerchantMail(feedback, customer2, onlineRateStatus/supportCountStatus, cfaForSupportFinal,emailForSupport);
        			  //MailSendUtil.feedbackResultOfClientToSupportMail(feedback, customer2,supportCountStatus,cfaForSupportFinal);
        		  }else{
        			  MailSendUtil.thankyouMailForFeedback(feedback, customer2, false);
        		  }
        		  
            	  System.out.println("mail sent");
                  
              }
          };
          Thread inventoryThread = new Thread(inventoryRunnable);
          inventoryThread.setName("KritiqMailThread");
          inventoryThread.start();
		  		  
		        System.out.println("feedback done");
		 }
	
	public List<CustomerFeedback> findByCustomerIdAndOrderId(String customerId, String orderid) {
		Integer custId = Integer.parseInt(EncryptionDecryptionUtil.decryption(customerId));
		Integer ordId = Integer.parseInt(EncryptionDecryptionUtil.decryption(orderid));
		
		return customerFeedbackRepository.findByCustomerIdAndOrderId(custId, ordId);
	}
	
	public List<CustomerFeedback> findByOrderPosID(String posID) {
		
		OrderR orderR=orderRepository.findByOrderPosId(posID);
		if(orderR!=null)
		return customerFeedbackRepository.findByOrderId( orderR.getId());
		else
			return null;
	}

	public OrderR findByOrderId(String orderid) {
		Integer orderId = Integer.parseInt(EncryptionDecryptionUtil.decryption(orderid));
		return orderRepository.findOne(orderId);
	}

	public List<OrderR> multipleOrdersListOfCustomer(Date startDate, Date endDate, int custId) {
		return orderRepository.findByStartDateAndEndDateAndCustomerId(startDate, endDate, custId);
	}

	public List<FeedbackQuestionCategory> getFeedbackQuestionCategorys() {
		List<FeedbackQuestionCategory> feedbackQuestionCategoriesResult = feedbackQuestionCategoryRepository.findAll();
		List<FeedbackQuestionCategory> feedbackQuestionCategories= new ArrayList<FeedbackQuestionCategory>();
		for (FeedbackQuestionCategory feedbackQuestionCategory : feedbackQuestionCategoriesResult) {
			
			
			
			if(feedbackQuestionCategory.getId()!=IConstant.FEEDBACK_CATEGORY_PICKUP && feedbackQuestionCategory.getId()!=IConstant.FEEDBACK_CATEGORY_ONLINE_ORDER &&feedbackQuestionCategory.getId()!=IConstant.FEEDBACK_CATEGORY_DELIVERY ){
			
				feedbackQuestionCategories.add(feedbackQuestionCategory);
			}
				List<FeedbackQuestion> feedbackQuestions = feedbackQuestionRepository
						.findByFeedbackQuestionCategoryId(feedbackQuestionCategory.getId());
				feedbackQuestionCategory.setFeedbackQuestions(feedbackQuestions);
				System.out.println(feedbackQuestionCategory.getFeedbackQuestions());
		}
		return feedbackQuestionCategories;		
		
	}

	
/*	public List<FeedbackQuestionCategory> getFeedbackQuestionCategorysByPosId(Integer posId) {
		List<FeedbackQuestionCategory> feedbackQuestionCategoriesResult = feedbackQuestionCategoryRepository.findAll();
		List<FeedbackQuestionCategory> feedbackQuestionCategories= new ArrayList<FeedbackQuestionCategory>();
		for (FeedbackQuestionCategory feedbackQuestionCategory : feedbackQuestionCategoriesResult) {
			
			
			
			if(feedbackQuestionCategory.getId()!=IConstant.FEEDBACK_CATEGORY_PICKUP && feedbackQuestionCategory.getId()!=IConstant.FEEDBACK_CATEGORY_ONLINE_ORDER &&feedbackQuestionCategory.getId()!=IConstant.FEEDBACK_CATEGORY_DELIVERY ){
			
				feedbackQuestionCategories.add(feedbackQuestionCategory);
			}
				List<FeedbackQuestion> feedbackQuestions = feedbackQuestionRepository.findByFeedbackQuestionCategoryId(feedbackQuestionCategory.getId());
				feedbackQuestionCategory.setFeedbackQuestions(feedbackQuestions);
		}
		return feedbackQuestionCategories;		
		
	}*/

	
	
	
	
	
	public Customer saveWalkInCustomerDetail(Customer customer) {
		if(customer!=null){
			customer.setCustomerType("clover");
		}
		return customerrRepository.save(customer);
	}

	public OrderR saveOrderDetails(OrderR orderR) {
		return orderRepository.save(orderR);
		
	}

	public Merchant validateMerchant(String merchantId){
		Integer merchantid = Integer.parseInt(EncryptionDecryptionUtil.decryption(merchantId));
		return merchantRepository.findOne(merchantid);
		
	}

	public Vendor validateVendor(String vendorId) {
		Integer vendorid = Integer.parseInt(EncryptionDecryptionUtil.decryption(vendorId));
		return vendorRepository.findOne(vendorid);
	}

	public List<Merchant> getMerchantsByVendorId(Integer id) {
	
		return merchantRepository.findByOwnerId(id);
	}
	
	public Merchant getMerchantDetailsByMerchantId(Integer id) {
		// TODO Auto-generated method stub
		return merchantRepository.findById(id);
	}
	
	
	

	public Vendor getVendorDetailsByVendorId(Integer vendorid) {
		// TODO Auto-generated method stub
		return vendorRepository.findOne(vendorid);
	}
	
	
	public Merchant getMerchantDetailsByPosMerchantId(String merchantPosId) {
		// TODO Auto-generated method stub
		return  merchantRepository.findByPosMerchantId(merchantPosId);
	}

	public boolean verifyOrderFromClover(String orderId, String merchantId) {
		System.out.println("kritiqServiceimpl-"+merchantId+" "+orderId);
    	Clover clover = new  Clover();
    	clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
        clover.setUrl(IConstant.CLOVER_URL);
        //clover.setUrl("http://localhost:8080");
    	Customer customer = new Customer();
        OrderR orderR = new OrderR();
        Merchant newMerchant = merchantRepository.findByPosMerchantId(merchantId);
        System.out.println(newMerchant.getId());
        if(newMerchant!=null)
        {
        	System.out.println("in merchant");
        	clover.setAuthToken(newMerchant.getAccessToken());
        	clover.setMerchantId(newMerchant.getPosMerchantId());
        	String orderResponse = CloverUrlUtil.getFeedbackClover(clover,orderId);
        	if(orderResponse!=null && orderResponse.contains("id")){
        		return true;
        	}else{
        		return false;
        	}
        	/*JSONObject jsonObj = new JSONObject(orderResponse);
        	
        	if(jsonObj.toString().contains("customers")){
        	JSONObject customersObject = jsonObj.getJSONObject("customers");
        	JSONArray customerObjArray = customersObject.getJSONArray("elements");
        	for(Object obj: customerObjArray)
        	{
        		System.out.println("cloverServiceimpl for loop");
        		JSONObject customerObj = (JSONObject) obj;
        		customer.setCustomerPosId(customerObj.getString("id"));
        		orderR.setCustomer(customer);
        		orderR.setOrderPosId(orderId);
        		orderR.setCreatedOn();
        		
        		
        	}
        	 customer = customerrRepository.save(customer);
        		orderR = orderRepository.save(orderR);
        		System.out.println("custId-"+customer.getId()+" ordId-"+orderR.getId());
        		String custId = EncryptionDecryptionUtil.encryption(String.valueOf(customer.getId()));
        		String orderid = EncryptionDecryptionUtil.encryption(String.valueOf(orderR.getId()));
        		System.out.println("cloverServiceimpl before redirect");
        		return "/feedbackForm?customerId="+custId+"&orderId="+orderid;
        		// return "redirect:"+UrlConstant.WEB_BASE_URL+"/feedbackForm?customerId="+custId+"&orderId="+orderid;
        		//List<CustomerFeedback> custFeedback = kritiqService.findByCustomerIdAndOrderId(customerId, orderid);
        	*/}
        
       
        return false;
	}

	public OrderR getOrderDetailsByOrderPosId(String o) {
		return orderRepository.findByOrderPosId(o);
	}

	public List<CustomerFeedback> findByOrderId(Integer id) {
		return customerFeedbackRepository.findByOrderId(id);
	}

	public String checkMerchantExpiration(Merchant merchant) {
		String merchantKritiqExpiryStatus=null;
		if(merchant!=null ){
		if( merchant.getActiveCustomerFeedback()!=null && merchant.getActiveCustomerFeedback()==0){
			merchantKritiqExpiryStatus="disabled";
		}else{
			merchantKritiqExpiryStatus="enabled";
		}
		List<MerchantSubscription> merchantSubscriptions =merchantSubscriptionRepository.findByMId(merchant.getId());
		Subscription subscription =null;
		if(merchantSubscriptions!=null && merchantSubscriptions.size()>0){
		 subscription =subscriptionRepository.findOne(merchantSubscriptions.get(0).getSubscription().getId());
		}
		if(subscription!=null &&  subscription.getPrice()<49.99){
		List<MerchantKritiq> kritiqs= merchantKritiqRepository.findByMerchantId(merchant.getId());
		boolean isFreeSubcription=false;
		if(kritiqs!=null && !kritiqs.isEmpty()){
			//for(MerchantKritiq ktitiq:kritiqs){
			MerchantKritiq ktitiq=kritiqs.get(kritiqs.size()-1);
				if(ktitiq!=null && ktitiq.getSubscrptionType()!=null && ktitiq.getSubscrptionType().equals("free")){
					isFreeSubcription=true;
				}else{
					isFreeSubcription=false;	
				}
				
					Date date1 = DateUtil.convertStringToDate(kritiqs.get(kritiqs.size()-1).getValidityDate());
					//currentDate=DateUtil.convertStringToDate(currentDate.toString());
					Date currentDate=new Date();
					System.out.println(date1.compareTo(currentDate));
					if ( date1.compareTo(currentDate) < 0) {
						merchantKritiqExpiryStatus="expired";
				}
		}
			//}
		}
		}
		return merchantKritiqExpiryStatus;
	}

	public List<CustomerFeedbackAnswer> getCustomerFeedbackResult(Integer custFeedbackId) {
	// Map<Integer, Integer> queAnswer = null;
		List<CustomerFeedbackAnswer> customerFeedbackList = null;
		if(custFeedbackId!= null && custFeedbackId!=0){
				 customerFeedbackList = customerFeedbackAnswerRepository.findCustomerFeedbackInfo(custFeedbackId);
				/*if(customerFeedbackList!= null && !customerFeedbackList.isEmpty()){
					for(CustomerFeedbackAnswer listCustomerFeedback :customerFeedbackList){
						System.out.println("FEEDBACKQUESTIONCATEGORY"+ listCustomerFeedback.getFeedbackQuestion().getFeedbackQuestionCategory().getFeedbackQuestionCategory());
						System.out.println("FEEDBACKQUESTION::"+listCustomerFeedback.getFeedbackQuestion().getQuestion()+"FEEDBACKANSWER"+listCustomerFeedback.getAnswer());
						//queAnswer.put(listCustomerFeedback.getFeedbackQuestion().getId(), listCustomerFeedback.getAnswer());
					}
				}*/
				 
			}
		
		return customerFeedbackList;
		}

	
	public FeedbackQuestionCategory getFeedbackQuestionCategory(Integer feedbackQuestionCategoryId) {
		if(feedbackQuestionCategoryId!= null && feedbackQuestionCategoryId!=0){
		FeedbackQuestionCategory feedbackQuestionCategoriesResult = feedbackQuestionCategoryRepository.findOne(feedbackQuestionCategoryId);
		
		List<FeedbackQuestion> feedbackQuestions = feedbackQuestionRepository
				.findByFeedbackQuestionCategoryId(feedbackQuestionCategoriesResult.getId());
		feedbackQuestionCategoriesResult.setFeedbackQuestions(feedbackQuestions);
		
		return feedbackQuestionCategoriesResult;
		}
		return null;		
		
	}

	public CustomerFeedback getCustomerFeedback(Integer id) {
		
		return customerFeedbackRepository.findById(id);
	}
	

}
