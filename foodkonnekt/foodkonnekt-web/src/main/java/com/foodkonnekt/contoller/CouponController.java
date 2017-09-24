
package com.foodkonnekt.contoller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.Vouchar;
import com.foodkonnekt.service.VoucharService;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class CouponController {

    @Autowired
    private VoucharService voucharService;

    /**
     * Check coupon validity
     * 
     * @param request
     * @param couponCode
     * @return boolean
     */
    @RequestMapping(value = "/checkCouponValidity", method = { RequestMethod.POST })
    public @ResponseBody String checkCoupons(@RequestBody String orderJson,  HttpServletRequest request,
                    HttpServletResponse response) {
    	try {
    		
    		System.out.println("s1- "+orderJson);
    		Map<String,Object> appliedDiscounts= new HashMap<String, Object>();
    		String voucherJson ="";
    		String cart[]=orderJson.split("#@");
    		System.out.println("cart.length--->>"+cart.length);
    		Boolean flag = false;
    		if(cart!=null && cart.length==5){
    	String kouponC = cart[3];
    	
    	
    	String voucherCode =cart[2];//orderJson.substring(orderJson.lastIndexOf("*x!")+3, orderJson.lastIndexOf("}")-1);
    	System.out.println("koupon name: "+voucherCode);
    	String cartJson = cart[1];//orderJson.substring(orderJson.indexOf(":")+2, orderJson.lastIndexOf("*x!"));
    	//cartJson = " {\"cartJson\":[ "+cartJson+"]}";
    	String itemJson = cart[0];//orderJson.substring(orderJson.indexOf(":")+2, orderJson.lastIndexOf("*x!"));
    	//kouponJson = " {\"orderJson\":[ "+kouponJson+"]}";
    	System.out.println("s1- "+orderJson);
    	String callingType = cart[4];
    	
    	
    	
            HttpSession session = request.getSession();
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            Customer customer=null;
            if(session.getAttribute("customer")!=null)
             customer = (Customer) session.getAttribute("customer");
            
            Integer couponCount = 0;
            String kouponCode = null;
            JSONObject kouponCountJsonObject = new JSONObject(kouponC);
    		// cartJsonObject=cartJsonObject.getAsJsonObject(cartJson);
    		 if(kouponCountJsonObject!=null &&kouponCountJsonObject.has("kouponCount")){
    			String kouponCount=kouponCountJsonObject.getString("kouponCount");
    			 if(kouponCount!=null && !kouponCount.equals("") && !kouponCount.isEmpty())
    			 couponCount=Integer.parseInt(kouponCount);
    			 
    		 }
    		 
    		 
    		 JSONObject callingTypeJsonObject = new JSONObject(callingType);
      		// cartJsonObject=cartJsonObject.getAsJsonObject(cartJson);
      		 if(callingTypeJsonObject!=null &&callingTypeJsonObject.has("callingType")){
      			callingType=callingTypeJsonObject.getString("callingType");
      		}
    		 
    		 JSONObject voucherCodeJsonObject = new JSONObject(voucherCode);
     		// cartJsonObject=cartJsonObject.getAsJsonObject(cartJson);
     		 if(voucherCodeJsonObject!=null &&voucherCodeJsonObject.has("couponCode")){
     			kouponCode=voucherCodeJsonObject.getString("couponCode");
     		}        
            
            
            if (merchant != null) {
                //return voucharService.checkCouponVaidity(itemJson,cartJson, voucherCode, merchant.getId());
            	if(callingType!=null && callingType.equalsIgnoreCase("recall")){
            		String coupons[]=kouponCode.split(",");
            		for(String coupon:coupons){
            			if(coupon==null || coupon.isEmpty() || coupon.equals("")){
            				continue;
            			}
            			voucherCode = " {\"couponCode\":"+coupon+"}";
            		if(session.getAttribute("appliedDiscounts")!=null && couponCount>0){
                		appliedDiscounts=(Map<String,Object>)session.getAttribute("appliedDiscounts");
            		}else{
            			appliedDiscounts=null;
            		}
                	
                	System.out.println("!!!!!!!!!!!!!!!!!!!"+appliedDiscounts);
                	double orderLeveldiscount=0.0;
                	List<String> orderLevelDiscounts=new ArrayList<String>();
                	Map<String,Object> appliedData= new HashMap<String, Object>();
                   	List<Map<String,Object>> discountMapList = new ArrayList<Map<String,Object>>();
                	if(couponCount > 0){
                
               	appliedData = (Map<String,Object>)appliedDiscounts.get("DATA");
               	discountMapList = (List<Map<String,Object>>)appliedData.get("discountList");
               	for(Map<String,Object> discountMap : discountMapList){
               		String vCode = (String)discountMap.get("voucherCode");
               		String inventoryLevel = (String)discountMap.get("inventoryLevel");
    		          double couponDiscount = (Double)discountMap.get("discount");
    		           	if(inventoryLevel!=null && inventoryLevel.equals("order")){
    		           	orderLeveldiscount=orderLeveldiscount+couponDiscount;
    		           	orderLevelDiscounts.add(vCode);
    		           	}
               			if(kouponCode.equals(vCode)){
               				flag = true;
               				break;
               			}
               	}}
                	if(!flag){
                	
                	
                	appliedDiscounts = voucharService.checkCouponVaidity(itemJson,cartJson, voucherCode, merchant.getId(),kouponC,appliedDiscounts, orderLeveldiscount,customer);
                	
                	if(orderLeveldiscount>0){
                		for(String orderLevelDiscount : orderLevelDiscounts){
                       		//String vCode = (String)discountMap.get("voucherCode");
                       		 voucherCode = " {\"couponCode\":"+orderLevelDiscount+"}";
                       		/*String inventoryLevel = (String)discountMap.get("inventoryLevel");
            		           	if(inventoryLevel!=null && inventoryLevel.equals("order")){*/
            		           		appliedDiscounts = voucharService.checkCouponVaidity(itemJson,cartJson, voucherCode, merchant.getId(),kouponC,appliedDiscounts, orderLeveldiscount,customer);
            		           	/*}*/
                       			
                       	}	
                	}
                	
                	}else{
                		
                		appliedDiscounts.put("responsCode", IConstant.RESPONSE_DUPLICATE_KOUPON);
                	}
                	
                	if(appliedDiscounts!=null){
                		String responsCode=(String)appliedDiscounts.get("responsCode");
                		if(responsCode!=null && responsCode.equals("200")){
                	session.setAttribute("appliedDiscounts", appliedDiscounts);
                		Gson gson = new Gson();
                    	voucherJson = gson.toJson(appliedDiscounts);
                    	couponCount++;
                    	kouponC= "{\"kouponCount\":\""+couponCount+"\"}";
                		}
                	}

                	
            	}
                	
                	
                	/*JSONObject jsonObj = new JSONObject(voucherJson);
       	    	 String responseCode= jsonObj.getString("responsCode");
                	System.out.println(responseCode);*/
            		
                	return voucherJson;
            		
            	}else{
            	
            	if(session.getAttribute("appliedDiscounts")!=null)
            		appliedDiscounts=(Map<String,Object>)session.getAttribute("appliedDiscounts");
            	
            	System.out.println("!!!!!!!!!!!!!!!!!!!"+appliedDiscounts);
            	double orderLeveldiscount=0.0;
            	List<String> orderLevelDiscounts=new ArrayList<String>();
            	Map<String,Object> appliedData= new HashMap<String, Object>();
               	List<Map<String,Object>> discountMapList = new ArrayList<Map<String,Object>>();
            	if(couponCount > 0){
            
           	appliedData = (Map<String,Object>)appliedDiscounts.get("DATA");
           	discountMapList = (List<Map<String,Object>>)appliedData.get("discountList");
           	for(Map<String,Object> discountMap : discountMapList){
           		String vCode = (String)discountMap.get("voucherCode");
           		String inventoryLevel = (String)discountMap.get("inventoryLevel");
		          double couponDiscount = (Double)discountMap.get("discount");
		           	if(inventoryLevel!=null && inventoryLevel.equals("order")){
		           	orderLeveldiscount=orderLeveldiscount+couponDiscount;
		           	orderLevelDiscounts.add(vCode);
		           	}
           			if(kouponCode.equals(vCode)){
           				flag = true;
           				break;
           			}
           	}}
            	if(!flag){
            	
            	
            	appliedDiscounts = voucharService.checkCouponVaidity(itemJson,cartJson, voucherCode, merchant.getId(),kouponC,appliedDiscounts, orderLeveldiscount,customer);
            	
            	if(orderLeveldiscount>0){
            		for(String orderLevelDiscount : orderLevelDiscounts){
                   		//String vCode = (String)discountMap.get("voucherCode");
                   		 voucherCode = " {\"couponCode\":"+orderLevelDiscount+"}";
                   		/*String inventoryLevel = (String)discountMap.get("inventoryLevel");
        		           	if(inventoryLevel!=null && inventoryLevel.equals("order")){*/
        		           		appliedDiscounts = voucharService.checkCouponVaidity(itemJson,cartJson, voucherCode, merchant.getId(),kouponC,appliedDiscounts, orderLeveldiscount,customer);
        		           	/*}*/
                   			
                   	}	
            	}
            	
            	}else{
            		
            		appliedDiscounts.put("responsCode", IConstant.RESPONSE_DUPLICATE_KOUPON);
            	}
            	
            	System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+appliedDiscounts);
            	Gson gson = new Gson();
            	voucherJson = gson.toJson(appliedDiscounts);
            	session.setAttribute("appliedDiscounts", appliedDiscounts);
            	/*JSONObject jsonObj = new JSONObject(voucherJson);
   	    	 String responseCode= jsonObj.getString("responsCode");
            	System.out.println(responseCode);*/
            	return voucherJson;
            	
            	
            	}
            	
            	
            	
            } else {
                try {
                    response.sendRedirect("sessionTimeOut");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    		}
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return null;
    }
}
