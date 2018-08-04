package com.neuedu.crm.controller;

import java.time.LocalDateTime;
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
import com.neuedu.crm.pojo.CustomerExample.Criteria;
import com.neuedu.crm.pojo.Linkman;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.ICustomerService;
import com.neuedu.crm.utils.Operation;

/**
 * 
 * @author huangwanzong
 * @date 2018年7月6日
 */
@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;
    
    private User user = null;

    /**
     * @author huangwanzong
     * @date 2018年7月6日
     */
    private User getUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        user = (User)session.getAttribute("user");
        return user;
    }
    

    /**
     * 
     * 描述：分页查询客户
     * @author huangwanzong
     * @date 2018/07/06
     * @version 1.0
     * @param page 可选参数，查询的页数，默认值 1
     * @param limit 可选参数，分页的大小，默认值 10
     * @param customer 可选参数，查询的条件
     * @param findtype 可选参数，该参数值为 all 时不执行分页查询，返回全部符合条件的客户
     * @param request
     * @return Map<String,Object>
     * @since 1.8
     *
     */
    
    @RequiresPermissions("5001")
    @Operation(name="分页查询客户")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> listCustomer(Integer page,Integer limit,Customer customer,String findtype,HttpServletRequest request){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        //获取用户
        user = this.getUser(request);
        //检验用户正确性
        if(user == null || user.getId() == null) {
            map.put("code", -1);
            map.put("msg", "用户不存在，无法执行操作.");
            return map;
        }
        
        CustomerExample example = new CustomerExample();
        Criteria criteria = example.createCriteria();
        
        //设置分页参数
        if(page == null || page <= 0) {
            page = 1;
        }
        if(limit == null || limit <= 0) {
            limit = 10;
        }
        String all = "all";
        if(!all.equals(findtype)) {
            example.setLimit(limit);
            Long offset = new Long((page-1)*limit);
            example.setOffset(offset);
        }

        //设置管理者ID
        criteria.andManagerIdEqualTo(user.getId());
        //只查询未删除的客户
        criteria.andDeleteStatusEqualTo(0);
        System.out.println(customer);
        //检测属性是否存在，存在则进行条件查询
        if(customer != null) {
            if(customer.getName() != null) {
                criteria.andNameLike("%" + customer.getName() + "%");
            }
            
            if(customer.getType() != null && !"".equals(customer.getType())) {
                criteria.andTypeEqualTo(customer.getType()); 
            }
            if(customer.getStatus() != null && !"".equals(customer.getStatus())) {
                criteria.andStatusEqualTo(customer.getStatus());
            }
            if(customer.getSource() != null && !"".equals(customer.getSource())) {
                criteria.andSourceEqualTo(customer.getSource());
            }
            if(customer.getLevel() != null && !"".equals(customer.getLevel())) {
                criteria.andLevelEqualTo(customer.getLevel());
            }
            if(customer.getCredit() != null && !"".equals(customer.getCredit())) {
                criteria.andCreditEqualTo(customer.getCredit());
            }
            if(customer.getMaturity() != null && !"".equals(customer.getMaturity())) {
                criteria.andMaturityEqualTo(customer.getMaturity());
            }
        }
        
        Long count = customerService.countByCustomerExample(example);
        List<Customer> customers = customerService.selectByCustomerExample(example);
        
        map.put("data", customers);
        map.put("count", count);
        map.put("code", 0);
        
        return map;
    }
    
    
    /**
     * 
     * 描述：添加一个客户
     * @author huangwanzong
     * @date 2018/07/17
     * @version 1.0
     * @param customer 客户信息
     * @param linkman 联系人信息
     * @param customerName 客户名称
     * @param linkmanName 联系人名称
     * @param customerLevel 客户等级
     * @param request
     * @return Map<String,Object>
     * @since 1.8
     *
     */
    @RequiresPermissions("5002")
    @Operation(name="添加客户")
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> addCustomer(Customer customer,Linkman linkman,String customerName,String linkmanName,String customerLevel,HttpServletRequest request){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        this.getUser(request);
        
        //检测是否存在customer对象
        if(customer == null || linkman == null) {
            map.put("msg", "参数为空");
            map.put("success", false);
            return map;
        }
        
        //检测客户名是否存在
        if(customerName == null || "".equals(customerName)) {
            map.put("msg", "用户名称参数不能为空");
            map.put("success", false);
            return map;
        }
        
        //检测联系人姓名是否存在
        if(linkmanName == null || "".equals(linkmanName)) {
            map.put("msg", "联系人名称参数不能为空");
            map.put("success", false);
            return map;
        }
        
        //客户所属者默认为创建者
        customer.setManagerId(user.getId());
        //设置创建者id
        customer.setCreater(user.getId());
        //设置创建时间
        customer.setCreateTime(LocalDateTime.now());
        //设置客户名称
        customer.setName(customerName);
        //设置客户等级
        customer.setLevel(customerLevel);
        //设置未删除
        customer.setDeleteStatus(0);
        
        //设置联系人名称
        linkman.setName(linkmanName);
     
        
        //进行数据插入
        if(customerService.insertSelective(customer,linkman)) {
            map.put("msg", "添加成功");
            map.put("success", true);
        }else {
            map.put("msg", "添加失败");
            map.put("success", false);
        }
        
        return map;
    }
    
    @RequiresPermissions("7009")
    @Operation(name="检测客户名称是否存在")
    @RequestMapping("checkname")
    @ResponseBody
    public boolean checkCustomerName(String name){
        
        CustomerExample example = new CustomerExample();
        example.createCriteria().andNameEqualTo(name);
        
        List<Customer> list = customerService.selectByCustomerExample(example);
        if(list.size()>0) {
            return true;
        }
        return false;
    }
    
    @RequiresPermissions("5003")
    @Operation(name="更新客户信息")
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> updateCustomer(Customer customer){
        Map<String, Object> map = new HashMap<String,Object>(16);
 
        if(customerService.updateCustomerByPrimaryKeySelective(customer)) {
            map.put("msg", "更新成功");
            map.put("success", true);
        }else {
            map.put("msg", "更新失败");
            map.put("success", false); 
        }
        return map;
    }
    
    @RequiresPermissions("5004")
    @Operation(name="删除客户")
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> deleteCustomer(int[] ids){
        Map<String, Object> map = new HashMap<String,Object>(16);
        List<Integer> success = new ArrayList<Integer>();
        List<Integer> fail = new ArrayList<Integer>();
        for(int id : ids) {
            if(customerService.deleteByPrimaryKey(id)) {
                success.add(id);
            }else {
                fail.add(id);
            }
        }
        
        map.put("msg", "删除完成");
        map.put("status", true);
        map.put("success", success);
        map.put("fail", fail);
        
        return map;
    }
    
    @RequiresPermissions("7010")
    @Operation(name="id查找客户")
    @RequestMapping("find")
    @ResponseBody
    public Map<String, Object> findCustomer(Integer id){
        Map<String, Object> map = new HashMap<String,Object>(16);
        Customer customer = null;
        
        if(id == null) {
            map.put("msg", "非法操作");
            map.put("success", false); 
            return map;
        }
        
        customer = customerService.selectCustomerByPrimaryKey(id);
        
        if(customer != null) {
            map.put("msg", "查找成功");
            map.put("success", true);
            map.put("data", customer);
        }else {
            map.put("msg", "查找失败");
            map.put("success", false); 
        }
        return map;
    }
    
    
}
