package com.foodkonnekt.serviceImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodkonnekt.clover.vo.CloverDiscountVO;
import com.foodkonnekt.clover.vo.CloverOrderVO;
import com.foodkonnekt.clover.vo.Modifications;
import com.foodkonnekt.clover.vo.OrderItemVO;
import com.foodkonnekt.clover.vo.OrderVO;
import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.CustomerFeedback;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemDto;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.MerchantOrders;
import com.foodkonnekt.model.MerchantSubscription;
import com.foodkonnekt.model.OpeningClosingDay;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.OrderType;
import com.foodkonnekt.model.PaymentMode;
import com.foodkonnekt.model.Pos;
import com.foodkonnekt.model.Subscription;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.TimeZone;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.repository.AddressRepository;
import com.foodkonnekt.repository.CategoryRepository;
import com.foodkonnekt.repository.CustomerrRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantOrdersRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.MerchantSubscriptionRepository;
import com.foodkonnekt.repository.OpeningClosingDayRepository;
import com.foodkonnekt.repository.OpeningClosingTimeRepository;
import com.foodkonnekt.repository.OrderRepository;
import com.foodkonnekt.repository.OrderTypeRepository;
import com.foodkonnekt.repository.PaymentModeRepository;
import com.foodkonnekt.repository.RoleRepository;
import com.foodkonnekt.repository.SubscriptionRepository;
import com.foodkonnekt.repository.TaxRateRepository;
import com.foodkonnekt.repository.TimeZoneRepository;
import com.foodkonnekt.repository.VendorRepository;
import com.foodkonnekt.service.CloverService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.JsonUtil;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;
import com.google.gson.Gson;
import com.google.gson.JsonParser;

@Service
@Transactional
public class CloverServiceImpl extends JsonUtil implements CloverService {

    @Autowired
    private MerchantRepository merchantRepository;
    
    @Autowired
    private TimeZoneRepository timeZoneRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private OrderTypeRepository orderTypeRepository;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Autowired
    private OpeningClosingDayRepository openingClosingDayRepository;

    @Autowired
    private OpeningClosingTimeRepository openingClosingTimeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemmRepository itemRepository;

    @Autowired
    private OrderRepository ordeRepo;

    @Autowired
    private MerchantSubscriptionRepository merchantSubscriptionRepository;

    @Autowired
    private MerchantOrdersRepository merchantOrdersRepository;
    
    @Autowired
    private CustomerrRepository customerrRepository;
    
