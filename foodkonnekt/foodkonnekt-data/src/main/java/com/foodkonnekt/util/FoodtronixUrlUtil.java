package com.foodkonnekt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

import com.foodkonnekt.model.FoodTronix;

public class FoodtronixUrlUtil {

    /**
     * Find foodtronix category
     * 
     * @param foodtronix
     * @return
     */
	public static String getRestaurantDetails(FoodTronix foodTronix) {
        HttpGet request = new HttpGet(foodTronix.getUrl()+foodTronix.getCompanyId() + foodTronix.getInstantUrl() + foodTronix.getStoreId()+"/store");
        request.addHeader("x-access-token", foodTronix.getAuthToken());
        return convertToStringJson(request);
    }
    
	public static String getItemProperty(FoodTronix foodTronix) {
        HttpGet request = new HttpGet(foodTronix.getUrl()+foodTronix.getCompanyId() + foodTronix.getInstantUrl() + foodTronix.getStoreId()+"/ItemProperty");
        request.addHeader("x-access-token", foodTronix.getAuthToken());
        return convertToStringJson(request);
    }
	
	public static String getItemPropertyGroup(FoodTronix foodTronix) {
        HttpGet request = new HttpGet(foodTronix.getUrl()+foodTronix.getCompanyId() + foodTronix.getInstantUrl() + foodTronix.getStoreId()+"/itempropertygroup");
        request.addHeader("x-access-token", foodTronix.getAuthToken());
        return convertToStringJson(request);
    }
	
	public static String getDishCategory(FoodTronix foodTronix) {
        HttpGet request = new HttpGet(foodTronix.getUrl()+foodTronix.getCompanyId() + foodTronix.getInstantUrl() + foodTronix.getStoreId()+"/dishcategory");
        request.addHeader("x-access-token", foodTronix.getAuthToken());
        return convertToStringJson(request);
    }
	
	public static String getPizzaTemplate(FoodTronix foodTronix) {
        HttpGet request = new HttpGet(foodTronix.getUrl()+foodTronix.getCompanyId() + foodTronix.getInstantUrl() + foodTronix.getStoreId()+"/pizzatemplate");
        request.addHeader("x-access-token", foodTronix.getAuthToken());
        return convertToStringJson(request);
    }
	
	public static String getPizzaTemplateSize(FoodTronix foodTronix) {
        HttpGet request = new HttpGet(foodTronix.getUrl()+foodTronix.getCompanyId() + foodTronix.getInstantUrl() + foodTronix.getStoreId()+"/pizzatemplatesize");
        request.addHeader("x-access-token", foodTronix.getAuthToken());
        return convertToStringJson(request);
    }
	
	public static String getPizzaTopping(FoodTronix foodTronix) {
        HttpGet request = new HttpGet(foodTronix.getUrl()+foodTronix.getCompanyId() + foodTronix.getInstantUrl() + foodTronix.getStoreId()+"/pizzatoppings");
        request.addHeader("x-access-token", foodTronix.getAuthToken());
        return convertToStringJson(request);
    }
	
	public static String getPizzaToppingSize(FoodTronix foodTronix) {
        HttpGet request = new HttpGet(foodTronix.getUrl()+foodTronix.getCompanyId() + foodTronix.getInstantUrl() + foodTronix.getStoreId()+"/pizzasizetopping");
        request.addHeader("x-access-token", foodTronix.getAuthToken());
        return convertToStringJson(request);
    }
	
	public static String getPizzaSize(FoodTronix foodTronix) {
        HttpGet request = new HttpGet(foodTronix.getUrl()+foodTronix.getCompanyId() + foodTronix.getInstantUrl() + foodTronix.getStoreId()+"/pizzasizes");
        request.addHeader("x-access-token", foodTronix.getAuthToken());
        return convertToStringJson(request);
    }
	
	
	
	
	
	
	
	
	
	
	
    
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

}
