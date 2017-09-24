package com.foodkonnekt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    public static String getStartTime(String startTime, String validity) {
        String finalTime = null;
        if (startTime != null) {
            String[] startTimeArray = startTime.split(",");
            if (startTimeArray.length > 0) {
                SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
                SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
                String time;
                try {
                    if (validity.equals("0")) {
                        time = startTimeArray[0] + ":" + startTimeArray[1];
                        Date date = displayFormat.parse(time);
                        finalTime = parseFormat.format(date);
                    }
                    if (validity.equals("1")) {
                        time = startTimeArray[2] + ":" + startTimeArray[3];
                        Date date = displayFormat.parse(time);
                        finalTime = parseFormat.format(date);
                    }
                    if (validity.equals("2")) {
                        time = startTimeArray[4] + ":" + startTimeArray[5];
                        Date date = displayFormat.parse(time);
                        finalTime = parseFormat.format(date);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        return finalTime;
    }
}
