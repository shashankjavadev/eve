package com.foodkonnekt.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.MerchantSubscription;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.model.OpeningClosingDay;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.OrderType;
import com.foodkonnekt.model.PaymentMode;
import com.foodkonnekt.model.Role;
import com.foodkonnekt.model.Subscription;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.OpeningClosingDayRepository;
import com.foodkonnekt.repository.OpeningClosingTimeRepository;
import com.foodkonnekt.repository.OrderTypeRepository;
import com.foodkonnekt.repository.PaymentModeRepository;
import com.foodkonnekt.repository.TaxRateRepository;

@Service
@Transactional
public class JsonUtil {

    @Autowired
    private PaymentModeRepository paymentModeRepository;

    @Autowired
    private TaxRateRepository taxRateRepository;

    @Autowired
    private OrderTypeRepository orderTypeRepository;

    @Autowired
    private OpeningClosingDayRepository openingClosingDayRepository;

    @Autowired
    private OpeningClosingTimeRepository openingClosingTimeRepository;

    @Autowired
    private ItemmRepository itemRepository;

    /**
     * Convert json string to Merchant instance
     * 
     * @param merchantDetails
     * @param oldMerchant
     * @return Merchant instance
     */
    public static Merchant setMerchant(String merchantDetails, Merchant merchant) {
        Vendor vendor = null;
        Role role = null;
        try {
            JSONObject jObject = new JSONObject(merchantDetails);
            
            if (merchant.getOwner() == null) {
                vendor = new Vendor();
                role = new Role();
            } else {
                vendor = merchant.getOwner();
                role = vendor.getRole();
            }
            
            /*if(jObject.toString().contains("owner")){
            JSONObject employeeJsonObject = jObject.getJSONObject("employees");
            JSONArray employeeJSONArray = employeeJsonObject.getJSONArray("elements");
            if (employeeJSONArray != null) {
                for (Object jObj : employeeJSONArray) {
                    JSONObject employeeObject = (JSONObject) jObj;
                    

                    if (employeeObject.toString().contains("role")){
                    	if(employeeObject.getString("role").equals("EMPLOYEE"))
                    	merchant.setEmployeePosId(employeeObject.getString("id"));
                    	break;
                    }
                    
                }
            }
            }*/
            
            if(jObject.toString().contains("owner")){
            	
                JSONObject owner = jObject.getJSONObject("owner");
            vendor.setName(owner.getString("name"));
            vendor.setEmail(owner.getString("email"));
            role.setRoleName(owner.getString("role"));}
            role.setId(2);
            vendor.setRole(role);
            if(jObject.toString().contains("name"))
            merchant.setName(jObject.getString("name"));
            
            if(jObject.toString().contains("phoneNumber"))
            merchant.setPhoneNumber(jObject.getString("phoneNumber"));
            
            if(jObject.toString().contains("id"))
            merchant.setPosMerchantId(jObject.getString("id"));
            
            merchant.setOwner(vendor);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return merchant;
    }

    public static MerchantSubscription setSubscription(String billingInfo) {
    	MerchantSubscription merchantSubscription = new MerchantSubscription();
    	Subscription subscription = new Subscription();
        try {
            JSONObject jObject = new JSONObject(billingInfo);
            if (jObject.has("appSubscription")) {
                JSONObject subscriptionJson = jObject.getJSONObject("appSubscription");
                subscription.setSubscriptionPlanId(subscriptionJson.getString("id"));
                merchantSubscription.setSubscription(subscription);
                if(jObject.toString().contains("billingStartTime")){
                long billingStartTime=jObject.getLong("billingStartTime");
                Date billingStartDate = new Date(billingStartTime);
                merchantSubscription.setBillingStartDate(billingStartDate);
                System.out.println("billingStartDate-> "+billingStartDate);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return merchantSubscription;
    }

    /**
     * Set address
     * 
     * @param address
     * @param merchant
     * @param address2
     * @return Address instance
     */
    public static Address setAddress(String addressJson, Merchant merchant, Address address) {
        JSONObject jObject = new JSONObject(addressJson);
        if (addressJson.contains("address1"))
            address.setAddress1(jObject.getString("address1"));
        if (addressJson.contains("address2"))
            address.setAddress2(jObject.getString("address2"));
        if (addressJson.contains("address3"))
            address.setAddress3(jObject.getString("address3"));
        if (addressJson.contains("city"))
            address.setCity(jObject.getString("city"));
        if (addressJson.contains("state"))
            address.setState(jObject.getString("state"));
        if (addressJson.contains("country"))
            address.setCountry(jObject.getString("country"));
        if (addressJson.contains("zip") && !jObject.getString("zip").isEmpty()&&!jObject.getString("zip").equals("") )
            address.setZip(jObject.getString("zip"));
        else
        	address.setZip(null);
        
        if (addressJson.contains("phoneNumber"))
        	merchant.setPhoneNumber(jObject.getString("phoneNumber"));
        
        
        address.setMerchant(merchant);
        return address;
    }

    /**
     * Set paymentMode
     * 
     * @param paymentMode
     * @param merchant
     * @return PaymentMode instance
     */
    public List<PaymentMode> setPaymentMode(String paymentModeJson, Merchant merchant) {
        JSONObject jObject = new JSONObject(paymentModeJson);
        if(jObject.has("elements")){
        JSONArray jSONArray = jObject.getJSONArray("elements");
        List<PaymentMode> paymentModeList = new ArrayList<PaymentMode>();
        for (Object jObj : jSONArray) {
            JSONObject paymentJson = (JSONObject) jObj;
            List<PaymentMode> paymentModes = paymentModeRepository.findByMerchantIdAndPosPaymentModeId(merchant.getId(),
                            paymentJson.getString("id"));
           if(paymentModes!=null && paymentModes.size()>0){
            for(PaymentMode paymentMode:paymentModes){
            if (paymentJson.toString().contains("label")){
                paymentMode.setLabel(paymentJson.getString("label"));
                /*if(paymentJson.getString("label").equals("Credit Card"))
                	paymentMode.setAllowPaymentMode(1);
                else
                	paymentMode.setAllowPaymentMode(0);*/
            }
            if (paymentJson.toString().contains("labelKey"))
                paymentMode.setLabelKey(paymentJson.getString("labelKey"));
            if (paymentJson.toString().contains("id"))
                paymentMode.setPosPaymentModeId(paymentJson.getString("id"));
            paymentMode.setMerchant(merchant);
            paymentModeList.add(paymentMode);
        }}else{
        	PaymentMode paymentMode=new PaymentMode();
        	if (paymentJson.toString().contains("label")){
                paymentMode.setLabel(paymentJson.getString("label"));
                if(paymentJson.getString("label").equals("Credit Card"))
                	paymentMode.setAllowPaymentMode(1);
                else
                	paymentMode.setAllowPaymentMode(0);
            }
            if (paymentJson.toString().contains("labelKey"))
                paymentMode.setLabelKey(paymentJson.getString("labelKey"));
            if (paymentJson.toString().contains("id"))
                paymentMode.setPosPaymentModeId(paymentJson.getString("id"));
            paymentMode.setMerchant(merchant);
            paymentModeList.add(paymentMode);
        }
        }
        
        return paymentModeList;
        }else{
        	return null;
        }
    }

    /**
     * Set paymentMode
     * 
     * @param paymentMode
     * @param merchant
     * @return OrderType instance
     */
    public String createOrderTypeOnClover(String orderType,Clover clover,String systemOrderTypeId) {
        HttpPost postRequest = new HttpPost(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
                        + "/order_types?access_token=" + clover.getAuthToken());
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            String customerJson = "{"
            		+ " \"id\": \"\","
            		+ " \"label\": \""+orderType+"\","
            		+ " \"taxable\": true,"
            		+ "\"isDefault\": false,"
            		+ " \"filterCategories\": false,"
            		+ "\"isHidden\": false,"
            		+ " \"fee\": 0,"
            		+ " \"minOrderAmount\": 0,"
            		+ " \"maxOrderAmount\": 0,"
            		+ " \"avgOrderTime\": 0,"
            		+ " \"hoursAvailable\": \"BUSINESS\","
            		+ "\"systemOrderTypeId\": \""+systemOrderTypeId+"\""
            		+ " }";
            StringEntity input = new StringEntity(customerJson);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);
            HttpEntity entity=response.getEntity();
            String responseString = EntityUtils.toString(entity, "UTF-8");
            System.out.println("Output from Server .... \n" + responseString);
	         return responseString;
            
        } catch (Exception e) {
        	return null;
        }
        
    }
    
    
    
    public List<OrderType> setOrderType(String orderTypeJson, Merchant merchant,Clover clover) {
       JSONObject jObject = new JSONObject(orderTypeJson);
        JSONArray jSONArray = jObject.getJSONArray("elements");
        
        for (Object jObj : jSONArray) {
            JSONObject orderTypes = (JSONObject) jObj;
            

            if (orderTypes.toString().contains("label")){
            	if(orderTypes.getString("label").equals("Foodkonnekt Online Delivery")||orderTypes.getString("label").equals("Foodkonnekt Online Pickup")||orderTypes.getString("label").equals("Online Delivery") || orderTypes.getString("label").equals("Future Pickup") ||orderTypes.getString("label").equals("Future Delivery")){
            		
            		OrderType orderType =checkOrderType(merchant,orderTypes.getString("label"));
            		if(orderType!=null){
            		orderTypeRepository.delete(orderType);
            		}
            		String orderTypePosId=orderTypes.getString("id");
            		String orderTypeDeleteResponse=CloverUrlUtil.deleteOrderType(merchant.getPosMerchantId(), merchant.getAccessToken(), orderTypePosId);
            		
            	    System.out.println("ordertype deleted"+orderTypeDeleteResponse);
            	    OrderType delivery =checkOrderType(merchant,"Foodkonnekt Online Delivery");//
            	    if(delivery!=null){
                		orderTypeRepository.delete(delivery);
                		}
                    OrderType pickUp =  checkOrderType(merchant,"Foodkonnekt Online Pickup");//
                    if(pickUp!=null){
                		orderTypeRepository.delete(pickUp);
                		}
            	}
            }
               
            
        }
        
        String orderTypeDelivery=createOrderTypeOnClover("Foodkonnekt Online Delivery",clover,"DELIVERY-TYPE");
        JSONObject orderTypeDeliveryJSONE = new JSONObject(orderTypeDelivery);
        String orderTypePickup=createOrderTypeOnClover("Foodkonnekt Online Pickup",clover,"PICK-UP-TYPE");
        JSONObject orderTypePickupJSONE = new JSONObject(orderTypePickup);
        List<OrderType> orderTypeList = new ArrayList<OrderType>();
        OrderType delivery =checkOrderType(merchant,"Foodkonnekt Online Delivery");// 
        OrderType pickUp =  checkOrderType(merchant,"Foodkonnekt Online Pickup");//
           
        if(delivery!=null){
            orderTypeList.add(delivery);
        }else{
        	delivery=setOrderType(orderTypeDeliveryJSONE,merchant,"Foodkonnekt Online Delivery");
        	
        	if(delivery!=null)
        	orderTypeList.add(delivery);
        }
        if(pickUp!=null){
            orderTypeList.add(pickUp);
        }else{
        	pickUp= setOrderType(orderTypePickupJSONE,merchant,"Foodkonnekt Online Pickup");
        	
        	if(pickUp!=null)
        	orderTypeList.add(pickUp);
        }
            
            
        
        return orderTypeList;
    }
    
    public OrderType checkOrderType(Merchant merchant,String orderType){
   return orderTypeRepository.findByMerchantIdAndLabel(merchant.getId(),orderType);
    	 
    }
    
    public OrderType setOrderType(JSONObject singleOrderTypeJson,Merchant merchant,String orderTypeLabel){
    	if(singleOrderTypeJson.toString().contains("label") && (singleOrderTypeJson.getString("label").equals(orderTypeLabel))){
            OrderType orderType = orderTypeRepository.findByMerchantIdAndPosOrderTypeId(merchant.getId(),
                            singleOrderTypeJson.getString("id"));
            if (orderType == null)
                orderType = new OrderType();
               
            if (singleOrderTypeJson.toString().contains("id"))
                orderType.setPosOrderTypeId(singleOrderTypeJson.getString("id"));

            if (singleOrderTypeJson.toString().contains("label"))
                orderType.setLabel(singleOrderTypeJson.getString("label"));

            if (singleOrderTypeJson.toString().contains("taxable"))
                orderType.setTaxable(singleOrderTypeJson.getBoolean("taxable"));

            if (singleOrderTypeJson.toString().contains("hoursAvailable"))
                orderType.setHoursAvailable(singleOrderTypeJson.getString("hoursAvailable"));

            orderType.setMerchant(merchant);
            try {
                if (singleOrderTypeJson.toString().contains("isHidden"))
                    orderType.setIsHidden(singleOrderTypeJson.getBoolean("isHidden"));

                if (singleOrderTypeJson.toString().contains("fee"))
                    orderType.setFee(singleOrderTypeJson.getDouble("fee"));

                if (singleOrderTypeJson.toString().contains("avgOrderTime"))
                    orderType.setAvgOrderTime(singleOrderTypeJson.getDouble("avgOrderTime"));

                if (singleOrderTypeJson.toString().contains("maxOrderAmount"))
                    orderType.setMaxOrderAmount(singleOrderTypeJson.getDouble("maxOrderAmount"));

                if (singleOrderTypeJson.toString().contains("minOrderAmount"))
                    orderType.setMinOrderAmount(singleOrderTypeJson.getDouble("minOrderAmount"));
            } catch (Exception e) {
                System.out.println(e);
            }

           return orderType;
           
            }
    	return null;
    }

    /**
     * convert tax rate json to TaxRate instance
     * 
     * @param taxRates
     * @param merchant
     * @return List<TaxRate>
     */
    public List<TaxRates> setTaxRates(String taxRates, Merchant merchant) {
        JSONObject jObject = new JSONObject(taxRates);
        if(jObject.toString().contains("elements")){
        JSONArray jSONArray = jObject.getJSONArray("elements");
        List<TaxRates> rates = new ArrayList<TaxRates>();
        for (Object jObj : jSONArray) {
            JSONObject taxRate = (JSONObject) jObj;
            TaxRates rate = taxRateRepository
                            .findByMerchantIdAndPosTaxRateId(merchant.getId(), taxRate.getString("id"));

            if (rate == null)
                rate = new TaxRates();

            if (taxRate.toString().contains("name"))
                rate.setName(taxRate.getString("name"));

            if (taxRate.toString().contains("id"))
                rate.setPosTaxRateId(taxRate.getString("id"));

            if (taxRate.toString().contains("rate"))
                rate.setRate(taxRate.getDouble("rate") / 100000);

            rate.setMerchant(merchant);

            if (taxRate.toString().contains("isDefault"))
                if (taxRate.getBoolean("isDefault")) {
                    rate.setIsDefault(IConstant.BOOLEAN_TRUE);
                } else {
                    rate.setIsDefault(IConstant.BOOLEAN_FALSE);
                }
            rates.add(rate);
        }
        return rates;
        }
        return null;
    }

    /**
     * Convert modifier json to ModifierGroup instance
     * 
     * @param modifierJsonObject
     * @param merchant
     * @return ModifierGroup instance
     */
    public static ModifierGroup setModifier(JSONObject modifierJsonObject, Merchant merchant) {
        ModifierGroup modifierGroup = new ModifierGroup();
        modifierGroup.setMerchant(merchant);

        if (modifierJsonObject.toString().contains("name"))
            modifierGroup.setName(modifierJsonObject.getString("name"));

        if (modifierJsonObject.toString().contains("id"))
            modifierGroup.setPosModifierGroupId(modifierJsonObject.getString("id"));

        if (modifierJsonObject.toString().contains("showByDefault"))
            if (modifierJsonObject.getBoolean("showByDefault")) {
                modifierGroup.setShowByDefault(IConstant.BOOLEAN_TRUE);
            } else {
                modifierGroup.setShowByDefault(IConstant.BOOLEAN_FALSE);
            }
        return modifierGroup;
    }

    /**
     * Set Item
     * 
     * @param modifierJsonObject
     * @return
     */
    public static Item getItem(JSONObject itemObject) {
        Item item = null;
        item = new Item();

        if (itemObject.toString().contains("id"))
            item.setPosItemId(itemObject.getString("id"));

        if (itemObject.toString().contains("hidden"))
            if (itemObject.getBoolean("hidden")) {
                item.setIsHidden(itemObject.getBoolean("hidden"));
            } else {
                item.setIsHidden(itemObject.getBoolean("hidden"));
            }

        if (itemObject.toString().contains("isRevenue"))
            if (itemObject.getBoolean("isRevenue")) {
                item.setIsRevenue(itemObject.getBoolean("isRevenue"));
            } else {
                item.setIsRevenue(itemObject.getBoolean("isRevenue"));
            }

        if (itemObject.toString().contains("name"))
            item.setName(itemObject.getString("name"));

        if (itemObject.toString().contains("price"))
            item.setPrice(itemObject.getDouble("price")/100);

        if (itemObject.toString().contains("priceType"))
            item.setPriceType(itemObject.getString("priceType"));

        if (itemObject.toString().contains("modifiedTime"))
            item.setModifiedTime(new Date(itemObject.getLong("modifiedTime")));
        
        

        return item;
    }

    /**
     * Set Modifier
     * 
     * @param modifierJsonObject
     * @param modifierGroup
     * @param merchant
     * @return Modifier instance
     */
    public static Set<Modifiers> getModifiers(JSONObject modifierJsonObject, ModifierGroup modifierGroup,
                    Merchant merchant) {
        JSONObject modifiersJsonObject = modifierJsonObject.getJSONObject("modifiers");
        JSONArray modifiersJSONArray = modifiersJsonObject.getJSONArray("elements");
        Set<Modifiers> modifiers = new HashSet<Modifiers>();
        if (modifiersJSONArray != null) {
            for (Object jObj : modifiersJSONArray) {
                JSONObject itemObject = (JSONObject) jObj;
                Modifiers modifier = new Modifiers();

                if (itemObject.toString().contains("name"))
                    modifier.setName(itemObject.getString("name"));

                if (itemObject.toString().contains("id"))
                    modifier.setPosModifierId(itemObject.getString("id"));

                if (itemObject.toString().contains("price"))
                    modifier.setPrice(itemObject.getDouble("price")/100);
                // modifier.setModifierGroup(modifierGroup);
                modifier.setMerchant(merchant);
                modifiers.add(modifier);
            }
        }
        return modifiers;
    }

    /**
     * Set Item object
     * 
     * @param itemmJson
     * @return Item instance
     */
    public static List<Item> setItems(String itemmJson, Merchant merchant) {
        List<Item> items = new ArrayList<Item>();
        JSONObject jsonObject = new JSONObject(itemmJson);
        JSONArray itemJsonArray = jsonObject.getJSONArray("elements");
        for (Object jObj : itemJsonArray) {
            JSONObject itemObject = (JSONObject) jObj;
            Item item = new Item();
            item.setName(itemObject.getString("name"));
            if (itemObject.getBoolean("hidden")) {
                item.setIsHidden(itemObject.getBoolean("hidden"));
            } else {
                item.setIsHidden(itemObject.getBoolean("hidden"));
            }
            item.setPosItemId(itemObject.getString("id"));
            item.setPrice(itemObject.getDouble("price"));
            item.setPriceType(itemObject.getString("priceType"));
            if (itemObject.getBoolean("isRevenue")) {
                item.setIsRevenue(itemObject.getBoolean("isRevenue"));
            } else {
                item.setIsRevenue(itemObject.getBoolean("isRevenue"));
            }
            item.setModifiedTime(new Date(itemObject.getLong("modifiedTime")));
            item.setMerchant(merchant);
            items.add(item);
        }
        return items;
    }

    /**
     * Set time
     * 
     * @param day
     * @param openingClosingDay
     * @param openingClosing
     * @return
     */
    public List<OpeningClosingTime> getOpeingClosingTime(String day, OpeningClosingDay openingClosingDay,
                    JSONObject openingClosing) {
    	
        List<OpeningClosingTime> dayTimis = openingClosingTimeRepository.findByOpeningClosingDayId(openingClosingDay
                        .getId());
        openingClosingTimeRepository.delete(dayTimis);
        JSONObject sundayJsonObject = openingClosing.getJSONObject(day);
        JSONArray sundayArray = sundayJsonObject.getJSONArray("elements");
        List<OpeningClosingTime> openingClosingTimes = new ArrayList<OpeningClosingTime>();
        if(sundayArray.length()==0){
        	openingClosingDay.setIsHoliday(1);
        }else{
        	openingClosingDay.setIsHoliday(0);
        for (Object sunday : sundayArray) {
            JSONObject sundayObject = (JSONObject) sunday;
            OpeningClosingTime openingClosingTime = new OpeningClosingTime();

            if (sundayObject.toString().contains("start")) {

                // long l= sundayObject.getLong("start");
                Long l = new Long(sundayObject.getLong("start"));
               // Double dayTime = l.doubleValue() / 100;
                String time = convertTime(l);
                openingClosingTime.setStartTime(time);
            }
            if (sundayObject.toString().contains("end")) {
                Long l = new Long(sundayObject.getLong("end"));
                //Double dayTime = l.doubleValue() / 100;
                String time = convertTime(l);
                openingClosingTime.setEndTime(time);
            }

            openingClosingTime.setOpeningClosingDay(openingClosingDay);
            openingClosingTimes.add(openingClosingTime);
        }
        }
        return openingClosingTimes;
    }
    
    String convertTime(Long l){
    	String time = l.toString();
        if(time.length()==4)
    	time = time.substring(0, 2) + ":" + time.substring(2, time.length());
        
        if(time.length()==3)
        	time = "0"+time.substring(0, 1) + ":" + time.substring(1, time.length());
        if(time.length()==2)
        	time = "00" + ":" + time;
        if(time.length()==1)
        	time = "00" + ":" + "0"+time;
        
        return time;
    }

    /**
     * Set day
     * 
     * @param openingClosing
     * @param string
     * @param merchant
     * @return
     */
    public OpeningClosingDay getDay(JSONObject openingClosing, String day, Merchant merchant) {
        OpeningClosingDay openingClosingDay = openingClosingDayRepository.findByMerchantIdAndDay(merchant.getId(), day);
        if (openingClosingDay == null)
            openingClosingDay = new OpeningClosingDay();

        openingClosingDay.setOpeningClosingPosId(openingClosing.getString("id"));
        openingClosingDay.setMerchant(merchant);
        openingClosingDay.setDay(day);
        return openingClosingDay;
    }

    public Item createNewLineItem(JSONObject itemJson, Merchant merchant) {

        Item item = itemRepository.findByPosItemIdAndMerchantId(itemJson.getString("id"), merchant.getId());
        if (item == null){
            item = new Item();
            item.setItemStatus(IConstant.BOOLEAN_FALSE);
        }

        
        item.setMerchant(merchant);
        if (itemJson.toString().contains("name"))
            item.setName(itemJson.getString("name"));

        if (itemJson.toString().contains("id"))
            item.setPosItemId(itemJson.getString("id"));

        if (itemJson.toString().contains("hidden"))
            item.setIsHidden(itemJson.getBoolean("hidden"));

        if (itemJson.toString().contains("price"))
            item.setPrice(itemJson.getDouble("price") / 100);

        if (itemJson.toString().contains("priceType"))
            item.setPriceType(itemJson.getString("priceType"));

        if (itemJson.toString().contains("defaultTaxRates"))
            item.setIsDefaultTaxRates(itemJson.getBoolean("defaultTaxRates"));

        if (itemJson.toString().contains("unitName"))
            item.setUnitName(itemJson.getString("unitName"));

        if (itemJson.toString().contains("isRevenue"))
            item.setIsRevenue(itemJson.getBoolean("isRevenue"));

        if (itemJson.toString().contains("modifiedTime"))
            item.setModifiedTime(new Date(itemJson.getLong("modifiedTime")));

        if (itemJson.toString().contains("taxRates")) {
            JSONObject taxRateJsonObject = itemJson.getJSONObject("taxRates");
            JSONArray taxRateJsonArray = taxRateJsonObject.getJSONArray("elements");
            Set<TaxRates> taxes = new HashSet<TaxRates>();
            for (Object jObj : taxRateJsonArray) {
                JSONObject taxRate = (JSONObject) jObj;
                TaxRates rate = taxRateRepository.findByMerchantIdAndPosTaxRateId(merchant.getId(),
                                taxRate.getString("id"));

                if (rate == null) {
                    rate = new TaxRates();

                    if (taxRate.toString().contains("name"))
                        rate.setName(taxRate.getString("name"));

                    if (taxRate.toString().contains("id"))
                        rate.setPosTaxRateId(taxRate.getString("id"));

                    if (taxRate.toString().contains("rate"))
                        rate.setRate(taxRate.getDouble("rate") / 100000);

                    rate.setMerchant(merchant);

                    if (taxRate.toString().contains("isDefault"))
                        if (taxRate.getBoolean("isDefault")) {
                            rate.setIsDefault(IConstant.BOOLEAN_TRUE);
                        } else {
                            rate.setIsDefault(IConstant.BOOLEAN_FALSE);
                        }
                    taxRateRepository.save(rate);
                }

                taxes.add(rate);
            }

            item.setTaxes(taxes);
        }
        
        //System.out.println(item.getName()+" - "+item.getIsDefaultTaxRates()+" - "+ item.getTaxes().size());
        
        if( !item.getName().equals("Convenience Fee") && !item.getName().equals("Delivery Fee") )
        itemRepository.save(item);
        return item;
    }

    public void createItemObject(String itemJson, Merchant merchant) {
        try {
            JSONObject jsonObject = new JSONObject(itemJson);
            List<Item> itemList = new ArrayList<Item>();
            JSONArray jsonArray = jsonObject.getJSONArray("elements");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject categoryItem = jsonArray.getJSONObject(i);
                itemList.add(createNewLineItem(categoryItem, merchant));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
