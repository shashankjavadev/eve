package com.foodkonnekt.util;

public class IConstant {
	
	 public static final String FROM_EMAIL_ID = "orders@foodkonnekt.com";
	 public static final String FROM_PASSWORD = "P@ssw0rd123";
	 
	 public static final String GOOGLE_API_KEY="AIzaSyAsZw5pB5EbFPTg-Fc5g8iFqdL7pu4ZfHk";

	    
	 // Clover PROD APP credential
//	    public static final String CLOVER_APP_ID="X476CXX3HM1Q8";
//	    public static final String CLOVER_APP_SECRET_KEY="2463965d-f37f-d4e9-0398-087e7352398f";
//	    public static final String CLOVER_URL = "https://www.clover.com";
//	    public static final String  CLOVER_V2_URL="https://api.clover.com/v2/merchant/"; 
//		public static final String CLOVER_MAIN_URL= "https://www.clover.com";
	    
    // Clover DEV App credential
    public static final String CLOVER_APP_ID="SMA6T1DNS8YAE";
    public static final String CLOVER_APP_SECRET_KEY="5c59e6ea-a081-67d4-2fc7-be8fdd95f4e5";
    public static final String CLOVER_URL = "https://www.clover.com";
    public static final String  CLOVER_V2_URL="https://api.clover.com/v2/merchant/"; 
    public static final String CLOVER_MAIN_URL= "https://www.clover.com";
    
  
//Clover Sandbox url
    
//    public static final String CLOVER_APP_ID="QXQF512HTEE7R";
//    public static final String CLOVER_APP_SECRET_KEY="229b0169-ac54-a8df-b3ed-fa3a3eee9c05";
//    public static final String CLOVER_URL = "https://apisandbox.dev.clover.com";
//    public static final String  CLOVER_V2_URL="https://apisandbox.dev.clover.com/v2/merchant/";
//    public static final String CLOVER_MAIN_URL= "https://sandbox.dev.clover.com";
    
    
    public static final String CLOVER_APP_URL=CLOVER_URL+"/v3/apps/";
    public static final String CLOVER_INSTANCE_URL = "/v3/merchants/";
    public static final String CLOVER_AUTH_TOKEN = "45b361e4-bda8-2af3-f12b-cc5e7a7e9a46";
    public static final String CLOVER_MERCHANT_ID = "K9HZGT1YTGRNY";
    
    //for Prod
    /*public static final String CLOVER_APP_ID="MPQKWV25FYQAY";
     public static final String CLOVER_APP_SECRET_KEY="03a25e96-68be-5d11-e7f8-26e35e929b1c";*/
    public static final String DEVICE_ID="MKRSKG5KBA2G8";
    
    // FoodTronix credential
    public static final String FOODTONIX_URL = "http://emstest.foodtronix.com/api/";
    public static final String FOODTONIX_INSTANCE_URL = "/stores/";
    public static final String FOODTONIX_AUTH_TOKEN = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIwNjg4YzlhOC02Yjg0LTRjODAtODA3Ny02NDQwNzhkOTMzNGUiLCJzdG9yZXMiOlt7InN0b3JlTmFtZSI6IlNvbmljIiwic3RvcmVJZCI6IjQzODk0ODk3MjM4NDIzIiwicGVybWlzc2lvbnMiOnsib25saW5lT3JkZXJpbmciOmZhbHNlLCJTb21lRnV0dXJlUGVybWlzc2lvbnMiOnRydWV9fSx7InN0b3JlTmFtZSI6IlNvbmljIiwic3RvcmVJZCI6IjU0NjU0Nzg2ODM0MzU2IiwicGVybWlzc2lvbnMiOnsib25saW5lT3JkZXJpbmciOnRydWUsIlNvbWVGdXR1cmVQZXJtaXNzaW9ucyI6dHJ1ZX19LHsic3RvcmVOYW1lIjoiU29uaWMiLCJzdG9yZUlkIjoiNTQzNTQzNzY4NjQzMjMiLCJwZXJtaXNzaW9ucyI6eyJvbmxpbmVPcmRlcmluZyI6dHJ1ZSwiU29tZUZ1dHVyZVBlcm1pc3Npb25zIjp0cnVlfX1dLCJpYXQiOjE0NTQwODQ5Mjh9.3_Kz6q3QZ3pv7Hz9piAGt_HM0s5oZqMuuqKa1sUh0gk";

    public static final String REGISTRATION_SUCCESSFUL = "Registration successful.";
    public static final String REGISTRATION_FAILURE = "Please try again";
    public static final String LOGIN_FAILURE = "Invalid login and password";
    public static final String CUSTOMER_NOT_FOUND = "Customer not found";
    public static final String ZONE = "false";
    public static final String CATEGORY_FAILURE = "No category found";
    public static final int IS_APPROVED = 1;
    public static final int IS_NOT_APPROVED = 0;
    
    public static final int BOOLEAN_TRUE = 1;
    public static final int BOOLEAN_FALSE = 0;
    public static final int SOFT_DELETE = -1;
    
    public static final int FEEDBACK_CATEGORY_FOOD = 4;
    public static final int FEEDBACK_CATEGORY_PICKUP = 2;
    public static final int FEEDBACK_CATEGORY_DELIVERY = 3;
    public static final int FEEDBACK_CATEGORY_ONLINE_ORDER = 1;
    public static final int FEEDBACK_CATEGORY_DINE_IN = 6;
    public static final int FEEDBACK_OUR_SERVICES = 5;
    
    
    public static final int POS_CLOVER = 1;
    public static final int POS_FOODTRONIX = 2;
    public static final int NON_POS = 3;

    /* It is used for login web service for web service */
    public static final String RESPONSE = "response";
    public static final String DATA = "DATA";
    public static final String MESSAGE = "MESSAGE";
    public static final String RESPONSE_SUCCESS_MESSAGE = "200";
    public static final String RESPONSE_NO_DATA_MESSAGE = "400";

    public static final String MAIL_SUCCESS = "Your email sent successsfully";
    public static final String MAIL_FAILURE = "From email and toemail can't be null";
    
    public static final String DEFAULT_PICKUP_TIME="45";
 
    //Added By Manish Gupta to use in CronSchedular on 08-05-17
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final String STARTDATE=   " 00:00:00";
    public static final String ENDDATE= " 23:59:00";
    
    /*Add for customerorder for order status*/
    public static final int Order_Status_Pending = 0;
    public static final int Order_Status_Confirmed = 1;
    public static final int Order_Status_Cancelled = 2;
    
    
    
    
    
    //for koupon validation response
    public static final String RESPONSE_EXPIRED_KOUPON = "300";
    public static final String MESSAGE_EXPIRED_KOUPON ="koupon is expired";
    public static final String RESPONSE_INVALID_KOUPON = "400";
    public static final String MESSAGE_INVALID_KOUPON = "Invalid Koupon";
    public static final String RESPONSE_RECURRING_KOUPON= "202";
    public static final String MESSAGE_RECURRING_KOUPON= "Not applicable today ";
    public static final String MESSAGE_ALREADY_USED_KOUPON= "You had already used this coupon";
    public static final String RESPONSE_NOT_EXIST_KOUPON = "201";
    public static final String MESSAGE_NOT_EXIST_KOUPON="koupon does not Exist";
    public static final String RESPONSE_DUPLICATE_KOUPON = "800";
    
    
    
    
    
}
