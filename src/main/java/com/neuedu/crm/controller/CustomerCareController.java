/**
 * 
 */
package com.neuedu.crm.controller;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.CustomerCare;
import com.neuedu.crm.pojo.CustomerCareExample;

import com.neuedu.crm.pojo.Linkman;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.ICustomerCareService;
import com.neuedu.crm.service.ICustomerService;
import com.neuedu.crm.service.ILinkmanService;
import com.neuedu.crm.utils.Operation;

/**
 * @author wanghaoyu
 *
 */
@Controller
@RequestMapping("/customerCare")
public class CustomerCareController {
    
    Logger logger = LoggerFactory.getLogger(CustomerCareController.class);
    
    @Autowired
    ICustomerService customerService;
    
    @Autowired
    ILinkmanService linkmanService;
    
    @Autowired
    ICustomerCareService customerCareService;

    @Operation(name="查找客户关怀")
    @RequiresPermissions("19001")
    @RequestMapping("/findCustomerCare")
    @ResponseBody
    public Map<String, Object> findCustomerCare(HttpServletRequest request, Integer day){
        Map<String, Object> map = new HashMap<String, Object>(26);
        //从session域中获取当前用户
        User user = (User) request.getSession().getAttribute("user");
        List<CustomerCare> customerCares = null;
        //根据当前客户经理的编号查找联系人
        if(user.getRoleId() == 1) {
            customerCares =  customerCareService.selectCustomerCareByManagerId(user.getId());
        }else {
            customerCares =  customerCareService.selectCustomerCareByManagerId(null);
        }
        
        
        LocalDate now = LocalDate.now();
        Iterator<CustomerCare> it =  customerCares.iterator();
        //再根据天数内来筛选要显示的数据
        while(it.hasNext()){
            LocalDate dateOfBirthday = it.next().getBirthday();
            //把生日的年份换成今年，以方便计算天数
            LocalDate  birthday = LocalDate.of(now.getYear(), dateOfBirthday.getMonth(), dateOfBirthday.getDayOfMonth());
            //计算生日离今天还有多少天
            long betweenDays = now.until(birthday, ChronoUnit.DAYS);
            //判断是不是在天数以内
            if(  !(betweenDays >= 0 && betweenDays <= day)){
                //移除掉不符合的联系人
                it.remove();
            }
        }
        map.put("data", customerCares);
        map.put("msg", "success");
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：根据联系人id查找联系生日等信息
     * @author wanghaoyu
     * @version 1.0
     * @param id
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="根据联系人id查找联系生日等信息")
    @RequestMapping("/findLinkmanBirthdayInfoById")
    @ResponseBody
    public Map<String, Object> findLinkmanBirthdayInfoById(Integer id){
        
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        Linkman linkman = null;
        
        try {
            linkman = linkmanService.selectLinkmanByPrimaryKey(id);
            //计算年龄差
            long age = linkman.getBirthday().until(LocalDate.now()).getYears();
            linkman.setAge((int)age);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        map.put("linkman", linkman);
        map.put("success", success);
        map.put("code", 0);
        
        return map;
    }
    
    /**
     * 
     * 描述：添加客户关怀信息
     * @author wanghaoyu
     * @version 1.0
     * @param request
     * @param customerCare
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="添加客户关怀")
    @RequestMapping("/addCustomerCare")
    @ResponseBody
    public Map<String, Object> addCustomerCare(HttpServletRequest request , CustomerCare customerCare){
        
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        
        try {
            User user = (User) request.getSession().getAttribute("user");
            //对数据进行二次封装
            customerCare.setManagerId(user.getId());
            if(customerCareService.insertCustomerCare(customerCare)) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        map.put("success", success);
        map.put("code", 0);
        return map;
    }
    
    
    /**
     * 
     * 描述：根据编号查找客户关怀记录
     * @author wanghaoyu
     * @version 1.0
     * @param id
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="根据编号查找客户关怀记录")
    @RequestMapping("/findCustomerCareById")
    @ResponseBody
    public Map<String, Object> findCustomerCareById(Integer id){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        CustomerCare customerCare = null;
        try {
            customerCare = customerCareService.selectCustomerCareByPrimaryKey(id);
            //封装联系人对象
            Linkman linkman = linkmanService.selectLinkmanByPrimaryKey(customerCare.getLinkmanId());
            customerCare.setLinkman(linkman);
            success = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("customerCare", customerCare);
        map.put("code", 0);
        map.put("success", success);
        return map;
    }
    
    /**
     * 
     * 描述：处理客户关怀
     * @author wanghaoyu
     * @version 1.0
     * @param customerCare
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="处理客户关怀")
    @RequestMapping("/handleCustomerCare")
    @RequiresPermissions("19002")
    @ResponseBody
    public Map<String, Object> handleCustomerCare(CustomerCare customerCare){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        logger.info("客户关怀" + customerCare.toString());
        try {
            customerCare.setStatus("已处理");
            //更新客户关怀记录
            if(customerCareService.updateCustomerCareByPrimaryKey(customerCare)) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("success", success);
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：忽略客户的生日处理
     * @author wanghaoyu
     * @version 1.0
     * @param id
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="忽略客户关怀")
    @RequiresPermissions("19006")
    @RequestMapping("/skipCustomerCare")
    @ResponseBody
    public Map<String, Object> skipCustomerCare(Integer id){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        try {
            CustomerCare customerCare = new CustomerCare();
            customerCare.setId(id);
            customerCare.setStatus("已忽略");
            if(customerCareService.updateCustomerCareByPrimaryKeySelective(customerCare)) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        map.put("success", success);
        map.put("code", 0);
        return map;
    }
    
}
