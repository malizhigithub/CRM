package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.Product;
import com.neuedu.crm.pojo.ProductExample;
/**
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface IProductService {
	
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param productExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByProductExample(ProductExample productExample);

	/**
	 * 
	 * 描述：按照Example 删除Product
	 * @author wujunyou
	 * @version 1.0
	 * @param productExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByProductExample(ProductExample productExample);

	/**
	 * 
	 * 描述：按照Product主键id删除Product
	 * @author wujunyou
	 * @version 1.0
	 * @param id 数据字典id
	 * @return boolean 删除结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：插入一条Product数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param product  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertProduct(Product product);
	
	/**
	 * 
	 * 描述：插入一条Product数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param product 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(Product product);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param productExample 查询条件
	 * @return List<Product> 含Product的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<Product> selectByProductExample(ProductExample productExample);
	
	/**
	 * 
	 * 描述：按照Product 的id 查找 Product
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return Product 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public Product selectProductByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新Product
	 * @author wujunyou
	 * @version 1.0
	 * @param product 对象中若有空则更新字段为null
	 * @param productExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByProductExample(Product product, ProductExample productExample);
	
	/**
	 * 
	 * 描述：更新Product 
	 * @author wujunyou
	 * @version 1.0
	 * @param product 对象中若有空则不会更新此字段
	 * @param productExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByProductExampleSelective(Product product, ProductExample productExample);
		
	/**
	 * 
	 * 描述：按照Product id 更新Product
	 * @author wujunyou
	 * @version 1.0
	 * @param product 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateProductByPrimaryKeySelective(Product product);
	
	/**
	 * 
	 * 描述：按照Product id 更新Product
	 * @author wujunyou
	 * @version 1.0
	 * @param product 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateProductByPrimaryKey(Product product);	
}
