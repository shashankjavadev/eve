package com.foodkonnekt.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemDto;
import com.foodkonnekt.model.ItemTax;
import com.foodkonnekt.model.Koupons;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.OpeningClosingDay;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.OrderDiscount;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.model.Vouchar;
import com.foodkonnekt.repository.ConvenienceFeeRepository;
import com.foodkonnekt.repository.ItemTaxRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.OpeningClosingDayRepository;
import com.foodkonnekt.repository.OpeningClosingTimeRepository;
import com.foodkonnekt.repository.OrderDiscountRepository;
import com.foodkonnekt.repository.TaxRateRepository;
import com.foodkonnekt.repository.VendorRepository;
import com.foodkonnekt.repository.VoucharRepository;
import com.foodkonnekt.service.VoucharService;
import com.foodkonnekt.util.CommonUtil;
import com.foodkonnekt.util.CouponUrlUtil;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.IConstant;
import com.google.gson.Gson;

@Service
public class VoucharServiceImpl implements VoucharService {

    @Autowired
    private VoucharRepository voucharRepository;

    @Autowired
    private OpeningClosingDayRepository openingClosingDayRepository;

    @Autowired
    private OpeningClosingTimeRepository openingClosingTimeRepository;
    
    @Autowired
    private MerchantRepository merchantRepository;
    
    @Autowired
    private VendorRepository vendorRepository;
    
    @Autowired
    private TaxRateRepository taxRateRepository;

    @Autowired
    private ItemTaxRepository itemTaxRepository;
    
    @Autowired
    private ItemmRepository itemmRepository;
    
    @Autowired
    private ConvenienceFeeRepository convenienceFeeRepository;
    
    @Autowired
    private OrderDiscountRepository orderDiscountRepository;
    

    /**
     * Save vouchar into database
     */
    public void save(Vouchar vouchar) {
        vouchar.setStartTime(CommonUtil.getStartTime(vouchar.getStartTime(), vouchar.getValidity()));
        vouchar.setEndTime(CommonUtil.getStartTime(vouchar.getEndTime(), vouchar.getValidity()));
        vouchar.setDate(DateUtil.convertStringToDate(vouchar.getStrDate()));
        vouchar.setCreatedOn(DateUtil.currentDate());
        voucharRepository.save(vouchar);
    }

    /**
     * Find by merchantId
     */
    public List<Vouchar> findByMerchantId(Integer merchantId) {
        return voucharRepository.findByMerchantId(merchantId);
    }

    /**
     * Find by Id
     */
    public Vouchar findById(int id) {
        // TODO Auto-generated method stub
        Vouchar vouchar = voucharRepository.findOne(id);
        if (vouchar.getDate() != null) {
            vouchar.setStrDate(DateUtil.getYYYYMMDD(vouchar.getDate()));
        }

        if (vouchar.getFromDate() != null) {
            vouchar.setFrmDate(DateUtil.getYYYYMMDD(vouchar.getFromDate()));
        }

        if (vouchar.getEndDate() != null) {
            vouchar.setToDate(DateUtil.getYYYYMMDD(vouchar.getEndDate()));
        }
        vouchar.setStartTime(DateUtil.convert12hoursTo24HourseFormate(vouchar.getStartTime()));
        vouchar.setEndTime(DateUtil.convert12hoursTo24HourseFormate(vouchar.getEndTime()));
        return vouchar;
    }

