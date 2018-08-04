package com.neuedu.crm.utils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

/**
 * 描述：自定义时间转换器
 * @author wanghaoyu
 *
 */
public class LocalDateTimeConverter implements Converter<String, LocalDateTime>{
    /**
     * 添加不同的日期格式
     */
    private static  List<String> formats = new ArrayList<String>();
        static{
            formats.add("yyyy");
            formats.add("yyyy-MM");
            formats.add("yyyy-MM-dd");
            formats.add("yyyy-MM-dd HH:mm");
            formats.add("yyyy-MM-dd HH:mm:ss");
            formats.add("yyyy/MM");
            formats.add("yyyy/MM/dd");
            formats.add("yyyy/MM/dd HH:mm");
            formats.add("yyyy/MM/dd HH:mm:ss");
        }
    
    @Override
    public LocalDateTime convert(String source) {
        
            try {
                String string = "^\\d{4}$";
                if (source.matches(string)) {
                    //2017
                    return parseDate(source, formats.get(0));
                } else if (source.matches("^\\d{4}-\\d{1,2}$")) {
                    //2017-09
                    return parseDate(source, formats.get(1));
                } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                    //2017-09-10
                    return parseDate(source, formats.get(2));
                } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
                    //2017-09-10 21:15
                    return parseDate(source, formats.get(3));
                } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
                    //2017-09-10 21:15:30
                    return parseDate(source, formats.get(4));
                } else if (source.matches("^\\d{4}/\\d{1,2}$")) {
                    //2017/09
                    return parseDate(source, formats.get(5));
                } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")) {
                    //2017/09/10
                    return parseDate(source, formats.get(6));
                } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
                    //2017/09/10 21:15
                    return parseDate(source, formats.get(7));
                } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
                    //2017/09/10 21:15:30
                    return parseDate(source, formats.get(8));
                } else {
                    throw new Exception("没有相应的日期类型匹配");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return null;
    }
    
     /**
     * 功能描述：格式化日期
     * 
     * @param dateStr
     *            String 字符型日期
     * @param format
     *            String 格式
     * @return LocalDateTime 日期
     */
    public  LocalDateTime parseDate(String dateStr, String format) {
        LocalDateTime date=null;
            try {
                date = LocalDateTimeUtil.parse(dateStr, format);
            }  catch (Exception e1) {
                e1.printStackTrace();
            }
        return date;
    }
}
