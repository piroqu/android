package com.example.piroacc.myapplication.helper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by PiroACC on 2015-11-27.
 */
public class DateParser {


    public static String getCurrentParsedDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        return (dateFormat.format(cal.getTime()));
    }

    public static Date getCurrentParsedDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        try {
            date = dateFormat.parse(dateFormat.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