    /**
     * Check coupon validity
     */
  //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ 
    //public String checkCouponVaidity(String itemJson,String cartJson,String voucherCode, Integer merchantId) {
    public Map<String, Object> checkCouponVaidity(String itemJson,String cartJson,String voucherCode, Integer merchantId,String kouponCount,Map<String,Object> appliedDiscounts,double orderLeveldiscount,Customer customer) {
    	Map<String,Object> responseKouponMap= new HashMap<String, Object>();
	    String	responseKoupon =null;
	    Boolean flag = false;
	    int couponCount=0;
	    String responseJson = null;
	    Map<String,Object> response= new HashMap<String, Object>();
	    	try {
	    		 
	    		 
	    		 JSONObject voucherCodeJsonObject = new JSONObject(voucherCode);
	    		// cartJsonObject=cartJsonObject.getAsJsonObject(cartJson);
	    		 if(voucherCodeJsonObject.has("couponCode"))
	    			 voucherCode=voucherCodeJsonObject.getString("couponCode");
	    		 
	    		 JSONObject kouponCountJsonObject = new JSONObject(kouponCount);
		    		// cartJsonObject=cartJsonObject.getAsJsonObject(cartJson);
		    		 if(kouponCountJsonObject!=null &&kouponCountJsonObject.has("kouponCount")){
		    			 kouponCount=kouponCountJsonObject.getString("kouponCount");
		    			 if(kouponCount!=null && !kouponCount.equals("") && !kouponCount.isEmpty())
		    			 couponCount=Integer.parseInt(kouponCount);
		    			 
		    		 }
	    		 
	    		
	            
	    	Merchant merchant = merchantRepository.findById(merchantId);
			System.out.println(merchant.getTimeZone().getHourDifference());
			System.out.println(merchant.getTimeZone().getMinutDifference());
			System.out.println("Allow multiple koupon-->"+merchant.getAllowMultipleKoupon());
			Vendor vendor=null;
			if(merchant.getOwner()!=null && merchant.getOwner().getId()!=null){
	    	 vendor = vendorRepository.findOne(merchant.getOwner().getId());
			}
	    	SimpleDateFormat sdf1 = new SimpleDateFormat(IConstant.YYYYMMDD);
	    	SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
	    	Calendar calobjs = Calendar.getInstance();
	    	if(merchant.getTimeZone()!=null)
	    	 {
	    	 calobjs.add(Calendar.HOUR, merchant.getTimeZone().getHourDifference());
	    	 calobjs.add(Calendar.MINUTE, merchant.getTimeZone().getMinutDifference());
	    	 }
	    	 Date d = calobjs.getTime();
	    	 System.out.println(d);
	    	 String time = sdf2.format(d);
	    	 String date = sdf1.format(d);

	    	 Merchant merchant2 = new Merchant();
	    	merchant2.setMerchantUid(merchant.getMerchantUid());
	    	merchant2.setFutureDaysAhead(null);
	    	Vendor vendor2 = new Vendor();
	    	vendor2.setVendorUid(vendor.getVendorUid());
	    	Koupons koupons = new Koupons();
	    	koupons.setMerchant(merchant2);
	    	koupons.setVendor(vendor2);
	    	koupons.setName(voucherCode);
	    	koupons.getMerchant().setMerchantUid(merchant.getMerchantUid());
	    	koupons.getVendor().setVendorUid(vendor.getVendorUid());
	    	koupons.setCurrentTime(time);
	    	koupons.setCurrentDate(date);
	    	String url = "http://localhost:8080/koupons/validateKoupon";
	    	 Gson gson = new Gson();
	    	 String voucherJson = gson.toJson(koupons);
	    	 System.out.println("voucherJson-"+voucherJson);
	    	 responseKoupon = CouponUrlUtil.getCouponData(url, voucherJson);
	    	 System.out.println("################## "+responseKoupon);
	    	 JSONObject jsonObj = new JSONObject(responseKoupon);
	    	 String responseCode= jsonObj.getString("response");
	    	 
	    	 
	    	 String eventName=null;
	    	 Boolean isDeliveryFeeKoupon = false;
	    	
	 		boolean isUseabilitySingle=false;
	 		boolean canApplyCoupon=true;
	 		JSONObject responseDataObj= jsonObj.getJSONObject("DATA");
 	 		System.out.println("responseDataObj-"+responseDataObj);
 	 		
 	 		if(responseCode.equals(IConstant.RESPONSE_SUCCESS_MESSAGE) && responseDataObj!=null && responseDataObj.toString().contains("isUsabilitySingle")){
 	 			String couponUID=null;
	 	 		if(!responseDataObj.get("kouponCode").equals(null) && !responseDataObj.get("kouponCode").equals("")){
					couponUID = (String)responseDataObj.get("kouponCode");
				}
 	 			isUseabilitySingle=responseDataObj.getBoolean("isUsabilitySingle");
 	 			if(isUseabilitySingle && customer!=null && customer.getId()!=null && couponUID!=null){
 	 				List<OrderDiscount> orderDiscounts= orderDiscountRepository.findByCustomerIdAndCouponCode(customer.getId(),couponUID);
 	 				if(orderDiscounts!=null && !orderDiscounts.isEmpty() && orderDiscounts.size()>0){
 	 					canApplyCoupon=false;
 	 				}
 	 			}
 	 		}
	 		if(responseCode.equals(IConstant.RESPONSE_SUCCESS_MESSAGE) && canApplyCoupon){
	 			 
	 	 		
	 			if(responseDataObj!=null && responseDataObj.toString().contains("eventCategory")){
	 	 			JSONObject eventCategoryObject = responseDataObj.getJSONObject("eventCategory");
	 	 			if(eventCategoryObject!=null && eventCategoryObject.has("eventName")){
	 	 				eventName=eventCategoryObject.getString("eventName");
	 	 				System.out.println("eventName-"+eventName);
	 	 				if(eventName.equals("freeDeliveryFee")){
	 	 					isDeliveryFeeKoupon=true;
	 	 					response.put("DATA",isDeliveryFeeKoupon);
	 	 		        	response.put("responsCode","300");
	 	 		        	//responseJson = gson.toJson(response);
	 	 		        	return response;
	 	 		        	//return responseJson;
	 	 				}else{
	 	 					//responseKoupon= applyCoupon(responseKoupon,itemJson,cartJson,merchant, voucherCode);
	 	 					/*if(appliedDiscounts == null || appliedDiscounts.isEmpty()  || appliedDiscounts.equals("")  )
	 	 					{
	 	 						appliedDiscounts = null;
	 	 					}*/
	 	 					/*double orderLeveldiscount=0.0;
	 	 					if(couponCount > 0){
	 	 						
	 	 			            Map<String,Object> appliedData= new HashMap<String, Object>();
	 	 			           	List<Map<String,Object>> discountMapList = new ArrayList<Map<String,Object>>();
	 	 			           	appliedData = (Map<String,Object>)appliedDiscounts.get("DATA");
	 	 			           	discountMapList = (List<Map<String,Object>>)appliedData.get("discountList");
	 	 			           	for(Map<String,Object> discountMap : discountMapList){
	 	 			           		String vCode = (String)discountMap.get("voucherCode");
	 	 			           	String inventoryLevel = (String)discountMap.get("inventoryLevel");
	 	 			          double couponDiscount = (Double)discountMap.get("discount");
	 	 			           	if(inventoryLevel!=null && inventoryLevel.equals("order")){
	 	 			           	orderLeveldiscount=orderLeveldiscount+couponDiscount;
	 	 			           	}
	 	 			           			
	 	 			           	}}*/
	 	 					responseKouponMap= applyCoupon(responseKoupon,itemJson,cartJson,merchant, voucherCode,couponCount,appliedDiscounts,orderLeveldiscount);
	 	 					responseKouponMap.put("allowMultipleKoupon", merchant.getAllowMultipleKoupon());
	 	 				}
	 	 			}
	 	 		}
	 			
			}else if(!responseCode.equals(IConstant.RESPONSE_SUCCESS_MESSAGE)){
			if(responseCode.equals(IConstant.RESPONSE_NOT_EXIST_KOUPON)){		
	    		//response.put("DATA", IConstant.MESSAGE_NOT_EXIST_KOUPON);
				response.put("DATA", appliedDiscounts.get("DATA"));
	    		response.put("responsMessage", IConstant.MESSAGE_NOT_EXIST_KOUPON);
	        	response.put("responsCode", IConstant.RESPONSE_INVALID_KOUPON);		
	        	  responseJson = gson.toJson(response);
	        	 flag =true;
			}		
			else if(responseCode.equals(IConstant.RESPONSE_EXPIRED_KOUPON)){
				System.out.println("in side---"+responseCode);
				//response.put("DATA", IConstant.MESSAGE_EXPIRED_KOUPON);
				response.put("DATA", appliedDiscounts.get("DATA"));
				response.put("responsMessage", IConstant.MESSAGE_EXPIRED_KOUPON);
	        	response.put("responsCode", IConstant.RESPONSE_INVALID_KOUPON);
	        	 responseJson = gson.toJson(response);
	        	flag =true;
			}
			else if(responseCode.equals(IConstant.RESPONSE_INVALID_KOUPON)){
				//response.put("DATA", IConstant.MESSAGE_INVALID_KOUPON);
				response.put("DATA", appliedDiscounts.get("DATA"));
				response.put("responsMessage", IConstant.MESSAGE_INVALID_KOUPON);
	        	response.put("responsCode",IConstant.RESPONSE_INVALID_KOUPON);
	        	 responseJson = gson.toJson(response);
	        	flag =true;
			}
			else if(responseCode.equals(IConstant.RESPONSE_RECURRING_KOUPON)){
				//response.put("DATA", IConstant.MESSAGE_RECURRING_KOUPON);
				response.put("DATA", appliedDiscounts.get("DATA"));
				response.put("responsMessage", IConstant.MESSAGE_RECURRING_KOUPON);
	        	response.put("responsCode",IConstant.RESPONSE_INVALID_KOUPON);
	        	 responseJson = gson.toJson(response);
	        	flag =true;
			}
		}else if(!canApplyCoupon){
			if(appliedDiscounts!=null)
    		response.put("DATA", appliedDiscounts.get("DATA"));
			response.put("responsMessage", IConstant.MESSAGE_ALREADY_USED_KOUPON);
        	response.put("responsCode",IConstant.RESPONSE_INVALID_KOUPON);
        	 responseJson = gson.toJson(response);
        	flag =true;
    	}
	 		
	    	
	    	 
	    	 
	    	//System.out.println("discount is :~~"+responseKoupon.;
	    	
	    	
	    	
	    	 } catch (Exception e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	    	if(flag)
	    	{
	    		//return responseJson;
	    		return response;
	    	}else{
	    	 //return responseKoupon;
	    		return responseKouponMap;
	    	}
	    	//return Double.parseDouble(responseKoupon);
		}
    
    
    	//String applyCoupon(String couponResponse,String itemJson,String cartJson,Merchant merchant,String voucherCode){
    Map<String, Object> applyCoupon(String responseKoupon,String itemJson,String cartJson,Merchant merchant,String voucherCode,int kouponCount,Map<String,Object> appliedDiscounts,double orderLeveldiscount){		
   
    	String discountType=null;
    	String eventName=null;
    	Integer eventId=null;
    	Double discount=null;
    	String personalLevel=null;
    	String voucherJson =null;
    	String limit = null;
    	String couponUID = null;
    	Boolean isUsabilitySingle = false;
    	Boolean isLeastPrice = false;
    	String inventoryLevel="order";
    	Map<String,Object> voucherResponse= new HashMap<String, Object>();
    	//Boolean isDeliveryFeeKoupon = false;
    	
    	JSONObject jsonObj = new JSONObject(responseKoupon);
		String responseCode= jsonObj.getString("response");
		
		//String responseData= jsonObj.getString("DATA");
		JSONObject responseDataObj= jsonObj.getJSONObject("DATA");
		System.out.println("responseDataObj-"+responseDataObj);
		System.out.println(responseCode);
		if(responseCode.equals(IConstant.RESPONSE_SUCCESS_MESSAGE)){
		if(responseDataObj!=null && responseDataObj.toString().contains("invenotryLevel")){
			JSONObject invenotryLevelObject = responseDataObj.getJSONObject("invenotryLevel");
			
			JSONObject eventCategoryObject = responseDataObj.getJSONObject("eventCategory");
			JSONObject personalLevelObject = responseDataObj.getJSONObject("personalLevel");
			
			
			
			if(eventCategoryObject!=null && eventCategoryObject.has("eventName")){
				eventName=eventCategoryObject.getString("eventName");
				eventId=eventCategoryObject.getInt("id");
				System.out.println("eventName-"+eventName);
				if(!eventName.equals("buyOneGetOne"))
				{
					JSONObject discountLevelObject = responseDataObj.getJSONObject("discountLevel");
					if(discountLevelObject!=null && discountLevelObject.has("discountType")){
						discountType=discountLevelObject.getString("discountType");
					}
				}
			}
			if(responseDataObj!=null && responseDataObj.has("discount")){
				discount=responseDataObj.getDouble("discount");
			}
			if(responseDataObj!=null && responseDataObj.has("limit")){
				limit=responseDataObj.getString("limit");
				System.out.println("--limit--"+limit);
			}
			if(responseDataObj!=null && responseDataObj.has("isLeastPrice")){
				isLeastPrice= responseDataObj.getBoolean("isLeastPrice");
				System.out.println("--isLeastPrice--"+isLeastPrice);
			}
			if(personalLevelObject!=null && personalLevelObject.has("name")){
				personalLevel=personalLevelObject.getString("name");
			}
			if(!responseDataObj.get("kouponCode").equals(null) && !responseDataObj.get("kouponCode").equals("")){
				couponUID = (String)responseDataObj.get("kouponCode");
			}
			if(!responseDataObj.get("isUsabilitySingle").equals(null) && !responseDataObj.get("isUsabilitySingle").equals("")){
				isUsabilitySingle = responseDataObj.getBoolean("isUsabilitySingle");
			}
			System.out.println("DISCOUNT COUPON UNIQUE ID::::"+couponUID);
			System.out.println("isUsabilitySingle-->"+isUsabilitySingle);
			System.out.println("invenotryLevelObject"+invenotryLevelObject);
			if(invenotryLevelObject.getInt("id")==1 && invenotryLevelObject.getString("invenotryName").equals("item") ){
				inventoryLevel="item";
				if(responseDataObj.toString().contains("kouponItem")){
					JSONArray kouponItemArray = responseDataObj.getJSONArray("kouponItem");
					System.out.println("kouponItemArray"+kouponItemArray);
					for(int i=0;i<kouponItemArray.length();i++){
						JSONObject kouponItemObject=kouponItemArray.getJSONObject(i);
						System.out.println("kouponItemObject-"+kouponItemObject);
					if(kouponItemObject.getInt("itemId")!=0 && kouponItemObject.getString("itemName")!=null){
						System.out.println(kouponItemObject.getString("itemName")+"------"+kouponItemObject.getInt("itemId"));
						int itemId=kouponItemObject.getInt("itemId");
						String itemName=kouponItemObject.getString("itemName");
						Item item=itemmRepository.findOne(itemId);
						System.out.println(item.getName());
						if(item!=null && item.getName().equals(itemName) && itemJson.contains(item.getPosItemId())){
                              System.out.println("item exists-"+item.getPosItemId());
                              List<String> itemArrayList = new ArrayList<String>();
                              List<String> freeItemArrayList = new ArrayList<String>();
                              if(eventId!=null && eventId==8 && limit!=null){
                            	  
                            	                             	  
                            	  int freeItemId=kouponItemObject.getInt("freeItemId");
          						String freeItemName=kouponItemObject.getString("freeItemName");
          						Item freeItem=itemmRepository.findOne(freeItemId);
          						System.out.println(item.getName());
          						if(freeItem.getName().equals(freeItemName)){
          							 /*if(limit.equals("single"))
         	    					 {
          							double freeItemPrice=freeItem.getPrice();
          							if(discount>freeItemPrice){
          								discount=freeItemPrice	;
          							}
         	    					 }else if(limit.equals("multiple")){
         	    						 
         	    					 }*/
          							 itemArrayList.add(freeItem.getPosItemId());
          							freeItemArrayList.add(item.getPosItemId());
          						}
                              }else{
                            	  itemArrayList.add(item.getPosItemId());
                              }
                              
                             
                              //voucherJson=  calculateDiscount(itemJson,cartJson,merchant,discountType,eventName,personalLevel,inventoryLevel,discount, voucherCode,itemArrayList, limit, isLeastPrice);
                              voucherResponse=  calculateDiscount(itemJson,cartJson,merchant,discountType,eventName,eventId,personalLevel,inventoryLevel,discount, voucherCode,itemArrayList, limit, isLeastPrice,kouponCount,appliedDiscounts,freeItemArrayList,orderLeveldiscount,couponUID, isUsabilitySingle);
						}
					}else{
						//return "Invalid Koupon";
						//voucherResponse.put("DATA", "Invalid Koupon");
						voucherResponse.put("DATA", appliedDiscounts.get("DATA"));
						voucherResponse.put("responsMessage", "Invalid Koupon");
						return voucherResponse;
					}
					}
				}
			}
				else if(invenotryLevelObject.getInt("id")==2 && invenotryLevelObject.getString("invenotryName").equals("category") ){
					inventoryLevel="category";	
					if(responseDataObj.toString().contains("kouponCategory")){
						JSONArray kouponCategoryArray= responseDataObj.getJSONArray("kouponCategory");
						for(int i=0;i<kouponCategoryArray.length();i++){
							JSONObject kouponCategoryObject=kouponCategoryArray.getJSONObject(i);
							System.out.println("kouponCategoryObject##"+kouponCategoryObject);
						if(kouponCategoryObject.getInt("categoryId")!=0 && kouponCategoryObject.getString("categoryName")!=null){
								int categoryId=kouponCategoryObject.getInt("categoryId");
								System.out.println("categoryId="+categoryId);
								List<Item> categoryItemList =null;
								List<Item> freeCategoryItemList =null;
								List<String> freeItemArrayList=null;
								 if(eventId!=null && eventId==8){
	                            	  int freeCategoryId=kouponCategoryObject.getInt("freeCategoryId");
	          						String freeCategoryName=kouponCategoryObject.getString("freeCategoryName");
	          						freeCategoryItemList=itemmRepository.findByCategoriesId(categoryId);
	          						if(freeCategoryItemList!=null && !freeCategoryItemList.isEmpty()){
										System.out.println("for all items of category"+freeCategoryItemList.get(0));
										 freeItemArrayList = new ArrayList<String>();
										
										 for(Item item:freeCategoryItemList){
											 freeItemArrayList.add(item.getPosItemId());
										 }
										 System.out.println("discpount--"+discount+" discountType- "+discountType);
			                             // voucherJson=  calculateDiscount(itemJson,cartJson,merchant,discountType,eventName,personalLevel,inventoryLevel,discount, voucherCode,itemArrayList, limit, isLeastPrice);
									}
	          						categoryItemList = itemmRepository.findByCategoriesId(freeCategoryId);
	                              }else{
	                            	   categoryItemList = itemmRepository.findByCategoriesId(categoryId);
	                              }
								//int categoryName=kouponCategoryObject.getInt("categoryName");
								
								if(categoryItemList!=null && !categoryItemList.isEmpty()){
									System.out.println("for all items of category"+categoryItemList.get(0));
									 List<String> itemArrayList = new ArrayList<String>();
									 for(Item item:categoryItemList){
		                              itemArrayList.add(item.getPosItemId());
									 }
									 System.out.println("discpount--"+discount+" discountType- "+discountType);
		                             // voucherJson=  calculateDiscount(itemJson,cartJson,merchant,discountType,eventName,personalLevel,inventoryLevel,discount, voucherCode,itemArrayList, limit, isLeastPrice);
									 voucherResponse=  calculateDiscount(itemJson,cartJson,merchant,discountType,eventName,eventId,personalLevel,inventoryLevel,discount, voucherCode,itemArrayList, limit, isLeastPrice,kouponCount,appliedDiscounts,freeItemArrayList,orderLeveldiscount,couponUID, isUsabilitySingle);
								}
								
							}
							else{
								//return "Invalid Koupon";
								//voucherResponse.put("DATA", "Invalid Koupon");
								voucherResponse.put("DATA", appliedDiscounts.get("DATA"));
								voucherResponse.put("responsMessage", "Invalid Koupon");
								return voucherResponse;
							}
						}
							
						}
				}
				else if(invenotryLevelObject.getInt("id")==3 && invenotryLevelObject.getString("invenotryName").equals("order") ){
					inventoryLevel="order";	
					//voucherJson=  calculateDiscount(itemJson,cartJson,merchant,discountType,eventName,personalLevel,inventoryLevel,discount, voucherCode,null, limit, isLeastPrice);
					//voucherJson=  calculateDiscount(itemJson,cartJson,merchant,discountType,eventName,personalLevel,inventoryLevel,discount, voucherCode,null, limit, isLeastPrice);
					voucherResponse=  calculateDiscount(itemJson,cartJson,merchant,discountType,eventName,eventId,personalLevel,inventoryLevel,discount, voucherCode,null, limit, isLeastPrice,kouponCount,appliedDiscounts,null,orderLeveldiscount,couponUID, isUsabilitySingle);
				}
			}
				
				
			}
			//return voucherJson;
		return voucherResponse;
	    }
    
		Map<String, Object> calculateDiscount(String itemJson,String cartJson,Merchant merchant,String discountType,String eventName,Integer eventId ,String personalLevel,String inventoryLevel,Double discount,String voucherCode, List<String> itemArrayList, String limit, Boolean isLeastPrice,int kouponCount,Map<String,Object> appliedDiscounts,List<String> freeItemArrayList,double orderLeveldiscount,String couponUID, Boolean isUsabilitySingle ){
	    	ArrayList<ItemDto> items= new ArrayList<ItemDto>();
	    	Map<String,ArrayList<ItemDto>> itemTaxes=new HashMap<String, ArrayList<ItemDto>>();
	    	Map<String,Object> response= new HashMap<String, Object>();
	    	Gson gson = new Gson();
	    	String voucherJson ="";
	    	
	    	Double orderPrice=null;
	    	double couponDiscount=0.0;
	    	if(discount!=null)
	    	 couponDiscount=discount;
	    	
	    	Double tipAmount=0.0;
	    	Double tax=null;
	    	Double convFee=null;
	    	Double subtotal=null;
	    	Double deliveryFee=null;
	    	double discountedSubTotal=0;
	    	double oldDiscountedSubTotal=0;
			 double discountedTotal=0;
			 double discountedTax=0;
			 boolean discountStatus=false;
			 Double categoryItemPrice=0.0;
			 Double orderDiscount=0.0;
			 Double totalOrderDiscount=0.0;
			// Integer totalQuantity =0;
			 Double minPriceAmount = 0.0;
			 int freeItemQty=0;
			 Double minOrderPriceAmount = 0.0;
			 ItemDto convenienceFeeItem=null;
			 boolean isConvenienceFeeTaxable =false;
			 List<Map<String,Object>> discountMapList = new ArrayList<Map<String,Object>>();
	    	
			 
	    	if(merchant!=null){
	    		
	    		 
	    		if(kouponCount>0){
	    			
	    			if( appliedDiscounts.containsKey("DATA") &&appliedDiscounts.get("DATA")!=null && appliedDiscounts.containsKey("responsCode") &&appliedDiscounts.get("responsCode")!=null)
	    			{
	    				String responsCode=(String)appliedDiscounts.get("responsCode");
	    				/*if(responsCode.equals(IConstant.RESPONSE_SUCCESS_MESSAGE)){*/
	    				Map<String,Object> data=(Map<String,Object>)appliedDiscounts.get("DATA");
	    				if(data.containsKey("items") && data.get("items")!=null )	
	    				items=(ArrayList<ItemDto>)data.get("items");
	    				
	    				
	    				
	    				if(data.containsKey("discount") && data.get("discount")!=null )
		    				totalOrderDiscount=(Double)data.get("discount");
	    				
	    				if(data.containsKey("subtotal") && data.get("subtotal")!=null )
	    					oldDiscountedSubTotal=(Double)data.get("subtotal");
	    				
	    				if(orderLeveldiscount>0){
	    					oldDiscountedSubTotal=0.0;
	    					for(ItemDto dto:items){
	    						dto.setPrice((dto.getOriginalPrice()+dto.getItemModifierPrice())*dto.getQunatity()-dto.getDiscount());
	    						oldDiscountedSubTotal=oldDiscountedSubTotal+dto.getPrice();
	    						
	    					}
	    					totalOrderDiscount=totalOrderDiscount-orderLeveldiscount;
	    				}
	    				
	    				if(data.containsKey("discountList") && data.get("discountList")!=null )
	    					discountMapList=(List<Map<String,Object>>)data.get("discountList");
	    				
	    				
	    				
	    				if(data.containsKey("isConvenienceFeeTaxable") && data.get("isConvenienceFeeTaxable")!=null )
	    					isConvenienceFeeTaxable=(Boolean)data.get("isConvenienceFeeTaxable");
	    				
	    				if(data.containsKey("convenienceFeeItem") && data.get("convenienceFeeItem")!=null )
	    					 convenienceFeeItem=(ItemDto)data.get("convenienceFeeItem");
	    				
	    			/*	}*/
	    				
	    			}else{

	    	    		JSONObject cartJsonObjects=new JSONObject(cartJson);
	    	    		if(cartJsonObjects!=null && cartJsonObjects.has("cartJson")){
	    	    		JSONObject cartJsonObject = cartJsonObjects.getJSONObject("cartJson");
	    	    		if(cartJsonObject!=null && cartJsonObject.has("tax")&& cartJsonObject.has("subtotal")&& cartJsonObject.has("total")){
	    	    			orderPrice=Double.parseDouble(cartJsonObject.getString("total"));
	    	    			tax=Double.parseDouble(cartJsonObject.getString("tax"));
	    	    			subtotal=Double.parseDouble(cartJsonObject.getString("subtotal"));
	    	    			if(cartJsonObject.has("convFee") )
	    	    			convFee=cartJsonObject.getDouble("convFee");
	    	    			if(cartJsonObject.has("deliveryFee"))
	    	    			deliveryFee=cartJsonObject.getDouble("deliveryFee");
	    	    			if(cartJsonObject.has("tipAmount"))
	    	    				tipAmount=cartJsonObject.getDouble("tipAmount");
	    	    			
	    	    			
	    	    		}
	    	    		}else{
	    	    			return null;
	    	    		}
	    	    		
	    	    		JSONObject itemJsonObjects=new JSONObject(itemJson);
	    	    		JSONArray jsonObj = itemJsonObjects.getJSONArray("orderJson");
	    	    		System.out.println("jsonObj-"+jsonObj);
	    	    		if(jsonObj!=null && jsonObj.length()>0){
	    	    			for(int i=0;i<jsonObj.length(); i++){
	    	    				JSONObject itemJsonObject=	jsonObj.getJSONObject(i);
	    	    				if(itemJsonObject!=null){
	    	    					if(itemJsonObject.has("itemid")&& itemJsonObject.has("amount")&& itemJsonObject.has("price")){
	    	    						ItemDto item= new ItemDto();
	    	    						String itemPosId=itemJsonObject.getString("itemid");
	    	    						
	    	    						item.setItemPosId(itemPosId);
	    	    						item.setQunatity(Integer.parseInt(itemJsonObject.getString("amount")));
	    	    						item.setTotalQunatity(item.getQunatity());
	    	    						for(ItemDto dto:items){
	    	    							if(dto.getItemPosId().equals(itemPosId)){
	    	    								System.out.println("inside duplicate check");
	    	    								item.setTotalQunatity(item.getQunatity()+dto.getQunatity());
	    	    								//totalQuantity = item.getTotalQunatity();
	    	    								dto.setTotalQunatity(item.getQunatity()+dto.getQunatity());
	    	    							}
	    	    						}
	    	    						Double itemPrice=Double.parseDouble(itemJsonObject.getString("price"));
	    	    						Double modifierPrice=0.0;
	    	    						if(itemJsonObject.has("modifier")){
	    	    						JSONArray modifierJsonArray=itemJsonObject.getJSONArray("modifier");
	    	    						if(modifierJsonArray!=null && modifierJsonArray.length()>0){
	    	    							
	    	    							for(int j=0;j<modifierJsonArray.length(); j++){
	    	    		    				JSONObject modifierJsonObject=	modifierJsonArray.getJSONObject(j);
	    	    		    				 modifierPrice=modifierPrice+modifierJsonObject.getDouble("price");
	    	    						}
	    	    						}
	    	    						}
	    	    						
	    	    						System.out.println("item Quantity-"+item.getQunatity());
	    	    						System.out.println("item total Quantity-"+item.getTotalQunatity());
	    	    						
	    	    						 item.setPrice(itemPrice);
	    	    						 item.setOriginalPrice(itemPrice);
	    		    						item.setItemModifierPrice(modifierPrice);
	    	    						
	    	    						/*if(limit!=null)
	    								 {
	    	    							System.out.println("in limit not null");
	    									 if(limit.equals("single"))
	    									 {
	    										 item.setPrice(itemPrice);
	    	    						item.setItemModifierPrice(modifierPrice);
	    									 }else if(limit.equals("multiple"))
	    									 {
	    										 item.setPrice(itemPrice);
	    	    						item.setItemModifierPrice(modifierPrice);
	    									 }
	    								 }*//*else{
	    									 System.out.println("in limit null");
	    									 item.setPrice(itemPrice+modifierPrice);
	    								 }*/
	    	    						items.add(item);
	    	    					}
	    	    				}
	    	    			}
	    	    		}
	    	    		
	    	    		 
	    				
	    	    		
	    	    		
	    	    		
	    	    		ConvenienceFee convenienceFee = convenienceFeeRepository.findByMerchantId(merchant.getId());
	    	            if (convenienceFee != null) {
	    	                if (convenienceFee.getConvenienceFee()!=null && Double.valueOf(convenienceFee.getConvenienceFee()) > 0) {
	    	               	 
	    	                	convenienceFeeItem= new ItemDto();
	    	                	convenienceFeeItem.setItemPosId(convenienceFee.getConvenienceFeeLineItemPosId());
	    	                	convenienceFeeItem.setQunatity(1);
	    	                	convenienceFeeItem.setPrice(Double.valueOf(convenienceFee.getConvenienceFee()));
	    	                	convenienceFeeItem.setOriginalPrice(Double.valueOf(convenienceFee.getConvenienceFee()));
	    	                	
	    	                	subtotal=subtotal+Double.valueOf(convenienceFee.getConvenienceFee());
	    	                	 
	    	                	items.add(convenienceFeeItem);
	    	                    if (convenienceFee.getIsTaxable() != null) {
	    	                        if (convenienceFee.getIsTaxable() == 1) {
	    	                        	isConvenienceFeeTaxable=true;
	    	   							
	    	                        }else{
	    	                        	isConvenienceFeeTaxable=false;
	    	                        }
	    	                    } else {
	    	                    	isConvenienceFeeTaxable=false;
	    	                    }
	    	                } 
	    	            }
	    	    		  
	    	    	
	    			}
	    			
	    		}else{
	    		JSONObject cartJsonObjects=new JSONObject(cartJson);
	    		if(cartJsonObjects!=null && cartJsonObjects.has("cartJson")){
	    		JSONObject cartJsonObject = cartJsonObjects.getJSONObject("cartJson");
	    		if(cartJsonObject!=null && cartJsonObject.has("tax")&& cartJsonObject.has("subtotal")&& cartJsonObject.has("total")){
	    			orderPrice=Double.parseDouble(cartJsonObject.getString("total"));
	    			tax=Double.parseDouble(cartJsonObject.getString("tax"));
	    			subtotal=Double.parseDouble(cartJsonObject.getString("subtotal"));
	    			if(cartJsonObject.has("convFee") )
	    			convFee=cartJsonObject.getDouble("convFee");
	    			if(cartJsonObject.has("deliveryFee"))
	    			deliveryFee=cartJsonObject.getDouble("deliveryFee");
	    			if(cartJsonObject.has("tipAmount"))
	    				tipAmount=cartJsonObject.getDouble("tipAmount");
	    			
	    			
	    		}
	    		}else{
	    			return null;
	    		}
	    		
	    		JSONObject itemJsonObjects=new JSONObject(itemJson);
	    		JSONArray jsonObj = itemJsonObjects.getJSONArray("orderJson");
	    		System.out.println("jsonObj-"+jsonObj);
	    		if(jsonObj!=null && jsonObj.length()>0){
	    			for(int i=0;i<jsonObj.length(); i++){
	    				JSONObject itemJsonObject=	jsonObj.getJSONObject(i);
	    				if(itemJsonObject!=null){
	    					if(itemJsonObject.has("itemid")&& itemJsonObject.has("amount")&& itemJsonObject.has("price")){
	    						ItemDto item= new ItemDto();
	    						String itemPosId=itemJsonObject.getString("itemid");
	    						
	    						item.setItemPosId(itemPosId);
	    						item.setQunatity(Integer.parseInt(itemJsonObject.getString("amount")));
	    						item.setTotalQunatity(item.getQunatity());
	    						for(ItemDto dto:items){
	    							if(dto.getItemPosId().equals(itemPosId)){
	    								System.out.println("inside duplicate check");
	    								item.setTotalQunatity(item.getQunatity()+dto.getQunatity());
	    								//totalQuantity = item.getTotalQunatity();
	    								dto.setTotalQunatity(item.getQunatity()+dto.getQunatity());
	    							}
	    						}
	    						Double itemPrice=Double.parseDouble(itemJsonObject.getString("price"));
	    						Double modifierPrice=0.0;
	    						if(itemJsonObject.has("modifier")){
	    						JSONArray modifierJsonArray=itemJsonObject.getJSONArray("modifier");
	    						if(modifierJsonArray!=null && modifierJsonArray.length()>0){
	    							
	    							for(int j=0;j<modifierJsonArray.length(); j++){
	    		    				JSONObject modifierJsonObject=	modifierJsonArray.getJSONObject(j);
	    		    				 modifierPrice=modifierPrice+modifierJsonObject.getDouble("price");
	    						}
	    						}
	    						}
	    						
	    						System.out.println("item Quantity-"+item.getQunatity());
	    						System.out.println("item total Quantity-"+item.getTotalQunatity());
	    						
	    						 item.setPrice(itemPrice);
	    						 item.setOriginalPrice(itemPrice);
		    						item.setItemModifierPrice(modifierPrice);
	    						
	    						/*if(limit!=null)
								 {
	    							System.out.println("in limit not null");
									 if(limit.equals("single"))
									 {
										 item.setPrice(itemPrice);
	    						item.setItemModifierPrice(modifierPrice);
									 }else if(limit.equals("multiple"))
									 {
										 item.setPrice(itemPrice);
	    						item.setItemModifierPrice(modifierPrice);
									 }
								 }*//*else{
									 System.out.println("in limit null");
									 item.setPrice(itemPrice+modifierPrice);
								 }*/
	    						items.add(item);
	    					}
	    				}
	    			}
	    		}
	    		
	    		 
				
	    		
	    		
	    		
	    		ConvenienceFee convenienceFee = convenienceFeeRepository.findByMerchantId(merchant.getId());
	            if (convenienceFee != null) {
	                if (convenienceFee.getConvenienceFee()!=null && Double.valueOf(convenienceFee.getConvenienceFee()) > 0) {
	               	 
	                	convenienceFeeItem= new ItemDto();
	                	convenienceFeeItem.setItemPosId(convenienceFee.getConvenienceFeeLineItemPosId());
	                	convenienceFeeItem.setQunatity(1);
	                	convenienceFeeItem.setPrice(Double.valueOf(convenienceFee.getConvenienceFee()));
	                	convenienceFeeItem.setOriginalPrice(Double.valueOf(convenienceFee.getConvenienceFee()));
	                	
	                	subtotal=subtotal+Double.valueOf(convenienceFee.getConvenienceFee());
	                	 
	                	items.add(convenienceFeeItem);
	                    if (convenienceFee.getIsTaxable() != null) {
	                        if (convenienceFee.getIsTaxable() == 1) {
	                        	isConvenienceFeeTaxable=true;
	   							
	                        }else{
	                        	isConvenienceFeeTaxable=false;
	                        }
	                    } else {
	                    	isConvenienceFeeTaxable=false;
	                    }
	                } 
	            }
	    		  
	    	}
	    		if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null){
	    			if(merchant.getOwner().getPos().getPosId()==IConstant.POS_CLOVER || merchant.getOwner().getPos().getName().toLowerCase().equals("clover") ){
	    			 
	    				for(ItemDto itemDto:items){
	    				 if(freeItemArrayList!=null && !freeItemArrayList.isEmpty()){
							 for(String itemPosId:freeItemArrayList){
								 if(itemDto.getItemPosId()!=null && itemDto.getItemPosId().equals(itemPosId)){
									 freeItemQty=freeItemQty+itemDto.getQunatity();
								 }
							 }
						 }
	    				}
	    				
	    				if(inventoryLevel.equals("order")){
	    				
	    				 if(limit!=null){
	    					 if(limit.equals("single"))
	    					 {
	    						orderDiscount= minPriceAmount;
	    						//totalOrderDiscount=totalOrderDiscount+orderDiscount;=//totalOrderDiscount=totalOrderDiscount+orderDiscount;+orderDiscount;
	    						ArrayList<Double> orderItemPriceList  = new ArrayList<Double>(); 
	    			    		
	    			    		 System.out.println("itemSize:-"+items.size());
	    			    		 for(int i = 0; i<items.size();i++)
	    						 {
	    							 System.out.println("prices---"+items.get(i).getPrice()+" "+items.get(i).getItemPosId()+" "+items.get(i).getQunatity());
	    							 orderItemPriceList.add(items.get(i).getPrice());
	    						 }
	    			    		 System.out.println(isLeastPrice);
	    						 //if(limit!=null && limit.equals("single")){
	    						 if(isLeastPrice){
	    							 minOrderPriceAmount  = Collections.min(orderItemPriceList);
	    						 System.out.println("minOrderpriceAmount----"+minOrderPriceAmount);
	    						 }else{
	    							 minOrderPriceAmount  = Collections.max(orderItemPriceList);
	    							 System.out.println("maxOrderPriceAmount-"+minOrderPriceAmount);
	    						 }
	    						// }
	    						
	    					 }
	    				 }
	    				 else{
	    				 
	    				 if(discountType.equals("amount")){
	    					orderDiscount=discount;
	    					
	    				}else{
	    					
	    					orderDiscount=subtotal*discount/100;
	    					orderDiscount=Math.round(orderDiscount * 100.0) / 100.0;
	    					
	    				}
	    			 }
	    			 }
	    				
	    				
	    				List<TaxRates> taxRates = taxRateRepository.findByMerchantId(merchant.getId());
	    				List<TaxRates> defaultTaxRates = taxRateRepository.findByMerchantIdAndIsDefault(merchant.getId(), IConstant.BOOLEAN_TRUE);
	    				 for(TaxRates rates:taxRates){
	    					 itemTaxes.put(rates.getName(), new ArrayList<ItemDto>());
	    				 }
	    				 int categorySize=0;
	    				 int categoryCount=1;
	    				 double categoryAppliedCount=0;
	    				 if(inventoryLevel.equals("category")){
	    					 ArrayList<Double> categoryItemPriceList = new ArrayList<Double>();
	    					 for(ItemDto itemDto:items){
	    						 
	    						 
	    						
	    						 
							 if(itemArrayList!=null && !itemArrayList.isEmpty()){
								 for(String itemPosId:itemArrayList){
									 if(itemDto.getItemPosId()!=null){
									 if(itemDto.getItemPosId().equals(itemPosId)){
										 
										 System.out.println("itemSize:-"+items.size());
										 
											 //categoryItemPriceList.add(itemDto.getPrice()); 
										 categoryItemPriceList.add(itemDto.getPrice()+itemDto.getItemModifierPrice());
										 if(limit!=null && eventId==8)
										 {
											 if(limit.equals("single"))
											 {
												 categoryItemPrice=categoryItemPrice+((itemDto.getOriginalPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity());	 
											 }else if(limit.equals("multiple")){
												 categoryItemPrice=categoryItemPrice+((itemDto.getOriginalPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity());
											 }
										 }else{
											// categoryItemPrice=categoryItemPrice+(itemDto.getPrice()*itemDto.getQunatity());
											 if(kouponCount>0){
												 categoryItemPrice=categoryItemPrice+itemDto.getPrice();
											 }else{
											 categoryItemPrice=categoryItemPrice+((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity());
											 }
											 }
										
										 System.out.println("categoryItemPrice---"+categoryItemPrice);
									 }
									 }
									 }
								 
								 
							 }
							 
							 }
	    					 
	    					 categorySize= categoryItemPriceList.size();
	    					 
	    					 System.out.println(isLeastPrice);
							 if(limit!=null && eventId==8){
							 if(isLeastPrice){
								 minPriceAmount  = Collections.min(categoryItemPriceList);
							 System.out.println("minpriceAmount----"+minPriceAmount);
							 }else{
								 minPriceAmount  = Collections.max(categoryItemPriceList);
								 System.out.println("maxPriceAmount-"+minPriceAmount);
							 }
							 }
	    				 }
	    				 System.out.println("items-->size"+items.size());
	    				 for(ItemDto itemDto:items){
	    					 Double discountedItemPrices=itemDto.getPrice();
	    					 if(inventoryLevel.equals("order")){
	    						 if(limit!=null && itemDto.getItemPosId()!= convenienceFeeItem.getItemPosId() && eventId==8){
	    						 if(limit.equals("single"))
									 {
										System.out.println("order--- itemDto.getTotalQunatity()-->"+itemDto.getTotalQunatity()+" minOrderPriceAmount "+minOrderPriceAmount+" = "+itemDto.getPrice()+" getQuantity--"+itemDto.getQunatity()+" statusFlag-"+discountStatus); 
										 if(itemDto.getQunatity()>1){
											
											 if(minOrderPriceAmount== itemDto.getPrice() && !discountStatus){
												 System.out.println("sumit.. minPriceAmount -->"+minOrderPriceAmount);
												 orderDiscount = itemDto.getPrice();
												 System.out.println("sumit.. orderDiscount -->"+orderDiscount);
												 System.out.println("before--"+discountedItemPrices);
												 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()-orderDiscount);
												 System.out.println(discountedItemPrices);
												 discountStatus=true;
											 }else{
												 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity());
											 }
										
										 System.out.println("sumit .. discountedItemPrices -->"+discountedItemPrices);
										 }else{
											// response.put("DATA", "plz add more than 1 item to apply this koupon");
											 response.put("responsMessage", "plz add more than 1 item to apply this koupon");
										    	response.put("responsCode", "400");
										    	response.put("DATA", appliedDiscounts.get("DATA"));
										    	//voucherJson = gson.toJson(response);
										    	return response;
										    	//return voucherJson;
										 }
									 }else if(limit.equals("multiple")){
										 System.out.println("sub total-"+itemDto.getPrice()*itemDto.getQunatity()+" price-"+itemDto.getPrice());
										System.out.println("order--itemDto.getQunatity()-->"+itemDto.getQunatity()); 

										 if(itemDto.getQunatity()>1){	
											 Integer itemQuantity;
											 itemQuantity = itemDto.getQunatity()/2;
											 System.out.println("discountedItemPrices before--->>"+discountedItemPrices);
											 discountedItemPrices = (discountedItemPrices-itemDto.getPrice())+ ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity() - (itemDto.getPrice()*itemQuantity));
											 System.out.println("discountedItemPrices after-----##--"+discountedItemPrices);
											 orderDiscount = orderDiscount+(itemDto.getPrice()*itemQuantity);
											 System.out.println(orderDiscount);
											 discountStatus=true;
										 }else{
											 //response.put("DATA", "plz add more than 1 item to apply this koupon");
											 response.put("responsMessage", "plz add more than 1 item to apply this koupon");
										    	response.put("responsCode", "400");
										    	response.put("DATA", appliedDiscounts.get("DATA"));
										    	//voucherJson = gson.toJson(response);
										    	//return voucherJson;
										    	return response;
										 }
									 }
								 
	    						 
	    						 System.out.println("subtotal--"+subtotal+" orderDiscount-"+orderDiscount);
	    						 
	    						 //discountStatus=true;
	    						 
	    					 }else{
	    						
	    						 if(kouponCount>0){
	    							 if(orderDiscount>oldDiscountedSubTotal){
		    							 orderDiscount=oldDiscountedSubTotal;
		    						 }
	    							 discountedItemPrices= (itemDto.getPrice()-((itemDto.getPrice()/oldDiscountedSubTotal)*orderDiscount));
	    							 discountedItemPrices=Math.round(discountedItemPrices * 100.0) / 100.0;
								 }else{
									 if(orderDiscount>subtotal){
		    							 orderDiscount=subtotal;
		    						 }
									 discountedItemPrices= (itemDto.getPrice()-((itemDto.getPrice()/subtotal)*orderDiscount))*itemDto.getQunatity();
									 discountedItemPrices=Math.round(discountedItemPrices * 100.0) / 100.0;
								 }
	    					 
	    						 discountStatus=true;
	    					 }
	    					 }
	    					 System.out.println("itemDto.getTotalQunatity()-->>>"+itemDto.getTotalQunatity()+" discountedItemPrices------>>>"+discountedItemPrices);
	    					 
	    					 
	    					 
	    					 if(inventoryLevel.equals("item")){
	    						 if( itemArrayList!=null && !itemArrayList.isEmpty()){
	    							 
	    							 
	    							 for(String itemPosId:itemArrayList){
	    								 
	    								 if(itemDto.getItemPosId()!=null && itemDto.getItemPosId().equals(itemPosId)){
	    									 if(eventId==8)
	    									 {
	    										 if(limit!=null){
	    										 if(freeItemArrayList!=null && !freeItemArrayList.isEmpty()){
	    										 if(limit.equals("single"))
	    										 {
	    											 
	    											 /*if(itemDto.getTotalQunatity()>1){*/
	    											 System.out.println("sub total-"+itemDto.getPrice()*itemDto.getQunatity()+" price-"+itemDto.getPrice());
	    											 //orderDiscount = discount;
	    											 
	    											 if(discountType.equals("amount")){
	    												 orderDiscount = discount;
	    											 }else{
	    												 double itemPercentDiscount=0.0;
			    					    					
			    					    						itemPercentDiscount=(itemDto.getOriginalPrice())*discount/100;
			    					    						orderDiscount=Math.round(itemPercentDiscount * 100.0) / 100.0;
	    											 }
	    											 ////
	    											
	    											 ////
	    											 if(kouponCount>0){
	    												 if(itemDto.getOriginalPrice()>itemDto.getPrice()&&orderDiscount>itemDto.getPrice()){
	    													 orderDiscount=itemDto.getPrice();
	    												 }else if(orderDiscount>itemDto.getOriginalPrice()){
	    													 orderDiscount=itemDto.getOriginalPrice();
	    												 }
	    												 discountedItemPrices=itemDto.getPrice()-orderDiscount;
	    											 }else{
	    												 if(orderDiscount>itemDto.getOriginalPrice()){
	    													 orderDiscount=itemDto.getOriginalPrice();
	    												 }
	    												 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()-orderDiscount);
	    											 }
	    											 
	    											 discountStatus=true;
	    											 itemDto.setDiscount(itemDto.getDiscount()+orderDiscount);
	    											 itemDto.setDiscountName(itemDto.getDiscountName()+" "+voucherCode);
	    											 
	    											 
	    										 }else if(limit.equals("multiple")){
	    											 System.out.println("sub total-"+itemDto.getPrice()*itemDto.getQunatity()+" price-"+itemDto.getPrice());
	    											/* if(itemDto.getQunatity()>1){*/
	    												 Integer itemQuantity=freeItemQty;
	    												 double multiItemPrice=0.0;
	    												 //itemQuantity = itemDto.getTotalQunatity()/2;
	    												 //System.out.println("eligible itemQuantity-"+itemQuantity);
	    												// orderDiscount = itemDto.getOriginalPrice()*itemQuantity;
	    												 multiItemPrice=itemDto.getOriginalPrice()*itemQuantity;
	    												 if(discountType.equals("amount")){
	    													 if(discount>itemDto.getOriginalPrice()){
	    														 discount= itemDto.getOriginalPrice();
	    													 }
		    												 orderDiscount = discount*itemQuantity;;
		    											 }else{
		    												 double itemPercentDiscount=0.0;
				    					    					
				    					    						itemPercentDiscount=(multiItemPrice)*discount/100;
				    					    						orderDiscount=Math.round(itemPercentDiscount * 100.0) / 100.0;
		    											 }
	    												 
	    												 if(kouponCount>0){
		    												 if(multiItemPrice>itemDto.getPrice()&&orderDiscount>itemDto.getPrice()){
		    													 orderDiscount=itemDto.getPrice();
		    												 }else if(orderDiscount>multiItemPrice){
		    													 orderDiscount=multiItemPrice;
		    												 }
		    												 discountedItemPrices=itemDto.getPrice()-orderDiscount;
		    											 }else{
		    												 if(orderDiscount>multiItemPrice){
		    													 orderDiscount=multiItemPrice;
		    												 }
		    												 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()-orderDiscount);
		    											 }
	    												 discountStatus=true;
	    												 itemDto.setDiscount(itemDto.getDiscount()+orderDiscount);
	    												 itemDto.setDiscountName(itemDto.getDiscountName()+" "+voucherCode);
	    											 /*}else{
	    												 response.put("DATA", "plz add more than 1 item to apply this koupon");
	    											    	response.put("responsCode", "400");
	    											    	voucherJson = gson.toJson(response);
	    											    	return voucherJson;
	    											    	return response;
	    											 }*/
	    										 }}else{
    												 response.put("responsMessage", "plz add more than 1 item to apply this koupon");
 											    	response.put("responsCode", "400");
 											    	response.put("DATA", appliedDiscounts.get("DATA"));
 											    	/*voucherJson = gson.toJson(response);
 											    	return voucherJson;*/
 											    	return response;
 											 }
	    										 
	    									 }else{
												 response.put("responsMessage", "koupon not valid");
											    	response.put("responsCode", "400");
											    	response.put("DATA", appliedDiscounts.get("DATA"));
											    	/*voucherJson = gson.toJson(response);
											    	return voucherJson;*/
											    	return response;
											 }}else{
	    									 if(discountType.equals("amount")){
	    										 System.out.println("discountType`"+discountType+" discountStatus="+discountStatus+" discount="+discount+" itemDto.getQunatity()="+itemDto.getQunatity());
	    					    					
	    					    					if(discount>0){
	    					    						orderDiscount=discount;
	    					    						if(kouponCount>0){
	    					    							if(orderDiscount>itemDto.getPrice())
		    					    							 orderDiscount=itemDto.getPrice();
		    					    							 
	    					    							discountedItemPrices= (itemDto.getPrice()-orderDiscount);
	    	    					    					itemDto.setDiscount(itemDto.getDiscount()+orderDiscount);
	    	    					    					itemDto.setDiscountName(itemDto.getDiscountName()+" "+voucherCode);
	    					    						}else {
	    					    							if(orderDiscount>((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity())){
	    					    							 orderDiscount=(itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity();
	    					    						 }	    					
	    					    					discountedItemPrices= (((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity())-orderDiscount);
	    					    					itemDto.setDiscount(itemDto.getDiscount()+orderDiscount);
	    					    					itemDto.setDiscountName(itemDto.getDiscountName()+" "+voucherCode);
	    					    					}
	    					    					}else{
	    					    						if(kouponCount>0){
	    					    							discountedItemPrices= itemDto.getPrice();
	    					    						}
	    					    						else{
	    					    						discountedItemPrices= (itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity();
	    					    						}
	    					    					}
	   	    									 discount=discount-orderDiscount;
	    					    				}else{
	    					    					double itemPercentDiscount=0.0;
	    					    					if(kouponCount>0){
	    					    						itemPercentDiscount=(itemDto.getPrice())*discount/100;
    					    						}else{
    					    							itemPercentDiscount=((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity())*discount/100;
    					    						}
	    					    					
	    					    					
	    					    					itemPercentDiscount=Math.round(itemPercentDiscount * 100.0) / 100.0;
	    					    					orderDiscount=orderDiscount+itemPercentDiscount;
	    					    					discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()-itemPercentDiscount);
	    					    					itemDto.setDiscount(itemDto.getDiscount()+orderDiscount);
	    					    					itemDto.setDiscountName(itemDto.getDiscountName()+" "+voucherCode);
	    					    				}
	    									 
	    									 //orderDiscount=orderDiscount+discount;
	    									
	    									 }
	    									 
	    									 
	    									 
	    									 discountStatus=true;
	    								 }else{
	    									 if(kouponCount>0){
					    							discountedItemPrices= itemDto.getPrice();
					    						}
					    						else{
					    						discountedItemPrices= (itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity();
					    						}
	    								 }
	    							 }
	    							 
	    						 }else{
	    							// return "invalid coupon";
	    							 //response.put("DATA", "Invalid koupon");
	    							 response.put("DATA", appliedDiscounts.get("DATA"));
	    							 response.put("responsMessage", "Invalid koupon");
								    	response.put("responsCode", "400");
								    	
								    	return response;
	    						 }
	    					 }
	    					 
	    					 if(inventoryLevel.equals("category")){
	    						 if(itemArrayList!=null && !itemArrayList.isEmpty()){
	    							
	    							 
	    							 for(String itemPosId:itemArrayList){
	    								 if(itemDto.getItemPosId()!=null && itemDto.getItemPosId().equals(itemPosId)){
	    									System.out.println("#limit-"+limit);
	    									 if(limit!=null)
	    									 {
	    										 if(limit.equals("single"))
	    										 {
	    											 if(freeItemQty>0){
	    												System.out.println(minPriceAmount.equals(itemDto.getPrice()));
	    												 if(minPriceAmount.equals(itemDto.getPrice()) && !discountStatus){
	    													 //int itemQry=(itemDto.getTotalQunatity()/2);
	    													 //System.out.println("itemQry -->"+itemQry);
	    													 if(discountType.equals("amount")){
	    			    					    					orderDiscount=discount;
	    													 }else{
	    														 orderDiscount=itemDto.getOriginalPrice()*discount/100;
	    														 orderDiscount=Math.round(orderDiscount * 100.0) / 100.0;
	    													 }
	    													 
	    													 if(kouponCount>0){
	    														 if(itemDto.getOriginalPrice()>itemDto.getPrice() && orderDiscount>itemDto.getPrice() ){
	    															 orderDiscount=itemDto.getPrice();
	    														 }else if(orderDiscount>itemDto.getOriginalPrice()){
		    														 orderDiscount=itemDto.getOriginalPrice();
		    													 }
		    													 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()-orderDiscount);
		    													
	    													 }else{
	    														 if(orderDiscount>itemDto.getOriginalPrice()){
		    														 orderDiscount=itemDto.getOriginalPrice();
		    													 }
		    													 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()-orderDiscount);
		    													 
	    													 }
	    													 
	    													 
	    													 itemDto.setDiscount(itemDto.getDiscount()+orderDiscount);
	    													 itemDto.setDiscountName(itemDto.getDiscountName()+" "+voucherCode);
	    													 discountStatus=true;
	    												 }else{
	    													 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity());
	    												 }
	    											 System.out.println("sumit .. discountedItemPrices -->"+discountedItemPrices);
	    											 }else{
	    												// response.put("DATA", "plz add more than 1 item to apply this koupon");
	    												 response.put("DATA", appliedDiscounts.get("DATA"));
	    												 response.put("responsMessage", "plz add more than 1 item to apply this koupon");
	    											    	response.put("responsCode", "400");
	    											    	/*voucherJson = gson.toJson(response);
	    											    	return voucherJson;*/
	    											    	return response;
	    											 }
	    											 System.out.println("original discounted price-"+discountedItemPrices);
	    										 }else if(limit.equals("multiple")){
	    											 System.out.println("sub total-"+itemDto.getPrice()*itemDto.getQunatity()+" price-"+itemDto.getPrice());
	    											 if(freeItemQty>0){
		    												System.out.println(minPriceAmount.equals(itemDto.getPrice()));
		    												 if(minPriceAmount.equals(itemDto.getPrice()) && !discountStatus){
		    													 //int itemQry=(itemDto.getTotalQunatity()/2);
		    													 //System.out.println("itemQry -->"+itemQry);
		    													 if(discountType.equals("amount")){
		    			    					    					orderDiscount=discount;
		    													 }else{
		    														 orderDiscount=itemDto.getOriginalPrice()*discount/100;
		    														 orderDiscount=Math.round(orderDiscount * 100.0) / 100.0;
		    													 }
		    													 
		    													 if(kouponCount>0){
		    														 
		    														 if(itemDto.getOriginalPrice()>itemDto.getPrice() && orderDiscount>itemDto.getPrice() ){
		    															 orderDiscount=itemDto.getPrice();
		    														 }else if(orderDiscount>itemDto.getOriginalPrice()){
			    														 orderDiscount=itemDto.getOriginalPrice();
			    														 if(itemDto.getOriginalPrice()*freeItemQty>itemDto.getOriginalPrice()*itemDto.getQunatity()){
			    															 orderDiscount= itemDto.getOriginalPrice()*itemDto.getQunatity();
			    														 }else{
			    															 orderDiscount=itemDto.getOriginalPrice()*freeItemQty;
			    														 }
			    													 }else{
			    														 orderDiscount=orderDiscount*freeItemQty;
			    													 }
			    													 discountedItemPrices= itemDto.getPrice()-orderDiscount;
			    													
		    													 }else{
		    														 if(orderDiscount>itemDto.getOriginalPrice()){
		    															 orderDiscount=itemDto.getOriginalPrice();
			    														 if(itemDto.getOriginalPrice()*freeItemQty>itemDto.getOriginalPrice()*itemDto.getQunatity()){
			    															 orderDiscount= itemDto.getOriginalPrice()*itemDto.getQunatity();
			    														 }else{
			    															 orderDiscount=itemDto.getOriginalPrice()*freeItemQty;
			    														 }
			    													 }else{
			    														 orderDiscount=orderDiscount*freeItemQty;
			    														 if(orderDiscount>(itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()){
			    															 orderDiscount=(itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity();
			    														 }
			    													 }
			    													 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()-orderDiscount);
			    													 
		    													 }
		    													 
		    													 
		    													 itemDto.setDiscount(itemDto.getDiscount()+orderDiscount);
		    													 itemDto.setDiscountName(itemDto.getDiscountName()+" "+voucherCode);
		    													 discountStatus=true;
		    												 }else{
		    													 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity());
		    												 }
		    											 System.out.println("sumit .. discountedItemPrices -->"+discountedItemPrices);
		    											 }else{
	    												 //response.put("DATA", "plz add more than 1 item to apply this koupon");
		    												 response.put("DATA", appliedDiscounts.get("DATA"));
		    												 response.put("responsMessage", "plz add more than 1 item to apply this koupon");
	    											    	response.put("responsCode", "400");
	    											    	/*voucherJson = gson.toJson(response);
	    											    	return voucherJson;*/
	    											    	return response;
	    											 }
	    										 }
	    									 }
	    									 else{
	    									 if(discountType.equals("amount")){
	    					    					orderDiscount=discount;
	    					    					//totalOrderDiscount=totalOrderDiscount+orderDiscount;
	    					    					
	    					    						if(orderDiscount>categoryItemPrice){
	    					    							 orderDiscount=categoryItemPrice;
	    					    						 }	    					
	    					    					
	    					    					
	    					    				}else{
	    					    					
	    					    					double categoryItemPercentDiscount=(categoryItemPrice)*discount/100;
	    					    					System.out.println("categoryItemPercentDiscount-"+categoryItemPercentDiscount);
	    					    					categoryItemPercentDiscount=Math.round(categoryItemPercentDiscount * 100.0) / 100.0;
	    					    					System.out.println("categoryItemPercentDiscount="+categoryItemPercentDiscount);
	    					    					orderDiscount=categoryItemPercentDiscount;
	    					    					System.out.println("orderDiscount-"+orderDiscount);
	    					    					
	    					    				}
	    									 double categoryDiscount=0.0;
	    									
	    									 if((categorySize)>categoryCount){
	    										 if(kouponCount>0){
	    											 categoryDiscount= (itemDto.getPrice()/categoryItemPrice)*orderDiscount;
												 }else{
													 categoryDiscount= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()/categoryItemPrice)*orderDiscount;
												 }
	    										 
	    										 categoryDiscount= Math.round(categoryDiscount * 100.0) / 100.0;
	    										 categoryAppliedCount=categoryAppliedCount+categoryDiscount;
	    									 }else{
	    										 categoryDiscount=orderDiscount-categoryAppliedCount;
	    										 categoryDiscount= Math.round(categoryDiscount * 100.0) / 100.0;
	    										 categoryAppliedCount=categoryAppliedCount+categoryDiscount;
	    									 }
	    									 if(kouponCount>0){
	    										 discountedItemPrices= (itemDto.getPrice()-categoryDiscount);
											 }else{
												 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity()-categoryDiscount);
											 }
	    									 
	    									
	    									 itemDto.setDiscount(itemDto.getDiscount()+categoryDiscount);
	    									 itemDto.setDiscountName(itemDto.getDiscountName()+" "+voucherCode);
	    									 discountStatus=true;
	    									 categoryCount++;
	    									 }
	    									 
	    									 break;
	    								 }else{
	    									 if(kouponCount>0){
	    										 discountedItemPrices= itemDto.getPrice();
	    									 }else{
	   									 discountedItemPrices= ((itemDto.getPrice()+itemDto.getItemModifierPrice())*itemDto.getQunatity());
	    									 }
	    								 }
	    							 }
	    							 
	    						 }else{
	    							// return "invalid coupon";
	    							 //response.put("DATA", "Invalid koupon");
	    							 response.put("DATA", appliedDiscounts.get("DATA"));
	    							 response.put("responsMessage", "Invalid koupon");
								    	response.put("responsCode", "400");
								    	
								    	return response;
	    						 }
	    					 }
	    					 discountedSubTotal=discountedSubTotal+discountedItemPrices;
	    					 System.out.println("discountedSubTotal final-->>"+discountedSubTotal+" discountedItemPrices-->"+discountedItemPrices);
	    					 itemDto.setPrice(discountedItemPrices);
	    					 List<ItemTax> itemTaxs=itemTaxRepository.findByItemPosItemId(itemDto.getItemPosId());
	    					 for(ItemTax itemTax:itemTaxs){
	    						 TaxRates rates=itemTax.getTaxRates();
	    						 ArrayList<ItemDto> itemTx= (ArrayList)itemTaxes.get(rates.getName());
	    						 itemTx.add(itemDto);
	    					 }
	    				 }
	    				 
	    				 if(convenienceFeeItem!=null && isConvenienceFeeTaxable ){
	    					 for (TaxRates defaultTax:defaultTaxRates) {
	    						 ArrayList<ItemDto> itemTx= (ArrayList)itemTaxes.get(defaultTax.getName());
	    						 itemTx.add(convenienceFeeItem);
							}
	    				 }
	    				 
	    				 
	    				 
	    				 for(TaxRates rates:taxRates){
	    					 ArrayList<ItemDto> itemTx= (ArrayList)itemTaxes.get(rates.getName());
	    					 double totalItemPrice=0;
	    					 for(ItemDto itemDto:itemTx){
	    						 totalItemPrice=totalItemPrice+itemDto.getPrice();
	    						 //System.out.println("totalItemPrice-"+totalItemPrice);
	    					 }
	    					 discountedTax=discountedTax+Math.round((totalItemPrice*rates.getRate()/100) * 100.0) / 100.0;;
	    					 
	    					 
	    				 }
	    				 discountedTax = Math.round(discountedTax * 100.0) / 100.0;
	    				 discountedSubTotal = Math.round(discountedSubTotal * 100.0) / 100.0;
	    				 discountedTotal=discountedSubTotal+discountedTax+tipAmount;
	    				 discountedTotal=Math.round(discountedTotal * 100.0) / 100.0;
	    				
	    				
	    			}else{
	    				
	    			}
	    		}
	    	}
	    	System.out.println(discountStatus+"<<<---discountStatus");
	    	if(discountStatus){
	    		kouponCount++;
	    		
	    		Map<String,Object> discountMap= new HashMap<String, Object>();
	    		/*discountMap.put("voucherCode", voucherCode);
	    		discountMap.put("discount", couponDiscount);
	    		discountMap.put("inventoryLevel", inventoryLevel);
	    		discountMap.put("discountType", "amount");
	    		discountMap.put("couponUID", couponUID);*/
	    		
	    		
	    		if(kouponCount>0){
	    			//discountMapList=cart.get("discountList");
	    			boolean existingCouon=false;
	    			for(Map<String,Object> map:discountMapList){
	    				String coupon=(String)map.get("voucherCode");
	    				if(coupon!=null && coupon.equals(voucherCode)){
	    					//discountMap=map;
	    					existingCouon=true;
	    					totalOrderDiscount=totalOrderDiscount+orderLeveldiscount;
	    				}
	    			}
	    			if(!existingCouon){
	    				discountMapList.add(discountMap);
	    			}
	    		}else{
	    			discountMapList=new ArrayList<Map<String,Object>>();
	    			discountMapList.add(discountMap);
	    		}
	    		
	    		
	    		totalOrderDiscount=totalOrderDiscount+orderDiscount;
	    		
	    		
	    		discountMap.put("voucherCode", voucherCode);
	    		discountMap.put("discount", totalOrderDiscount);
	    		discountMap.put("inventoryLevel", inventoryLevel);
	    		discountMap.put("discountType", "amount");
	    		discountMap.put("couponUID", couponUID);
	    		discountMap.put("isUsabilitySingle",isUsabilitySingle);
	    		
	    	Map<String,Object> cart= new HashMap<String, Object>();
	    	cart.put("tax", discountedTax);
	    	cart.put("subtotal", discountedSubTotal);
	    	cart.put("total", discountedTotal);
	    	cart.put("convFee", "5");
	    	cart.put("deliveryFee", "5");
	    	cart.put("discount", totalOrderDiscount);
	    	cart.put("discountType", "amount");
	    	//cart.put("voucherCode", voucherCode);
	    	//cart.put("inventoryLevel", inventoryLevel);
	    	cart.put("itemList", itemArrayList);
	    	cart.put("items",items);
	    	cart.put("discountList", discountMapList);
	    	cart.put("kouponCount", kouponCount);
	    	cart.put("convenienceFeeItem", convenienceFeeItem);
	    	cart.put("isConvenienceFeeTaxable", isConvenienceFeeTaxable);
	    	
	    	
	    	//cart.put("isDeliveryFeeKoupon", isDeliveryFeeKoupon);
	    	
	    	response.put("DATA", cart);
	    	response.put("responsCode", "200");
	    	
	    	
	    	System.out.println("kouponCount-->>>"+kouponCount);
	    	
	    	}else{
	    		
	    		//response.put("DATA",IConstant.MESSAGE_INVALID_KOUPON);
	    		response.put("DATA", appliedDiscounts.get("DATA"));
	    		response.put("responsMessage",IConstant.MESSAGE_INVALID_KOUPON);
	        	response.put("responsCode",IConstant.RESPONSE_INVALID_KOUPON);
	    	}
	    	
	    	//voucherJson = gson.toJson(response);
	    	//System.out.println("final json-"+voucherJson);
	    	//return voucherJson;
	    return response;
		}
	      
  // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    
    
    
   /* public Double checkCouponVaidity(String couponCode, Integer merchantId) {
        Vouchar vouchar = voucharRepository.findByVoucharCodeAndMerchantId(couponCode, merchantId);
        Date todayDate = new Date();
        if (vouchar != null) {
		  if (vouchar.getVoucharCode().equals(couponCode)) {
            try {
                if (vouchar.getDate() != null) {
                    int status = 0;
                    int timeStatuscount = 0;
                    if (todayDate.before(vouchar.getDate())) {
                        System.out.println("fixed date");
                        return vouchar.getDiscount();
                    } else {
                        if (todayDate.compareTo(vouchar.getDate()) == 0) {
                            status++;
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        String str = sdf.format(new Date());
                        boolean timeStatus = VoucharServiceImpl
                                        .isTimeBetweenTwoTime(convert12To24(vouchar.getStartTime()),
                                                        convert12To24(vouchar.getEndTime()), str);
                        if (timeStatus) {
                            timeStatuscount++;
                        }
                        if (status > 0 && timeStatuscount > 0) {
                            return vouchar.getDiscount();
                        }
                    }
                }
                if (vouchar.getFromDate() != null) {
                    int status = 0;
                    int timeStatuscount = 0;
                    if (todayDate.after(vouchar.getFromDate()) && todayDate.before(vouchar.getEndDate())) {
                        return vouchar.getDiscount();
                    } else {
                        if (todayDate.compareTo(vouchar.getEndDate()) == 0) {
                            status++;
                        }
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        String str = sdf.format(new Date());
                        boolean timeStatus = VoucharServiceImpl
                                        .isTimeBetweenTwoTime(convert12To24(vouchar.getStartTime()),
                                                        convert12To24(vouchar.getEndTime()), str);
                        if (timeStatus) {
                            timeStatuscount++;
                        }
                        if (status > 0 && timeStatuscount > 0) {
                            return vouchar.getDiscount();
                        }
                    }
                }
                if (vouchar.getDate() == null && vouchar.getFromDate() == null) {
                    Calendar calendar = Calendar.getInstance();
                    Date date = calendar.getTime();
                    System.out.println(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()));
                    String toDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
                    if (toDay.equals(vouchar.getRecurringR())) {
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                        String str = sdf.format(new Date());
                        boolean timeStatus = VoucharServiceImpl
                                        .isTimeBetweenTwoTime(convert12To24(vouchar.getStartTime()),
                                                        convert12To24(vouchar.getEndTime()), str);
                        if (timeStatus) {
                            return vouchar.getDiscount();
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
		  } else {
            return null;
         }
        } else {
            return null;
        }
        return null;
    }*/

    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String str = sdf.format(new Date());
            System.out.println(VoucharServiceImpl.isTimeBetweenTwoTime(convert12To24("11:30 AM"),
                            convert12To24("02:02 PM"), str));
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            // 3 letter name form of the day
            System.out.println(new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime()));
            // full name form of the day
            System.out.println(new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()).toLowerCase());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String currentTime)
                    throws ParseException {
        System.out.println(initialTime + "-" + finalTime + "-" + currentTime);
        String reg = "^([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$";
        if (initialTime.matches(reg) && finalTime.matches(reg) && currentTime.matches(reg)) {
            boolean valid = false;
            // Start Time
            java.util.Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(inTime);

            // Current Time
            java.util.Date checkTime = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(checkTime);

            // End Time
            java.util.Date finTime = new SimpleDateFormat("HH:mm:ss").parse(finalTime);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(finTime);

            if (finalTime.compareTo(initialTime) < 0) {
                calendar2.add(Calendar.DATE, 1);
                calendar3.add(Calendar.DATE, 1);
            }

            java.util.Date actualTime = calendar3.getTime();
            if ((actualTime.after(calendar1.getTime()) || actualTime.compareTo(calendar1.getTime()) == 0)
                            && actualTime.before(calendar2.getTime())) {
                valid = true;
            }
            return valid;
        } else {
            throw new IllegalArgumentException("Not a valid time, expecting HH:MM:SS format");
        }

    }

    public static String convert12To24(String str) {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = null;
        try {
            date = parseFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return displayFormat.format(date);
    }
    
    /**
     * find opening closing hour by merchantId and day
     */
    public List<OpeningClosingTime> findOpeningClosingHoursByMerchantId(Integer merchantId,String timeZoneCode) {
        /*Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(value);
        Date date = calendar.getTime();
        String toDay = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime()).toLowerCase();*/
    	Date date = new Date();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    	// Use Madrid's time zone to format the date in
    	df.setTimeZone(TimeZone.getTimeZone(timeZoneCode));

    	String toDay=DateUtil.findDayNameFromDate( df.format(date));
        OpeningClosingDay openingClosingDay = openingClosingDayRepository.findByMerchantIdAndDay(merchantId, toDay);
        if (openingClosingDay != null) {
            return openingClosingTimeRepository.findByOpeningClosingDayId(openingClosingDay.getId());
        } else {
            return null;
        }
    }

	public String findByMerchantIdAndCouponCode(Integer merchantId, String couponCode) {
		Vouchar vouchar = voucharRepository.findByVoucharCodeAndMerchantId(couponCode, merchantId);
		if(vouchar!=null){
			return "true";
		}
		else{
			return "false";
		}
	}

	public String findByMerchantIdAndCouponCodeAndCouponId(Integer merchantId, String couponCode, Integer couponId) {
		Vouchar vouchar = voucharRepository.findByVoucharCodeAndMerchantId(couponCode, merchantId);
		if(vouchar!=null){
			if(vouchar.getId()!=couponId)
			return "true";
			else
				return "false";
		}
		else{
			return "false";
		}
	}

	
}