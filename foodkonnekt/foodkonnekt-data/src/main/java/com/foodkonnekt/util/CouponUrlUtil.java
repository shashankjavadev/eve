package com.foodkonnekt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.Iterator;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.foodkonnekt.model.Merchant;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class CouponUrlUtil {
	
	public static String getCouponData(String url, String voucherJson) {
		System.out.println("getCouponData");
		HttpPost postRequest = new HttpPost(url);

		return postOnCoupon(postRequest, voucherJson);
		

	}
	
	public static String postOnCoupon(HttpPost postRequest, String voucherJson)
	{
		//System.out.println("koupon json-"+itemJson);
		
		
		StringBuilder responseBuilder = new StringBuilder();
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();

			StringEntity input = new StringEntity(voucherJson);
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
		//        	JSONObject customersObject = jsonObj.getJSONObject("customers");
		
			
		
		
		
			
		
		return responseBuilder.toString();
	}

}
