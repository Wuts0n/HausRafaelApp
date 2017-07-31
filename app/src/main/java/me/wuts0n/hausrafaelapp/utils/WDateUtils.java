package me.wuts0n.hausrafaelapp.utils;


import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class WDateUtils {

    private static final DateFormat mParseDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateFormat mFormatDate = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    static {
        // SQLite Timestamp is GMT+00:00
        mParseDate.setTimeZone(TimeZone.getTimeZone("GMT + 00"));
        mFormatDate.setTimeZone(TimeZone.getDefault());
    }


    public static Date getDateFromTimestamp(CharSequence timestamp) {
        Date date = null;
        try {
            date = mParseDate.parse(timestamp.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    @Nullable
    public static String getLocalFormatDateString(Date date) {
        return mFormatDate.format(date);
    }

    public static boolean isDateWithin(Date date, long timeSpan) {
        return date.getTime() > (System.currentTimeMillis() - timeSpan);
    }
}
