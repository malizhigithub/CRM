package com.neuedu.crm.mapper;



import java.util.List;

import com.neuedu.crm.pojo.LoggingEvent;
import com.neuedu.crm.pojo.LoggingEventExample;

/**
 * LoggingEventMapper继承基类
 * @author MybatisGenerator
 */
public interface LoggingEventMapper extends MyBatisBaseDao<LoggingEvent, Long, LoggingEventExample> {
	
	/**
	 * 描述：
	    * @Title: selectByExampleWithBLOBs
	    * @Description: TODO(查询含有text文本类型的结果集)
	    * @param @param loggingEventExample
	    * @param @return    参数
	    * @return List<LoggingEvent>    返回类型
	    * @throws
	    * @author huangqingwen
	 */
	public List<LoggingEvent> selectByExampleWithBLOBs(LoggingEventExample loggingEventExample);
	
	public int insertLog(LoggingEvent loggingEvent);
}