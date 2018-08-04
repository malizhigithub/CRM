/**
 * 
 */
package com.neuedu.crm.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerExample;
import com.neuedu.crm.pojo.Linkman;
import com.neuedu.crm.pojo.LinkmanExample;
import com.neuedu.crm.pojo.LinkmanExample.Criteria;
import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.pojo.SaleOpportunity;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.ICustomerService;
import com.neuedu.crm.service.ILinkmanService;
import com.neuedu.crm.service.ISaleOpportunityService;
import com.neuedu.crm.utils.Operation;

/**
 * @author malizhi
 *
 */
@Operation(name="销售机会管理")
@RequestMapping("/opportunity")
@Controller
public class SaleOpportunityController {

	private Logger logger = LoggerFactory.getLogger(SaleOpportunityController.class);
	
	@Autowired
	private ISaleOpportunityService saleOpportunityService;
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private ILinkmanService linkmanService;
	
	/**
	 * 查看销售机会
	 * @author malizhi
	 * @param page
	 * @param limit
	 * @param saleOpportunity
	 * @return Map<String,Object>
	 * @version 1.0
	 * @exception Nothing
	 */
	@Operation(name="查看销售机会")
	@RequestMapping("/findSaleOpportunity")
	@ResponseBody
	public Map<String, Object> findSaleOpportunity(@RequestParam(value="page",required=false,defaultValue="1")Integer page,@RequestParam(value="limit",required=false,defaultValue="10") Integer limit,SaleOpportunity saleOpportunity,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>(16);
		//logger.info("----------------+++++++++**************"+saleOpportunity);
		Pager pager = new Pager(page.intValue(),limit.intValue());
		//分页，where条件查询
		List<SaleOpportunity> list = saleOpportunityService.selectBySaleOpportunitySelectiveAndPager(saleOpportunity,pager,request);
		//logger.info(list.toString());
		map.put("code", 0);
		map.put("success", true);
		map.put("data", list);
		map.put("count", pager.getTotal());
		map.put("msg","查询成功");
		return map;
	}
	
	
	
	
	/**
	 * 创建销售机会
	 * @author malizhi
	 * @return Map<String,Object>
	 * @version 1.0
	 * @exception Nothing
	 */
	@Operation(name="创建销售机会")
	@RequestMapping("/addSaleOpportunity")
	@ResponseBody
	public Map<String, Object> addSaleOpportunity(SaleOpportunity saleOpportunity,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>(16);
		//1:得到此客户的信息
		Customer customer = null;
		if(saleOpportunity.getCustomerId()!=null){
			customer = customerService.selectCustomerByPrimaryKey(saleOpportunity.getCustomerId());
		}
		
		//2:校验数据
		//	2.1)校验是否存在此客户
		if(customer == null){
			map.put("code", 200);
			map.put("msg", "此客户不存在");
			return map;
		}
		
		//3:填充数据
		//	3.1)填充联系人号码
		if(saleOpportunity.getContactId()!=null){
			Linkman linkman = linkmanService.selectLinkmanByPrimaryKey(saleOpportunity.getContactId());
			saleOpportunity.setContactPhone(linkman.getMobilePhone());
		}
		//	3.2)填充创建人和分配的经理为当前用户，因为只有经理才能创建机会而且只能分配给自己，创建和分配时间为当前时间
		//		设置创建人和创建时间
			User user = (User) request.getSession().getAttribute("user");
			saleOpportunity.setCreater(user.getId());
			saleOpportunity.setCreateDate(LocalDateTime.now());
		//		设置分配的经理和分配时间
			saleOpportunity.setManagerId(user.getId());
			saleOpportunity.setAllotDate(LocalDateTime.now());
		//	3.3)设置销售机会状态为已分配和删除状态
		//	saleOpportunity.setStatus("已分配");
			saleOpportunity.setDeleteStatus(0);
			
		//	3.4)设置联系方式
			Linkman linkman = linkmanService.selectLinkmanByPrimaryKey(saleOpportunity.getContactId());
			saleOpportunity.setContactPhone(linkman.getMobilePhone());
		
			
		
		//      如果saleOpportunity.getManagerId() == null，表示未指派
		/*if(saleOpportunity.getManagerId()!=null){
			if(customer.getManagerId() != saleOpportunity.getManagerId() ){
				map.put("code", 200);
				map.put("msg", "被指派人不是该客户的经理，请重新输入");
			}
		}*/
		
		//3:校验无误插入数据
		saleOpportunityService.insertSaleOpportunity(saleOpportunity);
		map.put("success", true);
		map.put("code", 0);
		map.put("msg", "插入成功");
		return map;
	}
	
	
	/**
	 * 更新销售机会(未指派的销售机会才能更新)
	 * @author malizhi
	 * @param saleOpportunity
	 * @return Map<String,Object>
	 * @version 1.0
	 * @exception Nothing
	 */
	@Operation(name="更新销售机会")
	@ResponseBody
	@RequestMapping("/updateSaleOpportunity")
	public Map<String, Object> updateSaleOpportunity(SaleOpportunity saleOpportunity,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>(16);
		
		//1:校验是此销售机会是否未指派，已经指派则不能更新
		/*if(saleOpportunity.getManagerId() != null){
			map.put("code", 200);
			map.put("msg", "此机会已经指派，不能更新");
			return map;
		}*/
		logger.info("++++++++++++++------"+saleOpportunity.toString());
		//2:未指派情况下，更新也只能是创建人更新，检测当前登录用户是否为此销售机会的创建人
		User user = (User) request.getSession().getAttribute("user");
		if((!user.getId().equals(saleOpportunity.getCreater()))){
			map.put("code", 200);
			map.put("msg", "更新失败，只能由创建人更新销售机会");
		}
		
		//3:更新销售机会
		saleOpportunityService.updateSaleOpportunityByPrimaryKeySelective(saleOpportunity);
		map.put("code", 0);
		map.put("msg", "更新成功");
		map.put("success", true);
		return map;
	}
	
	
	/**
	 * 删除销售机会(未指派的销售机会才能删除)
	 * @author malizhi
	 * @param saleOpportunity
	 * @return Map<String,Object>
	 * @version 1.0
	 * @exception Nothing
	 */
	@ResponseBody
	@Operation(name="删除销售机会")
	@RequestMapping("/deleteSaleOpportunity")
	public Map<String, Object> deleteSaleOpportunity(SaleOpportunity saleOpportunity,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>(16);
		
		// 1:校验是此销售机会是否未指派，已经指派则不能删除
		/*if (saleOpportunity.getManagerId() != null) {
			map.put("code", 200);
			map.put("msg", "此机会已经指派，不能删除");
			return map;
		}*/
		
		// 2:未指派情况下，删除也只能是创建人删除，检测当前登录用户是否为此销售机会的创建人
		saleOpportunity = saleOpportunityService.selectSaleOpportunityByPrimaryKey(saleOpportunity.getId());
		User user = (User) request.getSession().getAttribute("user");
		if (!user.getId().equals(saleOpportunity.getCreater())) {
			map.put("code", 200);
			map.put("msg", "删除失败，只能由创建人删除销售机会");
		}
		
		saleOpportunity.setDeleteStatus(1);
		
		//3:删除销售机会
		saleOpportunityService.updateSaleOpportunityByPrimaryKey(saleOpportunity);
		map.put("code", 0);
		map.put("msg", "删除成功");
		map.put("success", true);
		return map;
	}
	
