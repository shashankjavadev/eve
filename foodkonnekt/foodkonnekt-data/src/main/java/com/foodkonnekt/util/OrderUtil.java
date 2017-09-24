package com.foodkonnekt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.foodkonnekt.clover.vo.CloverDiscountVO;
import com.foodkonnekt.clover.vo.CloverOrderVO;
import com.foodkonnekt.clover.vo.Employee;
import com.foodkonnekt.clover.vo.Item;
import com.foodkonnekt.clover.vo.Modifications;
import com.foodkonnekt.clover.vo.Modifier;
import com.foodkonnekt.clover.vo.OrderItemVO;
import com.foodkonnekt.clover.vo.OrderTypeVO;
import com.foodkonnekt.clover.vo.OrderVO;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.OpeningClosingTime;
import com.foodkonnekt.model.OrderR;
import com.google.gson.Gson;

public class OrderUtil {

    public static String findFinalOrderJson(String orderJson, String orderTotal, String instruction,
                    String orderTypePosid, Customer orderCustomer, String employeePosId,double discount,String discountType,String voucherCode,String itemPoSIdsjson,String inventoryLevel,String itemsForDiscount,String listOfALLDiscounts) {
        CloverOrderVO cloverOrderVO = new CloverOrderVO();
        CloverDiscountVO cloverDiscountVO= new CloverDiscountVO();
        OrderVO orderVO = new OrderVO();
        OrderTypeVO orderType = new OrderTypeVO();
        List<com.foodkonnekt.clover.vo.Customer> customers = new ArrayList<com.foodkonnekt.clover.vo.Customer>();
        com.foodkonnekt.clover.vo.Customer customer = new com.foodkonnekt.clover.vo.Customer();
        cloverOrderVO.setItemsForDiscount(itemsForDiscount);
        cloverOrderVO.setListOfALLDiscounts(listOfALLDiscounts);
        if(discount>0){
        	Long orderDiscount=(long) (discount*100);
        	if(discountType!=null && !discountType.isEmpty() && discountType!=""){
        		if(discountType.equals("amount")){
        			
        			cloverDiscountVO.setAmount("-"+orderDiscount.toString());
        			cloverDiscountVO.setPercentage("");
        		}else{
        			cloverDiscountVO.setAmount("");
        			long discountLong= (long)discount;
        			cloverDiscountVO.setPercentage(discountLong+"");
        		}
        	}else{
        		discountType="amount";
        		cloverDiscountVO.setAmount(orderDiscount.toString());
        	}
        	
        	
        	if(voucherCode!=null && !voucherCode.isEmpty() && voucherCode!="")
        	cloverDiscountVO.setName("Discount:"+voucherCode);
        	
        	cloverOrderVO.setCloverDiscountVO(cloverDiscountVO);
        }

        
        /*
         * List<AddressVO> addresses = new ArrayList<AddressVO>(); AddressVO address = new AddressVO();
         */

        Employee employee = new Employee();

        employee.setId(employeePosId);
        String customerPosId = "";
        if (orderCustomer != null && orderCustomer.getCustomerPosId() != null) {
            customerPosId = orderCustomer.getCustomerPosId();
            /*
             * if(orderCustomer.getAddresses()!=null && orderCustomer.getAddresses().size()>0){
             * address.setId(orderCustomer.getAddresses().get(0).getAddressPosId()); addresses.add(address);
             * customer.setAddresses(addresses); }
             */

        }

        customer.setId(customerPosId);

        customers.add(customer);
        orderVO.setCustomers(customers);
        orderVO.setEmployee(employee);
        orderType.setId(orderTypePosid);

        orderVO.setCurrency("USD");
        orderVO.setNote("From FoodKonnekt : "+instruction);
        orderVO.setState("Open");
        orderVO.setTaxRemoved(false);
        orderVO.setTestMode(false);
        if (orderTypePosid != null && !orderTypePosid.equals(""))
            orderVO.setOrderType(orderType);

        orderVO.setTitle("");

        double roundOff = Math.round(Double.valueOf(orderTotal) * 100.0) / 100.0;
        roundOff = roundOff * 100;
        roundOff = Math.round(Double.valueOf(roundOff) * 100.0) / 100.0;
        long price = new Double(roundOff).longValue();
        System.out.println(price);
        orderVO.setTotal(String.valueOf(price));

        cloverOrderVO.setOrderVO(orderVO);
      //  String orderJ = "{\n\"order\":[" + orderJson + "]}";
        System.out.println(orderJson);
        JSONObject jObject = new JSONObject(orderJson);
        JSONArray orderArray = jObject.getJSONArray("order");

        List<OrderItemVO> itemVOs = new ArrayList<OrderItemVO>();
        for (Object jObj : orderArray) {
            JSONObject orderJObject = (JSONObject) jObj;
            OrderItemVO orderItemVO = new OrderItemVO();
            orderItemVO.setUnitQty(orderJObject.getString("amount"));
            orderItemVO.setDiscountAmount("");
            Item item = new Item();
            item.setId(orderJObject.getString("itemid"));
            orderItemVO.setItem(item);
            orderItemVO.setModifications(getModifierObjects(orderJObject.getJSONArray("modifier")));
            itemVOs.add(orderItemVO);
        }
        cloverOrderVO.setOrderItemVOs(itemVOs);
        Gson gson = new Gson();
        return gson.toJson(cloverOrderVO);
    }

