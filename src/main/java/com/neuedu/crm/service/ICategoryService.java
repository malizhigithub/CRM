package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.Category;
import com.neuedu.crm.pojo.CategoryExample;

/**
 * @author HuangQingwen
 * @date 2018/07/24
 */
public interface ICategoryService {
    /**
     * 
     * 描述：按照Example 统计记录总数
     * @author HuangQingwen
     * @version 1.0
     * @param categoryExample category模板
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
	public long countByCategoryExample(CategoryExample categoryExample);
	

	/**
	 * 
	 * 描述：按照Example 删除category
	 * @author HuangQingwen
	 * @version 1.0
	 * @param categoryExample
	 * @return 
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByCategoryExample(CategoryExample categoryExample);
	
	/**
	 * 
	 * 描述：
	 * @author HuangQingwen
	 * @version 1.0
	 * @param id
	 * @return 
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByPrimaryKey(Integer id);
	
	/**
	 * 描述：插入一条category数据 如字段为空，则插入null
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param category
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean insertCategory(Category category);
	
	/**
	 * 描述：插入一条category数据，如字段为空，则插入数据库表字段的默认值
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param category
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(Category category);
	
	/**
	 * 描述：按照Example 条件 模糊查询
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param categoryExample
	 * @return List<Category>
	 * @since 1.8
	 *
	 */
	public List<Category> selectByCategoryExample(CategoryExample categoryExample);
	
	/**
	 * 描述：按照category 的id 查找 category
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param id
	 * @return Category
	 * @since 1.8
	 *
	 */
	public Category selectCategoryByPrimaryKey(Integer id);
	
	/**
	 * 描述：更新category ， category对象中若有空则不会更新此字段  categoryExample为where条件
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param category
	 * @param categoryExample
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateByCategoryExampleSelective(Category category, CategoryExample categoryExample);
	
	/**
	 * 描述：更新category， category对象中若有空则更新字段为null   categoryExample为where条件
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param category
	 * @param categoryExample
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateByCategoryExample(Category category, CategoryExample categoryExample);
	
	/**
	 * 描述：按照category id 更新category  category对象中如有空则不会更新此字段
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param category
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateCategoryByPrimaryKeySelective(Category category);
	
	/**
	 * 描述：按照category id 更新category  category对象中如有空则更新此字段为null
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param category
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateCategoryByPrimaryKey(Category category);
}
