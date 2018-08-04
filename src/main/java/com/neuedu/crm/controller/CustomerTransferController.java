package com.neuedu.crm.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerCare;
import com.neuedu.crm.pojo.CustomerCareExample;
import com.neuedu.crm.pojo.CustomerTransfer;
import com.neuedu.crm.pojo.CustomerTransferExample;
import com.neuedu.crm.pojo.CustomerTransferExample.Criteria;
import com.neuedu.crm.pojo.SaleOpportunity;
import com.neuedu.crm.pojo.SaleOpportunityExample;
import com.neuedu.crm.service.ICustomerCareService;
import com.neuedu.crm.service.ICustomerService;
import com.neuedu.crm.service.ICustomerTransferService;
import com.neuedu.crm.service.ISaleOpportunityService;
import com.neuedu.crm.service.IUserService;
import com.neuedu.crm.utils.Operation;


/**
 * 
 * @author huangwanzong
 * @date 2018/7/14
 *
 */
@Operation(name="客户转移控制器")
@Controller
@RequestMapping("customer/transfer")
public class CustomerTransferController {
    
    @Autowired
    private ICustomerTransferService customerTransferService;
    
    @Autowired
    private ICustomerService customerService;
    
    @Autowired
    private IUserService userService;
    
    @Autowired
    private ISaleOpportunityService saleOpportunityService;
    
    @Autowired
    private ICustomerCareService customerCareService;
    
    /**
     * 描述：查询客户的转移记录
     * @author huangwanzong
     * @version 1.0
     * @param page 可选参数，type不为all时生效，分页查询的页数
     * @param limit 可选参数，type不为all时生效，分页查询的分页大小
     * @param customerId 必填参数，要查询的客户Id
     * @param type 可选参数，值为all表示查询所有记录
     * @return 
     * @since 1.8
     *
     */
    @RequiresPermissions("7008")
    @Operation(name="客户转移记录查询")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> listTransferByCustomer(Integer page,Integer limit,Integer customerId,String type){
        Map<String,Object> map = new HashMap<String,Object>(16);
        
        CustomerTransferExample example = new CustomerTransferExample();
        Criteria criteria = example.createCriteria();
        
        if(customerId == null) {
            map.put("code", -1);
            map.put("status", false);
            map.put("msg", "客户ID不存在");
            return map;
        }
        
        //如果type的值不是all，则设置分页查询参数
        String all = "all";
        if(!all.equals(type)) {
            if(page == null) {
                page = 1;
            }
            if(limit == null) {
                limit = 10;
            }
            
            example.setLimit(limit);
            Long offset = new Long((page-1)*limit);
            example.setOffset(offset);
        }
        
        criteria.andCustomerIdEqualTo(customerId);
        example.setOrderByClause(" time desc");
        
        
        Long count = customerTransferService.countByCustomerTransferExample(example);
        List<CustomerTransfer> list = customerTransferService.selectByCustomerTransferExample(example);
        
        map.put("data", list);
        map.put("count", count);
        map.put("code", 0);
        int pages = (int)Math.ceil((count*1.0)/limit);
        map.put("pages", pages);
        return map;
    }
    
    
    /**
     * 
     * 描述：进行客户转移
     * @author huangwanzong
     * @version 1.0
     * @param transfer 必填参数:customerId，NewmanagerId,reason
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    @RequiresPermissions("7007")
    @Operation(name="客户转移")
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> addCustomerTranfer(CustomerTransfer transfer){
        Map<String,Object> map = new HashMap<String,Object>(16);
        
        //检测 transfer对象是否存在
        if(transfer == null) {
            map.put("code", -1);
            map.put("status", false);
            map.put("msg", "非法操作");
            return map;
        }
        
        //检测转移终点经理是否存在
        if(transfer.getCustomerId() == null) {
            map.put("code", -2);
            map.put("status", false);
            map.put("msg", "客户ID不存在");
            return map;
        }
        
        Customer customer = customerService.selectCustomerByPrimaryKey(transfer.getCustomerId());
        
        if(customer == null) {
            map.put("code", -3);
            map.put("status", false);
            map.put("msg", "客户不存在");
            return map;
        }
        
        if(transfer.getNewManagerId() == null || userService.findById(transfer.getNewManagerId()) == null) {
            map.put("code", -4);
            map.put("status", false);
            map.put("msg", "转移终点不存在");
            return map;
        }
               
        //设置转移起点
        if(transfer.getOldManagerId() == null) {
            transfer.setOldManagerId(customer.getManagerId());
        }
        
        //判断起点与终点是否相同
        if(transfer.getOldManagerId().equals(transfer.getNewManagerId())) {
            map.put("code", -5);
            map.put("status", false);
            map.put("msg", "不能将客户转移给自己");
            return map;
        }
        
        if(transfer.getReason() == null || "".equals(transfer.getReason())) {
            map.put("code", -6);
            map.put("status", false);
            map.put("msg", "转移原因不能为空");
            return map;
        }
        
        
        //设置转移时间为系统当前时间
        transfer.setTime(LocalDateTime.now());
        
        //进行客户转移
        try {
            //在转移表中插入一条数据
            customerTransferService.insertSelective(transfer);
            //更新客户的管理者ID
            customer.setManagerId(transfer.getNewManagerId());
            customerService.updateCustomerByPrimaryKeySelective(customer);
            
            //转移销售机会
            //根据旧客户经理找到销售机会
            SaleOpportunityExample saleOpportunityExample = new SaleOpportunityExample();
            saleOpportunityExample.createCriteria().andManagerIdEqualTo(transfer.getOldManagerId());
            List<SaleOpportunity> saleOpportunities = saleOpportunityService.selectBySaleOpportunityExample(saleOpportunityExample); 
            for (SaleOpportunity saleOpportunity : saleOpportunities) {
                //更新销售机会的所属客户经理
                saleOpportunity.setManagerId(transfer.getNewManagerId());
                saleOpportunityService.updateSaleOpportunityByPrimaryKeySelective(saleOpportunity);
            }
            
            //转移客户关怀
            //根据旧客户经理找到客户关怀
            CustomerCareExample customerCareExample = new CustomerCareExample();
            customerCareExample.createCriteria().andManagerIdEqualTo(transfer.getOldManagerId());
            List<CustomerCare> customerCares = customerCareService.selectByCustomerCareExample(customerCareExample);
            for (CustomerCare customerCare : customerCares) {
                //更新客户关怀的所属客户经理
                customerCare.setManagerId(transfer.getNewManagerId());
                customerCareService.updateCustomerCareByPrimaryKeySelective(customerCare);
            }

        } catch (Exception e) {
            
            e.printStackTrace();
            map.put("code", -1000);
            map.put("status", false);
            map.put("msg", "操作失败");
            return map;
        }
        
        map.put("code", 0);
        map.put("status", true);
        map.put("msg", "操作成功");
        return map;
    }
    
}
