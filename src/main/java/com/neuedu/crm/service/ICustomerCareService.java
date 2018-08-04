package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.CustomerCare;
import com.neuedu.crm.pojo.CustomerCareExample;
/**
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface ICustomerCareService {
	
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param customerCareExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByCustomerCareExample(CustomerCareExample customerCareExample);

	/**
	 * 
	 * 描述：按照Example 删除CustomerCare
	 * @author wujunyou
	 * @version 1.0
	 * @param customerCareExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByCustomerCareExample(CustomerCareExample customerCareExample);

	/**
	 * 
	 * 描述：按照CustomerCare主键id删除CustomerCare
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
	 * 描述：插入一条CustomerCare数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param customerCare  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertCustomerCare(CustomerCare customerCare);
	
	/**
	 * 
	 * 描述：插入一条CustomerCare数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param customerCare 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(CustomerCare customerCare);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param customerCareExample 查询条件
	 * @return List<CustomerCare> 含CustomerCare的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<CustomerCare> selectByCustomerCareExample(CustomerCareExample customerCareExample);
	
	/**
	 * 
	 * 描述：按照CustomerCare 的id 查找 CustomerCare
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return CustomerCare 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public CustomerCare selectCustomerCareByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新CustomerCare
	 * @author wujunyou
	 * @version 1.0
	 * @param customerCare 对象中若有空则更新字段为null
	 * @param customerCareExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByCustomerCareExample(CustomerCare customerCare, CustomerCareExample customerCareExample);
	
	/**
	 * 
	 * 描述：更新CustomerCare 
	 * @author wujunyou
	 * @version 1.0
	 * @param customerCare 对象中若有空则不会更新此字段
	 * @param customerCareExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByCustomerCareExampleSelective(CustomerCare customerCare, CustomerCareExample customerCareExample);
		
	/**
	 * 
	 * 描述：按照CustomerCare id 更新CustomerCare
	 * @author wujunyou
	 * @version 1.0
	 * @param customerCare 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateCustomerCareByPrimaryKeySelective(CustomerCare customerCare);
	
	/**
	 * 
	 * 描述：按照CustomerCare id 更新CustomerCare
	 * @author wujunyou
	 * @version 1.0
	 * @param customerCare 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateCustomerCareByPrimaryKey(CustomerCare customerCare);

    /**
     * 描述：根据客户经理ID查找
     * @author wanghaoyu
     * @version 1.0
     * @param id
     * @return 
     * @exception Nothing
     * @since 1.8
     * 
     */
    public List<CustomerCare> selectCustomerCareByManagerId(Integer id);	
}
