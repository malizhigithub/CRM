package com.neuedu.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.DictionaryItem;
import com.neuedu.crm.pojo.DictionaryItemExample;
import com.neuedu.crm.pojo.DictionaryType;
import com.neuedu.crm.pojo.DictionaryTypeExample;
import com.neuedu.crm.service.IDictionaryItemService;
import com.neuedu.crm.service.IDictionaryTypeService;
import com.neuedu.crm.utils.Operation;
/**
 * 
 * @author HuangWanzong
 *
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryTypeController {
    
    @Autowired
    private IDictionaryTypeService dictionaryTypeService;
    
    @Autowired
    private IDictionaryItemService dictionaryItemService;
   
    /**
     * 
     * 描述：
     * @author HuangWanzong
     * @date 2018/07/04
     * @version 1.0
     * @param page 必填参数，查询第几页参数，默认值 1
     * @param limit 可选参数，分页大小，默认值10
     * @param name 可选参数，名称模糊查询条件
     * @return Map<String,Object>
     * @since 1.8
     *
     */
    @RequiresPermissions("20001")
    @Operation(name="数据字典分页查询")
    @RequestMapping("admin/list")
    @ResponseBody
    public Map<String, Object> listDictionaryType(Integer page,Integer limit,String name){
        
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        if(page == null || page <= 0) {
            page = 1;
        }
        if(limit == null || limit <= 0) {
            limit = 10;
        }
        Long offset = new Long((page - 1) * limit);
        
        //设置名称模糊查询
        DictionaryTypeExample example = new DictionaryTypeExample();
        if(name != null) {
            example.createCriteria().andNameLike("%" + name + "%");
        }
        Long count = dictionaryTypeService.countByDictionaryTypeExample(example);
        
        example.setLimit(limit);
        example.setOffset(offset);
        
        List<DictionaryType> list = dictionaryTypeService.selectByDictionaryTypeExample(example);
        
        map.put("data", list);
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", count);
        return map;
    }
    
    /**
     * @author HuangWanzong
     * @date 2018年7月4日
     */
    @RequiresPermissions("20002")
    @Operation(name="添加数据字典")
    @RequestMapping("admin/add")
    @ResponseBody
    public Map<String, Object> addDictionaryType(DictionaryType dictionaryType){
        
        Map<String, Object> map = new HashMap<String,Object>(16);
        if(dictionaryTypeService.insertDictionaryTypeSelective(dictionaryType)) {
            map.put("success", true);
            map.put("msg", "添加成功");
        }else {
            map.put("success", false);
            map.put("msg", "添加失败");
        }
        
        return map;
    }
    
    /**
     * @author HuangWanzong
     * @date 2018年7月4日
     */
    @RequiresPermissions("20003")
    @Operation(name="更新数据字典")
    @RequestMapping("admin/update")
    @ResponseBody
    public Map<String, Object> updateDictionaryType(DictionaryType dictionaryType){

        
        Map<String, Object> map = new HashMap<String,Object>(16);
        if(dictionaryTypeService.updateDictionaryTypeByPrimaryKeySelective(dictionaryType)) {
            map.put("success", true);
            map.put("msg", "更新成功");
        }else {
            map.put("success", false);
            map.put("msg", "更新失败");
        }
        
        return map;
    }
    
    /**
     * @author huangwanzong
     * @date 2018年7月4日
     */
    @RequiresPermissions("20004")
    @Operation(name="删除数据字典")
    @RequestMapping("admin/delete")
    @ResponseBody
    public Map<String, Object> deleteDictionaryType(int id){
        
        Map<String, Object> map = new HashMap<String,Object>(16);
        if(dictionaryTypeService.deleteByPrimaryKey(id)) {
            map.put("success", true);
            map.put("msg", "删除成功");
        }else {
            map.put("success", false);
            map.put("msg", "删除失败");
        }
        
        return map;
    }

    
    /**
     * 
     * 描述：用户通过名称或ID查找字典（ID优先级比名称高）
     * @author HuangWanzong
     * @date 2018/07/10
     * @version 1.0
     * @param id 可选参数，若存在则根据id查询字典
     * @param name 可选参数，当名称存在且id不存在时按照名称查找字典
     * @return Map<String,Object>
     * @since 1.8
     *
     */
    @RequiresPermissions("20005")
    @Operation(name="用户通过名称或ID查找字典")
    @RequestMapping("find")
    @ResponseBody
    public Map<String, Object> findDictionaryType(Integer id,String name){
        
        Map<String, Object> map = new HashMap<String,Object>(16);
        DictionaryType type = null;
        //查找字典
        if(id != null) {
            type = dictionaryTypeService.selectDictionaryTypeByPrimaryKey(id);
        }else if(name != null) {
            DictionaryTypeExample example = new DictionaryTypeExample();
            example.createCriteria().andNameEqualTo(name);
            
            List<DictionaryType> list = dictionaryTypeService.selectByDictionaryTypeExample(example);
            if(list.size() > 0) {
                type = list.get(0);
            }
        }else {
            map.put("success", false);
            map.put("msg", "参数错误");
            return map;
        }
        
        //设置字典子项
        if(type != null) {
            DictionaryItemExample example = new DictionaryItemExample();
            example.createCriteria().andTypeIdEqualTo(type.getId());
            List<DictionaryItem> items = dictionaryItemService.selectByDictionaryItemExample(example);
            type.setDictionaryItems(items);
        }
        
        if(type != null) {
            map.put("success", true);
            map.put("msg", "查找成功");
            map.put("data", type);
        }else {
            map.put("success", false);
            map.put("msg", "查找失败，字典不存在");
        }
        return map;
    }
    
}