    /**
     * Set modifier in modifierVO
     * 
     * @param jsonArray
     * @return
     */
    private static List<Modifications> getModifierObjects(JSONArray jsonArray) {
        List<Modifications> modifications = new ArrayList<Modifications>();
        for (Object jObj : jsonArray) {
            JSONObject modifierObject = (JSONObject) jObj;
            Modifications modifier = new Modifications();
            Modifier modifierObj = new Modifier();
            modifierObj.setId(modifierObject.getString("id"));
            modifier.setModifier(modifierObj);
            modifications.add(modifier);
        }
        return modifications;
    }

    public static void main(String[] args) {
        double a = 38.3322;
        double roundOff = Math.round(a * 100.0) / 100.0;
        roundOff = roundOff * 100;
        System.out.println();
        long l = new Double(roundOff).longValue();
        System.out.println(l);
        String s = "";
        for (int i = 0; i < 2; i++) {
            s = s + "&" + i;
        }
        System.out.println(s.substring(1, s.length()));
    }

    public static OrderR getObjectFromJson(JSONObject result, Customer customer, Merchant merchant) {
        int status = IConstant.BOOLEAN_FALSE;
        OrderR orderR = new OrderR();
        orderR.setCustomer(customer);
        orderR.setMerchant(merchant);
        orderR.setIsDefaults(0);
        if (result.toString().contains("title")) {
            orderR.setOrderName(result.getString("title"));
            orderR.setOrderNote(result.getString("title"));
            status = IConstant.BOOLEAN_TRUE;
        }
        if (result.toString().contains("total")) {
            orderR.setOrderPrice(result.getDouble("total") / 100);
            status = IConstant.BOOLEAN_TRUE;
        }
        if (result.toString().contains("id")) {
            orderR.setOrderPosId(result.getString("id"));
            status = IConstant.BOOLEAN_TRUE;
        }
        if (status == IConstant.BOOLEAN_FALSE) {
            return null;
        }
        return orderR;
    }

    public static String checkOpeningClosingHour(List<OpeningClosingTime> openingClosingTimes,int avgTime,String timeZoneCode) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
       
