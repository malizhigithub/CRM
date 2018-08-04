package com.neuedu.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerExample;
import com.neuedu.crm.pojo.FollowUp;
import com.neuedu.crm.pojo.FollowUpExample;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.pojo.FollowUpExample.Criteria;
import com.neuedu.crm.service.ICustomerService;
import com.neuedu.crm.service.IFollowUpService;
import com.neuedu.crm.utils.Operation;


/**
 * @author HuangWanzong
 * @date 2018/07/12
 */
@Operation(name="跟踪记录管理")
@Controller
@RequestMapping("followup")
public class FollowUpController {
    
    @Autowired
    private IFollowUpService followupService;

    @Autowired 
    private ICustomerService customerService;
    
    /**
     *  分页查询跟踪记录
     * @author HuangWanzong
     * @date 2018/7/12/
     * @param page 必填参数，需要的查询的页数，默认值 1
     * @param limit 可选参数，分页大小，默认值 5
     * @param followUp 内部customerId为必填参数
     * @param type 可选参数，值为 all 时 page与limit参数无效
     */
    @RequiresPermissions("6001")
    @Operation(name="分页查询跟踪记录")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> listLinkman(Integer page,Integer limit,FollowUp followUp,String type,HttpServletRequest request){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        FollowUpExample example = new FollowUpExample();
        Criteria criteria = example.createCriteria();

        if(followUp == null) {
            map.put("code", -1);
            map.put("msg", "非法查询");
            return map;
        }
        
        //设置分页参数
        if(page == null || page <= 0) {
            page = 1;
        }
        if(limit == null || limit <= 0) {
            limit = 5;
        }
        
        //如果不是查询全部数据，就设置分页属性
        String all = "all";
        if(!all.equals(type)) {
            example.setLimit(limit);
            Long offset = new Long((page-1)*limit);
            example.setOffset(offset);
        }
        //设置时间倒叙排序
        example.setOrderByClause(" time desc");
        
        //设置客户id
        if(followUp.getCustomerId() != null) {
            criteria.andCustomerIdEqualTo(followUp.getCustomerId());
        }else {
            //如果客户ID不存在，表示查询该用户所有的客户跟踪记录
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            
            CustomerExample customerExample = new CustomerExample();
            customerExample.createCriteria().andManagerIdEqualTo(user.getId());
            
            List<Customer> customers = customerService.selectByCustomerExample(customerExample);
            List<Integer> ids = new ArrayList<Integer>();
            for(Customer customer : customers) {
                ids.add(customer.getId());
            }
            criteria.andCustomerIdIn(ids);
        }
        
        Long count = followupService.countByFollowUpExample(example);
        List<FollowUp> list = followupService.selectByFollowUpExample(example);
        
        map.put("data", list);
        map.put("count", count);
        map.put("code", 0);
        
        int pages = (int)Math.ceil((count*1.0)/limit);
        map.put("pages", pages);
        
        
        return map;
    }
    
    /**
     * 
     * 描述：添加跟踪记录
     * @author HuangWanzong
     * @date 2018/7/12
     * @version 1.0
     * @param followUp 要添加的跟踪记录数据
     * @return Map<String,Object>
     * @since 1.8
     *
     */
    @RequiresPermissions("6002")
    @Operation(name="添加跟踪记录")
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> addLinkman(FollowUp followUp,HttpServletRequest request){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        User user = (User)request.getSession().getAttribute("user");
        Integer managerId = user.getId();
        
        if(followUp == null) {
            map.put("code", -1);
            map.put("msg", "非法查询");
            return map;
        }

        //检测客户id
        if(followUp.getCustomerId() == null) {
            map.put("code", -2);
            map.put("msg", "客户ID不存在");
            return map;
        }

        //设置创建者id
        followUp.setManagerId(managerId);
        
        if(followupService.insertSelective(followUp)) {
            map.put("code", 0);
            map.put("status", true);
            map.put("msg", "操作成功");
        }else {
            map.put("code", -1000);
            map.put("status", false);
            map.put("msg", "操作失败");
        }

        return map;
    }
    
 
    /**
     * 描述：更新跟踪记录
     * @author HuangWanzong
     * @date 2018/7/12
     * @version 1.0
     * @param followUp 要更新的数据
     * @param request
     * @return Map<String,Object>
     * @since 1.8
     *
     */
    @RequiresPermissions("6004")
    @Operation(name="更新跟踪记录")
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> updateLinkman(FollowUp followUp){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        if(followUp == null) {
            map.put("code", -1);
            map.put("msg", "非法查询");
            return map;
        }

        //检测客户id
        if(followUp.getCustomerId() == null) {
            map.put("code", -2);
            map.put("msg", "客户ID不存在");
            return map;
        }

        if(followupService.updateFollowUpByPrimaryKey(followUp)) {
            map.put("status", true);
            map.put("msg", "操作成功");
        }else {
            map.put("code", -1000);
            map.put("status", false);
            map.put("msg", "操作失败");
        }

        return map;
    }    
    
    /**
     * 描述：删除跟踪记录
     * @author HuangWanzong
     * @date 2018/7/12
     * @version 1.0
     * @param ids 要删除的id数组
     * @return Map<String,Object>
     * @since 1.8
     *
     */
    @RequiresPermissions("6005")
    @Operation(name="删除跟踪记录")
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> udeleteLinkman(int[] ids){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        List<Integer> success = new ArrayList<Integer>();
        List<Integer> fail = new ArrayList<Integer>();
        
        FollowUp followUp = new FollowUp();
        for(int id : ids) {
            followUp.setId(id);
            followUp.setDeleteStatus(1);
            if(followupService.updateFollowUpByPrimaryKeySelective(followUp)) {
                success.add(id);
            }else {
                fail.add(id);
            }
        }
        
        map.put("msg", "操作完成");
        map.put("status", true);
        map.put("success", success);
        map.put("fail", fail);

        return map;
    }     
    
    @RequiresPermissions("6003")
    @Operation(name="id查找跟踪记录")
    @RequestMapping("find")
    @ResponseBody
    public Map<String, Object> findCustomer(int id){
        Map<String, Object> map = new HashMap<String,Object>(16);
        FollowUp followUp = followupService.selectFollowUpByPrimaryKey(id);
        
        if(followUp != null) {
            map.put("msg", "查找成功");
            map.put("success", true);
            map.put("data", followUp);
        }else {
            map.put("msg", "查找失败");
            map.put("success", false); 
        }
        return map;
    }
}
