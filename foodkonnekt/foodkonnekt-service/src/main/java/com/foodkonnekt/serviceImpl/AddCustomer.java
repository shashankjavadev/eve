package com.foodkonnekt.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddCustomer {
	
	public static void main(String[] args) {
		
		String s="Penguins.jpg";
		
		String ss[]=s.split("\\.");
		
		System.out.println(ss[1]);
    }
		

		
		
	
	public static String get12Format(String time) {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        String finalTime = null;
        Date date;
        try {
            date = displayFormat.parse(time);
            finalTime = parseFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return finalTime;
    }

}
