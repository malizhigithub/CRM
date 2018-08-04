package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import com.neuedu.crm.pojo.Report;

/**
 * 
 * @author HuangQingwen
 * @date 2018/07/24
 */
public interface IReportService {
	
	/**
     * 
     * 描述：统计公司新增的客户量 近n天的记录
     * @author HuangQingwen
     * @version 1.0
     * @param n
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
	public List<Map<String, Object>> countCustomerIncrease(int n);
	
	/**
     * 
     * 描述：统计公司损失的客户量 近n天的记录
     * @author HuangQingwen
     * @version 1.0
     * @param n 天数
     * @param 
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
	public List<Map<String, Object>> countCustomerDecrease(int n);
	
	/**
	 * 描述：描述：统计这一年的新增客户
	 * @author HuangWanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @param year 格式 2018
	 * @param userId
	 * @return List<Map<String,Object>>
	 * @since 1.8
	 *
	 */
	public List<Map<String, Object>> countCustomerByYear(String year,Integer userId);
	
	/**
	 * 描述：按照月份统计这一年的新增客户
	 * @author HuangWanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @param year 格式 2018
	 * @param userId
	 * @return List<Map<String,Object>>
	 * @since 1.8
	 *
	 */
	public List<Map<String, Object>> countCustomerByMonth(String year,Integer userId);
	
	/**
	 * 描述：统计这个月每天的新增客户
	 * @author HuangWanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @param yearMonth
	 * @param userId
	 * @return List<Map<String,Object>>
	 * @since 1.8
	 *
	 */
	public List<Map<String, Object>> countCustomerByDay(String yearMonth,Integer userId);
	
	/**
	 * 描述：统计这一年的跟踪记录
	 * @author HuangWanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @param year 格式 2018-12 2018-02
	 * @param userId
	 * @return List<Map<String,Object>>
	 * @since 1.8
	 *
	 */
    public List<Map<String, Object>> countFollowUpByYear(String year,Integer userId);
    
    /**
     * 
     * 描述：按照月份统计这一年的跟踪记录
     * @author HuangWanzong
     * @date 2018/07/24
     * @version 1.0
     * @param year 格式 2018
     * @param userId
     * @return List<Map<String,Object>>
     * @since 1.8
     *
     */
    public List<Map<String, Object>> countFollowUpByMonth(String year,Integer userId);
    

    /**
     * 
     * 描述：统计这个月每天的跟踪记录
     * @author HuangWanzong
     * @date 2018/07/24
     * @version 1.0
     * @param yearMonth 格式 2018-12 2018-02
     * @param userId
     * @return List<Map<String,Object>>
     * @since 1.8
     *
     */
    public List<Map<String, Object>> countFollowUpByDay(String yearMonth,Integer userId);
	
    
    /**
     * 描述：按照某个分类统计这一年的新增客户
     * @author HuangWanzong
     * @date 2018/07/24
     * @version 1.0
     * @param category 必须从Report中读取以 CATEGORY开头的静态变量
     * @param year 格式 2018
     * @param userId
     * @return List<Map<String,Object>>
     * @since 1.8
     *
     */
    public List<Map<String, Object>> countCustomerByCategoryWithYear(String category,String year,Integer userId);

    /**
     * 描述：按照某个分类统计这一月的新增客户
     * @author HuangWanzong
     * @date 2018/07/24
     * @version 1.0
     * @param category 必须从Report中读取以 CATEGORY开头的静态变量
     * @param yearMonth 格式 2018-02 2018-12
     * @param userId
     * @return List<Map<String,Object>>
     * @since 1.8
     *
     */
    public List<Map<String, Object>> countCustomerByCategoryWithMonth(String category,String yearMonth,Integer userId);

    /**
     * 
     * 描述：按照某个分类统计这一天的新增客户
     * @author HuangWanzong
     * @date 2018/07/24
     * @version 1.0
     * @param category 必须从Report中读取以 CATEGORY开头的静态变量
     * @param yearMonthDay 格式 2018-01-01
     * @param userId
     * @return List<Map<String,Object>>
     * @since 1.8
     *
     */
    public List<Map<String, Object>> countCustomerByCategoryWithDay(String category,String yearMonthDay,Integer userId);
    
    /**
     * 
     * 描述：按照某个分类和日期统计某个客户经理的服务数量等等
     * @author wanghaoyu
     * @version 1.0
     * @param service 这个为从Report中的静态变量
     * @param day 表示查询近几天内的记录
     * @param userId 查询的客户经理的ID
     * @return List<Map<String,Object>>
     * @exception Nothing
     * @since 1.8
     *
     */
    public List<Map<String, Object>> countManagerService(String service, Integer day, Integer userId);
    
    /**
     * 
     * 描述：按照某个分类和日期统计全部客户经理的服务信息
     * @author wanghaoyu
     * @version 1.0
     * @param service
     * @param day
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    public Map<String, Object> countAllManagerService(String service, Integer day);
    
    /**
     * 
     * 描述：统计某时段内客户经理客户量的排名
     * @author huangqingwen
     * @version 1.0
     * @param service
     * @param report
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    public List<Map<String, Object>> countManagerCustomerRank(Report report);

    /**
     * 
     * 描述：统计某时段内客户与公司成交的金额数排名
     * @author huangqingwen
     * @version 1.0
     * @param service
     * @param report
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    public List<Map<String, Object>> customerOrderPriceRank(Report report);
    
    /**
	 * 
	 * 描述：统计公司的客户来源情况
	 * @author huangqingwen
	 * @version 1.0
	 * @param report
	 * @return List<Map<String,Object>>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<Map<String, Object>> countCustomerSource();
}
