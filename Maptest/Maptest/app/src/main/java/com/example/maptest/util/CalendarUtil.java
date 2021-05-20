package com.example.maptest.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


public class CalendarUtil {
    private static CalendarUtil calendarUtil;

    public static CalendarUtil getInstance() {
        if (calendarUtil == null) {
            synchronized (CalendarUtil.class) {
                if (calendarUtil == null) {
                    calendarUtil = new CalendarUtil();
                }
            }
        }
        return calendarUtil;
    }


    public String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public String getYMDHMS() {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String dateString = dff.format(new Date());
        return dateString;
    }


    public String getDHMS() {
        SimpleDateFormat dff = new SimpleDateFormat("dd-HH-mm-ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String dateString = dff.format(new Date());
        return dateString;
    }


    public String getYMDHMSLine() {
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String dateString = dff.format(new Date());
        return dateString;
    }

    //获得本周一0点时间
    @SuppressLint("WrongConstant")
    public int getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    //获得本周日24点时间
    @SuppressLint("WrongConstant")
    public int getTimesWeeknight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return (int) ((cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000)) / 1000);
    }

    //获得本月第一天0点时间
    @SuppressLint("WrongConstant")
    public int getTimesMonthmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return (int) (cal.getTimeInMillis() / 1000);
    }

    //获得本月最后一天24点时间
    @SuppressLint("WrongConstant")
    public int getTimesMonthnight() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 24);
        return (int) (cal.getTimeInMillis() / 1000);
    }

    /**
     * 根据身份证的号码算出当前身份证持有者的性别和年龄 18位身份证
     *
     * @return
     * @throws Exception
     */
    public static Map<String, String> getCarInfo(String CardCode)
            throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String year = CardCode.substring(6).substring(0, 4);// 得到年份
        String yue = CardCode.substring(10).substring(0, 2);// 得到月份
        String day = CardCode.substring(12).substring(0, 2);//得到日
        String sex;
        if (Integer.parseInt(CardCode.substring(16).substring(0, 1)) % 2 == 0) {// 判断性别
            sex = "女";
        } else {
            sex = "男";
        }

        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String dateString = dff.format(new Date());

        String fyear = dateString.substring(0, 4);// 当前年份
        String fyue = dateString.substring(5, 7);// 月份
        int age = 0;

        if (Integer.parseInt(fyue) <= Integer.parseInt(yue)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(year) -1;
        } else {
            age = Integer.parseInt(fyear) - Integer.parseInt(year);
        }
        map.put("sex", sex);
        map.put("age", age + "");
        map.put("year", year + "-" + yue + "-" + day);
        return map;
    }

    /**
     * 15位身份证的验证
     *
     * @param
     * @throws Exception
     */
    public static Map<String, String> getCarInfo15W(String card)
            throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        String uyear = "19" + card.substring(6, 8);// 年份
        String uyue = card.substring(8, 10);// 月份
        // String uday=card.substring(10, 12);//日
        String usex = card.substring(14, 15);// 用户的性别
        String sex;
        if (Integer.parseInt(usex) % 2 == 0) {
            sex = "女";
        } else {
            sex = "男";
        }

        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String dateString = dff.format(new Date());

        String fyear = dateString.substring(0, 4);// 当前年份
        String fyue = dateString.substring(5, 7);// 月份
        int age = 0;
        if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
            age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
        } else {
            age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
        }
        map.put("sex", sex);
        map.put("age", age + "");
        return map;
    }
}
