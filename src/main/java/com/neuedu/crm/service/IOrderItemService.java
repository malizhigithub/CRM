package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.OrderItem;
import com.neuedu.crm.pojo.OrderItemExample;
import com.neuedu.crm.pojo.Pager;
/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface IOrderItemService {
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param orderItemExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByOrderItemExample(OrderItemExample orderItemExample);

	/**
	 * 
	 * 描述：按照Example 删除OrderItem
	 * @author wujunyou
	 * @version 1.0
	 * @param orderItemExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByOrderItemExample(OrderItemExample orderItemExample);

	/**
	 * 
	 * 描述：按照OrderItem主键id删除OrderItem
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
	 * 描述：插入一条OrderItem数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param orderItem  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertOrderItem(OrderItem orderItem);
	
	/**
	 * 
	 * 描述：插入一条OrderItem数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param orderItem 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(OrderItem orderItem);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param orderItemExample 查询条件
	 * @return List<OrderItem> 含OrderItem的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<OrderItem> selectByOrderItemExample(OrderItemExample orderItemExample);
	
	/**
	 * 
	 * 描述：按照OrderItem 的id 查找 OrderItem
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return OrderItem 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public OrderItem selectOrderItemByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新OrderItem
	 * @author wujunyou
	 * @version 1.0
	 * @param orderItem 对象中若有空则更新字段为null
	 * @param orderItemExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByOrderItemExample(OrderItem orderItem, OrderItemExample orderItemExample);
	
	/**
	 * 
	 * 描述：更新OrderItem 
	 * @author wujunyou
	 * @version 1.0
	 * @param orderItem 对象中若有空则不会更新此字段
	 * @param orderItemExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByOrderItemExampleSelective(OrderItem orderItem, OrderItemExample orderItemExample);
		
	/**
	 * 
	 * 描述：按照OrderItem id 更新OrderItem
	 * @author wujunyou
	 * @version 1.0
	 * @param orderItem 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateOrderItemByPrimaryKeySelective(OrderItem orderItem);
	
	/**
	 * 
	 * 描述：按照OrderItem id 更新OrderItem
	 * @author wujunyou
	 * @version 1.0
	 * @param orderItem 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateOrderItemByPrimaryKey(OrderItem orderItem);

	/**
	 * 描述：分页获取订单的订单项
	 * @author HuangQingwen
	 * @version 1.0
     * @param orderItemExample
     * @param pager
	 * @return List<OrderItem>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<OrderItem> selectByOrderItemExample(OrderItemExample orderItemExample, Pager pager);	
}
