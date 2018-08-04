package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.Log;
import com.neuedu.crm.pojo.LogExample;
/**
 * 
 * @author WuJunyou
 * @date 2018/07/24
 */
public interface ILogService {
	/**
	 * 
	 * 描述： 按照Example 统计记录总数
	 * @author wujunyou
	 * @version 1.0
	 * @param logExample 查询条件
	 * @return long 数据的数量
	 * @exception Nothing
	 * @since 1.8
	 *
	 */ 	
	public long countByLogExample(LogExample logExample);

	/**
	 * 
	 * 描述：按照Example 删除Log
	 * @author wujunyou
	 * @version 1.0
	 * @param logExample 
	 * @return boolean 删除的结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean deleteByLogExample(LogExample logExample);

	/**
	 * 
	 * 描述：按照Log主键id删除Log
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
	 * 描述：插入一条Log数据 如字段为空，则插入null
	 * @author wujunyou
	 * @version 1.0
	 * @param log  客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertLog(Log log);
	
	/**
	 * 
	 * 描述：插入一条Log数据，如字段为空，则插入数据库表字段的默认值
	 * @author wujunyou
	 * @version 1.0
	 * @param log 客户数据
	 * @return boolean 插入结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(Log log);
	
	/**
	 * 
	 * 描述：按照Example条件 模糊查询
	 * @author wujunyou
	 * @version 1.0
	 * @param logExample 查询条件
	 * @return List<Log> 含Log的list
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public List<Log> selectByLogExample(LogExample logExample);
	
	/**
	 * 
	 * 描述：按照Log 的id 查找 Log
	 * @author wujunyou
	 * @version 1.0
	 * @param id 要查询的id
	 * @return Log 查到的数据或空值
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public Log selectLogByPrimaryKey(Integer id);
	
	/**
	 * 
	 * 描述：更新Log
	 * @author wujunyou
	 * @version 1.0
	 * @param log 对象中若有空则更新字段为null
	 * @param logExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByLogExample(Log log, LogExample logExample);
	
	/**
	 * 
	 * 描述：更新Log 
	 * @author wujunyou
	 * @version 1.0
	 * @param log 对象中若有空则不会更新此字段
	 * @param logExample 为where条件
	 * @return boolean 更新结果
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateByLogExampleSelective(Log log, LogExample logExample);
		
	/**
	 * 
	 * 描述：按照Log id 更新Log
	 * @author wujunyou
	 * @version 1.0
	 * @param log 对象中如有空则不会更新此字段
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateLogByPrimaryKeySelective(Log log);
	
	/**
	 * 
	 * 描述：按照Log id 更新Log
	 * @author wujunyou
	 * @version 1.0
	 * @param log 对象中如有空则更新此字段为null
	 * @return boolean
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	public boolean updateLogByPrimaryKey(Log log);	
}
