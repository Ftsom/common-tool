package com.ftsom.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 日期工具类「java.time」
 *
 * @author ftsom
 * @date 2022/10/10 10:02
 */
public class DateUtil {

    private static final String PATTERN_DATE = "yyyyMMdd";

    private static final String PATTERN_LINE_DATE = "yyyy-MM-dd";
    private static final DateTimeFormatter DATE_TIME_LINE_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_LINE_DATE);

    private static final String PATTERN_TIME = "HH:mm:ss";
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_TIME);

    private static final String PATTERN_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(PATTERN_DATE_TIME);

    /**
     * 获取当前日期字符串
     *
     * @return 当前日期字符串：2022-10-10
     */
    public static String getDateNowStr() {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        return localDate.format(DATE_TIME_LINE_FORMATTER);
    }

    /**
     * 获取当前日期格式字符串
     *
     * @return 当前日期格式字符串
     */
    public static String getDateNowFormat(String pattern) {
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串：10:23:43
     */
    public static String getTimeNowStr() {
        LocalTime localTime = LocalTime.now(ZoneId.systemDefault());
        return localTime.format(TIME_FORMATTER);
    }

    /**
     * 获取当前时间格式字符串
     *
     * @return 当前时间格式字符串
     */
    public static String getTimeNowFormat(String pattern) {
        LocalTime localTime = LocalTime.now(ZoneId.systemDefault());
        return localTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前日期时间字符串
     *
     * @return 当前日期字符串：2022-10-10 10:23:43
     */
    public static String getDateTimeNowStr() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTime.format(DATE_TIME_FORMATTER);
    }

    /**
     * 获取当前日期时间格式字符串
     *
     * @return 当前日期时间格式字符串
     */
    public static String getDateTimeNowFormat(String pattern) {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.systemDefault());
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取开始时间
     *
     * @param localDate 日期
     * @return 时间：2022-10-10T00:00
     */
    public static LocalDateTime getStartTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MIN);
    }

    /**
     * 获取结束时间
     *
     * @param localDate 日期
     * @return 时间：2022-10-10T23:59:59.999999999
     */
    public static LocalDateTime getEndTime(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    /**
     * 转化为Date
     *
     * @param localDate 日期
     * @return 时间
     */
    public static Date toDate(LocalDate localDate) {
        return toDate(localDate.atStartOfDay());
    }

    /**
     * 转化为Date
     *
     * @param localDateTime 时间
     * @return 时间
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 转化为LocalDate
     *
     * @param date 时间
     * @return LocalDate
     */
    public static LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 转化为LocalDateTime
     *
     * @param date 时间
     * @return LocalDateTime
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static void main(String[] args) {
        System.out.println(getDateNowStr());
        System.out.println(getDateNowFormat(PATTERN_DATE));

        System.out.println("=========");
        System.out.println(getTimeNowStr());
        System.out.println(getTimeNowFormat("HH时mm分ss秒"));

        System.out.println("=========");
        System.out.println(getDateTimeNowStr());
        System.out.println(getDateTimeNowFormat("yyyy年MM月dd日 HH时mm分ss秒"));

        System.out.println("=========");
        System.out.println(getStartTime(LocalDate.now().minusDays(1)));
        System.out.println(getEndTime(LocalDate.now().plusDays(1)));

        System.out.println("=========");
        System.out.println(toDate(LocalDate.now()));
        System.out.println(toDate(LocalDateTime.now()));

        System.out.println("=========");
        System.out.println(toLocalDate(new Date()));
        System.out.println(toLocalDateTime(new Date()));
    }
}
