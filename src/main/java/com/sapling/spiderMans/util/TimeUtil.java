package com.sapling.spiderMans.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author lijinhua
 * @version 创建时间：2015年7月20日 下午3:57:09
 */

public abstract class TimeUtil {

    public static final String DATE_YYYY_MM = "yyyy-MM";
    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_YYYY_M_MDD = "yyyyMMdd";
    public static final String DATE_YYYY_M_MDD_H_HMMSS = "yyyyMMddHHmmss";
    public static final String DATE_YYYY_MM_CN = "yyyy年MM月";
    public static final String DATE_YYYY_MM2 = "yyyyMM";
    public static final String DATE_YYYY_M_MDD_H_HMMSS_SSSS = "yyyyMMddHHmmssSSSS";
    public static final String DATE_YYMMDDHHMM = "yyMMddHHmm";
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";


    /**
     * 格式化某个月的第一天
     *
     * @param date
     * @param format
     * @return
     */
    public static String getFirstOfMonthFormat(Date date, String format) {
        return dateToString(getFirstOfMonth(date), format);
    }

    /**
     * 输出某个月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstOfMonth(Date date) {
        date = stringToDate(dateToString(date, DATE_YYYY_M_MDD), DATE_YYYY_M_MDD);
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    /**
     * 格式化某月的最后一天
     *
     * @param date
     * @param format
     * @return
     */
    public static String getLastOfMonthFormat(Date date, String format) {
        return dateToString(getLastOfMonth(date), format);
    }

    /**
     * 返回一个月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastOfMonth(Date date) {
        date = stringToDate(dateToString(date, DATE_YYYY_M_MDD) + "235959", DATE_YYYY_M_MDD_H_HMMSS);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DATE, 1);
        cal.roll(Calendar.DATE, -1);
        Date endTime = cal.getTime();

        return endTime;

    }

    public static String dateToString(Date date, String format) {
        SimpleDateFormat myFormatter = new SimpleDateFormat(format);
        if (date == null) {
            return " ";
        } else {
            return myFormatter.format(date);
        }
    }

    /**
     * String To Date yyyy-MM-dd HH:mm:ss
     *
     * @param stringdate
     * @return
     */
    public static Date stringToDate(String stringdate, String formt) {
        SimpleDateFormat fdate = new SimpleDateFormat(formt);
        Date date = null;
        try {
            date = fdate.parse(stringdate);
        } catch (ParseException e) {
            date = null;


        }
        return date;
    }

    public static Date addSec(Date date, int sec) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.SECOND, sec);
        return cal.getTime();

    }

    public static Date minute(Date date, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();

    }

    public static Date hour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, hour);
        return cal.getTime();

    }

    public static Date addOneDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);

        return cal.getTime();

    }

    public static Date addDay(Date date, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);

        return cal.getTime();

    }

    public static Date addMonth(Date date, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();

    }

    public static String formatSecond(Object second) {
        String html = "0秒";
        if (second != null) {
            Long s = (Long) second;
            String format;
            Object[] array;
            Integer hours = (int) (s / (60 * 60));
            Integer minutes = (int) (s / 60 - hours * 60);
            Integer seconds = (int) (s - minutes * 60 - hours * 60 * 60);
            if (hours > 0) {
                format = "%1$,d时%2$,d分%3$,d秒";
                array = new Object[]{hours, minutes, seconds};
            } else if (minutes > 0) {
                format = "%1$,d分%2$,d秒";
                array = new Object[]{minutes, seconds};
            } else {
                format = "%1$,d秒";
                array = new Object[]{seconds};
            }
            html = String.format(format, array);
        }
        System.out.println(html);
        return html;
    }

    public static void main(String[] args) {
        Date date = addSec(new Date(), -60);
        System.out.println(new Date());
        System.out.println(dateToString(date, DATE_YYMMDDHHMM));

        //      Date date=     addOneDay(new Date());
        //      System.out.println(dateToString(date, DATE_YYYY_M_MDD_H_HMMSS));
    }
}
