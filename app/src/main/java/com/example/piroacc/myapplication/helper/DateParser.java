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

    private static final String DATE_FROMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String DATE_FORMAT_MAP = "yyyy-MM-dd HH:mm:ss";


    public static String getCurrentParsedDateAsString() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FROMAT);
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        return (dateFormat.format(cal.getTime()));
    }

    public static Date getCurrentParsedDate() {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FROMAT);
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        try {
            date = dateFormat.parse(dateFormat.format(cal.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String parseDateForMapFormatDisplay(String stringDate) {
        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            String reformattedStr = myFormat.format(fromUser.parse(stringDate));
            return reformattedStr;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "parser fails";
    }
}