    /**
     * Save vendor,merchant and address
     */
    public void deleteCloverEmployees(Clover clover){
    	String employeeJson=CloverUrlUtil.getEmployeesFromClover(clover);
        if(employeeJson.contains("{")){
        	JSONObject employeeJsonObject = new JSONObject(employeeJson);
        	if(employeeJsonObject.has("elements")){
	        JSONArray jSONArray = employeeJsonObject.getJSONArray("elements");
        for (Object jObj : jSONArray) {
            JSONObject jObject = (JSONObject) jObj;
            

            if(jObject.has("id") && jObject.has("name")){
				if(jObject.getString("name").equals("FoodKonnekt Employee")){
					String deleteEmployeeResponse =CloverUrlUtil.deleteEmployee(clover,jObject.getString("id"));
					System.out.println("Employee Delete Response ->"+deleteEmployeeResponse);
				}
				
			}
               
            
        }
        	}
        }
    }
    public Merchant saveMerchant(Clover clover) {
        String billingInfo = CloverUrlUtil.getMerchantBilling(clover);
        String merchantDetails = CloverUrlUtil.getMerchantDetails(clover);
        
        MerchantSubscription  merchantSubscription = JsonUtil.setSubscription(billingInfo);
                                
        //Subscription subscription = JsonUtil.setSubscription(billingInfo);
        Subscription subscription=null;
        if (merchantSubscription != null) {
        	 subscription = merchantSubscription.getSubscription();
        	if(subscription!=null)
            subscription = subscriptionRepository.findBySubscriptionPlanId(subscription.getSubscriptionPlanId());
            /* System.out.println(subscription.getSubscriptionPlanId()); */
        }

        Merchant newMerchant = merchantRepository.findByPosMerchantId(clover.getMerchantId());
        if (null == newMerchant) {
            newMerchant = new Merchant();
            newMerchant.setIsInstall(IConstant.BOOLEAN_FALSE);
            /*deleteCloverEmployees(clover);
            String employeePosId= CloverUrlUtil.createEmployeeOnClover(clover);
            if(employeePosId!=null)
            newMerchant.setEmployeePosId(employeePosId);*/
            
        } else {
        	String employeePosId=newMerchant.getEmployeePosId();
        	if(employeePosId==null || employeePosId.isEmpty() || employeePosId.equals("")){
        		 employeePosId= CloverUrlUtil.createEmployeeOnClover(clover);
        	}
        	if(newMerchant.getIsInstall()!=null && newMerchant.getIsInstall()==2)
            newMerchant.setIsInstall(IConstant.BOOLEAN_TRUE);
        }
        newMerchant = JsonUtil.setMerchant(merchantDetails, newMerchant);
        Vendor vendor = vendorRepository.findByEmail(newMerchant.getOwner().getEmail());
        Pos pos = null;
        if (vendor == null) {
            pos = new Pos();
            pos.setPosId(IConstant.POS_CLOVER);
            newMerchant.getOwner().setPos(pos);
            System.out.println("if insde clverserviceImpl---");
            vendorRepository.save(newMerchant.getOwner());
        } else {
            System.out.println("else insde clverserviceImpl---" + vendor);
            newMerchant.setOwner(vendor);
        }
        newMerchant.setAccessToken(clover.getAuthToken());
        newMerchant.setSubscription(subscription);
        newMerchant = merchantRepository.save(newMerchant);
        String addressJson = CloverUrlUtil.getMerchantAddress(clover);
        List<Address> addresses = addressRepository.findByMerchantId(newMerchant.getId());
        Address address = null;
        if (addresses.isEmpty()) {
            address = new Address();
            
            
        } else {
            address = addresses.get(IConstant.BOOLEAN_FALSE);
        }
        address = JsonUtil.setAddress(addressJson, newMerchant, address);
        if(addresses==null ||addresses.isEmpty() ||addresses.size()==0){
        	addresses = new ArrayList<Address>();
        	addresses.add(address);
        	
        }
        
        
        
        
        if (addressJson.contains("phoneNumber"))
            merchantRepository.save(address.getMerchant());

        addressRepository.save(address);
        
        if(address!=null && address.getZip()!=null){
        	String timeZoneCode=DateUtil.getTimeZone(address);
        	if(timeZoneCode!=null){
        		TimeZone timeZone=timeZoneRepository.findByTimeZoneCode(timeZoneCode);
        		newMerchant.setTimeZone(timeZone);
        		merchantRepository.save(newMerchant);
        	}
        	}

        // Save merchant subscription
        
        Integer merchantSubscriptionId = merchantSubscriptionRepository.findByMerchantId(newMerchant.getId());
        if (merchantSubscriptionId != null) {
            merchantSubscription = merchantSubscriptionRepository.findOne(merchantSubscriptionId);
        } else {
            
            merchantSubscription.setMerchant(newMerchant);
        }
        merchantSubscription.setSubscription(subscription);
        merchantSubscriptionRepository.save(merchantSubscription);
        List<MerchantSubscription> merchantSubscriptions = new ArrayList<MerchantSubscription>();
        merchantSubscriptions.add(merchantSubscription);
        newMerchant.setMerchantSubscriptions(merchantSubscriptions);
        
        // Save merchant orders count information
        MerchantOrders merchantOrders = merchantOrdersRepository.findByMerchantSubscriptionId(merchantSubscription
                        .getId());
        if (merchantOrders == null) {
            merchantOrders = new MerchantOrders();
            merchantOrders.setOrderCount(IConstant.BOOLEAN_FALSE);
            merchantOrders.setMerchantSubscription(merchantSubscription);
            merchantOrders.setStartDate(DateUtil.currentDate());
            merchantOrders.setEndDate(DateUtil.incrementedDate());
            merchantOrdersRepository.save(merchantOrders);
        }
        newMerchant.setAddresses(addresses);
        
        return newMerchant;
    }

