package com.example.maptest.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

;


@SuppressLint("SimpleDateFormat")
public class DateUtils {

    /**
     * 获取当前时间戳
     *
     * @return long
     */
    public static long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取格式化的当前系统时间
     *
     * @return String
     */
    public static String getCurrentDateStr() {
        return getFormatDate(getCurrentTimeStamp(), "yyyy-MM-dd");
    }


    /**
     * 获取格式化时间
     *
     * @param timeStamp 时间戳
     * @param pattern   格式化格式（默认yyyy-MM-dd HH:mm:ss）
     */
    public static String getFormatDate(long timeStamp, String pattern) {
        String time;
        if (Long.toString(Math.abs(timeStamp)).length() < 11) {
            timeStamp *= 1000;
        }
        if (TextUtils.isEmpty(pattern)) {
            pattern = Constants.DATE_FORMAT_DEFAULT;
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getDefault());
        time = format.format(timeStamp);
        return time;
    }

    /**
     * 格式化返回日期时间
     *
     * @param stringDate stringDate
     * @param pattern    pattern
     * @return String
     */
    public static String getFormatDate(String stringDate, String pattern) {
        if (TextUtils.isEmpty(stringDate)) {
            return "";
        }
        String parentPattern;
        if (stringDate.length() == 16) {
            parentPattern = "yyyy-MM-dd HH:mm";
        } else if (stringDate.length() == 19) {
            parentPattern = "yyyy-MM-dd HH:mm:ss";
        } else {
            return stringDate;
        }
        SimpleDateFormat sdf1 = new SimpleDateFormat(pattern);
        try {
            return sdf1.format(new SimpleDateFormat(parentPattern).parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return stringDate;
    }

}
