package com.foodkonnekt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Merchant;
import com.google.gson.Gson;

public class CloverUrlUtil {

	/**
	 * Find merchant details based on merchantId
	 * 
	 * @param clover
	 * @return
	 */
	
	public static void enableFuturOrderOnClover(String allowFutureOrder,Merchant merchant){
		Gson gson = new Gson();
   	 Map<String, Map<String, String>> notf = new HashMap<String, Map<String, String>>();
   	Map<String, String> notification = new HashMap<String, String>();
       notification.put("appEvent", "notification");
       notification.put("payload", "allowFutureOrder@#"+allowFutureOrder);

// +"@#"+tip add this to notification payload if we add tip amount to show on popup on
// notification app side
notf.put("notification", notification);
String notificationJson = gson.toJson(notf);

notificationJson = notificationJson.trim().replaceAll("\\\\+\"", "'").replace("'[", "[")
       .replace("]'", "]");

sendNotification(notificationJson,merchant.getPosMerchantId(),merchant.getAccessToken());
	}
	
	private static void sendNotification(String notificationJson, String merchantId, String accessToken) {
        HttpPost postRequest = new HttpPost(IConstant.CLOVER_V2_URL + merchantId + "/apps/" + IConstant.CLOVER_APP_ID
                        + "/notify?access_token=" + accessToken);
        System.out.println("=====" + convertToStringJson(postRequest, notificationJson));
    }
	public static String getMerchantDetails(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "?expand=owner,employees&access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	/**
	 * Find address of merchant
	 * 
	 * @param clover
	 * @return
	 */
	public static String getMerchantAddress(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/address?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	/**
	 * find billing information
	 * 
	 * @param clover
	 * @return
	 */
	public static String getBillingInfo(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/address?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	/**
	 * Convert response to json output
	 * 
	 * @param request
	 * @return
	 */
	public static String convertToStringJson(HttpGet request) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		StringBuilder responseBuilder = new StringBuilder();
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBuilder.toString();
	}

	public static String convertToStringJson(HttpPost postRequest, String customerJson) {
		StringBuilder responseBuilder = new StringBuilder();
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			StringEntity input = new StringEntity(customerJson);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			System.out.println("Output from Server .... \n" + response.getEntity().toString());
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseBuilder.append(line);
			}
			// System.out.println(responseBuilder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBuilder.toString();
	}

	public static String convertToStringJson(HttpPost request) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		StringBuilder responseBuilder = new StringBuilder();
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = rd.readLine()) != null) {
				responseBuilder.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBuilder.toString();
	}

	/**
	 * Get oder types
	 * 
	 * @param clover
	 * @return
	 */
	public static String getOderType(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/order_types?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	/**
	 * Find tax rates
	 * 
	 * @param clover
	 * @return
	 */
	public static String getTaxRate(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/tax_rates?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	/**
	 * Find opening hour
	 * 
	 * @param clover
	 * @return
	 */
	public static String getOpeningHour(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/opening_hours?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	/**
	 * Find payment modes
	 * 
	 * @param clover
	 * @return
	 */
	public static String getPaymentModes(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/tenders?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	public static String getCustomer(Clover clover, String customerId) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId() + "/customers/"
				+ customerId + "?expand=emailAddresses,phoneNumbers&access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	public static String getOrderItemsWithModifier(Clover clover, String orderId, String itemPosId) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId() + "/orders/"
				+ orderId + "/line_items/" + itemPosId + "?expand=modifications&access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	public static String getOrderItems(Clover clover, String orderId) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId() + "/orders/"
				+ orderId + "/line_items/?expand=modifications&access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	public static String getMerchantBilling(Clover clover) {
		HttpGet request = new HttpGet(IConstant.CLOVER_APP_URL + IConstant.CLOVER_APP_ID + "/merchants/"
				+ clover.getMerchantId() + "/billing_info?access_token=" + IConstant.CLOVER_APP_SECRET_KEY);
		return convertToStringJson(request);
	}

	/**
	 * Find categories
	 * 
	 * @param clover
	 * @return
	 */
	public static String getCategories(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/categories?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	/**
	 * Covert json string to object
	 * 
	 * @param request
	 * @return
	 */
	public static Merchant convertJsonStringToObject(HttpGet request) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		Merchant merchant = null;
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			Gson gson = new Gson();
			merchant = gson.fromJson(rd, Merchant.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return merchant;
	}

	public static Merchant getVendorDetails(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "?expand=owner&access_token=" + clover.getAuthToken());
		return convertJsonStringToObject(request);
	}

	/**
	 * Get modifier and modifier group
	 * 
	 * @param clover
	 * @return
	 */
	public static String getModifierAndModifierGroup(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/modifier_groups?expand=modifiers%2Citems&access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	/**
	 * Get items
	 * 
	 * @param clover
	 * @return ItemJsonString
	 */
	public static String getItems(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/items?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	public static String getAllCategories(Clover clover) {
		HttpGet request = null;
		String responseString = null;
		try {
			request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
					+ "/categories?access_token=" + clover.getAuthToken() + "&expand=items");
			HttpClient client = HttpClientBuilder.create().build();
			HttpResponse response = null;
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			return responseString;
		} catch (Exception e) {
			// logger.error("Exception occure while getting data from clover is
			// : {}", e.getStackTrace().toString());
		}

		return responseString;
	}

	/**
	 * 
	 * @param clover
	 * @return
	 */
	public static String getAllItem(Clover clover, Merchant merchant) {
		String responseString = null;
		try {
			HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
					+ "/items?limit=1000&expand=taxRates&access_token=" + clover.getAuthToken());
			HttpClient client = HttpClientBuilder.create().build();
			HttpResponse response = null;
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			return responseString;

		} catch (Exception e) {
			// logger.error("Exception occure while getting data from clover is
			// : {}", e.getStackTrace().toString());
		}
		return responseString;
	}

	public static String getCloverItem(Clover clover, Merchant merchant, String itemPOSId) {
		String responseString = null;
		try {
			HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId() + "/items/"
					+ itemPOSId + "?expand=taxRates&access_token=" + clover.getAuthToken());
			HttpClient client = HttpClientBuilder.create().build();
			HttpResponse response = null;
			response = client.execute(request);
			HttpEntity entity = response.getEntity();
			responseString = EntityUtils.toString(entity, "UTF-8");
			return responseString;

		} catch (Exception e) {
			// logger.error("Exception occure while getting data from clover is
			// : {}", e.getStackTrace().toString());
		}
		return responseString;
	}

	public static String addMteredPrice(Merchant merchant) {
		HttpPost request = new HttpPost(IConstant.CLOVER_APP_URL + IConstant.CLOVER_APP_ID + "/merchants/"
				+ merchant.getPosMerchantId() + "/metereds/" + merchant.getSubscription().getMeteredId()
				+ "?access_token=" + IConstant.CLOVER_APP_SECRET_KEY);
		return convertToStringJson(request);
	}

	public static String getMeteredCount(Merchant merchant) {
		HttpGet request = new HttpGet(IConstant.CLOVER_APP_URL + IConstant.CLOVER_APP_ID + "/merchants/"
				+ merchant.getPosMerchantId() + "/metereds/" + merchant.getSubscription().getMeteredId()
				+ "?access_token=" + IConstant.CLOVER_APP_SECRET_KEY);
		return convertToStringJson(request);
	}

	public static String postOrderOnClover(String orderJson, Clover clover) {

		HttpPost postRequest = new HttpPost(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/orders?access_token=" + clover.getAuthToken());

		return postOnClover(postRequest, orderJson);

	}

	public static String getEmployeeFromClover(Clover clover, String employeeId) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId() + "/employees/"
				+ employeeId + "?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}
	
	public static String getEmployeesFromClover(Clover clover) {
		HttpGet request = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId() + "/employees/"
				+  "?access_token=" + clover.getAuthToken());
		return convertToStringJson(request);
	}

	public static String deleteEmployee(Clover clover, String employeeId) {
		HttpDelete deleteRequest = new HttpDelete(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/employees/" + employeeId + "?access_token=" + clover.getAuthToken());

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(deleteRequest);
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			return responseString;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String createEmployeeOnClover(Clover clover) {
		HttpPost postRequest = new HttpPost(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/employees?access_token=" + clover.getAuthToken());
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			String customerJson = "{" + " \"role\": \"EMPLOYEE\"," + " \"name\": \"FoodKonnekt Employee\"" + " }";
			StringEntity input = new StringEntity(customerJson);
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			System.out.println("Output from Server .... \n" + responseString);
			String employeePosId = null;

			JSONObject jObject = new JSONObject(responseString);
			if (jObject.has("id")) {

				employeePosId = jObject.getString("id");

			}

			return employeePosId;

		} catch (Exception e) {
			return null;
		}

	}

	public static String updatetOrderOnClover(String orderJson, Clover clover, String cloverOrderId) {

		HttpPost postRequest = new HttpPost(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/orders/" + cloverOrderId + "?access_token=" + clover.getAuthToken());

		return postOnClover(postRequest, orderJson);

	}

	public static String postOrderLineItemOnClover(String orderLineItemJson, Clover clover, String cloverOrderId) {

		HttpPost postRequest = new HttpPost(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/orders/" + cloverOrderId + "/line_items?access_token=" + clover.getAuthToken());

		return postOnClover(postRequest, orderLineItemJson);

	}

	public static String postModifiersWithLineItemOnClover(String modificationsJson, Clover clover,
			String orderLineItemId, String cloverOrderId) {

		HttpPost postRequest = new HttpPost(
				clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId() + "/orders/" + cloverOrderId
						+ "/line_items/" + orderLineItemId + "/modifications?access_token=" + clover.getAuthToken());

		return postOnClover(postRequest, modificationsJson);

	}
	
	public static String postOrderDiscountOnClover(String orderDiscountJson, Clover clover,String cloverOrderId) {

		HttpPost postRequest = new HttpPost(
				clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId() + "/orders/" + cloverOrderId
						+ "/discounts?access_token=" + clover.getAuthToken());

		return postOnClover(postRequest, orderDiscountJson);

	}
	
	public static String postItemDiscountOnClover(String itemDiscountJson, Clover clover,String cloverOrderId,String orderLineItemId) {

		HttpPost postRequest = new HttpPost(
				clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId() + "/orders/" + cloverOrderId
						+ "/line_items/"+orderLineItemId+"/discounts?access_token=" + clover.getAuthToken());

		return postOnClover(postRequest, itemDiscountJson);

	}
	
	

	public static String cashPayment(String paymentJson, String merchantId, String accessToken, String orderId) {
		HttpPost postRequest = new HttpPost(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL + merchantId
				+ "/orders/" + orderId + "/payments?access_token=" + accessToken);
		return postOnClover(postRequest, paymentJson);
	}

	public static String postOnClover(HttpPost postRequest, String jsonObject) {
		StringBuilder responseBuilder = new StringBuilder();
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();

			StringEntity input = new StringEntity(jsonObject);
			input.setContentType("application/json");
			postRequest.setEntity(input);

			HttpResponse response = httpClient.execute(postRequest);
			System.out.println("Output from Server .... \n");
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

	/**
	 * Create line item on clover
	 * 
	 * @param accessToken
	 * @param merchantPosId
	 * @param feeName
	 * 
	 * @param double1
	 * @return String
	 */
	public static String addDeliveryFeeLineItem(Double fees, String merchantPosId, String accessToken, String feeName,
			Integer isTaxable) {
		HttpPost postRequest = new HttpPost(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL + merchantPosId
				+ "/items?access_token=" + accessToken);

		if (fees != null) {
			JSONObject obj = new JSONObject();
			obj.put("price", fees * 100);
			obj.put("name", feeName);
			obj.put("hidden", "true");
			if (isTaxable != null && isTaxable == 1)
				obj.put("defaultTaxRates", true);
			else
				obj.put("defaultTaxRates", false);
			return postOnClover(postRequest, obj.toString());
		}
		return null;
	}

	public static String updateDeliveryFeeLineItem(Double fees, String merchantPosId, String accessToken,
			String feeName, String lineItemId, Integer isTaxable) {
		HttpPost postRequest = new HttpPost(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL + merchantPosId
				+ "/items/" + lineItemId + "?access_token=" + accessToken);

		if (fees != null) {
			JSONObject obj = new JSONObject();
			obj.put("price", fees * 100);
			obj.put("name", feeName);
			obj.put("hidden", "true");
			if (isTaxable != null && isTaxable == 1)
				obj.put("defaultTaxRates", true);
			else
				obj.put("defaultTaxRates", false);

			String itemJson = obj.toString();
			System.out.println(itemJson);
			return postOnClover(postRequest, obj.toString());
		}
		return null;
	}

	public static void applyCoupon(Double discount, Merchant merchant, String orderPosId) {
		double roundOff = discount * 100;
		long disCountAmount = new Double(roundOff).longValue();
		JSONObject obj = new JSONObject();
		obj.put("amount", "-" + disCountAmount);
		obj.put("percentage", "");
		obj.put("name", "Discount");
		JSONObject discountObj = new JSONObject();
		discountObj.put("id", "");
		obj.put("discount", discountObj);
		String jsonText = obj.toString();
		System.out.println(jsonText);
		HttpPost postRequest = new HttpPost(
				IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL + merchant.getPosMerchantId() + "/orders/"
						+ orderPosId + "/discounts?access_token=" + merchant.getAccessToken());
		System.out.println(postOnClover(postRequest, jsonText));

	}

	public static String deleteItem(String merchantPosId, String accessToken, String lineItemId) {
		HttpDelete deleteRequest = new HttpDelete(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL + merchantPosId
				+ "/items/" + lineItemId + "?access_token=" + accessToken);

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(deleteRequest);
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			return responseString;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String deleteOrderType(String merchantPosId, String accessToken, String orderTypeId) {
		HttpDelete deleteRequest = new HttpDelete(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL + merchantPosId
				+ "/order_types/" + orderTypeId + "?access_token=" + accessToken);

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(deleteRequest);
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			return responseString;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static String deleteOrder(String merchantPosId, String accessToken, String orderPOSId) {
		HttpDelete deleteRequest = new HttpDelete(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL + merchantPosId
				+ "/orders/" + orderPOSId + "?access_token=" + accessToken);

		HttpClient client = HttpClientBuilder.create().build();
		HttpResponse response = null;
		try {
			response = client.execute(deleteRequest);
			HttpEntity entity = response.getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			return responseString;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String createUpdateCustomerAddress(Clover clover, String addressJson, String customerPosId) {
		HttpPost postRequest = new HttpPost(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/customers/" + customerPosId + "/addresses?access_token=" + clover.getAuthToken());

		return postOnClover(postRequest, addressJson);
	}

	public static void main(String[] args) {
		double roundOff = 2.3 * 100;
		long price = new Double(roundOff).longValue();
		System.out.println(price);
	}

	public static String getFeedbackClover(Clover clover,String orderId) {
		System.out.println("getFeedbackClover ");
		HttpGet getRequest = new HttpGet(clover.getUrl() + clover.getInstantUrl() + clover.getMerchantId()
				+ "/orders/"+orderId+"?expand=customers&access_token=" + clover.getAuthToken());
		System.out.println("getRequest=== "+getRequest.toString());
		return convertToStringJson(getRequest);

	}
}
