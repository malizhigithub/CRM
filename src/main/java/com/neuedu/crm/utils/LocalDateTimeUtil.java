package com.neuedu.crm.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * @author wanghaoyu
 * @Description 用于对jdk8的localDateTime类型进行转换
 */
/**
 * @author HuangWanzong
 * @date 2018/07/24
 */
public class LocalDateTimeUtil {

    //获取当前时间的LocalDateTime对象
    //LocalDateTime.now();

    //根据年月日构建LocalDateTime
    //LocalDateTime.of();

    //比较日期先后
    //LocalDateTime.now().isBefore(),
    //LocalDateTime.now().isAfter(),

    /**
     * 描述：Date转换为LocalDateTime
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param date
     * @return LocalDateTime
     * @since 1.8
     *
     */
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 描述：LocalDateTime转换为Date
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param time
     * @return Date
     * @since 1.8
     *
     */
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 描述：获取指定日期的毫秒
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param time
     * @return Long
     * @since 1.8
     *
     */
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 描述：获取指定日期的秒
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param time
     * @return Long
     * @since 1.8
     *
     */
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 描述：获取指定时间的指定格式
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param time
     * @param pattern
     * @return String
     * @since 1.8
     *
     */
    public static String formatTime(LocalDateTime time,String pattern) {
        if(time != null) {
            return time.format(DateTimeFormatter.ofPattern(pattern));
        }
        return null;
    }

    /**
     * 描述：获取当前时间的指定格式
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param pattern
     * @return String
     * @since 1.8
     *
     */
    public static String formatNow(String pattern) {
        return  formatTime(LocalDateTime.now(), pattern);
    }

    /**
     * 描述：日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param time
     * @param number
     * @param field
     * @return LocalDateTime
     * @since 1.8
     *
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 描述：日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param time
     * @param number
     * @param field
     * @return LocalDateTime
     * @since 1.8
     *
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field){
        return time.minus(number,field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     * @param startTime
     * @param endTime
     * @param field  单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths()  ;
        }
        return field.between(startTime, endTime);
    }

    /**
     * 描述：获取一天的开始时间，2017,7,22 00:00
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param time
     * @return LocalDateTime
     * @since 1.8
     *
     */
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    /**
     * 描述：获取一天的结束时间，2017,7,22 23:59:59.999999999
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param time
     * @return LocalDateTime
     * @since 1.8
     *
     */
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }
    
    /**
     * 描述：String转为LocalDateTime
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param localDateTimeStr
     * @param pattern
     * @return LocalDateTime
     * @since 1.8
     *
     */
    public static LocalDateTime parse(String localDateTimeStr, String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(localDateTimeStr, df);
    }

}