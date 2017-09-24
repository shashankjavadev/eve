package com.foodkonnekt.contoller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.CustomerFeedback;
import com.foodkonnekt.model.CustomerFeedbackAnswer;
import com.foodkonnekt.model.FeedbackQuestion;
import com.foodkonnekt.model.FeedbackQuestionCategory;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.service.KritiqService;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;


@Controller
public class KritiqController {
	
	 @Autowired
	    private KritiqService kritiqService;
	
		/**
	     * Show Inventory page
	     * 
	     * @return String
	     */
	 /*    @RequestMapping(value = "/feedbackForm", method = RequestMethod.GET)
    public String feedbackForm(ModelMap model, HttpServletRequest request,@RequestParam(value = "customerId") String customerId,@RequestParam(value = "orderId") String orderid) {
       
    	if(customerId!=null && !customerId.isEmpty()  && orderid!=null && !orderid.isEmpty() ){
    		OrderR orderR=	kritiqService.validateCustomerAndOrder(customerId,orderid);
    		List<CustomerFeedback> custFeedback = kritiqService.findByCustomerIdAndOrderId(customerId, orderid);
    		boolean feedBackStatus= false;
    	if(orderR!=null){
    			String orderType="pickup";
    			if(orderR.getOrderType()!=null){
    				orderType=orderR.getOrderType();
    			}
    			List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys(orderType);
    	    	
    			if(custFeedback.size()>0)
        		{
    				System.out.println(custFeedback.get(0));
        			feedBackStatus= true;
        			model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
        	    	model.addAttribute("orderR", orderR);
        	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
        			model.addAttribute("feedBackStatus",feedBackStatus);
        		return "feedbackForm";	
        		}else{
    			
    			System.out.println("feedBackStatus"+feedBackStatus);

    	    	model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
    	    	model.addAttribute("orderR", orderR);
    	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
    			return "feedbackForm";
        		}
    		}else{
    			return null;
    		
    	}
    	
    	}else{
    		return null;
    	}
    }
*/	 
	 /*@RequestMapping(value = "/saveCustomerFeedback", method = RequestMethod.POST)
    public String saveCustomerFeedback(@ModelAttribute("CustomerFeedback") CustomerFeedback customerFeedback,ModelMap model, HttpServletRequest request) {
		 try{
		 System.out.println("custid "+customerFeedback.getCustomer().getId()+" ordrid-"+customerFeedback.getOrderR().getId());
		int customerId = customerFeedback.getCustomer().getId();
		int orderId = customerFeedback.getOrderR().getId();
		String custId = EncryptionDecryptionUtil.encryption(String.valueOf(customerId));
		String orderid = EncryptionDecryptionUtil.encryption(String.valueOf(orderId));
		List<CustomerFeedback> custFeedback = kritiqService.findByCustomerIdAndOrderId(custId, orderid);
		
		if(custFeedback.size()>0)
		{
			System.out.println("size is >0");
			 return "redirect:feedbackForm?customerId="+custId+"&orderId="+orderid;
		}
		
		kritiqService.saveCustomerFeedback(customerFeedback);
		 System.out.println("Done");
}catch(Exception e){
			 
			 if (e != null) {
	                MailSendUtil.sendExceptionByMail(e);

	                e.printStackTrace();
	                return "redirect:https://www.foodkonnekt.com";
	            }
			 
		 }
   return "feedbackshare";
    }*/
	 
	
	 @RequestMapping(value = "/saveCustomerFeedbackUsingAjax", method = RequestMethod.POST)
	 @ResponseBody
	    public String saveCustomerFeedback(@ModelAttribute("CustomerFeedback") CustomerFeedback customerFeedback,ModelMap model, HttpServletRequest request) {
			 try{
			 System.out.println("custid "+customerFeedback.getCustomer().getId()+" ordrid-"+customerFeedback.getOrderR().getId());
			int customerId = customerFeedback.getCustomer().getId();
			int orderId = customerFeedback.getOrderR().getId();
			String custId = EncryptionDecryptionUtil.encryption(String.valueOf(customerId));
			String orderid = EncryptionDecryptionUtil.encryption(String.valueOf(orderId));
			List<CustomerFeedback> custFeedback = kritiqService.findByCustomerIdAndOrderId(custId, orderid);
			
			if(custFeedback.size()>0)
			{
				System.out.println("size is >0");
				return IConstant.RESPONSE_NO_DATA_MESSAGE;
				
				 //return "redirect:feedbackForm?customerId="+custId+"&orderId="+orderid;
			}
			
			kritiqService.saveCustomerFeedback(customerFeedback);
			 System.out.println("Done");
	}catch(Exception e){
				 
				 if (e != null) {
		                MailSendUtil.sendExceptionByMail(e);

		                e.printStackTrace();
		                return "redirect:https://www.foodkonnekt.com";
		            }
				 
			 }
			 return IConstant.RESPONSE_SUCCESS_MESSAGE;
	    }
	 
	 
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/orderRec", method = RequestMethod.GET)
	    public String orderRec(HttpServletRequest request,@RequestParam String orderid, @RequestParam String customerid,ModelMap model) {
		
		 try{
		 System.out.println(orderid);
		 System.out.println(customerid);
		 Integer orderId = 0;
		 Integer customerID=0;
         if(orderid!=null){
         if (orderid.matches("[0-9]+")) {
        	 return "redirect:https://www.foodkonnekt.com";
         } else {
         	if(EncryptionDecryptionUtil.decryption(orderid).matches("[0-9]+"))
         		orderId = Integer.parseInt(EncryptionDecryptionUtil.decryption(orderid));
         }
		 
	    }
         
         if(customerid!=null){
             if (customerid.matches("[0-9]+")) {
            	 return "redirect:https://www.foodkonnekt.com";
             } else {
             	if(EncryptionDecryptionUtil.decryption(customerid).matches("[0-9]+"))
             		customerID = Integer.parseInt(EncryptionDecryptionUtil.decryption(customerid));
             }
    		  	  
    	    }
         
         System.out.println(orderId);
		 System.out.println(customerID);
         
         
        OrderR order = kritiqService.getAllOrderInfo(customerID, orderId );
        String orderDetail = kritiqService.getOrderDetail(orderId);
         model.addAttribute("order",  order);
         model.addAttribute("orderdetails", orderDetail);
         model.addAttribute("kritiqLink",UrlConstant.WEB_BASE_URL + "/feedbackForm?customerId=" + customerid+ "&orderId=" + orderid);
         
        Merchant merchant= order.getMerchant();
       String merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
         if(merchant != null){
        	 
        	 if (merchant.getMerchantLogo() == null) {
                 merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
             } else {
                 merchantLogo = UrlConstant.BASE_PORT + merchant.getMerchantLogo();
             }
         }
        
         model.addAttribute("merchantLogo", merchantLogo);
         
         
		 }catch(Exception e){
			 
			 if (e != null) {
	                MailSendUtil.sendExceptionByMail(e);

	                e.printStackTrace();
	                return "redirect:https://www.foodkonnekt.com";
	            }
			 
		 }
		
		
		 
		 return "OrderReceipt";
	 }
	 
	 
	 
	 
	 
