package com.neuedu.crm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.Linkman;
import com.neuedu.crm.pojo.LinkmanExample;
import com.neuedu.crm.pojo.LinkmanExample.Criteria;
import com.neuedu.crm.service.ILinkmanService;
import com.neuedu.crm.utils.Operation;

/**
 * 
 * @author HuangWanzong
 * @date 2018年7月11日
 */
@Controller
@RequestMapping("linkman")
public class LinkmanController {
    @Autowired
    private ILinkmanService linkmanService;
    
    /**
     * @author HuangWanzong
     * @date 2018年7月11日
     */
    @RequiresPermissions("7003")
    @Operation(name="分页查询联系人")
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> listLinkman(Integer page,Integer limit,Linkman linkman,String type){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        LinkmanExample example = new LinkmanExample();
        Criteria criteria = example.createCriteria();

        if(linkman == null) {
            map.put("code", -1);
            map.put("msg", "非法查询");
            return map;
        }
        
        //设置分页参数
        if(page == null || page <= 0) {
            page = 1;
        }
        if(limit == null || limit <= 0) {
            limit = 10;
        }
        
        //如果不是查询全部数据，就设置分页属性
        String all = "all";
        if(!all.equals(type)) {
            example.setLimit(limit);
            Long offset = new Long((page-1)*limit);
            example.setOffset(offset);
        }
        
        
        //设置客户id
        if(linkman.getCustomerId() != null) {
            criteria.andCustomerIdEqualTo(linkman.getCustomerId());
        }else {
            map.put("code", -2);
            map.put("msg", "客户ID不存在");
            return map;
        }
        
        Long count = linkmanService.countByLinkmanExample(example);
        List<Linkman> list = linkmanService.selectByLinkmanExample(example);
        
        map.put("data", list);
        map.put("count", count);
        map.put("code", 0);
        
        return map;
    }
    
    /**
     * @author HuangWanzong
     * @date 2018年7月11日
     */
    @RequiresPermissions("7001")
    @Operation(name="添加联系人")
    @RequestMapping("add")
    @ResponseBody
    public Map<String, Object> addLinkman(Linkman linkman){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        //检测必要参数是否完整
        if(linkman == null || linkman.getName() == null || 
                linkman.getCustomerId() == null || linkman.getBirthday() == null
                || linkman.getLevel() == null) {
            map.put("status",false);
            map.put("msg", "非法操作");
            return map;
        }

        if(linkman.getLevel() == 0) {
            //当前联系人为重要级别
            //将其他联系全部设置为普通级别
            LinkmanExample example = new LinkmanExample();
            example.createCriteria().andCustomerIdEqualTo(linkman.getCustomerId());
            Linkman linkman2 = new Linkman();
            linkman2.setLevel(1);
            linkmanService.updateByLinkmanExampleSelective(linkman2, example);
        }
        
        //插入数据
        if(linkmanService.insertLinkmanSelective(linkman)) {
            map.put("status",true);
            map.put("msg", "操作成功");
        }else {
            map.put("status",false);
            map.put("msg", "操作失败");
        }
        
        return map;
    }
    
    /**
     * @author HuangWanzong
     * @date 2018年7月11日
     */
    @RequiresPermissions("7002")
    @Operation(name="更新联系人")
    @RequestMapping("update")
    @ResponseBody
    public Map<String, Object> updateLinkman(Linkman linkman){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        if(linkman == null || linkman.getId() == null ) {
            map.put("status",false);
            map.put("msg", "非法操作");
            return map;
        }
        Linkman old = linkmanService.selectLinkmanByPrimaryKey(linkman.getId());
        
        //检测必要参数是否完整
        if(linkman == null || linkman.getName() == null || linkman.getCustomerId() == null) {
            map.put("status",false);
            map.put("msg", "非法操作");
            return map;
        }
        
   
        //如果要将联系人从重要设置为普通则报错
        if(old.getLevel() == 0 && linkman.getLevel() == 1) {
            map.put("status",false);
            map.put("msg", "非法操作");
            return map;
        }
        
        if(linkman.getLevel() == 0) {
            //当前联系人为重要级别
            //将其他联系全部设置为普通级别
            LinkmanExample example = new LinkmanExample();
            example.createCriteria().andCustomerIdEqualTo(linkman.getCustomerId());
            Linkman linkman2 = new Linkman();
            linkman2.setLevel(1);
            linkmanService.updateByLinkmanExampleSelective(linkman2, example);
        }
        
        //更新数据
        if(linkmanService.updateLinkmanByPrimaryKey(linkman)) {
            map.put("status",true);
            map.put("msg", "操作成功");
        }else {
            map.put("status",false);
            map.put("msg", "操作失败");
        }
        
        return map;
    }
    
    /**
     * @author HuangWanzong
     * @date 2018年7月11日
     */
    @RequiresPermissions("7004")
    @Operation(name="删除联系人")
    @RequestMapping("delete")
    @ResponseBody
    public Map<String, Object> deleteLinkman(int[] ids){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        List<Integer> success = new ArrayList<Integer>();
        List<Integer> fail = new ArrayList<Integer>();
        
        for(int id:ids) {
            if(linkmanService.deleteLinkmanByPrimaryKey(id)) {
                success.add(id);
            }else {
                fail.add(id);
            }
        }
        map.put("status",true);
        map.put("msg", "操作完成");
        map.put("success", success);
        map.put("fail", fail);
        
        return map;
    }
    
    /**
     * @author HuangWanzong
     * @date 2018年7月11日
     */
    @RequiresPermissions("7005")
    @Operation(name="ID查找联系人")
    @RequestMapping("find")
    @ResponseBody
    public Map<String, Object> findLinkman(int id){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        Linkman linkman = linkmanService.selectLinkmanByPrimaryKey(id);
        
        if(linkman != null) {
            map.put("status", true);
            map.put("msg", "查找成功");
            map.put("data", linkman);
        }else {
            map.put("status", false);
            map.put("msg", "查找失败");
        }
        
        return map;
    }
    
    
    /**
     * @author HuangWanzong
     * @date 2018年7月11日
     */
    @RequiresPermissions("7006")
    @Operation(name="设置重要联系人")
    @RequestMapping("mainlinkman")
    @ResponseBody
    public Map<String, Object> updateMainLinkman(int id){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        Linkman linkman = linkmanService.selectLinkmanByPrimaryKey(id);
        
        if(linkman == null) {
            map.put("code", -1);
            map.put("msg", "找不到对应联系人");
            return map;
        }
        
        //设置更新条件为相同的客户
        LinkmanExample example = new LinkmanExample();
        example.createCriteria().andCustomerIdEqualTo(linkman.getCustomerId());
        
        //将所有的联系人设置为普通级别
        Linkman linkmanexample = new Linkman();
        linkmanexample.setLevel(1);
        
        if(!linkmanService.updateByLinkmanExampleSelective(linkmanexample, example)){
            map.put("code", -2);
            map.put("msg", "操作失败");
            return map;
        }
        
        //将传进来的联系人设置为主要级别
        linkman.setLevel(0);
        if(linkmanService.updateLinkmanByPrimaryKey(linkman)) {
            map.put("code", 0);
            map.put("msg", "操作成功");
        }else {
            map.put("code", -3);
            map.put("msg", "操作失败");
        }

        return map;
    }
    
    
}
