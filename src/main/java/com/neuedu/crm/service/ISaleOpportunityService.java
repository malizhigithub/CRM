package com.neuedu.crm.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.pojo.SaleOpportunity;
import com.neuedu.crm.pojo.SaleOpportunityExample;

/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface ISaleOpportunityService {
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param saleOpportunityExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countBySaleOpportunityExample(SaleOpportunityExample saleOpportunityExample);

	/**
	 * 
	 * 描述：按照Example 删除SaleOpportunity
	 * @author wujunyou
	 * @version 1.0
	 * @param saleOpportunityExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteBySaleOpportunityExample(SaleOpportunityExample saleOpportunityExample);

	/**
	 * 
	 * 描述：按照SaleOpportunity主键id删除SaleOpportunity
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
	 * 描述：插入一条SaleOpportunity数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param saleOpportunity  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSaleOpportunity(SaleOpportunity saleOpportunity);
	
	/**
	 * 
	 * 描述：插入一条SaleOpportunity数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param saleOpportunity 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(SaleOpportunity saleOpportunity);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param saleOpportunityExample 查询条件
	 * @return List<SaleOpportunity> 含SaleOpportunity的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<SaleOpportunity> selectBySaleOpportunityExample(SaleOpportunityExample saleOpportunityExample);
	
	/**
	 * 
	 * 描述：根据传入的参数封装到example中，再进行selectBySaleOpportunityExample查询
	 * @author MaLizhi
	 * @date 2018/07/24
	 * @version 1.0
	 * @param saleOpportunity
	 * @param pager
	 * @param request
	 * @return List<SaleOpportunity>
	 * @since 1.8
	 *
	 */
	public List<SaleOpportunity> selectBySaleOpportunitySelectiveAndPager(SaleOpportunity saleOpportunity,Pager pager,HttpServletRequest request);
	
	
	/**
	 * 
	 * 描述：按照SaleOpportunity 的id 查找 SaleOpportunity
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return SaleOpportunity 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public SaleOpportunity selectSaleOpportunityByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新SaleOpportunity
	 * @author wujunyou
	 * @version 1.0
	 * @param saleOpportunity 对象中若有空则更新字段为null
	 * @param saleOpportunityExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateBySaleOpportunityExample(SaleOpportunity saleOpportunity, SaleOpportunityExample saleOpportunityExample);
	
	/**
	 * 
	 * 描述：更新SaleOpportunity 
	 * @author wujunyou
	 * @version 1.0
	 * @param saleOpportunity 对象中若有空则不会更新此字段
	 * @param saleOpportunityExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateBySaleOpportunityExampleSelective(SaleOpportunity saleOpportunity, SaleOpportunityExample saleOpportunityExample);
		
	/**
	 * 
	 * 描述：按照SaleOpportunity id 更新SaleOpportunity
	 * @author wujunyou
	 * @version 1.0
	 * @param saleOpportunity 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateSaleOpportunityByPrimaryKeySelective(SaleOpportunity saleOpportunity);
	
	/**
	 * 
	 * 描述：按照SaleOpportunity id 更新SaleOpportunity
	 * @author wujunyou
	 * @version 1.0
	 * @param saleOpportunity 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateSaleOpportunityByPrimaryKey(SaleOpportunity saleOpportunity);	
	
	/**
	 * 根据id（分隔符是-）批量软删除销售机会
	 * @author malizhi
	 * @param ids
	 * @return boolean
	 * @version 1.0
	 * @exception Nothing
	 */
	public boolean deleteSaleOpportunitiesByPrimaryKey(String ids);
	
}

