package me.wuts0n.hausrafaelapp.utils;


import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LocalDateUtils {

    private static DateFormat mParseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static DateFormat mFormatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");


    @Nullable
    public static String getLocalFormatDateString(CharSequence timestamp) {
        String t = timestamp.toString();
        try {
            Date date = mParseDate.parse(t);
            t = mFormatDate.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return t;
    }
}
