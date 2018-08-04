package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.DictionaryType;
import com.neuedu.crm.pojo.DictionaryTypeExample;
/**
 * 
 * @author huangwanzong
 * 
 */

public interface IDictionaryTypeService {
    /**
     * 
     * 描述： 按照Example 统计记录总数
     * @author huangwanzong
     * @version 1.1
     * @param dictionaryTypeExample 查询条件
     * @return long 数据的数量
     * @exception Nothing
     * @since 1.8
     *
     */     
    public long countByDictionaryTypeExample(DictionaryTypeExample dictionaryTypeExample);
    
    /**
     * 
     * 描述：按照Example 删除 DictionaryType
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryTypeExample 
     * @return boolean 删除的结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean deleteByDictionaryTypeExample(DictionaryTypeExample dictionaryTypeExample);

    /**
     * 
     * 描述：按照主键id删除DictionaryTypeExample
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
     * 描述：插入一条DictionaryType数据 如字段为空，则插入null
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryType
     * @return boolean 插入结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean insertDictionaryType(DictionaryType dictionaryType);
    
    /**
     * 
     * 描述：插入一条DictionaryType数据，如字段为空，则插入数据库表字段的默认值
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryType
     * @return boolean 插入结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean insertDictionaryTypeSelective(DictionaryType dictionaryType);
    
    /**
     * 
     * 描述：按照Example条件 模糊查询
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryTypeExample 查询条件
     * @return List<DictionaryType> 含DictionaryType的list
     * @exception Nothing
     * @since 1.8
     *
     */
    public List<DictionaryType> selectByDictionaryTypeExample(DictionaryTypeExample dictionaryTypeExample);
    
    /**
     * 
     * 描述：按照DictionaryType 的id 查找 DictionaryType
     * @author huangwanzong
     * @version 1.0
     * @param id 要查询的id
     * @return DictionaryType 查到的数据或空值
     * @exception Nothing
     * @since 1.8
     *
     */
    public DictionaryType selectDictionaryTypeByPrimaryKey(Integer id);
    
    /**
     * 
     * 描述：更新DictionaryType
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryType 对象中若有空则更新字段为null
     * @param dictionaryTypeExample 为where条件
     * @return boolean 更新结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean updateByDictionaryTypeExample(DictionaryType dictionaryType, DictionaryTypeExample dictionaryTypeExample);
    
    /**
     * 
     * 描述：更新DictionaryType 
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryType 对象中若有空则不会更新此字段
     * @param dictionaryTypeExample 为where条件
     * @return boolean 更新结果
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean updateByDictionaryTypeExampleSelective(DictionaryType dictionaryType, DictionaryTypeExample dictionaryTypeExample);
        
    /**
     * 
     * 描述：按照DictionaryType id 更新DictionaryType
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryType 对象中如有空则不会更新此字段
     * @return boolean
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean updateDictionaryTypeByPrimaryKeySelective(DictionaryType dictionaryType);
    
    /**
     * 
     * 描述：按照DictionaryType id 更新DictionaryType
     * @author huangwanzong
     * @version 1.0
     * @param dictionaryType 对象中如有空则更新此字段为null
     * @return boolean
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean updateDictionaryTypeByPrimaryKey(DictionaryType dictionaryType);   
}
