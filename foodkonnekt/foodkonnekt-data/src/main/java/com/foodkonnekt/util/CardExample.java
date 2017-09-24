package com.foodkonnekt.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

class TestCronJOb implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("job");
	}
	
}
public class CardExample {
	
	
	/*static <K,V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K,V> map) {

		List<Entry<K,V>> sortedEntries = new ArrayList<Entry<K,V>>(map.entrySet());

					Collections.sort(sortedEntries, new Comparator<Entry<K,V>>() {
						
						public int compare(Entry<K,V> e1, Entry<K,V> e2) {
								return e2.getValue().compareTo(e1.getValue());
						}
					}
							);

					return sortedEntries;
		}*/
	
	
	
	

    public static void main(String[] args) throws SchedulerException {
    
    	
    	/*
    	String dateStr = "Mon July 18 00:00:00 IST 2017";
    	DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
    	Date date;
		try {
			date = (Date)formatter.parse(dateStr);
			System.out.println(date);        

	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(date);
	    	//String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" +         cal.get(Calendar.YEAR);
	    	String formatedDate =  cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" +   cal.get(Calendar.DATE)     ;
	    	System.out.println("formatedDate : " + formatedDate); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	   
    	
    	
    	
    	String enc = EncryptionDecryptionUtil.encryption("51");
    	String dec = EncryptionDecryptionUtil.decryption(enc);
    	System.out.println(dec);
    	System.out.println(EncryptionDecryptionUtil.encryption(dec));
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	/*double d = 10;
    	int qty=3;
    	System.out.println(d/qty);;*/
    	/*
    	List <Map<String,String>> list = new ArrayList<Map<String,String>>();
    	
    	 Map<String, String> map = new HashMap<String, String>();
    	    map.put("A", "category");
    	    Map<String, String> map1 = new HashMap<String, String>();
    	    Map<String, String> map2= new HashMap<String, String>();
    	    Map<String, String> map3= new HashMap<String, String>();
    	    Map<String, String> map4= new HashMap<String, String>();
    	    Map<String, String> map5= new HashMap<String, String>();
    	   
    	    map1.put("A", "order");
    	    map2.put("A", "item");
    	    map5.put("A", "item");
    	    map3.put("A", "category");
    	    map4.put("A", "order");
    	   // map.put("D", "category"); // "duplicate" value

    	    
    	    
    	    
    	    
    	    list.add(map);
    	    list.add(map1);
    	    list.add(map2);
    	    list.add(map3);
    	    list.add(map4);
    	    list.add(map5);
    	    
    	   
    	    
    	    
    	    
    	    
    	    
    	    
    	    System.out.println(list);*/
    	    
    	   // System.out.println(entriesSortedByValues(map));
    	   
    	    
    	    
    	    /*Collections.sort(list, new Comparator<Map<String, String>>() {
    	        public int compare(final Map<String, String> o1, final Map<String, String> o2) {
    	            return o1.get("A").compareTo(o2.get("A"));
    	        }
    	    });*/

    	 
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	/* Date date= new Date();
     	SimpleDateFormat dateForma=  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     	String currentDate=dateForma.format(date);
     	String currentDay=DateUtil.findDayNameFromDate(currentDate);
     	String futureDay=DateUtil.findDayNameFromDate("2017-05-15");
    	
     	//System.out.println(currentDate);
     	
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calobj = Calendar.getInstance();
        String currentTimeArray[]=DateUtil.findCurrentTime().split(":");
      	 @SuppressWarnings("deprecation")
      	 java.sql.Time currentTime = new java.sql.Time(Integer.parseInt(currentTimeArray[0]),
                   Integer.parseInt(currentTimeArray[1]), 0);
         calobj.setTime(currentTime);
        calobj.add(Calendar.HOUR_OF_DAY, -6);
        System.out.println(df.format(calobj.getTime()));*/
    	/*Date date= new Date();
    	SimpleDateFormat dateForma=  new SimpleDateFormat("yyyy-MM-dd");
    	dateForma.format(date);
    	 LocalDate start = LocalDate.parse(dateForma.format(date));
    	 String dateStart=start.toString();
    		
    	 System.out.println(start.plusDays(1));
    	 LocalDate nextDate=start;
    	 int i=1;
        while(i<=10){
        	System.out.println(nextDate);
        	String dateStart=nextDate.toString();
        	String dayName=DateUtil.findDayNameFromDate(dateStart);
        	System.out.println(dayName);
        	 nextDate= nextDate.plusDays(1);
        	i++;
        }*/
    	
        /*Map<String, Map<String, String>> notf=new HashMap<String, Map<String, String>>();
        Map<String, String> notification=new HashMap<String, String>();
        List< Map<String, String>> list=new ArrayList<Map<String,String>>();
        notification.put("appEvent", "notification");
       // notification.put("payload", "M4TYFMG8VNA8P@#You got a new order(M4TYFMG8VNA8P) at 06-05-2016 02:39:12 am@#$37.82@#Cash@#{'total':37.82,'tax':2.88,'productItems':[{'product_id':'9344','name':'12 Tamales','price':'22.00','qty':3,'extras':[]}]}@#9GK2J085R8A3A@#instruction@#DNTP71VET1APM");
        Map<String, String> order=new HashMap<String, String>();
        order.put("total", "30.11");
        order.put("tax", "2.38");
        Map<String, String> poduct=new HashMap<String, String>();
        poduct.put("product_id", "11");
        poduct.put("name", "12 templ");
        poduct.put("price", "12");
        poduct.put("qty", "1");
        list.add(poduct);
        Map<String, String> exctra=new HashMap<String, String>();
        exctra.put("id", "50618");
        exctra.put("price", "0.00");
        exctra.put("name", "binssss");
        exctra.put("qty", "1");
        poduct.put("extras", "["+exctra+"]");
        order.put("productItems", "["+poduct+"]");
        notification.put("payload", "M4TYFMG8VNA8P@#You got a new order(M4TYFMG8VNA8P) at 06-05-2016 02:39:12 am@#$37.82@#Cash@#"+order);
        notf.put("notification", notification);
        Gson gson = new Gson();
        String notificationJson = gson.toJson(notf);
        System.out.println(notificationJson);*/
        //HttpPost postRequest = new HttpPost("https://api.clover.com/v2/merchant/9GK2J085R8A3A/apps/MKRSKG5KBA2G8/notify?access_token=2b5d6fe3-eec3-0f35-2e6d-4525d9fecb76");
        //System.out.println(convertToStringJson(postRequest, notificationJson));
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
