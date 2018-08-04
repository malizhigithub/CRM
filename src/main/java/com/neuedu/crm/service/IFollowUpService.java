package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.FollowUp;
import com.neuedu.crm.pojo.FollowUpExample;
/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface IFollowUpService {
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param followUpExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByFollowUpExample(FollowUpExample followUpExample);

	/**
	 * 
	 * 描述：按照Example 删除FollowUp
	 * @author wujunyou
	 * @version 1.0
	 * @param followUpExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByFollowUpExample(FollowUpExample followUpExample);

	/**
	 * 
	 * 描述：按照FollowUp主键id删除FollowUp
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
	 * 描述：插入一条FollowUp数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param followUp  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertFollowUp(FollowUp followUp);
	
	/**
	 * 
	 * 描述：插入一条FollowUp数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param followUp 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(FollowUp followUp);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param followUpExample 查询条件
	 * @return List<FollowUp> 含FollowUp的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<FollowUp> selectByFollowUpExample(FollowUpExample followUpExample);
	
	/**
	 * 
	 * 描述：按照FollowUp 的id 查找 FollowUp
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return FollowUp 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public FollowUp selectFollowUpByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新FollowUp
	 * @author wujunyou
	 * @version 1.0
	 * @param followUp 对象中若有空则更新字段为null
	 * @param followUpExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByFollowUpExample(FollowUp followUp, FollowUpExample followUpExample);
	
	/**
	 * 
	 * 描述：更新FollowUp 
	 * @author wujunyou
	 * @version 1.0
	 * @param followUp 对象中若有空则不会更新此字段
	 * @param followUpExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByFollowUpExampleSelective(FollowUp followUp, FollowUpExample followUpExample);
		
	/**
	 * 
	 * 描述：按照FollowUp id 更新FollowUp
	 * @author wujunyou
	 * @version 1.0
	 * @param followUp 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateFollowUpByPrimaryKeySelective(FollowUp followUp);
	
	/**
	 * 
	 * 描述：按照FollowUp id 更新FollowUp
	 * @author wujunyou
	 * @version 1.0
	 * @param followUp 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateFollowUpByPrimaryKey(FollowUp followUp);	
}