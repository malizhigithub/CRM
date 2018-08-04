package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.OrdersMapper;
import com.neuedu.crm.pojo.Orders;
import com.neuedu.crm.pojo.OrdersExample;
import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.service.IOrdersService;
/**
 * 
 * @author 
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class OrdersServiceImpl implements IOrdersService {

	@Autowired
	private OrdersMapper ordersMapper;

	@Override
	public long countByOrdersExample(OrdersExample ordersExample) {
		
		return ordersMapper.countByExample(ordersExample);
	}

	@Override
	public boolean deleteByOrdersExample(OrdersExample ordersExample) {
		
		return ordersMapper.deleteByExample(ordersExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		
		return ordersMapper.deleteByPrimaryKey(id)>0?true:false;
	}

	@Override
	public boolean insertOrder(Orders orders) {
		
		return ordersMapper.insert(orders)>0?true:false;
	}

	@Override
	public boolean insertSelective(Orders orders) {
		
		return ordersMapper.insertSelective(orders)>0?true:false;
	}

	@Override
	public List<Orders> selectByOrdersExample(OrdersExample ordersExample) {
		
		return ordersMapper.selectByExample(ordersExample);
	}

	@Override
	public Orders selectOrderByPrimaryKey(Integer id) {
		
		return ordersMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByOrdersExample(Orders orders, OrdersExample ordersExample) {
		
		return ordersMapper.updateByExample(orders, ordersExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByOrdersExampleSelective(Orders orders, OrdersExample ordersExample) {
		
		return ordersMapper.updateByExampleSelective(orders, ordersExample) > 0 ? true : false;
	}

	@Override
	public boolean updateOrderByPrimaryKeySelective(Orders orders) {
		
		return ordersMapper.updateByPrimaryKeySelective(orders) > 0 ? true : false;
	}

	@Override
	public boolean updateOrderByPrimaryKey(Orders orders) {
		
		return ordersMapper.updateByPrimaryKey(orders) > 0 ? true : false;
	}

	@Override
	public List<Orders> selectOrdersGroupByCustomerId() {
		return ordersMapper.selectOrdersGroupByCustomerId();
	}

	/**
	 * 分页查询客户历史订单信息
	 * @author huangqingwen
	 */
	@Override
	public List<Orders> selectByOrdersExample(OrdersExample ordersExample, Pager pager) {
		
		Long count = ordersMapper.countByExample(ordersExample);
		
		pager.setTotal(count.intValue());
		
		ordersExample.setLimit(pager.getPageSize());
		ordersExample.setOffset(new Long(pager.getOffset()));
		List<Orders> orders = ordersMapper.selectByExample(ordersExample);
		
		return orders;
	}

}
