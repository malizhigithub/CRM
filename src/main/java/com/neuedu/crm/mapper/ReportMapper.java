package com.neuedu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.neuedu.crm.pojo.Report;


/**
 * 报表模块
 * @author admin
 *
 */
public interface ReportMapper{
	
	/**
     * 
     * 描述：统计公司新增的客户量 近n天的记录
     * @author huangqingwen
     * @version 1.0
     * @param n 天数
     * @param 
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
	public List<Map<String, Object>> countCustomerIncrease(@Param("n")int n);
	
	
	/**
     * 
     * 描述：统计公司损失的客户量 近n天的记录
     * @author huangqingwen
     * @version 1.0
     * @param n 天数
     * @param 
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
	public List<Map<String, Object>> countCustomerDecrease(@Param("n")int n);
	/**
	 * 描述：查询某段时间内客户的新增客户
	 * @author HuangWanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @param report
	 * @return List<Map<String,Object>>
	 * @since 1.8
	 *
	 */
	public List<Map<String, Object>> countCustomerByDate(Report report);
	/**
	 * 描述：查询某段时间内客户的新增跟进记录
	 * @author HuangWanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @param report
	 * @return List<Map<String,Object>>
	 * @since 1.8
	 *
	 */
	public List<Map<String, Object>> countFollowupByDate(Report report);
	/**
	 * 描述：查询某段时间内客户的新增客户,按照某个客户信息某个字段分类
	 * @author HuangWanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @param report
	 * @return List<Map<String,Object>>
	 * @since 1.8
	 *
	 */
	public List<Map<String, Object>> countCustomerByCategory(Report report);
	
	/**
	 * 
	    * @Title: countManagerCustomer
	    * @Description: TODO(统计某时段内客户经理客户量的排名)
	    * @param @param report
	    * @param @return    参数
	    * @return List<Map<String,Object>>    返回类型
	    * @throws
	    * @author huangqingwen
	 */
	public List<Map<String, Object>> countManagerCustomerRank(Report report);
	
	/**
	 * 
	    * @Title: countCustomerOrderPrice
	    * @Description: TODO(统计某时段内客户与公司成交的金额数排名)
	    * @param @param report
	    * @param @return    参数
	    * @return List<Map<String,Object>>    返回类型
	    * @throws
	    * @author huangqingwen
	 */
	public List<Map<String, Object>> customerOrderPriceRank(Report report);
	
	/**
	 * 
	 * 描述：统计某个时段内某个客户经理服务的情况
	 * @author wanghaoyu
	 * @version 1.0
	 * @param report
	 * @return List<Map<String,Object>>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<Map<String, Object>> countManagerSerivce(Report report);
	
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
