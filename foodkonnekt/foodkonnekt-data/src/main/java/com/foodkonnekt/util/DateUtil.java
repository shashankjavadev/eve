package com.foodkonnekt.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.OrderR;

public class DateUtil {

    /**
     * Convert string to date
     * 
     * @param stringDate
     * @return Date
     */
    public static String convert12hoursTo24HourseFormate(String time) {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date = null;
        try {
            if (time != null)
                date = parseFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date != null)
            return displayFormat.format(date);
        else {
            return null;
        }
    }

    public static Date convertStringToDate(String stringDate) {
        Date date = null;
        if (stringDate != null) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                if (stringDate != null && !stringDate.isEmpty())
                    date = formatter.parse(stringDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    /**
     * Find current date
     * 
     * @return Date
     */
    public static Date currentDate() {
        Calendar now = Calendar.getInstance();
        String start_dt = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
                        + (now.get(Calendar.DATE));
        return convertStringToDate(start_dt);
    }

    /**
     * Find date after 30days
     * 
     * @return Date
     */
    public static Date incrementedDate() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.DATE, 30);
        String incrementedDate = (now.get(Calendar.YEAR)) + "-" + (now.get(Calendar.MONTH) + 1) + "-"
                        + (now.get(Calendar.DATE));
        return convertStringToDate(incrementedDate);
    }

    public static long findDifferenceBetweenTwoDates(Date currentDate, Date endDate) {
        long diff = currentDate.getTime() - endDate.getTime();
        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.print(diffDays + " days, ");
        System.out.print(diffHours + " hours, ");
        System.out.print(diffMinutes + " minutes, ");
        System.out.print(diffSeconds + " seconds.");
        return diffDays;
    }

