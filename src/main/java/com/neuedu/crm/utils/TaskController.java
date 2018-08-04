package com.neuedu.crm.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.neuedu.crm.pojo.CustomerLoss;
import com.neuedu.crm.pojo.CustomerLossExample;
import com.neuedu.crm.pojo.Orders;
import com.neuedu.crm.service.ICustomerLossService;
import com.neuedu.crm.service.IOrdersService;

/**
 * 
    * @ClassName: TashController
    * @Description: TODO(定时任务)
    * @author 黄清文
    * @date 2018年7月13日
    *
 */
@Component
public class TaskController {
	
	@Autowired
	private ICustomerLossService lossService;
	
	@Autowired
	private IOrdersService ordersService;


	/**
	* 
    * @Title: checkCustomerLoss
    * @Description: TODO(检查客户流失  计算距离上一下下单时间超过 6 个月就自动添加到客户流失表)
    * @param     参数
    * @return void    返回类型
    * @throws
    * @author huangqingwen 
	*/
	/**
	 * 每天早上10：15触发 
	 */
	@Scheduled(cron="0 15 10 ? * *")
	public void checkCustomerLoss(){
		/**
		 * 1. 按客户id分组查询时间最大的订单， 如果订单时间跟现在时间的差 time大于6个月，则自动添加到客户流失表
		 */
		/**
		 *  按客户id分组查询时间最大的订单
		 */
		List<Orders> orders = ordersService.selectOrdersGroupByCustomerId();
		
		/**
		 * 遍历订单集合  判断条件：
		 * 1. 有历史订单客户最后一次下单的时间距离现在的时间差大于6个月 
		 * 2. 没有历史订单的客户判断客户的添加时间距离现在的时间差大于6个月
		 */
		
		for (Orders o : orders) {
			if(o.getDate() != null){
				/**
				 * 有历史订单的客户，计算最后一次下单的时间距离现在的时间查是否大于180天
				 */
				Duration duration = Duration.between(o.getLocalDateTimeDate(), LocalDateTime.now());
				if(duration.toDays() > 1){ 
					//插入
					insertCustomerLoss(o);
				}
			}else{
				/**
				 * 没有历史订单的客户，计算客户的添加时间距离现在的时间差是否大于180天
				 */
				Duration duration = Duration.between(o.getCustomer().getCreateTime(), LocalDateTime.now());
				if(duration.toDays() > 1){
					//插入
					insertCustomerLoss(o);
				}
			}
		}
		
		/**
		 * 插入客户流失记录表
		 */
		
	}
	
	/**
	 * 
	    * @Title: insertCustomerLoss
	    * @Description: TODO(插入客户流失表)
	    * @param @param loss    参数
	    * @return void    返回类型
	    * @throws
	    * @author huangqingwen
	 */
	public void insertCustomerLoss(Orders orders){
		/**
		 * 组装需要插入流失客户表的数据
		 */
		CustomerLoss loss = new CustomerLoss();
		loss.setCustomerId(orders.getCustomerId());
		loss.setLastOrderTime(orders.getLocalDateTimeDate());
		loss.setStatus("0");
		/**
		 * 先判断客户流失表是否已经存在这条客户记录
		 */
		List<String> status = new ArrayList<String>();
		status.add("0");
		status.add("1");
		CustomerLossExample customerLossExample = new CustomerLossExample();
		customerLossExample.createCriteria().andCustomerIdEqualTo(loss.getCustomerId());
		customerLossExample.createCriteria().andStatusIn(status);
		List<CustomerLoss> losses = lossService.selectByCustomerLossExample(customerLossExample);
		
		if(losses == null || losses.size() == 0){
			/**
			 * 如果客户流失表不存在这条客户记录
			 */
			lossService.insertSelective(loss);
		}
	}
}