    public void saveOrderType(String orderTypeDetail, Merchant merchant, Clover clover) {
        List<OrderType> orderTypeList = setOrderType(orderTypeDetail, merchant, clover);
        orderTypeRepository.save(orderTypeList);
    }

    public void savePaymentMode(String paymentModeJson, Merchant merchant) {
        List<PaymentMode> paymentModeList = setPaymentMode(paymentModeJson, merchant);
        if(paymentModeList!=null)
        paymentModeRepository.save(paymentModeList);

    }

    /**
     * Save tax rate
     */
    public void saveTaxRate(String taxRates, Merchant merchant) {
        List<TaxRates> rates = setTaxRates(taxRates, merchant);
        if (rates != null)
            taxRateRepository.save(rates);
    }

    /**
     * Save opening closing hour
     */
    public void saveOpeningClosing(Merchant merchant, String openingHour) {
        JSONObject jObject = new JSONObject(openingHour);
        if(jObject.has("elements")){
        JSONArray jSONArray = jObject.getJSONArray("elements");
        if (jSONArray != null) {
            for (Object jObj : jSONArray) {
                JSONObject openingClosing = (JSONObject) jObj;
                OpeningClosingDay sundayDay = getDay(openingClosing, "sunday", merchant);
                openingClosingDayRepository.save(sundayDay);

                List<OpeningClosingTime> sunday = getOpeingClosingTime("sunday", sundayDay, openingClosing);
                openingClosingTimeRepository.save(sunday);

                OpeningClosingDay mondayDay = getDay(openingClosing, "monday", merchant);

                openingClosingDayRepository.save(mondayDay);

                List<OpeningClosingTime> monday = getOpeingClosingTime("monday", mondayDay, openingClosing);
                openingClosingTimeRepository.save(monday);

                OpeningClosingDay tuesdayDay = getDay(openingClosing, "tuesday", merchant);
                openingClosingDayRepository.save(tuesdayDay);

                List<OpeningClosingTime> tuesday = getOpeingClosingTime("tuesday", tuesdayDay, openingClosing);
                openingClosingTimeRepository.save(tuesday);

                OpeningClosingDay wednesdayDay = getDay(openingClosing, "wednesday", merchant);
                openingClosingDayRepository.save(wednesdayDay);

                List<OpeningClosingTime> wednesday = getOpeingClosingTime("wednesday", wednesdayDay, openingClosing);
                openingClosingTimeRepository.save(wednesday);

                OpeningClosingDay thursdayDay = getDay(openingClosing, "thursday", merchant);
                openingClosingDayRepository.save(thursdayDay);

                List<OpeningClosingTime> thursday = getOpeingClosingTime("thursday", thursdayDay, openingClosing);
                openingClosingTimeRepository.save(thursday);

                OpeningClosingDay fridayDay = getDay(openingClosing, "friday", merchant);
                openingClosingDayRepository.save(fridayDay);

                List<OpeningClosingTime> friday = getOpeingClosingTime("friday", fridayDay, openingClosing);
                openingClosingTimeRepository.save(friday);

                OpeningClosingDay saturdayDay = getDay(openingClosing, "saturday", merchant);
                openingClosingDayRepository.save(saturdayDay);

                List<OpeningClosingTime> saturday = getOpeingClosingTime("saturday", saturdayDay, openingClosing);
                openingClosingTimeRepository.save(saturday);
            }
        }
        }
    }

    private Set<Item> getItemForCategory(JSONArray itemJsonArray, Merchant merchant, Clover clover) {
        Set<Item> itemSet = new HashSet<Item>();
        for (int i = 0; i < itemJsonArray.length(); i++) {
            JSONObject itemJson = itemJsonArray.getJSONObject(i);
            String posItemId = itemJson.getString("id");

            Item item = null;
            item = itemRepository.findByPosItemIdAndMerchantId(posItemId, merchant.getId());
            if (item != null) {
                itemSet.add(item);
            } else {
                String itemResponse = CloverUrlUtil.getCloverItem(clover, merchant, posItemId);
                JSONObject jObject = new JSONObject(itemResponse);
                item = createNewLineItem(jObject, merchant);
                itemSet.add(item);
            }
        }
        return itemSet;
    }

