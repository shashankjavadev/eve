package com.foodkonnekt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.service.CloverService;
import com.foodkonnekt.service.MigrationService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.UrlConstant;

@Controller
public class MigrationController {
	
	@Autowired
    private MigrationService migrationService;
	
	@RequestMapping(value = "/migratPhpMerchant", method = RequestMethod.GET)
    @ResponseBody
    public String migratPhpMerchant(@RequestParam(required = true) String merchantId,@RequestParam(required = true)String startDate,@RequestParam(required = true) String endDate) {
        
		
        return migrationService.migrateCustomer(merchantId,startDate,endDate);
    }
	
	@RequestMapping(value = "/updateWebhookData", method = RequestMethod.GET)
    @ResponseBody
    public String updateWebhookData() {
		
		Clover clover= new Clover();
    	clover.setAuthToken("3f5cbb43-ad57-bd51-610c-214ad3e2bc28");
    	clover.setMerchantId("VAHJVXB36EQ2C");
    	clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
    	clover.setUrl(IConstant.CLOVER_URL);
    	String json=CloverUrlUtil.getAllItem(clover, null);
    	createItemObject(json,clover.getMerchantId());
		
        return "200";
    }
	
	  public static void createItemObject(String itemJson,String merchantId) {
	        try {
	            JSONObject jsonObject = new JSONObject(itemJson);
	            JSONArray jsonArray = jsonObject.getJSONArray("elements");
	            for (int i = 0; i < jsonArray.length(); i++) {
	                JSONObject categoryItem = jsonArray.getJSONObject(i);
	                String itemPosId=categoryItem.getString("id");
	            	HttpPost postRequest = new HttpPost(UrlConstant.BASE_URL+"/webHooks");
	            	String request="{\"appId\":\"X476CXX3HM1Q8\",\"merchants\":{\""+merchantId+"\":[{\"objectId\":\"I:"+itemPosId+"\",\"type\":\"UPDATE\",\"ts\":1483863300411}]}} ";
	            	convertToStringJson(postRequest,request);
	                //convertToStringJson();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public static String convertToStringJson(HttpPost postRequest, String customerJson) {
	    	
	        StringBuilder responseBuilder = new StringBuilder();
	        try {
	            HttpClient httpClient = HttpClientBuilder.create().build();
	            StringEntity input = new StringEntity(customerJson);
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

}
