package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerExample;
import com.neuedu.crm.pojo.Linkman;

/**
 * @author HuangQingwen
 * @date 2018/07/11
 */
public interface ICustomerService {
	
    /**
     * 描述：按照Example 统计记录总数
     * @author HuangQingwen
     * @date 2018/07/11
     * @version 1.0
     * @param customerExample
     * @return long
     * @since 1.8
     *
     */
	public long countByCustomerExample(CustomerExample customerExample);
	
	/**
	 * 描述：按照Example 删除Customer
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param customerExample
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean deleteByCustomerExample(CustomerExample customerExample);
	
	/**
	 * 描述：按照Customer主键id删除Customer
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param id
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean deleteByPrimaryKey(Integer id);
	
	/**
	 * 描述：插入一条Customer数据 如字段为空，则插入null
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param customer
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean insertCustomer(Customer customer);
	
	/**
	 * 描述：插入一条Customer数据，如字段为空，则插入数据库表字段的默认值
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param customer
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(Customer customer);
	
	/**
	 * 描述：按照Example 条件 模糊查询
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param customerExample
	 * @return List<Customer>
	 * @since 1.8
	 *
	 */
	public List<Customer> selectByCustomerExample(CustomerExample customerExample);
	
	/**
	 * 描述：按照Customer 的id 查找 Customer
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param id
	 * @return Customer
	 * @since 1.8
	 *
	 */
	public Customer selectCustomerByPrimaryKey(Integer id);
	
	/**
	 * 描述：更新Customer ， Customer对象中若有空则不会更新此字段  CustomerExample为where条件
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param customer
	 * @param customerExample
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateByCustomerExampleSelective(Customer customer, CustomerExample customerExample);
	
	/**
	 * 描述：更新Customer， Customer对象中若有空则更新字段为null   CustomerExample为where条件
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param customer
	 * @param customerExample
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateByCustomerExample(Customer customer, CustomerExample customerExample);
	
	/**
	 * 描述：按照Customer id 更新Customer  Customer对象中如有空则不会更新此字段
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param customer
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateCustomerByPrimaryKeySelective(Customer customer);
	
	/**
	 * 描述：按照Customer id 更新Customer  Customer对象中如有空则更新此字段为null
	 * @author HuangQingwen
	 * @date 2018/07/11
	 * @version 1.0
	 * @param customer
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateCustomerByPrimaryKey(Customer customer);

	/**
	 * 描述：添加客户，同时设置主要联系人
	 * @author HuangWanzong
	 * @date 2018/07/11
	 * @version 1.0
	 * @param customer
	 * @param linkman
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(Customer customer, Linkman linkman);
}
