package com.neuedu.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerExample;
import com.neuedu.crm.pojo.Service;
import com.neuedu.crm.pojo.ServiceExample;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.pojo.ServiceExample.Criteria;
import com.neuedu.crm.pojo.ServiceTransfer;
import com.neuedu.crm.pojo.ServiceTransferExample;
import com.neuedu.crm.service.ICustomerService;
import com.neuedu.crm.service.IServiceService;
import com.neuedu.crm.service.IServiceTransferService;
import com.neuedu.crm.utils.Operation;

/**
 * 
 * @author wanghaoyu
 *
 */
@Controller
@RequestMapping("/service")
public class ServiceController {
    
    Logger logger = LoggerFactory.getLogger(ServiceController.class);
    
    @Autowired
    IServiceService serviceService;
    
    @Autowired
    ICustomerService customerService;
    
    @Autowired
    IServiceTransferService serviceTransferService;
    
    /**
     * 
     * 描述：查找服务
     * @author wanghaoyu
     * @version 1.0
     * @param page
     * @param limit
     * @param service
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="查找服务")
    @RequiresPermissions("10001")
    @RequestMapping("/findServices")
    @ResponseBody
    public Map<String, Object> findServices(HttpServletRequest request, Integer page, Integer limit, Service service){
        Map<String, Object> map = new HashMap<String, Object>(16);
        //从session域中取出当前用户
        User user = (User) request.getSession().getAttribute("user");
        //创建服务模板类
        ServiceExample serviceExample = new ServiceExample();
        //创建服务查询准则
        Criteria criteria = serviceExample.createCriteria();
        //客户经理只能看到处理人为自己的服务
        if (user.getRoleId() == 1) {
            criteria.andHandlerEqualTo(user.getId());
        }
        //添加查询条件
        if(service.getCustomerId() != null) {
            logger.info("添加了客户条件");
            criteria.andCustomerIdEqualTo(service.getCustomerId());
        }
        if(service.getStatus() != null && !"".equals(service.getStatus().trim())) {
            logger.info("添加了状态条件");
            criteria.andStatusEqualTo(service.getStatus());
        }
        if(service.getEmergency() != null && !"".equals(service.getEmergency().trim())) {
            logger.info("添加了紧急程度条件");
            criteria.andEmergencyEqualTo(service.getEmergency());
        }
        if(service.getType() != null && !"".equals(service.getType().trim())) {
            logger.info("添加了类型条件");
            criteria.andTypeEqualTo(service.getType());
        }
        if(service.getDegree() != null && !"".equals(service.getDegree().trim())){
            logger.info("添加了满意度条件");
            criteria.andDegreeEqualTo(service.getDegree());
        }
        //删除标志默认为0,即没有被软删除的
        criteria.andDeleteStatusEqualTo(0);
        //分页数据合法性判断
        if(page == null || page <= 0) {
            page = 1;
        }
        if(limit == null || limit <= 0) {
            limit = 10;
        }
        //偏移值，即从第几条数据开始查
        Long offset = new Long((page - 1) * limit);
        //数据总条数
        Long count = serviceService.countByServiceExample(serviceExample);
        
        serviceExample.setOffset(offset);
        serviceExample.setLimit(limit);
        
        List<Service> services = serviceService.selectByServiceExample(serviceExample);
        
        logger.info(services.toString());
        
        map.put("data", services);
        map.put("count", count);
        map.put("msg", "success");   
        map.put("code",0);
        return map; 
    }
    
    /**
     * 
     * 描述：查找属于当前客户经理的客户
     * @author wanghaoyu
     * @version 1.0
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="查找属于当前客户经理的客户")
    @RequiresAuthentication
    @RequestMapping("/findAllCustomerByRole")
    @ResponseBody
    public Map<String, Object> findAllCustomerByRole(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<Customer> customers = null;
        //从session域中取出当前用户
        User user = (User) request.getSession().getAttribute("user");
        //客户经理的ID为1,客户经理只能查询到属于自己的客户
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
        map.put("code",0);
        return map;
    }
    

    /**
     * 
     * 描述：批量删除服务
     * @author wanghaoyu
     * @version 1.0
     * @param ids
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="批量删除服务")
    @RequiresPermissions("10006") 
    @RequestMapping("/deleteServices")
    @ResponseBody
    public Map<String, Object> deleteServices(String ids){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        String msg = "";
        //软删除
        if(serviceService.deleteServicesByPrimaryKey(ids)) {
            success = true;
            msg = "删除成功！";
        }else {
            msg = "删除失败！";
        }
        map.put("success", success);
        map.put("msg", msg); 
        map.put("code",0);
        return map;
    }
    
    /**
     * 
     * 描述：添加服务
     * @author wanghaoyu
     * @version 1.0
     * @param request
     * @param service
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="添加服务")
    @RequiresPermissions("10002")
    @RequestMapping("/addService")
    @ResponseBody
    public Map<String, Object> addService(HttpServletRequest request, Service service){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        String msg = "";
        logger.info(service.getCreateDate());
        //从session域中获取当前登录user
        User user = (User) request.getSession().getAttribute("user");
        //设置服务的创建人
        service.setCreater(user.getId());
        //设置服务默认的处理人(处理人默认为创建人，除非创建人把服务转移出去，同时改变处理人)
        service.setHandler(user.getId());
        //服务状态默认为未处理
        service.setStatus("未处理");
        if(serviceService.insertService(service)){
            success = true;
            msg = "添加成功！";
        }else {
            msg = "添加失败！";
        }
        map.put("success",success);
        map.put("msg", msg);
        map.put("code",0);
        return map;
    }
    
    /**
     * 
     * 描述：编辑服务
     * @author wanghaoyu
     * @version 1.0
     * @param request
     * @param service
     * @param createTime
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="编辑服务")
    @RequiresPermissions(value={"10003","10005","10007"}, logical=Logical.OR)
    @RequestMapping("/editService")
    @ResponseBody
    public Map<String, Object> editService(Service service){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;     
        String msg = "";
        
        service.setDeleteStatus(0);
        //有选择性地更新数据，防止处理服务被置空
        if(serviceService.updateServiceByPrimaryKeySelective(service)){
            success = true;
            msg = "编辑成功！";
        }else {
            msg = "编辑失败！";
        }
        map.put("success",success);
        map.put("msg", msg);
        map.put("code",0);
        return map;
    }
    
    
    
    /**
     * 
     * 描述：根据服务ID查找服务
     * @author wanghaoyu
     * @version 1.0
     * @param request
     * @param id
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="通过编号查询服务")
    @RequiresAuthentication
    @RequestMapping("/findServiceById")
    @ResponseBody
    public Map<String, Object> findServiceById(Integer id){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        Service service = serviceService.selectServiceByPrimaryKey(id);
        if(service != null) {
            logger.info(service.toString());
        }else {
            logger.info("service is null.");
        }
        
        if(service != null) {
            success = true;
        }
        map.put("success", success);
        map.put("data", service);
        map.put("code",0);
        return map;
    }
    
    @Operation(name="转交服务")
    @RequiresPermissions("10004")
    @RequestMapping("/transferSerive")
    @ResponseBody
    public Map<String, Object> transferSerive(HttpServletRequest request, ServiceTransfer serviceTransfer){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        String msg = "";
        //从session域中获取当前登录user
        User user = (User) request.getSession().getAttribute("user");
        //只有客户经理才能转交服务
        if(user.getRoleId() == 1){
            //二次封装数据
            logger.info("============"+user.getId());
            serviceTransfer.setOldManagerId(user.getId());
            //判断插入是否成功
            if(serviceTransferService.insertServiceTransfer(serviceTransfer)){
                success = true;
                msg = "转交成功！";
            }else {
                msg = "转交失败！";
            }
        }else {
            msg = "只有所属的客户经理能转交服务！";
        }
        map.put("success", success);
        map.put("msg", msg);
        map.put("code",0);
        return map;
    }
    
    @Operation(name="查找服务转交记录")
    @RequiresAuthentication
    @RequestMapping("/findServiceTransfersByServiceId")
    @ResponseBody
    public Map<String, Object> findServiceTransfersByServiceId(Integer serviceId){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        //创建服务转交模板类
        ServiceTransferExample serviceTransferExample = new ServiceTransferExample();
        //创建查询准则
        com.neuedu.crm.pojo.ServiceTransferExample.Criteria criteria = serviceTransferExample.createCriteria();
        //按照时间倒序查询（PS：因为要按时间线来展示记录）
        serviceTransferExample.setOrderByClause(" time desc ");
        //添加查询条件
        criteria.andServiceIdEqualTo(serviceId);
        List<ServiceTransfer> serviceTransfers = serviceTransferService.selectByServiceTransferExample(serviceTransferExample);
        success = true;
        logger.info(serviceTransfers.toString());
        map.put("success", success);
        map.put("code", 0);
        map.put("list", serviceTransfers);
        return map;
    }
}