    /**
     * Find current time
     * 
     * @return String
     */
    public static String findCurrentTime() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH:mm");
        System.out.println(df.format(date));
        String currentTime = df.format(date);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        System.out.println(df.format(date));
        return currentTime;
    }
    
    

    /**
     * Find current day
     * 
     * @return String
     */
    
    public static String getCurrentDayForTimeZone(String timeZoneCode){
    	Date date = new Date();
    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    	// Use Madrid's time zone to format the date in
    	df.setTimeZone(TimeZone.getTimeZone(timeZoneCode));

    	String toDay=DateUtil.findDayNameFromDate( df.format(date));
    	return toDay;
    }
    public static String getCurrentTimeForTimeZone(String timeZoneCode){
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    
    sdf.setTimeZone(TimeZone.getTimeZone(timeZoneCode));
    String currentTime = sdf.format(new Date());
    return currentTime;
    }
    public String findTodayDayName() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String currentDay = null;
        switch (day) {
        case Calendar.SUNDAY:
            currentDay = "sunday";
            break;
        case Calendar.MONDAY:
            currentDay = "monday";
            break;
        case Calendar.TUESDAY:
            currentDay = "tuesday";
            break;
        case Calendar.WEDNESDAY:
            currentDay = "wednesday";
            break;
        case Calendar.THURSDAY:
            currentDay = "thursday";
            break;
        case Calendar.FRIDAY:
            currentDay = "friday";
            break;
        case Calendar.SATURDAY:
            currentDay = "saturday";
            break;
        }
        return currentDay;
    }

    public boolean checkCurrentTimeExistBetweenTwoTimeOrNot(String startTime, String endTime) {
        boolean status = true;
        try {
            String string1 = startTime;
            Date time1 = new SimpleDateFormat("HH:mm").parse(string1);
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(time1);

            String string2 = endTime;
            Date time2 = new SimpleDateFormat("HH:mm").parse(string2);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(time2);
            calendar2.add(Calendar.DATE, 1);

            String someRandomTime = findCurrentTime();
            Date d = new SimpleDateFormat("HH:mm").parse(someRandomTime);
            Calendar calendar3 = Calendar.getInstance();
            calendar3.setTime(d);
            calendar3.add(Calendar.DATE, 1);

            Date x = calendar3.getTime();
            if (x.after(calendar1.getTime()) && x.before(calendar2.getTime())) {
                // checkes whether the current time is between 14:49:00 and 20:11:13.
                status = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static String findCurrentDate() {
        Date myDate = new Date();
        return new SimpleDateFormat("MM-dd-yyyy").format(myDate);
    }

    public static String getYYYYMMDD(Date date) {

        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public static String getDDMMYYYY(Date date) {

        return new SimpleDateFormat("dd-MM-yyyy").format(date);
    }

    /**
     * All time list
     * 
     * @return List<String>
     */
    
    public static List<String> findAllWeekDays(){
    	 List<String> weekDays = new ArrayList<String>();
    	 weekDays.add("Sunday");
    	 weekDays.add("Monday");
    	 weekDays.add("Tuesday");
    	 weekDays.add("Wednesday");
    	 weekDays.add("Thursday");
    	 weekDays.add("Friday");
    	 weekDays.add("Saturday");
    	 return weekDays;
    }
    public static List<String> findAllTime() {
        List<String> times = new ArrayList<String>();
        times.add("12:00 AM");
        times.add("12:30 AM");
        times.add("01:00 AM");
        times.add("01:30 AM");
        times.add("02:00 AM");
        times.add("02:30 AM");
        times.add("03:00 AM");
        times.add("03:30 AM");
        times.add("04:00 AM");
        times.add("04:30 AM");
        times.add("05:00 AM");
        times.add("05:30 AM");
        times.add("06:00 AM");
        times.add("06:30 AM");
        times.add("07:00 AM");
        times.add("07:30 AM");
        times.add("08:00 AM");
        times.add("08:30 AM");
        times.add("09:00 AM");
        times.add("09:30 AM");
        times.add("10:00 AM");
        times.add("10:30 AM");
        times.add("11:00 AM");
        times.add("11:30 AM");
        times.add("12:00 PM");
        times.add("12:30 PM");
        times.add("01:00 PM");
        times.add("01:30 PM");
        times.add("02:00 PM");
        times.add("02:30 PM");
        times.add("03:00 PM");
        times.add("03:30 PM");
        times.add("04:00 PM");
        times.add("04:30 PM");
        times.add("05:00 PM");
        times.add("05:30 PM");
        times.add("06:00 PM");
        times.add("06:30 PM");
        times.add("07:00 PM");
        times.add("07:30 PM");
        times.add("08:00 PM");
        times.add("08:30 PM");
        times.add("09:00 PM");
        times.add("09:30 PM");
        times.add("10:00 PM");
        times.add("10:30 PM");
        times.add("11:00 PM");
        times.add("11:30 PM");
        times.add("12:00 AM");
        return times;
    }
    
    public static List<String> findAll24Time() {
        List<String> times = new ArrayList<String>();
        times.add("00:00");
        times.add("12:30");
        times.add("01:00");
        times.add("01:30");
        times.add("02:00");
        times.add("02:30");
        times.add("03:00");
        times.add("03:30");
        times.add("04:00");
        times.add("04:30");
        times.add("05:00");
        times.add("05:30");
        times.add("06:00");
        times.add("06:30");
        times.add("07:00");
        times.add("07:30");
        times.add("08:00");
        times.add("08:30");
        times.add("09:00");
        times.add("09:30");
        times.add("10:00");
        times.add("10:30");
        times.add("11:00");
        times.add("11:30");
        times.add("12:00");
        times.add("12:30");
        times.add("13:00");
        times.add("13:30");
        times.add("14:00");
        times.add("14:30");
        times.add("15:00");
        times.add("15:30");
        times.add("16:00");
        times.add("16:30");
        times.add("17:00");
        times.add("17:30");
        times.add("18:00");
        times.add("18:30");
        times.add("19:00");
        times.add("19:30");
        times.add("20:00");
        times.add("20:30");
        times.add("21:00");
        times.add("21:30");
        times.add("22:00");
        times.add("22:30");
        times.add("23:00");
        times.add("23:30");
        times.add("24:00");
        return times;
    }

    public static String getTimeFormat(String time) {

        return null;
    }

    public static String getTwentyFourFormat(String time) {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        String finalTime = null;
        Date date;
        try {
            date = parseFormat.parse(time);
            finalTime = displayFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return finalTime;
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

    /**
     * Find future 10 days dates
     * 
     * @return List<String>
     */
    public static List<String> find10FutureDates() {
        List<String> futureDates = new ArrayList<String>();
        // get a calendar instance, which defaults to "now"
        Calendar calendar = Calendar.getInstance();
        // get a date to represent "today"
        Date today = calendar.getTime();
        System.out.println("today:    " + today);

        futureDates.add((calendar.get(Calendar.DATE)) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                        + (calendar.get(Calendar.YEAR)));
        for (int i = 1; i <= 9; i++) {
            // add one day to the date/calendar
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            // now get "tomorrow"
            // Date tomorrow = calendar.getTime();
            // System.out.println("tomorrow: " + tomorrow);
            String start_dt = (calendar.get(Calendar.DATE)) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                            + (calendar.get(Calendar.YEAR));
            futureDates.add(start_dt);
        }
        return futureDates;
    }

    public static void main(String[] args) {
        System.out.println(find10FutureDates());

        List<java.sql.Time> intervals = new ArrayList<java.sql.Time>(25);
        java.sql.Time startTime = new java.sql.Time(00, 30, 0);
        java.sql.Time endTime = new java.sql.Time(20, 0, 0);

        intervals.add(startTime);

        Calendar cal = Calendar.getInstance();
        cal.setTime(startTime);
        while (cal.getTime().before(endTime)) {
            cal.add(Calendar.MINUTE, 30);
            intervals.add(new java.sql.Time(cal.getTimeInMillis()));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for (java.sql.Time time : intervals) {
            System.out.println(sdf.format(time));
        }
        
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd-MM-yyyy HH:mm:ss a");
        DateTime dt = formatter.parseDateTime("11-2-2017 01:10:00 AM");
        System.out.println(dt);
        Date dateInUS = dt.toDate();
        System.out.println(dateInUS);

        final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs

        Calendar date = Calendar.getInstance();
        long t = date.getTimeInMillis();
        Date afterAddingTenMins = new Date(t + (30 * ONE_MINUTE_IN_MILLIS));
        System.out.println(date.getTime() + "---" + afterAddingTenMins);

        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date2;
        try {
            date2 = parseFormat.parse("10:30 PM");
            String futureTime = displayFormat.format(date2);
            System.out.println(parseFormat.format(date2) + " = " + futureTime.split(":")[2].replace("00", "10"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find Day from date
     * 
     * @param futureDate
     * @return String
     */
    public static String findDayNameFromDate(String futureDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = format.parse(futureDate);
           // System.out.println("date util date- "+date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return (new SimpleDateFormat("EEEE").format(date)).toLowerCase();
    }

    public static Date futureDateAndTime(String futureDate, String futureTime) {
        SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
        Date date2;
        Date futureDateTime = null;
        try {
            date2 = parseFormat.parse(futureTime);
            String updateTime = displayFormat.format(date2);
            String timeArray[] = updateTime.split(":");
            String futureUpdatedFinalTime = timeArray[0] + ":" + timeArray[1] + ":" + timeArray[2].replace("00", "10");
            DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
            DateTime dt = formatter.parseDateTime(futureDate + " " + futureUpdatedFinalTime);
            System.out.println(dt);
            futureDateTime = dt.toDate();
            System.out.println("----" + futureDateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return futureDateTime;
    }
    
public static String getTimeZone(Address address) {
        
        HttpGet latLongRequest;
        HttpGet timeZoneRequest;
        Double lat=null;
        Double lng=null;
        String timeZoneCode=null;
        try {
        	latLongRequest = new HttpGet("http://maps.googleapis.com/maps/api/geocode/json?address="+address.getZip());
            String latLongResponse = convertToStringJson(latLongRequest);
            JSONObject latLongjObject = new JSONObject(latLongResponse);
            if (latLongjObject.getString("status").equals("OK")) {
                JSONArray results = latLongjObject.getJSONArray("results");
                for (Object jObj : results) {
                    JSONObject jsonObj = (JSONObject) jObj;
                    JSONObject geometry = jsonObj.getJSONObject("geometry");
                   
                        JSONObject location = geometry.getJSONObject("location");
                         lat=location.getDouble("lat");
                         lng=location.getDouble("lng");
                }
            }
            
            if(lat!=null && lng!=null){
            timeZoneRequest = new HttpGet("https://maps.googleapis.com/maps/api/timezone/json?location="+lat+","+lng+"&timestamp=1458000000&key="+IConstant.GOOGLE_API_KEY);
            String timeZoneresponse = convertToStringJson(timeZoneRequest);
            JSONObject timeZoneObject = new JSONObject(timeZoneresponse);
            if (timeZoneObject.getString("status").equals("OK")) {
                
                     timeZoneCode=timeZoneObject.getString("timeZoneId");
                }
            }
            
            
            
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        
        if(timeZoneCode!=null){
        	return timeZoneCode;
        }else{
        	return null;
        }
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
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return responseBuilder.toString();
    }

}
