package com.foodkonnekt.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.FoodTronix;


@Service
public class FoodkonnektRESTClient {
	
	/*@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	private String grantType;
    
    private String userName;
    
    private String password;
    
    private String clientId;
    
    private String clientSecret;
    
    private String oAuthUrl;
    
    private String dataSynchURL;*/
    
	/**
	 * Method getSalesForceCredentialsAsHeaders.
	 * 
	 * @return String
	 */
	/*@SuppressWarnings({ "unchecked", "rawtypes" })
	public CloverOAuth getCloverCredentialsAsHeaders() {
		CloverOAuth cloverOAuth = new CloverOAuth();
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(oAuthUrl);
		List nameValuePairs = new ArrayList(1);
		nameValuePairs.add(new BasicNameValuePair("grant_type", "client_credentials"));
		nameValuePairs.add(new BasicNameValuePair("username", userName));
		nameValuePairs.add(new BasicNameValuePair("password", password));
		nameValuePairs.add(new BasicNameValuePair("client_id", clientId));
		nameValuePairs.add(new BasicNameValuePair("client_secret", clientSecret));
		try {
			post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(post);
			String json = EntityUtils.toString(response.getEntity());
			CloverOAuthResponse cloverOAuthResponse = gson.fromJson(json, CloverOAuthResponse.class);
			cloverOAuth.setAuthToken(cloverOAuthResponse.getToken_type()+" "+cloverOAuthResponse.getAccess_token());
			cloverOAuth.setInstantUrl(cloverOAuthResponse.getInstance_url()+dataSynchURL);
			return cloverOAuth;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cloverOAuth;
	}*/
	
	public String getCloverCategories(Clover clover) {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(clover.getUrl()+clover.getInstantUrl()+clover.getMerchantId()
						+"/categories?expand=items&access_token="+clover.getAuthToken());
		HttpResponse response;
		StringBuilder responseBuilder = new StringBuilder();
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line="";
			while ((line = rd.readLine()) != null) {
				responseBuilder.append(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBuilder.toString();
	}
	
	public String getFoodTronixCategories(FoodTronix foodtronix){
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(foodtronix.getUrl()+foodtronix.getInstantUrl());
		request.addHeader(" x-access-token",foodtronix.getAuthToken());
		HttpResponse response;
		StringBuilder responseBuilder = new StringBuilder();
		try {
			response = client.execute(request);
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line="";
			while ((line = rd.readLine()) != null) {
				responseBuilder.append(line);
				System.out.println(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return responseBuilder.toString();
		
	}
}