	 @RequestMapping(value = "/feedbackForm", method = RequestMethod.GET)
	    public String feedbackForm(ModelMap model, HttpServletRequest request,@RequestParam(value = "customerId") String customerId,@RequestParam(value = "orderId") String orderid) {
		 System.out.println("inside feedbackForm");
		 if(customerId!=null && !customerId.isEmpty()  && orderid!=null && !orderid.isEmpty() ){
			 
			 try {
	    		OrderR orderR=	kritiqService.validateCustomerAndOrder(customerId,orderid);
	    		/*if(orderR.getMerchant().getActiveCustomerFeedback()==1){*/
	    		List<CustomerFeedback> custFeedback = kritiqService.findByCustomerIdAndOrderId(customerId, orderid);
	    		OrderR orderDetails = kritiqService.findByOrderId(orderid);
	    		
	    		int custId = orderDetails.getCustomer().getId();
	    		Date date=	orderDetails.getCreatedOn();
	    		System.out.println(date);
	    		DateFormat dateFormat = new SimpleDateFormat(IConstant.YYYYMMDD);
	    		DateFormat dateFormat1 = new SimpleDateFormat(IConstant.YYYYMMDDHHMMSS);
	    		String date1 = dateFormat.format(date).toString();
	    		
	    		String s1 = date1+IConstant.STARTDATE;
	    		String s2 = date1+IConstant.ENDDATE;
	    		
	    		boolean pickup_flag = false;
	    		boolean delivery_flag = false;
	    		boolean dineIn_flag = false;
	    		
					java.util.Date startDate = dateFormat1.parse(s1);
					java.util.Date endDate = dateFormat1.parse(s2);
				
	    		List<OrderR> multipleOrderList = kritiqService.multipleOrdersListOfCustomer(startDate,endDate, custId);
	    		List<String> oType = new ArrayList<String>();
	    		
	    		for(OrderR multipleOrderList1:multipleOrderList )
	    		{
	    		String type =multipleOrderList1.getOrderType();
	    		oType.add(type);
	    		}
	    		
	    		for(String o : oType)
	    		{
	    		if("pickup".equals(o.toLowerCase()))
	    		{
	    			pickup_flag=true;
	    		}
	    		else if("delivery".equals(o.toLowerCase())){
	    			delivery_flag = true;
	    		}else
	    			dineIn_flag= true;
	    			
	    		
	    		}
	    		boolean feedBackStatus= false;
		    	if(orderR!=null){
	    			String orderType="pickup";
	    			if(orderR.getOrderType()!=null){
	    				orderType=orderR.getOrderType();
	    			}
	    			List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys(orderType, pickup_flag, delivery_flag, dineIn_flag);
	    			//List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys(orderType);
	    			model.addAttribute("merchantLogo", orderR.getMerchant().getMerchantLogo());
	    			if(orderR.getMerchant()!=null){
	    			 String merchantKritiqExpiryStatus=kritiqService.checkMerchantExpiration(orderR.getMerchant());
	    			 model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
	    			}
	    			if(custFeedback.size()>0)
	        		{
	    				System.out.println(custFeedback.get(0));
	        			feedBackStatus= true;
	        			model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
	        	    	model.addAttribute("orderR", orderR);
	        	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
	        			model.addAttribute("feedBackStatus",feedBackStatus);
	        		return "feedbackForm";	
	        		}else{
	    			
	    			System.out.println("feedBackStatus"+feedBackStatus);
	    			model.addAttribute("feedBackStatus",feedBackStatus);
	    	    	model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
	    	    	model.addAttribute("orderR", orderR);
	    	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
	    			return "feedbackForm";
	        		}
	    		}else{
	    			return "redirect:https://www.foodkonnekt.com"; 
	    	}
	    	
		    	/*}else{
	    			return "redirect:https://www.foodkonnekt.com"; 
	    		}*/
	    	} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:https://www.foodkonnekt.com"; 
			}
	    		
	    		  		
	    		
	    		
	    	}   else{
	    		return "redirect:https://www.foodkonnekt.com"; 
	    	}
		  		
	 }
	    
	 
	 @RequestMapping(value = "/feedbackForm1", method = RequestMethod.GET)
	    public String feedbackForm1(ModelMap model, HttpServletRequest request,@RequestParam(value = "customerId") String customerId,@RequestParam(value = "orderId") String orderid) {
		 System.out.println("inside feedbackForm");
		 if(customerId!=null && !customerId.isEmpty()  && orderid!=null && !orderid.isEmpty() ){
			 
			 try {
	    		OrderR orderR=	kritiqService.validateCustomerAndOrder(customerId,orderid);
	    		/*if(orderR.getMerchant().getActiveCustomerFeedback()==1){*/
	    		List<CustomerFeedback> custFeedback = kritiqService.findByCustomerIdAndOrderId(customerId, orderid);
	    		OrderR orderDetails = kritiqService.findByOrderId(orderid);
	    		
	    		int custId = orderDetails.getCustomer().getId();
	    		Date date=	orderDetails.getCreatedOn();
	    		System.out.println(date);
	    		DateFormat dateFormat = new SimpleDateFormat(IConstant.YYYYMMDD);
	    		DateFormat dateFormat1 = new SimpleDateFormat(IConstant.YYYYMMDDHHMMSS);
	    		String date1 = dateFormat.format(date).toString();
	    		
	    		String s1 = date1+IConstant.STARTDATE;
	    		String s2 = date1+IConstant.ENDDATE;
	    		
	    		boolean pickup_flag = false;
	    		boolean delivery_flag = false;
	    		boolean dineIn_flag = false;
	    		
					java.util.Date startDate = dateFormat1.parse(s1);
					java.util.Date endDate = dateFormat1.parse(s2);
				
	    		List<OrderR> multipleOrderList = kritiqService.multipleOrdersListOfCustomer(startDate,endDate, custId);
	    		List<String> oType = new ArrayList<String>();
	    		
	    		for(OrderR multipleOrderList1:multipleOrderList )
	    		{
	    		String type =multipleOrderList1.getOrderType();
	    		oType.add(type);
	    		}
	    		
	    		for(String o : oType)
	    		{
	    		if("pickup".equals(o.toLowerCase()))
	    		{
	    			pickup_flag=true;
	    		}
	    		else if("delivery".equals(o.toLowerCase())){
	    			delivery_flag = true;
	    		}else
	    			dineIn_flag= true;
	    			
	    		
	    		}
	    		boolean feedBackStatus= false;
		    	if(orderR!=null){
	    			String orderType="pickup";
	    			if(orderR.getOrderType()!=null){
	    				orderType=orderR.getOrderType();
	    			}
	    			List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys(orderType, pickup_flag, delivery_flag, dineIn_flag);
	    			//List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys(orderType);
	    			model.addAttribute("merchantLogo", orderR.getMerchant().getMerchantLogo());
	    			if(orderR.getMerchant()!=null){
	    			 String merchantKritiqExpiryStatus=kritiqService.checkMerchantExpiration(orderR.getMerchant());
	    			 model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
	    			}
	    			if(custFeedback.size()>0)
	        		{
	    				System.out.println(custFeedback.get(0));
	        			feedBackStatus= true;
	        			model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
	        	    	model.addAttribute("orderR", orderR);
	        	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
	        			model.addAttribute("feedBackStatus",feedBackStatus);
	        		return "feedbackForm1";	
	        		}else{
	    			
	    			System.out.println("feedBackStatus"+feedBackStatus);
	    			model.addAttribute("feedBackStatus",feedBackStatus);
	    	    	model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
	    	    	model.addAttribute("orderR", orderR);
	    	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
	    			return "feedbackForm1";
	        		}
	    		}else{
	    			return "redirect:https://www.foodkonnekt.com"; 
	    	}
	    	
		    	/*}else{
	    			return "redirect:https://www.foodkonnekt.com"; 
	    		}*/
	    	} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:https://www.foodkonnekt.com"; 
			}
	    		
	    		  		
	    		
	    		
	    	}   else{
	    		return "redirect:https://www.foodkonnekt.com"; 
	    	}
		  		
	 }
	 
	 

	 @RequestMapping(value = "/vfeedbackForm", method = RequestMethod.GET) 
	 public String feedbackFormWalkInCustomer(ModelMap model, @RequestParam(value = "vendorId") String vendorId, HttpServletRequest request) {
		 System.out.println("inside vfeedbackForm");
		 try{
		 System.out.println("feedbackFormWalkInCustomer");
		 Vendor vendor = kritiqService.validateVendor(vendorId);
		 System.out.println("vend id-"+vendor.getId());
		 List<Merchant> merchantList = kritiqService.getMerchantsByVendorId(vendor.getId());
		 List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
		String formType ="vendor";
		 model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
		 model.addAttribute("merchantList", merchantList);
		 
	    	//model.addAttribute("orderR", orderR);
		 model.addAttribute("formType",formType);
	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
			//model.addAttribute("feedBackStatus",feedBackStatus);
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:https://www.foodkonnekt.com";
			}
		 
		 return "feedbackFormWalkInCustomer"; 
	 }
	 
	 /*@RequestMapping(value = "/mfeedbackForm", method = RequestMethod.GET) 
	 public String feedbackFormWalkInCustomerByMerchant(ModelMap model, @RequestParam(value = "merchantId") String merchantId, HttpServletRequest request) {
		 System.out.println("Inside mfeedbackForm");
		 try{
		 System.out.println("feedbackFormWalkInCustomer");
		 Merchant merchant =  kritiqService.validateMerchant(merchantId);
		// Vendor vendor = kritiqService.validateVendor(vendorId);
		 System.out.println("vend id-"+merchant.getId());
		// List<Merchant> merchantList = kritiqService.getMerchantsByVendorId(vendor.getId());
		Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
		System.out.println( "pos id-->"+merchant1.getPosMerchantId());
		 List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
		 String formType ="merchant";
		 model.addAttribute("formType",formType);
		 model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
		 model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
	    	//model.addAttribute("orderR", orderR);
	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
			//model.addAttribute("feedBackStatus",feedBackStatus);
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:https://www.foodkonnekt.com";
			}
		 
		 return "feedbackFormWalkInCustomer"; 
	 }*/
	 
	 
	 @RequestMapping(value = "/mfeedbackForm", method = RequestMethod.GET) 
	 public String feedbackFormWalkInCustomerByMerchant(ModelMap model, @RequestParam(required = false, value = "merchantId") String merchantId, @RequestParam(required = false, value = "orderId") String orderId, HttpServletRequest request) {
		 System.out.println("Inside mfeedbackForm");
		 /*try{
			 if(orderId!=null){
		 System.out.println("feedbackFormWalkInCustomer");
		 Merchant merchant =  kritiqService.validateMerchant(merchantId);
		
		Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
		String merchantKritiqExpiryStatus=kritiqService.checkMerchantExpiration(merchant1);
		Vendor vendor = kritiqService.getVendorDetailsByVendorId(merchant1.getOwner().getId());
		 List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
		// List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorysByPosId(vendor.getPos().getPosId()); 
		model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
		 model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
		 model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
		 model.addAttribute("orderId",orderId);
	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
	    	boolean feedBackStatus= false;
	    	List<CustomerFeedback> custFeedback = kritiqService.findByOrderPosID(orderId);
	    	 if(custFeedback!=null && custFeedback.size()>0)
	  		{
	  			feedBackStatus= true;
	  		}
	    	 model.addAttribute("feedBackStatus",feedBackStatus);
	    	 model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
			 }else{
				 return "redirect:https://www.foodkonnekt.com";
			 }
			
		 } catch (Exception e) {
				e.printStackTrace();
				//return "redirect:https://www.foodkonnekt.com";
			}*/
		 
		 try{
			 if(merchantId!= null && !merchantId.isEmpty() && merchantId!="" && orderId!= null && !orderId.isEmpty() && orderId!=""){
			 Merchant merchant =  kritiqService.validateMerchant(merchantId);
				
				
			
			 if(merchant!=null){
				 Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
				 String merchantKritiqExpiryStatus=kritiqService.checkMerchantExpiration(merchant1);
				 model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
				 OrderR order = kritiqService.getOrderDetailsByOrderPosId(orderId);
				 boolean feedBackStatus= false;
				 if(order==null ){
					 List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
					 
					List<CustomerFeedback> custFeedback = kritiqService.findByOrderPosID(orderId);
					model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
					 model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
					 model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
					 model.addAttribute("orderId", orderId);
				    	//model.addAttribute("orderR", orderR);
				    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
				    	 
				    	 if(custFeedback!=null && custFeedback.size()>0)
				  		{
				  			feedBackStatus= true;
				  			
				  			
				  		}else{
				 			model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
				 	    	//model.addAttribute("orderR", orderR);
				 	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
				 			
				  		}
				  			model.addAttribute("feedBackStatus",feedBackStatus);

				    	
				    	
				    	return "feedbackFormWalkInCustomer";
				 
			 
			 }else{
				 
				 List<CustomerFeedback> custFeedback = kritiqService.findByOrderId(order.getId());
				 CustomerFeedback customerFeedback=new CustomerFeedback();
				 if(order.getCustomer()!=null && order.getCustomer().getId()!=null){
					 customerFeedback.setCustomer(order.getCustomer());
					}
				 model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
					if(custFeedback.size()>0)
					{
						feedBackStatus= true;
			  			model.addAttribute("feedBackStatus",feedBackStatus);
			  			model.addAttribute("CustomerFeedback", new CustomerFeedback());
			  			 return "feedbackFormWalkInCustomer";
			  			
					}else{
						 List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
						 System.out.println("today05-08-2017"+order.getCustomer().getId());
						 String custId = EncryptionDecryptionUtil.encryption(String.valueOf(order.getCustomer().getId()));
				  			String orderid = EncryptionDecryptionUtil.encryption(String.valueOf(order.getId()));
				  			
				  			model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
						 model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
						 model.addAttribute("orderId", orderId);
						 model.addAttribute("orderR", order);
						 model.addAttribute("CustomerFeedback", new CustomerFeedback());
						 model.addAttribute("feedBackStatus",feedBackStatus);
						 return "redirect:feedbackForm?customerId="+custId+"&orderId="+orderid;
						 
					}
			 }
			 
			 }else{
				 return "redirect:https://www.foodkonnekt.com"; 
			 }
				//model.addAttribute("feedBackStatus",feedBackStatus);
			 }else{
				 return "redirect:https://www.foodkonnekt.com"; 
			 }
			 } catch (Exception e) {
					e.printStackTrace();
					return "redirect:https://www.foodkonnekt.com";
				}
		 //return "feedbackFormWalkInCustomer";
		// return "redirect:" + UrlConstant.WEB_BASE_URL + "/feedbackFormWalkInCustomer";
	 }
	 
	 
	 @RequestMapping(value = "/ofeedbackForm", method = RequestMethod.GET) 
	 public String feedbackOrderIdByMerchant(ModelMap model,
			 @RequestParam("merchantId") String merchantId, HttpServletRequest request) {
		 try{
		 Merchant merchant =  kritiqService.validateMerchant(merchantId);
		 if(merchant!=null ){
		//System.out.println("vend id-"+merchant.getId());
		// List<Merchant> merchantList = kritiqService.getMerchantsByVendorId(vendor.getId());
		Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
		String merchantKritiqExpiryStatus=kritiqService.checkMerchantExpiration(merchant1);
		Vendor vendor = kritiqService.getVendorDetailsByVendorId(merchant1.getOwner().getId());
		//System.out.println( "pos id-->"+merchant1.getPosMerchantId());
		 //List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
		 String formType ="merchant";
		 //model.addAttribute("formType",formType);
		// model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
		 model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
		 model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
		 model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
		 model.addAttribute("merchantId", merchantId);
		 model.addAttribute("posId", vendor.getPos().getPosId());
	    	//model.addAttribute("orderR", orderR);
	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
			//model.addAttribute("feedBackStatus",feedBackStatus);
		 }else{
			 return "redirect:https://www.foodkonnekt.com";
		 }
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:https://www.foodkonnekt.com";
			}
		 
		 return "feedbackOrderId"; 
	 }
	 
	 
	 @RequestMapping(value = "/ofeedbackForm5", method = RequestMethod.GET) 
	 public String feedbackOrderIdByMerchantNew(ModelMap model,
			 @RequestParam("merchantId") String merchantId, HttpServletRequest request) {
		 try{
		 Merchant merchant =  kritiqService.validateMerchant(merchantId);
		 if(merchant!=null ){
		//System.out.println("vend id-"+merchant.getId());
		// List<Merchant> merchantList = kritiqService.getMerchantsByVendorId(vendor.getId());
		Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
		String merchantKritiqExpiryStatus=kritiqService.checkMerchantExpiration(merchant1);
		Vendor vendor = kritiqService.getVendorDetailsByVendorId(merchant1.getOwner().getId());
		//System.out.println( "pos id-->"+merchant1.getPosMerchantId());
		 //List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
		 String formType ="merchant";
		 //model.addAttribute("formType",formType);
		// model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
		 model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
		 model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
		 model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
		 model.addAttribute("merchantId", merchantId);
		 model.addAttribute("posId", vendor.getPos().getPosId());
	    	//model.addAttribute("orderR", orderR);
	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
			//model.addAttribute("feedBackStatus",feedBackStatus);
		 }else{
			 return "redirect:https://www.foodkonnekt.com";
		 }
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:https://www.foodkonnekt.com";
			}
		 
		 return "feedbackOrderId5"; 
	 }
	 
	 
	 
	@RequestMapping(value = "/mfeedbackForm5", method = RequestMethod.GET)
	public String feedbackFormWalkInCustomerByMerchant5(ModelMap model,
			@RequestParam(required = false, value = "merchantId") String merchantId,
			@RequestParam(required = false, value = "orderId") String orderId, HttpServletRequest request) {
		System.out.println("Inside mfeedbackForm5");

		try {
			if (merchantId != null && !merchantId.isEmpty() && merchantId != "" && orderId != null && !orderId.isEmpty()
					&& orderId != "") {
				Merchant merchant = kritiqService.validateMerchant(merchantId);

				if (merchant != null) {
					Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
					String merchantKritiqExpiryStatus = kritiqService.checkMerchantExpiration(merchant1);
					model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
					OrderR order = kritiqService.getOrderDetailsByOrderPosId(orderId);
					boolean feedBackStatus = false;
					if (order == null) {
						List<FeedbackQuestionCategory> feedbackQuestionCategories = kritiqService
								.getFeedbackQuestionCategorys();

						List<CustomerFeedback> custFeedback = kritiqService.findByOrderPosID(orderId);
						model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
						model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
						model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
						model.addAttribute("orderId", orderId);

						model.addAttribute("CustomerFeedback", new CustomerFeedback());

						if (custFeedback != null && custFeedback.size() > 0) {
							feedBackStatus = true;

						} else {
							model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);

							model.addAttribute("CustomerFeedback", new CustomerFeedback());

						}
						model.addAttribute("feedBackStatus", feedBackStatus);

						return "feedbackFormWalkInCustomer5";

					} else {

						List<CustomerFeedback> custFeedback = kritiqService.findByOrderId(order.getId());
						CustomerFeedback customerFeedback = new CustomerFeedback();
						if (order.getCustomer() != null && order.getCustomer().getId() != null) {
							customerFeedback.setCustomer(order.getCustomer());
						}
						model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
						if (custFeedback.size() > 0) {
							feedBackStatus = true;
							model.addAttribute("feedBackStatus", feedBackStatus);
							model.addAttribute("CustomerFeedback", new CustomerFeedback());
							return "feedbackFormWalkInCustomer5";

						} else {
							List<FeedbackQuestionCategory> feedbackQuestionCategories = kritiqService
									.getFeedbackQuestionCategorys();
							System.out.println("today05-08-2017" + order.getCustomer().getId());
							String custId = EncryptionDecryptionUtil
									.encryption(String.valueOf(order.getCustomer().getId()));
							String orderid = EncryptionDecryptionUtil.encryption(String.valueOf(order.getId()));

							model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
							model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
							model.addAttribute("orderId", orderId);
							model.addAttribute("orderR", order);
							model.addAttribute("CustomerFeedback", new CustomerFeedback());
							model.addAttribute("feedBackStatus", feedBackStatus);
							return "redirect:feedbackForm?customerId=" + custId + "&orderId=" + orderid;

						}
					}

				} else {
					return "redirect:https://www.foodkonnekt.com";
				}

			} else {
				return "redirect:https://www.foodkonnekt.com";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:https://www.foodkonnekt.com";
		}

	}
	 
	 
	 @RequestMapping(value = "/ofeedbackForm6", method = RequestMethod.GET) 
	 public String feedbackOrderIdByMerchantNew6(ModelMap model,
			 @RequestParam("merchantId") String merchantId, HttpServletRequest request) {
		 try{
		 Merchant merchant =  kritiqService.validateMerchant(merchantId);
		 if(merchant!=null ){
		//System.out.println("vend id-"+merchant.getId());
		// List<Merchant> merchantList = kritiqService.getMerchantsByVendorId(vendor.getId());
		Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
		String merchantKritiqExpiryStatus=kritiqService.checkMerchantExpiration(merchant1);
		Vendor vendor = kritiqService.getVendorDetailsByVendorId(merchant1.getOwner().getId());
		//System.out.println( "pos id-->"+merchant1.getPosMerchantId());
		 //List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
		 String formType ="merchant";
		 //model.addAttribute("formType",formType);
		// model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
		 model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
		 model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
		 model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
		 model.addAttribute("merchantId", merchantId);
		 model.addAttribute("posId", vendor.getPos().getPosId());
	    	//model.addAttribute("orderR", orderR);
	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
			//model.addAttribute("feedBackStatus",feedBackStatus);
		 }else{
			 return "redirect:https://www.foodkonnekt.com";
		 }
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:https://www.foodkonnekt.com";
			}
		 
		 return "feedbackOrderId6"; 
	 }
	 
	 
	 
	@RequestMapping(value = "/mfeedbackForm6", method = RequestMethod.GET)
	public String feedbackFormWalkInCustomerByMerchant6(ModelMap model,
			@RequestParam(required = false, value = "merchantId") String merchantId,
			@RequestParam(required = false, value = "orderId") String orderId, HttpServletRequest request) {
		System.out.println("Inside mfeedbackForm6");

		try {
			if (merchantId != null && !merchantId.isEmpty() && merchantId != "" && orderId != null && !orderId.isEmpty()
					&& orderId != "") {
				Merchant merchant = kritiqService.validateMerchant(merchantId);

				if (merchant != null) {
					Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
					String merchantKritiqExpiryStatus = kritiqService.checkMerchantExpiration(merchant1);
					model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
					OrderR order = kritiqService.getOrderDetailsByOrderPosId(orderId);
					boolean feedBackStatus = false;
					if (order == null) {
						List<FeedbackQuestionCategory> feedbackQuestionCategories = kritiqService
								.getFeedbackQuestionCategorys();

						List<CustomerFeedback> custFeedback = kritiqService.findByOrderPosID(orderId);
						model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
						model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
						model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
						model.addAttribute("orderId", orderId);

						model.addAttribute("CustomerFeedback", new CustomerFeedback());

						if (custFeedback != null && custFeedback.size() > 0) {
							feedBackStatus = true;

						} else {
							model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);

							model.addAttribute("CustomerFeedback", new CustomerFeedback());

						}
						model.addAttribute("feedBackStatus", feedBackStatus);

						return "feedbackFormWalkInCustomer6";

					} else {

						List<CustomerFeedback> custFeedback = kritiqService.findByOrderId(order.getId());
						CustomerFeedback customerFeedback = new CustomerFeedback();
						if (order.getCustomer() != null && order.getCustomer().getId() != null) {
							customerFeedback.setCustomer(order.getCustomer());
						}
						model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
						if (custFeedback.size() > 0) {
							feedBackStatus = true;
							model.addAttribute("feedBackStatus", feedBackStatus);
							model.addAttribute("CustomerFeedback", new CustomerFeedback());
							return "feedbackFormWalkInCustomer6";

						} else {
							List<FeedbackQuestionCategory> feedbackQuestionCategories = kritiqService
									.getFeedbackQuestionCategorys();
							System.out.println("today05-08-2017" + order.getCustomer().getId());
							String custId = EncryptionDecryptionUtil
									.encryption(String.valueOf(order.getCustomer().getId()));
							String orderid = EncryptionDecryptionUtil.encryption(String.valueOf(order.getId()));

							model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
							model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
							model.addAttribute("orderId", orderId);
							model.addAttribute("orderR", order);
							model.addAttribute("CustomerFeedback", new CustomerFeedback());
							model.addAttribute("feedBackStatus", feedBackStatus);
							return "redirect:feedbackForm?customerId=" + custId + "&orderId=" + orderid;

						}
					}

				} else {
					return "redirect:https://www.foodkonnekt.com";
				}

			} else {
				return "redirect:https://www.foodkonnekt.com";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:https://www.foodkonnekt.com";
		}

	} 
	
	
	
	@RequestMapping(value = "/ofeedbackForm7", method = RequestMethod.GET) 
	 public String feedbackOrderIdByMerchantNew7(ModelMap model,
			 @RequestParam("merchantId") String merchantId, HttpServletRequest request) {
		 try{
		 Merchant merchant =  kritiqService.validateMerchant(merchantId);
		 if(merchant!=null ){
		//System.out.println("vend id-"+merchant.getId());
		// List<Merchant> merchantList = kritiqService.getMerchantsByVendorId(vendor.getId());
		Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
		String merchantKritiqExpiryStatus=kritiqService.checkMerchantExpiration(merchant1);
		Vendor vendor = kritiqService.getVendorDetailsByVendorId(merchant1.getOwner().getId());
		//System.out.println( "pos id-->"+merchant1.getPosMerchantId());
		 //List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
		 String formType ="merchant";
		 //model.addAttribute("formType",formType);
		// model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
		 model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
		 model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
		 model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
		 model.addAttribute("merchantId", merchantId);
		 model.addAttribute("posId", vendor.getPos().getPosId());
	    	//model.addAttribute("orderR", orderR);
	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
			//model.addAttribute("feedBackStatus",feedBackStatus);
		 }else{
			 return "redirect:https://www.foodkonnekt.com";
		 }
		 } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return "redirect:https://www.foodkonnekt.com";
			}
		 
		 return "feedbackOrderId7"; 
	 }
	 
	 
	 
	@RequestMapping(value = "/mfeedbackForm7", method = RequestMethod.GET)
	public String feedbackFormWalkInCustomerByMerchant7(ModelMap model,
			@RequestParam(required = false, value = "merchantId") String merchantId,
			@RequestParam(required = false, value = "orderId") String orderId, HttpServletRequest request) {
		System.out.println("Inside mfeedbackForm6");

		try {
			if (merchantId != null && !merchantId.isEmpty() && merchantId != "" && orderId != null && !orderId.isEmpty()
					&& orderId != "") {
				Merchant merchant = kritiqService.validateMerchant(merchantId);

				if (merchant != null) {
					Merchant merchant1 = kritiqService.getMerchantDetailsByMerchantId(merchant.getId());
					String merchantKritiqExpiryStatus = kritiqService.checkMerchantExpiration(merchant1);
					model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
					OrderR order = kritiqService.getOrderDetailsByOrderPosId(orderId);
					boolean feedBackStatus = false;
					if (order == null) {
						List<FeedbackQuestionCategory> feedbackQuestionCategories = kritiqService
								.getFeedbackQuestionCategorys();

						List<CustomerFeedback> custFeedback = kritiqService.findByOrderPosID(orderId);
						model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
						model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
						model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
						model.addAttribute("orderId", orderId);

						model.addAttribute("CustomerFeedback", new CustomerFeedback());

						if (custFeedback != null && custFeedback.size() > 0) {
							feedBackStatus = true;

						} else {
							model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);

							model.addAttribute("CustomerFeedback", new CustomerFeedback());

						}
						model.addAttribute("feedBackStatus", feedBackStatus);

						return "feedbackFormWalkInCustomer7";

					} else {

						List<CustomerFeedback> custFeedback = kritiqService.findByOrderId(order.getId());
						CustomerFeedback customerFeedback = new CustomerFeedback();
						if (order.getCustomer() != null && order.getCustomer().getId() != null) {
							customerFeedback.setCustomer(order.getCustomer());
						}
						model.addAttribute("merchantLogo", merchant1.getMerchantLogo());
						if (custFeedback.size() > 0) {
							feedBackStatus = true;
							model.addAttribute("feedBackStatus", feedBackStatus);
							model.addAttribute("CustomerFeedback", new CustomerFeedback());
							return "feedbackFormWalkInCustomer7";

						} else {
							List<FeedbackQuestionCategory> feedbackQuestionCategories = kritiqService
									.getFeedbackQuestionCategorys();
							System.out.println("today05-08-2017" + order.getCustomer().getId());
							String custId = EncryptionDecryptionUtil
									.encryption(String.valueOf(order.getCustomer().getId()));
							String orderid = EncryptionDecryptionUtil.encryption(String.valueOf(order.getId()));

							model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
							model.addAttribute("posMerchantId", merchant1.getPosMerchantId());
							model.addAttribute("orderId", orderId);
							model.addAttribute("orderR", order);
							model.addAttribute("CustomerFeedback", new CustomerFeedback());
							model.addAttribute("feedBackStatus", feedBackStatus);
							return "redirect:feedbackForm?customerId=" + custId + "&orderId=" + orderid;

						}
					}

				} else {
					return "redirect:https://www.foodkonnekt.com";
				}

			} else {
				return "redirect:https://www.foodkonnekt.com";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:https://www.foodkonnekt.com";
		}

	} 
	
	
	 
	 
	 @RequestMapping(value = "/cfeedbackForm", method = RequestMethod.GET) 
	 public String cfeedbackForm(ModelMap model, @RequestParam(value = "m") String m, @RequestParam(value = "o") String o,@RequestParam(value = "c") String c,HttpServletRequest request) {
		 System.out.println("inside cfeedbackForm");
		 if(m!=null && !m.isEmpty() && m!= "" && o!=null && !o.isEmpty() && o!=""){
			 
		 
		 try{
		 
		 Merchant merchant =  kritiqService.getMerchantDetailsByPosMerchantId(m);
		
		 if(merchant!=null){
			 String merchantKritiqExpiryStatus=kritiqService.checkMerchantExpiration(merchant);
			 model.addAttribute("merchantKritiqExpiryStatus", merchantKritiqExpiryStatus);
			 OrderR order = kritiqService.getOrderDetailsByOrderPosId(o);
			 boolean feedBackStatus= false;
			 if(order==null ){
				 List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
				 boolean data =  kritiqService.verifyOrderFromClover(o,m);
				 if(data){
				List<CustomerFeedback> custFeedback = kritiqService.findByOrderPosID(o);
				 model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
				 model.addAttribute("posMerchantId", m);
				 model.addAttribute("orderId", o);
			    	//model.addAttribute("orderR", orderR);
			    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
			    	 
			    	 if(custFeedback!=null && custFeedback.size()>0)
			  		{
			  			feedBackStatus= true;
			  			
			  			
			  		}else{
			 			model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
			 	    	//model.addAttribute("orderR", orderR);
			 	    	model.addAttribute("CustomerFeedback", new CustomerFeedback());
			 			
			  		}
			  			model.addAttribute("feedBackStatus",feedBackStatus);

			    	
			    	
			    	return "feedbackFormWalkInCustomer";
			 }else{
				 return "redirect:https://www.foodkonnekt.com"; 
			 }
		 
		 }else{
			 
			 List<CustomerFeedback> custFeedback = kritiqService.findByOrderId(order.getId());
			 CustomerFeedback customerFeedback=new CustomerFeedback();
			 if(order.getCustomer()!=null && order.getCustomer().getId()!=null){
				 customerFeedback.setCustomer(order.getCustomer());
				}
				if(custFeedback.size()>0)
				{
					feedBackStatus= true;
		  			model.addAttribute("feedBackStatus",feedBackStatus);
		  			model.addAttribute("CustomerFeedback", new CustomerFeedback());
		  			 return "feedbackFormWalkInCustomer";
		  			
				}else{
					 List<FeedbackQuestionCategory> feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
					 System.out.println("today05-08-2017"+order.getCustomer().getId());
					 String custId = EncryptionDecryptionUtil.encryption(String.valueOf(order.getCustomer().getId()));
			  			String orderid = EncryptionDecryptionUtil.encryption(String.valueOf(order.getId()));
			  			
			  			model.addAttribute("FeedbackQuestionCategories", feedbackQuestionCategories);
					 model.addAttribute("posMerchantId", m);
					 model.addAttribute("orderId", o);
					 model.addAttribute("orderR", order);
					 model.addAttribute("CustomerFeedback", new CustomerFeedback());
					 model.addAttribute("feedBackStatus",feedBackStatus);
					 return "redirect:feedbackForm?customerId="+custId+"&orderId="+orderid;
					 
				}
		 }
		 
		 }else{
			 return "redirect:https://www.foodkonnekt.com"; 
		 }
			//model.addAttribute("feedBackStatus",feedBackStatus);
		 } catch (Exception e) {
				e.printStackTrace();
				return "redirect:https://www.foodkonnekt.com";
			}
		 }else{
			 return "redirect:https://www.foodkonnekt.com"; 
		 }
		 
	 }
	 
	 @RequestMapping(value = "/verifyOrderFromClover", method = RequestMethod.GET)
	    public @ResponseBody
	    Boolean verifyOrderFromClover(String orderId, String merchantId, HttpServletResponse response) {
	    	System.out.println("inside /verifyOrderFromClover"); 
	    	try{
	    		 boolean data =  kritiqService.verifyOrderFromClover(orderId,merchantId);
	 	     	
	 	     	return data;
	    		
	    	}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	     	
	    }
	 
	/* @RequestMapping(value = "/saveWalkInCustomerFeedback", method = RequestMethod.POST)
	    public String saveWalkInCustomerFeedback(@ModelAttribute("CustomerFeedback") CustomerFeedback customerFeedback,
	    		@RequestParam(value = "merchantPosId") String merchantPosId,
	    		ModelMap model, HttpServletRequest request) {
			Customer customer = new Customer();
			
			Merchant merchantDetail =kritiqService.getMerchantDetailsByPosMerchantId(merchantPosId);
			
			OrderR order = kritiqService.getOrderDetailsByOrderPosId(customerFeedback.getOrderR().getOrderPosId());
			if(order!=null)
			{
				customerFeedback.setOrderR(order);
				List<CustomerFeedback> custFeedback = kritiqService.findByOrderPosID(order.getOrderPosId());
				System.out.println("custFeedback-"+custFeedback.size());
				 boolean feedBackStatus= false; 
				if(custFeedback!=null && custFeedback.size()>0)
			  		{
			 				System.out.println(custFeedback.get(0));
			  			feedBackStatus= true;
			  			
			  			model.addAttribute("feedBackStatus",feedBackStatus);
			  			 return "feedbackFormWalkInCustomer";
			  		}else{
			  			System.out.println(customerFeedback.getCustomer().getId());
			  			kritiqService.saveCustomerFeedback(customerFeedback);
						// return "feedbackResponse";
			  			 return "feedbackshare";
			  		}
				
		  
			}
			//customerFeedback.getCustomer().setMerchantt(merchantDetail);
			
			customer =customerFeedback.getCustomer();
			customer.setMerchantt(merchantDetail);
			 Customer customer1 =kritiqService.saveWalkInCustomerDetail(customer);
			 
			 System.out.println(customer1.getBirthDate()+" an-"+customer1.getAnniversaryDate());
			 OrderR orderR = customerFeedback.getOrderR();
			 orderR.setCustomer(customer1);
			  orderR = kritiqService.saveOrderDetails(orderR);
			System.out.println("orderRc id-"+orderR.getId());
		
			
			kritiqService.saveCustomerFeedback(customerFeedback);
			 System.out.println("Done");
	   //return "feedbackResponse";
			 return "feedbackshare";
	    }*/

	 
	 @RequestMapping(value = "/saveWalkInCustomerFeedbackByAjax", method = RequestMethod.POST)
	 @ResponseBody
	    public String saveWalkInCustomerFeedback(@ModelAttribute("CustomerFeedback") CustomerFeedback customerFeedback,
	    		@RequestParam(value = "merchantPosId") String merchantPosId,
	    		ModelMap model, HttpServletRequest request) {
			Customer customer = new Customer();
			try{
			Merchant merchantDetail =kritiqService.getMerchantDetailsByPosMerchantId(merchantPosId);
			
			OrderR order = kritiqService.getOrderDetailsByOrderPosId(customerFeedback.getOrderR().getOrderPosId());
			if(order!=null)
			{
				customerFeedback.setOrderR(order);
				List<CustomerFeedback> custFeedback = kritiqService.findByOrderPosID(order.getOrderPosId());
				System.out.println("custFeedback-"+custFeedback.size());
				 boolean feedBackStatus= false; 
				if(custFeedback!=null && custFeedback.size()>0)
			  		{
			 				System.out.println(custFeedback.get(0));
			  			feedBackStatus= true;
			  			
			  			model.addAttribute("feedBackStatus",feedBackStatus);
			  			 return "feedbackFormWalkInCustomer";
			  		}else{
			  			System.out.println(customerFeedback.getCustomer().getId());
			  			kritiqService.saveCustomerFeedback(customerFeedback);
						// return "feedbackResponse";
			  			 return IConstant.RESPONSE_SUCCESS_MESSAGE;
			  		}
				
		  
			}
			//customerFeedback.getCustomer().setMerchantt(merchantDetail);
			
			customer =customerFeedback.getCustomer();
			customer.setMerchantt(merchantDetail);
			 Customer customer1 =kritiqService.saveWalkInCustomerDetail(customer);
			 
			 System.out.println(customer1.getBirthDate()+" an-"+customer1.getAnniversaryDate());
			 OrderR orderR = customerFeedback.getOrderR();
			 orderR.setCustomer(customer1);
			  orderR = kritiqService.saveOrderDetails(orderR);
			System.out.println("orderRc id-"+orderR.getId());
		
			
			kritiqService.saveCustomerFeedback(customerFeedback);
			 System.out.println("Done");
	   //return "feedbackResponse";
			 return IConstant.RESPONSE_SUCCESS_MESSAGE;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return IConstant.RESPONSE_NO_DATA_MESSAGE;
			}
	    }
	 
	 
	 @RequestMapping(value = "/customerfeedback", method = RequestMethod.GET) 
	 public String customerFeedbackResult(ModelMap model,
			 @RequestParam("customerFeedbackId") String customerFeedbackId, HttpServletRequest request) {
		 if(customerFeedbackId!= null && customerFeedbackId!="" && !customerFeedbackId.isEmpty()){
			 Map<Integer, Integer> queAnswer = new HashMap<Integer, Integer>();
			 List<FeedbackQuestionCategory> feedbackQuestionCategories = new ArrayList<FeedbackQuestionCategory>();
			// Stack<Integer> categoryId = null;
		 Integer custFeedbackId = Integer.parseInt(EncryptionDecryptionUtil.decryption(customerFeedbackId));
		 List<CustomerFeedbackAnswer> customerFeedbackAnswers = kritiqService.getCustomerFeedbackResult(custFeedbackId);
		 if(customerFeedbackAnswers!= null && !customerFeedbackAnswers.isEmpty()){
				for(CustomerFeedbackAnswer listCustomerFeedback :customerFeedbackAnswers){
					Integer key =listCustomerFeedback.getFeedbackQuestion().getId();
					Integer value = listCustomerFeedback.getAnswer();
					queAnswer.put(key, value);
					FeedbackQuestionCategory feedbackQuestionCategory = kritiqService.getFeedbackQuestionCategory(listCustomerFeedback.getFeedbackQuestion().getFeedbackQuestionCategory().getId());
					boolean categoryAlreadyAdded=false;
					for(FeedbackQuestionCategory questionCategory:feedbackQuestionCategories){
						if(feedbackQuestionCategory.getId()==questionCategory.getId()){
							categoryAlreadyAdded=true;
							feedbackQuestionCategory=questionCategory;
							break;
						}
					
					}
					if(!categoryAlreadyAdded){
						List<FeedbackQuestion> feedbackQuestions= new ArrayList<FeedbackQuestion>();
						feedbackQuestions.add(listCustomerFeedback.getFeedbackQuestion());
						feedbackQuestionCategory.setFeedbackQuestions(feedbackQuestions);
					feedbackQuestionCategories.add(feedbackQuestionCategory);
					}else{
						List<FeedbackQuestion> feedbackQuestions=feedbackQuestionCategory.getFeedbackQuestions();
						feedbackQuestions.add(listCustomerFeedback.getFeedbackQuestion());
						feedbackQuestionCategory.setFeedbackQuestions(feedbackQuestions);
					}
					
			}
				CustomerFeedback customerFeedback=null;
				customerFeedback=kritiqService.getCustomerFeedback(custFeedbackId);
				if(customerFeedback==null){
					customerFeedback=	new CustomerFeedback();
				}else{
					customerFeedback.setCustomerFeedbackAnswers(customerFeedbackAnswers);
					if(customerFeedback!=null){
						Customer customer=customerFeedback.getCustomer();
						String anniversary=customer.getAnniversaryDate();
						if(anniversary!=null){
							String[] monthDate=anniversary.split(",");
							if(monthDate!=null && monthDate.length>1){
								String month=monthDate[0];
								String date=monthDate[1];
								if(month!=null &&date!=null){
									customerFeedback.setAnniversaryMonth(month);
									customerFeedback.setAnniversaryDate(date);
								}
							}
						}else{
							customerFeedback.setAnniversaryMonth("Pick a Month");
							customerFeedback.setAnniversaryDate("Pick a Date");
						}
						String birthDay=customer.getBirthDate();
						if(birthDay!=null){
							String[] monthDate=birthDay.split(",");
							if(monthDate!=null && monthDate.length>1){
								String month=monthDate[0];
								String date=monthDate[1];
								if(month!=null &&date!=null){
									customerFeedback.setBdayMonth(month);
									customerFeedback.setBdayDate(date);
								}
							}
						}else{
							customerFeedback.setBdayMonth("Pick a Month");
							customerFeedback.setBdayDate("Pick a Month");
							
						}
						}
				}
				
				
				model.addAttribute("CustomerFeedback",customerFeedback);
				model.addAttribute("queAnswer", queAnswer);
				//feedbackQuestionCategories=kritiqService.getFeedbackQuestionCategorys();
				model.addAttribute("FeedbackQuestionCategories",feedbackQuestionCategories);
				
		 }else{
			 return "redirect:https://www.foodkonnekt.com"; 
		 }
		 return "displayCustomerFeedback";
	 }else{
		 return "redirect:https://www.foodkonnekt.com"; 
	 }
		
	 }
	 
}
