package com.neuedu.crm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.neuedu.crm.pojo.CustomerLoss;
import com.neuedu.crm.pojo.CustomerLossExample;
import com.neuedu.crm.pojo.Orders;

/**
 * CustomerLossMapper继承基类
 * @author MybatisGenerator
 */
public interface CustomerLossMapper extends MyBatisBaseDao<CustomerLoss, Integer, CustomerLossExample> {
	
	/**
	* 描述：
    * @Title: insertLossBatch
    * @Description: TODO(批量插入)
    * @param @param orders
    * @param @return    参数
    * @return int    返回类型
    * @throws
    * @author huangqingwen
	*/
	public int insertLossBatch(@Param("orders")List<Orders> orders);
}