package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.Orders;
import com.neuedu.crm.pojo.OrdersExample;
import com.neuedu.crm.pojo.Pager;
/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface IOrdersService {
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param ordersExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByOrdersExample(OrdersExample ordersExample);

	/**
	 * 
	 * 描述：按照Example 删除Order
	 * @author wujunyou
	 * @version 1.0
	 * @param ordersExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByOrdersExample(OrdersExample ordersExample);

	/**
	 * 
	 * 描述：按照Order主键id删除Order
	 * @author wujunyou
	 * @version 1.0
	 * @param id 数据字典id
	 * @return boolean 删除结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：插入一条Order数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param orders  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertOrder(Orders orders);
	
	/**
	 * 
	 * 描述：插入一条Order数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param orders 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(Orders orders);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param ordersExample 查询条件
	 * @return List<Order> 含Order的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<Orders> selectByOrdersExample(OrdersExample ordersExample);
	
	/**
	 * 
	 * 描述：按照Order 的id 查找 Order
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return Order 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public Orders selectOrderByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新Order
	 * @author wujunyou
	 * @version 1.0
	 * @param orders 对象中若有空则更新字段为null
	 * @param ordersExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByOrdersExample(Orders orders, OrdersExample ordersExample);
	
	/**
	 * 
	 * 描述：更新Order 
	 * @author wujunyou
	 * @version 1.0
	 * @param orders 对象中若有空则不会更新此字段
	 * @param ordersExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByOrdersExampleSelective(Orders orders, OrdersExample ordersExample);
		
	/**
	 * 
	 * 描述：按照Order id 更新Order
	 * @author wujunyou
	 * @version 1.0
	 * @param orders 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateOrderByPrimaryKeySelective(Orders orders);
	
	/**
	 * 
	 * 描述：按照Order id 更新Order
	 * @author wujunyou
	 * @version 1.0
	 * @param orders 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateOrderByPrimaryKey(Orders orders);		
	
	/**
	 * 描述：按照客户id分组查询最后一个订单的时间
	 * @author HuangWanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @return List<Orders>
	 * @since 1.8
	 *
	 */
	public List<Orders> selectOrdersGroupByCustomerId();
	
	/**
	 * 描述：分页查询客户历史订单信息
	 * @author HuangWanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @param ordersExample
	 * @param pager
	 * @return List<Orders>
	 * @since 1.8
	 *
	 */
	public List<Orders> selectByOrdersExample(OrdersExample ordersExample, Pager pager);
}
