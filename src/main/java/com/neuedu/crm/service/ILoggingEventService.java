package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.LoggingEvent;
import com.neuedu.crm.pojo.LoggingEventExample;
import com.neuedu.crm.pojo.Pager;
/**
 * @author HuangQingwen
 * @date 2018/07/24
 */
public interface ILoggingEventService {

    /**
     * 描述：按照Example 统计记录总数
     * @author HuangQingwen
     * @date 2018/07/24
     * @version 1.0
     * @param loggingEventExample
     * @return long
     * @since 1.8
     *
     */
	public long countByLoggingEventExample(LoggingEventExample loggingEventExample);
	
	/**
	 * 描述：按照Example 删除LoggingEvent
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param loggingEventExample
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean deleteByLoggingEventExample(LoggingEventExample loggingEventExample);
	
	/**
	 * 描述：按照LoggingEvent主键id删除LoggingEvent
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param id
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean deleteByPrimaryKey(Long id);
	
	/**
	 * 描述：插入一条LoggingEvent数据 如字段为空，则插入null
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param loggingEvent
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean insertLoggingEvent(LoggingEvent loggingEvent);
	
	/**
	 * 描述： 插入一条LoggingEvent数据，如字段为空，则插入数据库表字段的默认值
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param loggingEvent
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean insertSelective(LoggingEvent loggingEvent);
	
	/**
	 * 描述：按照Example 条件 模糊查询
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param loggingEventExample
	 * @param pager
	 * @return List<LoggingEvent>
	 * @since 1.8
	 *
	 */
	public List<LoggingEvent> selectByLoggingEventExample(LoggingEventExample loggingEventExample, Pager pager);
	
	/**
	 * 描述：按照LoggingEvent 的id 查找 LoggingEvent
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param id
	 * @return LoggingEvent
	 * @since 1.8
	 *
	 */
	public LoggingEvent selectLoggingEventByPrimaryKey(Long id);
	
	/**
	 * 描述：更新LoggingEvent ， LoggingEvent对象中若有空则不会更新此字段  LoggingEventExample为where条件
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param loggingEvent
	 * @param loggingEventExample
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateByLoggingEventExampleSelective(LoggingEvent loggingEvent, LoggingEventExample loggingEventExample);
	
	/**
	 * 描述：更新LoggingEvent， LoggingEvent对象中若有空则更新字段为null   LoggingEventExample为where条件
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param loggingEvent
	 * @param loggingEventExample
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateByLoggingEventExample(LoggingEvent loggingEvent, LoggingEventExample loggingEventExample);
	
	/**
	 * 描述：按照LoggingEvent id 更新LoggingEvent  LoggingEvent对象中如有空则不会更新此字段
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param loggingEvent
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateLoggingEventByPrimaryKeySelective(LoggingEvent loggingEvent);
	
	/**
	 * 描述：按照LoggingEvent id 更新LoggingEvent  LoggingEvent对象中如有空则更新此字段为null
	 * @author HuangQingwen
	 * @date 2018/07/24
	 * @version 1.0
	 * @param loggingEvent
	 * @return boolean
	 * @since 1.8
	 *
	 */
	public boolean updateLoggingEventByPrimaryKey(LoggingEvent loggingEvent);
	
	public boolean insertLog(LoggingEvent loggingEvent);
}
