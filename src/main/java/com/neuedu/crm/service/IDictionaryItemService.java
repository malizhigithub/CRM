package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.DictionaryItem;
import com.neuedu.crm.pojo.DictionaryItemExample;
/**
 * 
 * @author huangwanzong
 *
 */
public interface IDictionaryItemService {
    /**
     * 
     * 描述： 按照Example 统计记录总数
     * @author huangwanzong
     * @version 1.1
     * @param dictionaryItemExample 查询条件
     * @return long 数据的数量
     * @exception Nothing
     * @since 1.8
     *
     */     
    public long countByDictionaryItemExample(DictionaryItemExample dictionaryItemExample);
    
    /**
     * 
     * 描述：按照Example 删除 DictionaryItemExample
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryItemExample 
     * @return boolean 删除的结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean deleteByDictionaryItemExample(DictionaryItemExample dictionaryItemExample);

    /**
     * 
     * 描述：按照主键id删除DictionaryItem
     * @author huangwanzong
     * @version 1.0
     * @param id 数据字典 id
     * @return boolean 删除结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean deleteByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：插入一条DictionaryItem数据 如字段为空，则插入null
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryItem
     * @return boolean 插入结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean insertDictionaryItem(DictionaryItem dictionaryItem);
    
    /**
     * 
     * 描述：插入一条DictionaryItem数据，如字段为空，则插入数据库表字段的默认值
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryItem 客户数据
     * @return boolean 插入结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean insertDictionaryItemSelective(DictionaryItem dictionaryItem);
    
    /**
     * 
     * 描述：按照Example条件 模糊查询
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryItemExample 查询条件
     * @return List<DictionaryItem> 含DictionaryItem的list
     * @exception Nothing
     * @since 1.8
     *
     */
    public List<DictionaryItem> selectByDictionaryItemExample(DictionaryItemExample dictionaryItemExample);
    
    /**
     * 
     * 描述：按照DictionaryItem 的id 查找 DictionaryItem
     * @author huangwanzong
     * @version 1.0
     * @param id 要查询的id
     * @return DictionaryItem 查到的数据或空值
     * @exception Nothing
     * @since 1.8
     *
     */
    public DictionaryItem selectDictionaryItemByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：更新DictionaryItem
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryItem 对象中若有空则更新字段为null
     * @param dictionaryItemExample 为where条件
     * @return boolean 更新结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean updateByDictionaryItemExample(DictionaryItem dictionaryItem, DictionaryItemExample dictionaryItemExample);
    
    /**
     * 
     * 描述：更新DictionaryItem 
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryItem 对象中若有空则不会更新此字段
     * @param dictionaryItemExample 为where条件
     * @return boolean 更新结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean updateByDictionaryItemExampleSelective(DictionaryItem dictionaryItem, DictionaryItemExample dictionaryItemExample);
        
    /**
     * 
     * 描述：按照DictionaryItem id 更新DictionaryItem
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryItem 对象中如有空则不会更新此字段
     * @return boolean
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean updateDictionaryItemByPrimaryKeySelective(DictionaryItem dictionaryItem);
    
    /**
     * 
     * 描述：按照DictionaryItem id 更新DictionaryItem
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryItem 对象中如有空则更新此字段为null
     * @return boolean
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean updateDictionaryItemByPrimaryKey(DictionaryItem dictionaryItem);   
}
