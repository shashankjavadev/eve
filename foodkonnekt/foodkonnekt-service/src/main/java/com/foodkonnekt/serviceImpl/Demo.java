package com.foodkonnekt.serviceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Demo {
    public static void main(String[] args) throws IOException {
        long l=756;
        float d=(l/100);
        System.out.println(d);
        
       /* String s="=Saket Nagar, Sanvid Nagar,indore,MP,452001";
        String f="Telephone ,nagar,Indore,MP,452011";
        URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+ URLEncoder.encode(s, "UTF-8")+"&destinations="+ URLEncoder.encode(f, "UTF-8"));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        String line, outputString = "";
        BufferedReader reader = new BufferedReader(
        new InputStreamReader(conn.getInputStream()));
        while ((line = reader.readLine()) != null) {
             outputString += line;
        }
        System.out.println(outputString);*/
    }

}
