package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.CustomerTransfer;
import com.neuedu.crm.pojo.CustomerTransferExample;
/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface ICustomerTransferService {
	
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param customerTransferExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByCustomerTransferExample(CustomerTransferExample customerTransferExample);

	/**
	 * 
	 * 描述：按照Example 删除CustomerTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param customerTransferExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByCustomerTransferExample(CustomerTransferExample customerTransferExample);

	/**
	 * 
	 * 描述：按照CustomerTransfer主键id删除CustomerTransfer
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
	 * 描述：插入一条CustomerTransfer数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param customerTransfer  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertCustomerTransfer(CustomerTransfer customerTransfer);
	
	/**
	 * 
	 * 描述：插入一条CustomerTransfer数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param customerTransfer 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(CustomerTransfer customerTransfer);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param customerTransferExample 查询条件
	 * @return List<CustomerTransfer> 含CustomerTransfer的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<CustomerTransfer> selectByCustomerTransferExample(CustomerTransferExample customerTransferExample);
	
	/**
	 * 
	 * 描述：按照CustomerTransfer 的id 查找 CustomerTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return CustomerTransfer 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public CustomerTransfer selectCustomerTransferByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新CustomerTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param customerTransfer 对象中若有空则更新字段为null
	 * @param customerTransferExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByCustomerTransferExample(CustomerTransfer customerTransfer, CustomerTransferExample customerTransferExample);
	
	/**
	 * 
	 * 描述：更新CustomerTransfer 
	 * @author wujunyou
	 * @version 1.0
	 * @param customerTransfer 对象中若有空则不会更新此字段
	 * @param customerTransferExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByCustomerTransferExampleSelective(CustomerTransfer customerTransfer, CustomerTransferExample customerTransferExample);
		
	/**
	 * 
	 * 描述：按照CustomerTransfer id 更新CustomerTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param customerTransfer 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateCustomerTransferByPrimaryKeySelective(CustomerTransfer customerTransfer);
	
	/**
	 * 
	 * 描述：按照CustomerTransfer id 更新CustomerTransfer
	 * @author wujunyou
	 * @version 1.0
	 * @param customerTransfer 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateCustomerTransferByPrimaryKey(CustomerTransfer customerTransfer);	
}