    private List<Category> createCategoryObject(String responseString, Merchant merchant, Clover clover) {
        List<Category> categories = new ArrayList<Category>();
        JSONObject jsonObject = new JSONObject(responseString);
        JSONArray jsonArray = jsonObject.getJSONArray("elements");
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                JSONObject categoryItem = jsonArray.getJSONObject(i);
                Category category = categoryRepository.findByMerchantIdAndPosCategoryId(merchant.getId(),
                                categoryItem.getString("id"));

                if (category == null){
                    category = new Category();
                    category.setItemStatus(IConstant.BOOLEAN_FALSE);
                    category.setSortOrder(i+1);
                }
                   category.setMerchant(merchant);

                if (categoryItem.toString().contains("name"))
                    category.setName(categoryItem.getString("name"));

                if (categoryItem.toString().contains("id"))
                    category.setPosCategoryId(categoryItem.getString("id"));

                /*if (categoryItem.toString().contains("sortOrder"))
                    category.setSortOrder(categoryItem.getInt("sortOrder"));
                else
                	category.setSortOrder(0);*/
                
                

                if (categoryItem.toString().contains("items")) {
                    JSONObject itemJsonObject = categoryItem.getJSONObject("items");

                    JSONArray itemJsonArray = itemJsonObject.getJSONArray("elements");
                    Set<Item> items = getItemForCategory(itemJsonArray, merchant, clover);
                    if (!items.isEmpty() || items != null) {
                        category.setItems(items);
                    }
                }
                
                categoryRepository.save(category);
                categories.add(category);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return categories;
    }

    public void saveCategory(Clover clover, Merchant merchant) {
        String responseString = CloverUrlUtil.getAllCategories(clover);
        if (!responseString.isEmpty() && responseString != null) {
            createCategoryObject(responseString, merchant, clover);
        }
    }

    public List<Category> getAllCategory(Integer merchantId) {
        return categoryRepository.findByMerchantId(merchantId);
    }

    public Item getItemByItemID(String itemId, Merchant merchant) {
        return itemRepository.findByPosItemIdAndMerchantId(itemId, merchant.getId());
    }

    public void saveItem(Clover clover, Merchant merchant) {
        String responseString = CloverUrlUtil.getAllItem(clover, merchant);
        createItemObject(responseString, merchant);
    }

