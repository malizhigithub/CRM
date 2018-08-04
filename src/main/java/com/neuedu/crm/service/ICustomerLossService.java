package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.CustomerLoss;
import com.neuedu.crm.pojo.CustomerLossExample;
import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.pojo.User;

/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface ICustomerLossService {
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param customerLossExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByCustomerLossExample(CustomerLossExample customerLossExample);

	/**
	 * 
	 * 描述：按照Example 删除CustomerLoss
	 * @author wujunyou
	 * @version 1.0
	 * @param customerLossExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByCustomerLossExample(CustomerLossExample customerLossExample);

	/**
	 * 
	 * 描述：按照CustomerLoss主键id删除CustomerLoss
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
	 * 描述：插入一条CustomerLoss数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param customerLoss  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertCustomerLoss(CustomerLoss customerLoss);
	
	/**
	 * 
	 * 描述：插入一条CustomerLoss数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param customerLoss 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(CustomerLoss customerLoss);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author huangqingwen
	 * @version 1.0
	 * @param customerLossExample 查询条件
	 * @param pager 
	 * @return List<CustomerLoss> 含CustomerLoss的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<CustomerLoss> selectByCustomerLossExample(CustomerLossExample customerLossExample);
	
	/**
	 * 
	 * 描述：按照CustomerLoss 的id 查找 CustomerLoss
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return CustomerLoss 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public CustomerLoss selectCustomerLossByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新CustomerLoss
	 * @author wujunyou
	 * @version 1.0
	 * @param customerLoss 对象中若有空则更新字段为null
	 * @param customerLossExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByCustomerLossExample(CustomerLoss customerLoss, CustomerLossExample customerLossExample);
	
	/**
	 * 
	 * 描述：更新CustomerLoss 
	 * @author wujunyou
	 * @version 1.0
	 * @param customerLoss 对象中若有空则不会更新此字段
	 * @param customerLossExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByCustomerLossExampleSelective(CustomerLoss customerLoss, CustomerLossExample customerLossExample);
		
	/**
	 * 
	 * 描述：按照CustomerLoss id 更新CustomerLoss
	 * @author wujunyou
	 * @version 1.0
	 * @param customerLoss 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateCustomerLossByPrimaryKeySelective(CustomerLoss customerLoss);
	
	/**
	 * 
	 * 描述：按照CustomerLoss id 更新CustomerLoss
	 * @author wujunyou
	 * @version 1.0
	 * @param customerLoss 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateCustomerLossByPrimaryKey(CustomerLoss customerLoss);

	/**
	 * 描述：
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param customerLoss
	 * @param pager
	 * @param user
	 * @return List<CustomerLoss>
	 * @since 1.8
	 *
	 */
	public List<CustomerLoss> selectByCustomerLossExampleByPager(CustomerLoss customerLoss, Pager pager, User user);	
}