	/**
	 * 批量删除销售机会
	 * @author malizhi
	 * @param ids
	 * @return Map<String,Object>
	 * @version 1.0
	 * @exception Nothing
	 */
	@Operation(name="批量删除销售机会")
	@ResponseBody
	@RequestMapping("deleteSaleOpportunities")
	public Map<String, Object> deleteSaleOpportunities(String ids){
		Map<String,Object> map = new HashMap<String,Object>(16);
		if(saleOpportunityService.deleteSaleOpportunitiesByPrimaryKey(ids)){
			map.put("code", 0);
			map.put("msg", "删除成功");
			map.put("success", true);
		}else{
			map.put("code", 200);
			map.put("msg", "删除失败");
			map.put("success", false);
		}
		return map;
	}
	
	
	
	/**
	 * 描述：根据用户的角色，展示相应的客户
	 * @author malizhi
	 * @param request
	 * @return Map<String,Object>
	 * @version 1.0
	 * @exception Nothing
	 */
    @Operation(name="查找当前经理的所有客户")
    @RequestMapping("/findAllCustomerByRole")
    @ResponseBody
    public Map<String, Object> findAllCustomerByRole(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<Customer> customers = null;
        //从session域中取出当前用户
        User user = (User) request.getSession().getAttribute("user");
        //经理角色的ID为1,只能查询到属于自己的客户
        //logger.info("_____--------------------+++_+_+_+_+_+_+_"+user.getRoleId().toString());
        if(user.getRoleId() == 1) {
            //新建客户模板类
            CustomerExample customerExample = new CustomerExample();
            //添加查询条件
            customerExample.createCriteria().andManagerIdEqualTo(user.getId());
            customers = customerService.selectByCustomerExample(customerExample);
        }else {
            customers = customerService.selectByCustomerExample(new CustomerExample());
        }
        map.put("list", customers);
        map.put("success", true);
        map.put("msg", "请求成功");
        return map;
    }
    
    /**
     * 根据客户ID查找此客户的所有联系人
     * @author malizhi
     * @param customerId
     * @return Map<String,Object>
     * @version 1.0
     * @exception Nothing
     */
    @Operation(name="根据客户ID查找此客户的所有联系人")
    @ResponseBody
    @RequestMapping("findLinkmanByCustomerId")
    public Map<String,Object> findLinkmanByCustomerId(@RequestParam("id")Integer customerId){
    	Map<String,Object> map = new HashMap<String ,Object>(16);
    	//如果客户ID为空，返回失败
    	if(customerId == null){
    		map.put("code", 200);
    		map.put("msg", "查找用户的联系人失败");
    		return map;
    	}
    	
    	LinkmanExample linkmanExample = new LinkmanExample();
    	Criteria criteria = linkmanExample.createCriteria();
    	criteria.andCustomerIdEqualTo(customerId);
    	List<Linkman> list = linkmanService.selectByLinkmanExample(linkmanExample);
    	
    	map.put("msg", "查找成功");
    	map.put("code", 0);
    	map.put("success", true);
    	map.put("data", list);
    	
    	return map;
    }
	
	
	
}
