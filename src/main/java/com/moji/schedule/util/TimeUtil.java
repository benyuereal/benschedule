package com.moji.schedule.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class TimeUtil {
    /**
     * 返回当前时间的六位数字
     */
//    public static List<Integer> currentTime() {
//        Calendar cal = Calendar.getInstance();
//        int year = cal.get(Calendar.YEAR);//获取年份
//        int month = cal.get(Calendar.MONTH);//获取月份
//        int day = cal.get(Calendar.DATE);//获取日
//        int hour = cal.get(Calendar.HOUR_OF_DAY);//小时
//        int minute = cal.get(Calendar.MINUTE);//分
//        int second = cal.get(Calendar.SECOND);//秒
//        return Arrays.asList(year, month, day, hour, minute, second);
//    }

    /**
     * 获取当前时间的秒索引位置
     */
    public static int currentSecond() {
        Calendar cal = Calendar.getInstance();
        int minute = cal.get(Calendar.MINUTE);//分
        int second = cal.get(Calendar.SECOND);//秒
        return minute * 60 + second;
    }
}
