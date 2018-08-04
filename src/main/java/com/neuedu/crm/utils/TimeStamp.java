package com.neuedu.crm.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * 时间格式工具
 * 1. 获得当前的时间戳 getNowTimeStamp
 * 2. 将Unix时间戳转换成指定格式日期字符串 TimeStamp2Date(String timestampString, String formats)
 * @author huangqingwen
 *
 */
public class TimeStamp {
	 
	/**
     * 取得当前时间戳（精确到秒）
     *
     * @return nowTimeStamp
     */
    public static int getNowTimeStamp() {
        long time = System.currentTimeMillis();
        String nowTimeStamp = String.valueOf(time / 1000);
        return Integer.parseInt(nowTimeStamp);
    }
    
    /**
     * Java将Unix时间戳转换成指定格式日期字符串
     * @param timestampString 时间戳 如："1473048265";
     * @param formats 要格式化的格式 默认："yyyy-MM-dd HH:mm:ss";
     *
     * @return 返回结果 如："2016-09-05 16:06:42";
     */
    public static String timeStamp2Date(String timestampString, String formats) {
    	if(formats == null || "".equals(formats))
    	{
    	    formats = "yyyy-MM-dd HH:mm:ss";
    	}
    	if(timestampString==null || "".equals(timestampString)){
    		return "";
    	}
        Long timestamp = Long.parseLong(timestampString) * 1000;
        String date = new SimpleDateFormat(formats, Locale.CHINA).format(new Date(timestamp));
        return date;
    }
    
    /**
     * 将时间转换为时间戳   
       * @Title : dateToStamp 
       * @功能描述: TODO
       * @返回类型：Long 
       * @作者：    huangqingwen
       * @throws ：
     */
    public static long dateToStamp(String s, String pattern)throws ParseException{
        //String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        //res = String.valueOf(ts);
        return new Long(ts);
    }
}
