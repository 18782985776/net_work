package com.jobnew.farm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @auther：wyw on 2017/2/6 10:59.
 */

public class DateUtils {
    /**
     * 将时间戳转换成指定的格式
     *
     * @param dateTime   时间戳
     * @param dateFormat 转换的格式
     * @return 返回格式化后的时间
     * @throws ParseException
     */
    public static String DateToString(String dateTime, String dateFormat) {
        Date date = new Date(Long.parseLong(dateTime));
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        String format = formatter.format(date);
        return format;
    }

    /**
     * 转换10位时间戳工具，返回yyyy-MM-dd HH:mm:ss时间
     *
     * @param datetime int型10位时间戳
     */
    public static String toString(String datetime) {
        SimpleDateFormat sdf = null;
        String dateTime = null;
        try {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long lDatatime = Long.parseLong(datetime);
            dateTime = sdf.format(lDatatime);
        } catch (Exception e) {
            //TODO 处理异常
            e.printStackTrace();
        }
        return dateTime;
    }

    /**
     * 将时间转换成时间戳
     *
     * @param time 需要转换的时间
     * @return 转换后的时间戳
     */
    public static long stringToLong(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 处理时间，如果是今天，返回今天+时分
     * 如果不是今天，返回年月日
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static String getDayStr(String day) {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                String time = formatter.format(date);
                return "今天 " + time;
            }
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(date);
    }

    /**
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return 返回年月日 "2016-06-28"
     */
    public static String getMonthAndDay(String day) {
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        return formatter.format(date);
    }
}
