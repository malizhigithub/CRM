package com.neuedu.crm.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.CustomerLossMapper;
import com.neuedu.crm.mapper.CustomerMapper;
import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerExample;
import com.neuedu.crm.pojo.CustomerLoss;
import com.neuedu.crm.pojo.CustomerLossExample;
import com.neuedu.crm.pojo.CustomerLossExample.Criteria;
import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.ICustomerLossService;
/**
 * 
 * @author WangHaoyu
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CustomerLossServiceImpl implements ICustomerLossService{

	@Autowired
	private CustomerLossMapper customerLossMapper;

	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public long countByCustomerLossExample(CustomerLossExample customerLossExample) {
		return customerLossMapper.countByExample(customerLossExample);
	}

	@Override
	public boolean deleteByCustomerLossExample(CustomerLossExample customerLossExample) {
		return customerLossMapper.deleteByExample(customerLossExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return customerLossMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertCustomerLoss(CustomerLoss customerLoss) {
		return customerLossMapper.insert(customerLoss) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(CustomerLoss customerLoss) {
		return customerLossMapper.insertSelective(customerLoss) > 0 ? true : false;
	}

	@Override
	public List<CustomerLoss> selectByCustomerLossExample(CustomerLossExample customerLossExample) {
		return customerLossMapper.selectByExample(customerLossExample);
		
	}

	@Override
	public CustomerLoss selectCustomerLossByPrimaryKey(Integer id) {
		return customerLossMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByCustomerLossExampleSelective(CustomerLoss customerLoss, CustomerLossExample customerLossExample) {
		return customerLossMapper.updateByExampleSelective(customerLoss, customerLossExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByCustomerLossExample(CustomerLoss customerLoss, CustomerLossExample customerLossExample) {
		return customerLossMapper.updateByExample(customerLoss, customerLossExample) > 0 ? true : false;
	}

	@Override
	public boolean updateCustomerLossByPrimaryKeySelective(CustomerLoss customerLoss) {
		return customerLossMapper.updateByPrimaryKeySelective(customerLoss) > 0 ? true : false;
	}

	@Override
	public boolean updateCustomerLossByPrimaryKey(CustomerLoss customerLoss) {
		return customerLossMapper.updateByPrimaryKey(customerLoss) > 0 ? true : false ;
	}

	@Override
	public List<CustomerLoss> selectByCustomerLossExampleByPager(CustomerLoss customerLoss, Pager pager, User user) {
		CustomerLossExample customerLossExample = new CustomerLossExample();
		/**
		 * 1. 根据登录用户查找 属于登录用户的客户
		 * 2. 拿客户id 作为查询客户流失的条件，查询出流失客户
		 * 3. 遍历流失客户 子遍历客户集合，相同客户id的把客户信息放到流失客户集合中
		 * 
		 */
		
		/**
		 * 根据登录用户查找 属于登录用户的客户  判断是否搜索
		 */
		CustomerExample customerExample = new CustomerExample();
		
		String queryCustomerName = "";
		if(customerLoss.getCustomer() != null) {
			if(customerLoss.getCustomer().getName() != null && !"".equals(customerLoss.getCustomer().getName())) {
				queryCustomerName = customerLoss.getCustomer().getName();
				
			}
		}
		
		//判断是否是客户经理
		if(user.getRoleId() == 1) {
			customerExample.createCriteria().andManagerIdEqualTo(user.getId()).andDeleteStatusEqualTo(0).andNameLike("%"+queryCustomerName+"%");
		}else { //如果不是客户经理则查询全部的客户流失记录
			customerExample.createCriteria().andDeleteStatusEqualTo(0).andNameLike("%"+queryCustomerName+"%");
		}
		
		List<Customer> customers = customerMapper.selectByExample(customerExample);
		
		/**
		 * 拿客户id 作为查询客户流失的条件，查询出流失客户
		 */
		List<Integer> customerIds = new ArrayList<Integer>();
		for (Customer customer : customers) {
			customerIds.add(customer.getId());
		}
		if(customerIds.size() > 0) {
			Criteria criteria = customerLossExample.createCriteria();
			criteria.andCustomerIdIn(customerIds);
			//搜索条件
			if(customerLoss.getStatus() != null && !"".equals(customerLoss.getStatus())) {
				criteria.andStatusEqualTo(customerLoss.getStatus());
			}
			
			//计算总数
			Long count = countByCustomerLossExample(customerLossExample);
			
			pager.setTotal(count.intValue());
			customerLossExample.setLimit(pager.getPageSize());
			customerLossExample.setOffset(new Long(pager.getOffset()));
			customerLossExample.setOrderByClause("status asc");
			List<CustomerLoss> losses = customerLossMapper.selectByExample(customerLossExample);
			
			/**
			 * 遍历流失客户 子遍历客户集合，相同客户id的把客户信息放到流失客户集合中
			 */
			if(losses!= null){
				for (int i=0;i<losses.size(); i++) {
					for (Customer customer : customers) {
						if(losses.get(i).getCustomerId().equals(customer.getId())){
							losses.get(i).setCustomer(customer);
						}
					}
				}
			}
			return losses;
		}
		
		return null;
	}

}
