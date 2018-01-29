package kfjalr.jvjamf.dllewa.utils;


import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

    private DateUtils() {}

    @SuppressLint("SimpleDateFormat")
    public static long parseDate(String format, String dateTime) {
        long time = System.currentTimeMillis();
        try {
            DateFormat sdf = new SimpleDateFormat(format);
            Date date = sdf.parse(dateTime);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }
}