        sdf.setTimeZone(TimeZone.getTimeZone(timeZoneCode));
        String currentTime = sdf.format(new Date());
        int count = 0;
        for (OpeningClosingTime time : openingClosingTimes) {
            try {
                boolean status = isTimeBetweenTwoTime(time.getStartTime() + ":00", time.getEndTime() + ":00",
                                currentTime,avgTime);
                if (status) {
                    count++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (count > 0) {
            return "Y";
        } else {
            return "N";
        }
    }

    /**
     * Check current time is existing between operating hours
     * 
     * @param initialTime
     * @param finalTime
     * @param currentTime
     * @return String
     * @throws ParseException
     */
    private static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String currentTime,int avgTime) {
        try {
            System.out.println(initialTime + "-" + finalTime + "-" + currentTime);
            String reg = "^([0-1][0-9]|2[0-4]):([0-5][0-9]):([0-5][0-9])$";
            if (initialTime.matches(reg) && finalTime.matches(reg) && currentTime.matches(reg)) {
                boolean valid = false;
                if (finalTime.equals("00:00:00") || finalTime.equals("24:00:00")) {
                    finalTime = "23:59:30";
                }

                // Start Time
                java.util.Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(inTime);

                // Current Time
                java.util.Date checkTime = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(checkTime);
                /*calendar3.add(Calendar.HOUR, hourDifference);
                calendar3.add(Calendar.MINUTE, minutDifference);*/

                // End Time
                java.util.Date finTime = new SimpleDateFormat("HH:mm:ss").parse(finalTime);
                Calendar calendar2 = Calendar.getInstance();
                calendar2.setTime(finTime);
                calendar2.add(Calendar.MINUTE, -avgTime);

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
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return false;
    }
    
    public static boolean isTimeBetweenTwoTime(String initialTime, String finalTime, String currentTime) {
        try {
            System.out.println(initialTime + "-" + finalTime + "-" + currentTime);
            String reg = "^([0-1][0-9]|2[0-4]):([0-5][0-9]):([0-5][0-9])$";
            if (initialTime.matches(reg) && finalTime.matches(reg) && currentTime.matches(reg)) {
                boolean valid = false;
                if (finalTime.equals("00:00:00") || finalTime.equals("24:00:00")) {
                    finalTime = "23:59:30";
                }

                // Start Time
                java.util.Date inTime = new SimpleDateFormat("HH:mm:ss").parse(initialTime);
                Calendar calendar1 = Calendar.getInstance();
                calendar1.setTime(inTime);

                // Current Time
                java.util.Date checkTime = new SimpleDateFormat("HH:mm:ss").parse(currentTime);
                Calendar calendar3 = Calendar.getInstance();
                calendar3.setTime(checkTime);
                /*calendar3.add(Calendar.HOUR, hourDifference);
                calendar3.add(Calendar.MINUTE, minutDifference);*/

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
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Find operating hours
     * 
     * @param openingClosingTimes
     * @return String
     */
    public static String findOperatingHour(List<OpeningClosingTime> openingClosingTimes) {
        String operatingHours = "";
        if (!openingClosingTimes.isEmpty()) {
            for (OpeningClosingTime time : openingClosingTimes) {
                if (time != null) {
                    operatingHours = operatingHours + "&"
                                    + convert24HoursTo12HourseFormate(time.getStartTime() + ":00") + "-"
                                    + convert24HoursTo12HourseFormate(time.getEndTime() + ":00");
                }
            }
            operatingHours = operatingHours.substring(1, operatingHours.length());
        }
        return operatingHours;
    }

    /**
     * Convert time 24 hour to 12 hour format
     * 
     * @param str
     * @return String
     */
    public static String convert24HoursTo12HourseFormate(String str) {
        SimpleDateFormat displayFormat = new SimpleDateFormat("KK:mm a");
        SimpleDateFormat parseFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = parseFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null) {
            return displayFormat.format(date);
        } else {
            return null;
        }
    }
    
    
    public static String postCouponData(String url, String couponRedeemedJson){
    	
    	System.out.println("getCouponData");
		HttpPost postRequest = new HttpPost(url);

		return postOnCoupon(postRequest, couponRedeemedJson);
    	
    }
    
    public static String postOnCoupon(HttpPost postRequest, String couponRedeemedJson)
	{
		StringBuilder responseBuilder = new StringBuilder();
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();

			StringEntity input = new StringEntity(couponRedeemedJson);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseBuilder.append(line);
			}
			System.out.println(responseBuilder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return responseBuilder.toString();
	}
    
    
}
