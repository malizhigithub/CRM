package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.OrderItemMapper;
import com.neuedu.crm.mapper.ProductMapper;
import com.neuedu.crm.pojo.OrderItem;
import com.neuedu.crm.pojo.OrderItemExample;
import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.service.IOrderItemService;
/**
 * 
 * @author 
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class OrderItemServiceImpl implements IOrderItemService {

	@Autowired
	private OrderItemMapper orderItemMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	@Override
	public long countByOrderItemExample(OrderItemExample orderItemExample) {
		return orderItemMapper.countByExample(orderItemExample);
	}

	@Override
	public boolean deleteByOrderItemExample(OrderItemExample orderItemExample) {
		return orderItemMapper.deleteByExample(orderItemExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return orderItemMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertOrderItem(OrderItem orderItem) {
		return orderItemMapper.insert(orderItem) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(OrderItem orderItem) {
		return orderItemMapper.insertSelective(orderItem) > 0 ? true : false;
	}

	@Override
	public List<OrderItem> selectByOrderItemExample(OrderItemExample orderItemExample) {
		return orderItemMapper.selectByExample(orderItemExample);
	}

	@Override
	public OrderItem selectOrderItemByPrimaryKey(Integer id) {
		return orderItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByOrderItemExampleSelective(OrderItem orderItem, OrderItemExample orderItemExample) {
		return orderItemMapper.updateByExampleSelective(orderItem, orderItemExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByOrderItemExample(OrderItem orderItem, OrderItemExample orderItemExample) {
		return orderItemMapper.updateByExample(orderItem, orderItemExample) > 0 ? true : false;
	}

	@Override
	public boolean updateOrderItemByPrimaryKeySelective(OrderItem orderItem) {
		return orderItemMapper.updateByPrimaryKeySelective(orderItem) > 0 ? true : false;
	}

	@Override
	public boolean updateOrderItemByPrimaryKey(OrderItem orderItem) {
		return orderItemMapper.updateByPrimaryKey(orderItem) > 0 ? true : false ;
	}

	/**
	 * 分页获取订单的订单项
	 */
	@Override
	public List<OrderItem> selectByOrderItemExample(OrderItemExample orderItemExample, Pager pager) {
		
		Long count = orderItemMapper.countByExample(orderItemExample);
		
		pager.setTotal(count.intValue());
		
		orderItemExample.setLimit(pager.getPageSize());
		orderItemExample.setOffset(new Long(pager.getOffset()));
		
		List<OrderItem> orderItems = orderItemMapper.selectByExample(orderItemExample);
		
		//循环遍历设置产品的信息
		for (OrderItem orderItem : orderItems) {
			orderItem.setProduct(productMapper.selectByPrimaryKey(orderItem.getProductId()));
		}
		
		return orderItems;
	}


}
