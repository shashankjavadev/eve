package com.foodkonnekt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.foodkonnekt.model.CategoryDto;

public class CategoryUtil {

    /**
     * Call allCategory web service
     * 
     * @return
     */
    public static String getAllCategory(Integer merchantId) {
        HttpGet request = new HttpGet(UrlConstant.BASE_URL + "/AllCategory?merchantId="+merchantId);
        return convertToStringJson(request);
    }

    /**
     * Hit URL and get response and parse to string
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

    /**
     * Convert json object to java category object
     * 
     * @param categoryJSONObject
     * @return
     */
    public static List<CategoryDto> getCategoryJavaObject(JSONArray categoryJSONArray) {
        List<CategoryDto> categoires = new ArrayList<CategoryDto>();
        for (Object jObj : categoryJSONArray) {
            CategoryDto category = new CategoryDto();
            JSONObject cat = (JSONObject) jObj;
            category.setId(cat.getInt("id"));
            category.setCategoryName(cat.getString("name"));
            category.setCategoryStatus(cat.getInt("itemStatus"));
            categoires.add(category);
        }
        return categoires;
    }
}