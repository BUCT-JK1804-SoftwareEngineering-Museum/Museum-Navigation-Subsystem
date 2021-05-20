package com.imp.util;


import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatetimeUtil {
    public static final String DEFAULT_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_DATE = "yyyy-MM-dd";
    public static final String DEFAULT_TIME = "HH:mm:ss";
    public static final String DEFAULT_FORMAT_STRING_CHINESE = "yyyy年MM月dd日";
    public static final String DEFAULT_FORMAT_STRING_CHINESE_SECONDS = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String DEFAULT_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String DEFAULT_FORMAT_YEAR_MONTH = "yyyy-MM";
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String getDate(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE);
        return format.format(date);
    }
    public static String getTime(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_TIME);
        return format.format(date);
    }

    public static Date getDateTime2Date(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE);
        try {
            return format.parse(format.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getNextDay(Date date) {
        return plusDay(date, 1);
    }

    public static Date plusDay(Date date, int n) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);//增加一天
        return calendar.getTime();
    }

    public static String toDefaultDateString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
        return format.format(date);
    }

    public static String getDateTime(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING_CHINESE_SECONDS);
        return format.format(date);
    }

    public static String defaultDateString(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE);
        return format.format(date);
    }

    public static String getCurDateString() {
        return toDefaultDateString(new Date());
    }

    public static String dateFormate(String dateTime) {
        String regEx = "^\\d{2}/\\d{2}/\\d{4}  \\d:\\d{2}$";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(dateTime);
        if (m.find()) {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");
            try {
                Date date = format.parse(dateTime);
                format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
                return format.format(date);
            } catch (ParseException e) {
                return dateTime;
            }

        }
        return dateTime;
    }

    public static Date string2Date(String datetime) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
        try {
            return format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseDate(String datetime) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_DATE);
        try {
            return format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String formatDate(Date date, String regEx) {
        if (date == null) {
            return "";
        }
        DateFormat format = new SimpleDateFormat(regEx);
        return format.format(date);
    }

    public static Date getRealDate(Date date, String regEx) {
        DateFormat format = new SimpleDateFormat(regEx);
        String dateStr = format.format(date);
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date parseString2Date(String datetime, String regEx) {
        SimpleDateFormat format = new SimpleDateFormat(regEx);
        try {
            return format.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @param str1 时间参数 1：
     * @param str2 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String str1, String str2) {
        DateFormat df = new SimpleDateFormat(DEFAULT_FORMAT_STRING);
        Date one;
        Date two;
        long days = 0;
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff;
            if (time1 < time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 隧道人员定位-停留时间计算
     */
    public static String stayTime(String startTime) {
        return stayTime(startTime, getCurDateString());
    }

    public static String stayTime(String startTime, String endTime) {
        long l = string2Date(endTime).getTime() - string2Date(startTime).getTime();
        if (l > 0) {
            long hour = l / (1000 * 60 * 60);
            long min = (l % (1000 * 60 * 60)) / 1000 / 60;
            return hour + "小时" + min + "分钟";
        } else {
            return "0小时0分钟";
        }

    }


    /**
     * 两个时间之间相加减时间
     *
     * @param time       时间参数 ：
     * @param timeMillis 加减时间 ：
     * @return 相加时间结果
     */
    public static String plusTimeMillis(String time, Long timeMillis) {
        String result = null;
        try {
            result = toDefaultDateString(new Date(string2Date(time).getTime() + timeMillis));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    //将时间转换date类型
    public static Date convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }

        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
        try {
            date = format.parse(source.trim());
        } catch (Exception e) {
            try {
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                date = format.parse(source.trim());
            } catch (Exception e1) {
                try {
                    format = new SimpleDateFormat("yyyy-MM-dd");
                    date = format.parse(source.trim());
                } catch (Exception e2) {
                }
            }
        }

        return date;
    }

    public static String getDatFormat(Integer value, String year, Integer week, boolean sunday) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.WEEK_OF_YEAR, value);
        cal.set(Calendar.DAY_OF_WEEK, week);
        if (sunday) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Date date = cal.getTime();
        return getDate(date);
    }


    /**
     * 是否默认时间格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    public static boolean isDefaultDateFormat(String date) {
        if (StringUtils.isEmpty(date)) {
            return false;
        }
        String reg = "^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$";
        if (Pattern.matches(reg, date)) {
            return true;
        }
        return false;
    }

    public static int compareMonthToMonthTime(String checkDate, String faceDutyOffDaysAsExceptionStartTime) {
        checkDate = checkDate + "-01";
        LocalDate createTimeDate = LocalDate.parse(checkDate, DateTimeFormatter.ofPattern(DEFAULT_FORMAT_YEAR_MONTH_DAY));
        LocalDate endTimeDate = LocalDate.parse(faceDutyOffDaysAsExceptionStartTime, DateTimeFormatter.ofPattern(DEFAULT_FORMAT_YEAR_MONTH_DAY));
        return createTimeDate.compareTo(endTimeDate);
    }

    public static int compareNowMonthToTargetMonth(String checkDate) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT_YEAR_MONTH);
        long nowMonth = Long.valueOf(sdf.format(new Date()).replace("-", ""));
        long checkDateLong = Long.valueOf(checkDate.replace("-", ""));
        if (nowMonth > checkDateLong) {
            return 1;
        } else if (nowMonth == checkDateLong) {
            return 0;
        } else {
            return -1;
        }

    }

    public static int compareCurrentDutyDaysToCurrentDay(int offDays) {
        LocalDate localDate = LocalDate.now();
        int dayNums = localDate.getDayOfMonth();
        if (offDays >= dayNums) {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 获取当天开始时间
     */
    public static String getStartTime() {
        //获取当前日期
        LocalDate nowDate = LocalDate.now();
        //设置零点
        LocalDateTime beginTime = LocalDateTime.of(nowDate, LocalTime.MIN);
        return beginTime.format(DTF);
    }

    /**
     * 获取开始时间
     */
    public static String getStartTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        //获取当前日期
        LocalDate nowDate = instant.atZone(zone).toLocalDate();
        //设置零点
        LocalDateTime beginTime = LocalDateTime.of(nowDate, LocalTime.MIN);
        return beginTime.format(DTF);
    }

    /**
     * 获取当天结束时间
     */
    public static String getEndTime() {
        //获取当前日期
        LocalDate nowDate = LocalDate.now();
        //设置当天的结束时间
        LocalDateTime endTime = LocalDateTime.of(nowDate, LocalTime.MAX);
        return DTF.format(endTime);
    }

    /**
     * 获取结束时间
     */
    public static String getEndTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        //获取当前日期
        LocalDate nowDate = instant.atZone(zone).toLocalDate();
        //设置当天的结束时间
        LocalDateTime endTime = LocalDateTime.of(nowDate, LocalTime.MAX);
        return DTF.format(endTime);
    }

    public static String getDate(String time) {
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter dateDay = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateDay.format(dateTime.parse(time));
    }

    /**
     * 获取两个日期之间的差值  相同算1天
     *
     * @param start
     * @param end
     * @return
     */
    public static int getTwoDayDifference(String start, String end) {
        Date startDate = string2Date(start);
        Date endDate = string2Date(end);
        return Math.round((endDate.getTime() - startDate.getTime()) / (1000f * 3600 * 24));
    }

    /**
     * 格式化日期
     *
     * @param time
     * @param reg
     * @return
     */
    public static String formatStringDate(String time, String reg) {
        Date date = string2Date(time);
        return formatDate(date, reg);
    }

    public static String addDay(String time, int day) {
        Date date = string2Date(time);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return formatDate(calendar.getTime(), DEFAULT_DATE);
    }
}
