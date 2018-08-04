package com.neuedu.crm.pojo;

import java.util.Date;

public class Report {
    
    /**
     * 日期格式
     */
    public static String TIME_YEAR = "%Y";
    public static String TIME_YEAR_MONTH = "%Y-%m";
    public static String TIME_YEAR_MONTH_DAY = "%Y-%m-%d";
    public static String TIME_MONTH_DAY = "%m-%d";
    


    public static String CATEGORY_LEVEL = "level";
    public static String CATEGORY_TYPE = "type";
    public static String CATEGORY_STATUS = "status";
    public static String CATEGORY_CREDIT = "credit";
    public static String CATEGORY_SOURCE = "source";
    public static String CATEGORY_MATURITY = "maturity";
   
    public static String SERVICE_STATUS = "status";
    public static String SERVICE_TYPE = "type";
    
    
    private String resultDateFormat = Report.TIME_YEAR_MONTH_DAY;
    private String timeFormat = Report.TIME_YEAR_MONTH_DAY;
    private Date startTime;
    private Date endTime;
    private String category = Report.CATEGORY_STATUS;
    private String service;
    private Integer userId;
    private Integer day;
    private Integer dictionaryTypeId;

    public String getResultDateFormat() {
        return resultDateFormat;
    }
    public void setResultDateFormat(String resultDateFormat) {
        this.resultDateFormat = resultDateFormat;
    }
    public String getTimeFormat() {
        return timeFormat;
    }
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
    public Integer getDay() {
        return day;
    }
    public void setDay(Integer day) {
        this.day = day;
    }
    public Integer getDictionaryTypeId() {
        return dictionaryTypeId;
    }
    public void setDictionaryTypeId(Integer dictionaryTypeId) {
        this.dictionaryTypeId = dictionaryTypeId;
    }
    
    
    
    
}
