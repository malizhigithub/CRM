package com.neuedu.crm.mapper;

import java.util.List;

import com.neuedu.crm.pojo.Orders;
import com.neuedu.crm.pojo.OrdersExample;

/**
 * OrdersMapper继承基类
 * @author MybatisGenerator
 */
public interface OrdersMapper extends MyBatisBaseDao<Orders, Integer, OrdersExample> {
	
	/**
	 *  描述：按照客户id分组查询最后一个订单的时间
	    * @Title: selectOrdersGroupByCustomerId
	    * @Description: TODO(按照客户id分组查询最后一个订单的时间)
	    * @param @return    参数
	    * @return List<Orders>    返回类型
	    * @throws
	 */
	public List<Orders> selectOrdersGroupByCustomerId();
}