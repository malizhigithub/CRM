package com.neuedu.crm.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;

/**
 * 描述：自定义时间转换器
 * @author wanghaoyu
 *
 */
public class LocalDateConverter implements Converter<String, LocalDate>{
    /**
     * 添加不同的日期格式
     */
    private static  List<String> formats = new ArrayList<String>();
        static{
            formats.add("yyyy");
            formats.add("yyyy-MM");
            formats.add("yyyy-MM-dd");
            formats.add("yyyy/MM");
            formats.add("yyyy/MM/dd");
        }
    
    @Override
    public LocalDate convert(String source) {
        
            try {
                String string = "^\\\\d{4}$";
                if (source.matches(string)) {
                    //2017
                    return parseDate(source, formats.get(0));
                } else if (source.matches("^\\d{4}-\\d{1,2}$")) {
                    //2017-09
                    return parseDate(source, formats.get(1));
                } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                    //2017-09-10
                    return parseDate(source, formats.get(2));
                }  else if (source.matches("^\\d{4}/\\d{1,2}$")) {
                    //2017/09
                    return parseDate(source, formats.get(5));
                } else if (source.matches("^\\d{4}/\\d{1,2}/\\d{1,2}$")) {
                    //2017/09/10
                    return parseDate(source, formats.get(6));
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
    public  LocalDate parseDate(String dateStr, String format) {
        LocalDate date=null;
            try {
                DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
                date = LocalDate.parse(dateStr, df);
            }  catch (Exception e1) {
                e1.printStackTrace();
            }
        return date;
    }
}