    public String createCustomer(Customer customer, Clover clover) {
        HttpPost postRequest = null;
        StringBuilder responseBuilder = new StringBuilder();
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            String lastName = "";
            String emailPosId = "";
            String phonePosId = "";
            if (customer.getLastName() != null)
                lastName = customer.getLastName();
            if (customer.getCustomerPosId() != null) {
                postRequest = new HttpPost(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
                                + "/customers/" + customer.getCustomerPosId()
                                + "?expand=addresses,emailAddresses,phoneNumbers&access_token=" + clover.getAuthToken());
                if (customer.getEmailPosId() != null && customer.getPhonePosId() != null) {
                    emailPosId = customer.getEmailPosId();
                    phonePosId = customer.getPhonePosId();
                }
            } else {
                postRequest = new HttpPost(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
                                + "/customers?expand=addresses,emailAddresses,phoneNumbers&access_token="
                                + clover.getAuthToken());
            }

            /*
             * List<AddressVO> addresses = new ArrayList<AddressVO>(); for(Address address:customer.getAddresses()){
             * AddressVO addressVO = new AddressVO(); addressVO.setAddress1(address.getAddress1());
             * addressVO.setAddress2(address.getAddress2()); addressVO.setAddress3(address.getAddress3());
             * addressVO.setZip(address.getZip()); addressVO.setState(address.getState());
             * addressVO.setCity(address.getCity()); addressVO.setCountry(address.getCountry()); addressVO.setId(""); }
             */
            String customerwholeAddress = "";
            String comma = "";
            if (customer.getAddresses() != null && customer.getAddresses().size() > 0) {
                for (Address address : customer.getAddresses()) {
                    String addressPosId = "";
                    if (address.getAddressPosId() != null) {
                        addressPosId = address.getAddressPosId();
                    }
                    if (address.getZip() != null && !address.getZip().isEmpty()) {
                        String customerAddress = "{		\"zip\": \"" + address.getZip()
                                        + "\",		\"country\": \"\",		\"address3\": \"\",		\"address2\": \""
                                        + address.getAddress2() + "\",		\"city\": \"" + address.getCity()
                                        + "\",		\"address1\": \"" + address.getAddress1() + "\",		\"id\": \""
                                        + addressPosId + "\",		\"state\": \"" + address.getState() + "\"	}";

                        customerwholeAddress = "\"addresses\": [" + comma + customerAddress + "],";
                        comma = ",";
                    }
                }

            }
            String customerJson = "{\"customerSince\": \"\",	\"firstName\": \"" + customer.getFirstName()
                            + "\",	\"lastName\": \"" + lastName + "\",	" + customerwholeAddress
                            + "	\"emailAddresses\": [{		\"emailAddress\": \"" + customer.getEmailId()
                            + "\",		\"id\": \"" + emailPosId
                            + "\",		\"verifiedTime\": \"\"	}],	\"phoneNumbers\": [{		\"phoneNumber\": \""
                            + customer.getPhoneNumber() + "\" ,		\"id\": \"" + phonePosId + "\"	}]}";
            StringEntity input = new StringEntity(customerJson);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                responseBuilder.append(line);
            }
            System.out.println(responseBuilder.toString());
            System.out.println("Customer created/updated on clover .... \n" + responseBuilder.toString());
            return responseBuilder.toString();
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * Post order on clover
     */
    public String postOrderOnClover(CloverOrderVO cloverOrderVO, Clover clover) {
        OrderVO orderVO = cloverOrderVO.getOrderVO();
        List<OrderItemVO> orderItemVOs = cloverOrderVO.getOrderItemVOs();
        Gson gson = new Gson();
        String orderJson = gson.toJson(orderVO);
        String orderResponse = CloverUrlUtil.postOrderOnClover(orderJson, clover);
        JSONObject jsonObject = new JSONObject(orderResponse);
       // JsonParser parser = new JsonParser();
        Double orderDiscount=0.0;
        String orderDiscountName ="";
        Double itemTotalDiscount=0.0;
        Double itemDiscount=0.0;
        String itemDiscountName= "";
        
        ArrayList<ItemDto> items= new ArrayList<ItemDto>();
        ArrayList<Map<String,Object>> allDiscounts= new ArrayList<Map<String,Object>>();
       
        String itemForDiscount = cloverOrderVO.getItemsForDiscount();
        String listOfALLDiscounts= cloverOrderVO.getListOfALLDiscounts();
        if(itemForDiscount != null &&!itemForDiscount.isEmpty() &&!itemForDiscount.equals("") && listOfALLDiscounts !=null  &&!listOfALLDiscounts.isEmpty() &&!listOfALLDiscounts.equals("")){
        	
        	JSONArray jsonitemForDiscountArray = new JSONArray(itemForDiscount);
        	JSONArray jsonlistOfALLDiscountsArray = new JSONArray(listOfALLDiscounts);
        	System.out.println(listOfALLDiscounts);
        	
        	for(int i=0 ; i<jsonitemForDiscountArray.length() ; i++){
        		JSONObject object = jsonitemForDiscountArray.getJSONObject(i);
        		ItemDto itemDto = new ItemDto();
        		itemDto.setQunatity(object.getInt("qunatity"));
        		itemDto.setTotalQunatity(object.getInt("totalQunatity"));
        		itemDto.setDiscount(object.getDouble("discount"));
        		itemDto.setPrice(object.getDouble("price"));
        		itemDto.setOriginalPrice(object.getDouble("originalPrice"));
        		itemDto.setItemModifierPrice(object.getDouble("itemModifierPrice"));
        		itemDto.setItemPosId(object.getString("itemPosId"));
        		itemDto.setDiscountName(object.getString("discountName"));
        		items.add(itemDto);
        	}
        	
        	for(int i=0 ; i<jsonlistOfALLDiscountsArray.length(); i++){
        		JSONObject object = jsonlistOfALLDiscountsArray.getJSONObject(i);
        		 Map<String,Object> discountMap= new HashMap<String, Object>();
        		discountMap.put("voucherCode", object.getString("voucherCode"));
	    		discountMap.put("discount", object.getDouble("discount"));
	    		discountMap.put("inventoryLevel", object.getString("inventoryLevel"));
	    		discountMap.put("discountType", object.getString("discountType"));
	    		allDiscounts.add(discountMap);
        	}
        }
       
       
        if(allDiscounts.size() >0){
        	
        	
        	for(Map<String,Object> allDiscount : allDiscounts){
        		System.out.println("discount value"+ allDiscount.get("discount")); 
        		Double discount = (Double)allDiscount.get("discount");	
        		String inventoryLevel= (String)allDiscount.get("inventoryLevel");
        		String discountName = (String)allDiscount.get("voucherCode");
        		if(inventoryLevel != null && discount != null && discountName !=null)
        		{
        			
        			if(inventoryLevel.equals("order") )
        			{
        				orderDiscount= orderDiscount + discount;
        				orderDiscountName = orderDiscountName +" "+ discountName;
        				
        			}else if(inventoryLevel.equals("item") || inventoryLevel.equals("category")){
        				itemTotalDiscount = itemTotalDiscount + discount;
        				
        			}
        		}
        	}
        	
        }
        
       
       
        //ArrayList itemForDiscountlist = parser.parse(ArrayList.class, itemForDiscount);
        
        if (jsonObject.toString().contains("id")) {
            String cloverOrderId = jsonObject.getString("id");
            System.out.println("cloverOrderId--> " + cloverOrderId);

            String updateOrderResponse = CloverUrlUtil.updatetOrderOnClover(orderJson, clover, cloverOrderId);
            System.out.println("Order Update response -" + updateOrderResponse);
            int k=0;
            for (OrderItemVO orderItemVO : orderItemVOs) {
            	
            	
            	
                String orderItemJson = gson.toJson(orderItemVO);
                JSONObject orderItemJsonObject = new JSONObject(orderItemJson);
                Integer qty = Integer.parseInt(orderItemJsonObject.getString("unitQty"));
                String orderLineItemId="";
                ItemDto item=null;
                if(itemTotalDiscount > 0){
            		//for(ItemDto  itemDto :items){
                	ItemDto  itemDto= items.get(k++);
            			if(itemDto!=null && itemDto.getItemPosId().equals(orderItemVO.getItem().getId())){
            				itemDiscount=itemDto.getDiscount();
            				itemDiscountName=itemDto.getDiscountName();
            				item=itemDto;
            				//break;
            			}
            		//}
                }
                for (int i = 1; i <= qty; i++) {
                    String OrderLineitemResponse = CloverUrlUtil.postOrderLineItemOnClover(orderItemJson, clover,
                                    cloverOrderId);

                    JSONObject OrderLineitemResponseJsonObject = new JSONObject(OrderLineitemResponse);
                    if(OrderLineitemResponseJsonObject.toString().contains("id")){
                    orderLineItemId = OrderLineitemResponseJsonObject.getString("id");
                    List<Modifications> modificationsList = orderItemVO.getModifications();
                    for (Modifications modifications : modificationsList) {
                        String modificationsJson = gson.toJson(modifications);
                        CloverUrlUtil.postModifiersWithLineItemOnClover(modificationsJson, clover, orderLineItemId,
                                        cloverOrderId);
                    }
                    System.out.println("orderLineItemId--> " + orderLineItemId);
                    
                  //write discount code here
                   
                				
                				if(itemDiscount>0){
                					System.out.println("ITEMDISCOUNT VALUE IS::::"+itemDiscount);
                					double d=itemDiscount*100;
                					Long itemLongDiscount=Math.round(d);
                					double itemPrice=item.getOriginalPrice()+item.getItemModifierPrice();
                					if(item!=null && itemDiscount>=itemPrice){
                						itemDiscount=itemDiscount-itemPrice;
                					}else{
                						itemDiscount=0.0;
                					}
                					CloverDiscountVO cloverDiscountVO= new CloverDiscountVO();
                		        	
                		        	
                		        			
                		        			cloverDiscountVO.setAmount("-"+itemLongDiscount.toString());
                		        			cloverDiscountVO.setPercentage("");
                		        		
                		        	
                		        	
                		        	
                		        	cloverDiscountVO.setName("Discount:"+itemDiscountName);
                		        	
                		        	String itemDiscountJson="";
                		        	
                		        	Gson gson2 = new Gson();
                		        	itemDiscountJson=gson2.toJson(cloverDiscountVO);
                		        	String orderDiscountResponse=CloverUrlUtil.postItemDiscountOnClover(itemDiscountJson, clover, cloverOrderId,orderLineItemId);
                	            	 System.out.println("orderDiscountResponse-->"+orderDiscountResponse);
                		        	
                		        }
                				
                			
                    
                    
                    }
                }
                
            }
            
            if(orderDiscount>0){
             CloverDiscountVO cloverDiscountVO=new CloverDiscountVO();
            if(cloverDiscountVO!=null){
            	Long orderLongDiscount=(long) (orderDiscount*100);
            	cloverDiscountVO.setAmount("-"+orderLongDiscount.toString());
    			cloverDiscountVO.setPercentage("");
    		   	cloverDiscountVO.setName("Discount:"+orderDiscountName);
            	String orderDiscountJson = gson.toJson(cloverDiscountVO);
            	 String orderDiscountResponse=CloverUrlUtil.postOrderDiscountOnClover(orderDiscountJson, clover, cloverOrderId);
            	 System.out.println("orderDiscountResponse-->"+orderDiscountResponse);
            }}
        }else{
        	MailSendUtil.orderFaildMail("Merchant ->"+clover.getMerchantId()+" , Order API Response -> "+orderResponse);
        }
        return orderResponse;
    }

