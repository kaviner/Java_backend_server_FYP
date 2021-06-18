package com.java_backend.apiserver.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

    public static SimpleDateFormat getSimpleDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
    }

    public static Date stringToDate(String dateStr){
        SimpleDateFormat simpleDate = getSimpleDateFormat();
        simpleDate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date=null;
        try {
            date = simpleDate.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
}
