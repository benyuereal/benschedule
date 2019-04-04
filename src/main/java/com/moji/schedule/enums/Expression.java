package com.moji.schedule.enums;

public class Expression {

    /**
     * 字符串型时间表达式
     */
    public static final String ALL = "*";
    public static final String TOTAL = "?";
    public static final String SPACE = "/";

    public static final int[] ALL_TIME = new int[]{100};

    /**
     * int[] 型时间表达式
     */
    public static final int[] ALL_YEAR = new int[]{1, 1};
    public static final int[] ALL_MONTH = new int[]{1, 12};
    public static final int[] ALL_DAY = new int[]{1, 30};
    public static final int[] ALL_HOUR = new int[]{0, 23};
    public static final int[] ALL_MINUTE = new int[]{0, 59};
    public static final int[] ALL_SECOND = new int[]{0, 59};
    public static final int[] RANGE_MONTH = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
    public static final int[] RANGE_DAY = new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30};
    public static final int[] RANGE_HOUR = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23};
    public static final int[] RANGE_MINUTE_SECOND = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,
            31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59};

}