	public String getMerchantData(String orderId, String merchantId) {
		System.out.println("cloverServiceimpl");
    	Clover clover = new  Clover();
    	clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
        clover.setUrl(IConstant.CLOVER_URL);
        //clover.setUrl("http://localhost:8080");
    	Customer customer = new Customer();
        OrderR orderR = new OrderR();
        Merchant newMerchant = merchantRepository.findByPosMerchantId(merchantId);
        if(newMerchant!=null)
        {
        	clover.setAuthToken(newMerchant.getAccessToken());
        	clover.setMerchantId(newMerchant.getPosMerchantId());
        	String orderResponse = CloverUrlUtil.getFeedbackClover(clover,orderId);
        	
        	JSONObject jsonObj = new JSONObject(orderResponse);
        	
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
        		//orderR.setCreatedOn();
        		
        		
        	}
        	 customer = customerrRepository.save(customer);
        		orderR = ordeRepo.save(orderR);
        		System.out.println("custId-"+customer.getId()+" ordId-"+orderR.getId());
        		String custId = EncryptionDecryptionUtil.encryption(String.valueOf(customer.getId()));
        		String orderid = EncryptionDecryptionUtil.encryption(String.valueOf(orderR.getId()));
        		System.out.println("cloverServiceimpl before redirect");
        		return "/feedbackForm?customerId="+custId+"&orderId="+orderid;
        		// return "redirect:"+UrlConstant.WEB_BASE_URL+"/feedbackForm?customerId="+custId+"&orderId="+orderid;
        		//List<CustomerFeedback> custFeedback = kritiqService.findByCustomerIdAndOrderId(customerId, orderid);
        	}
        }
       
        return null;
	}
}
