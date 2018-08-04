package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.LoggingEventMapper;
import com.neuedu.crm.pojo.LoggingEvent;
import com.neuedu.crm.pojo.LoggingEventExample;
import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.service.ILoggingEventService;
/**
 * 
 * @author huangqinwen
 *
 */

@Service
@Transactional(rollbackFor=Exception.class)
public class LoggingEventServiceImpl implements ILoggingEventService{

	@Autowired
	private LoggingEventMapper logMapper;
	
	@Override
	public long countByLoggingEventExample(LoggingEventExample loggingEventExample) {
		
		return logMapper.countByExample(loggingEventExample);
	}

	@Override
	public boolean deleteByLoggingEventExample(LoggingEventExample loggingEventExample) {
		
		return logMapper.deleteByExample(loggingEventExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Long id) {
		
		return logMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertLoggingEvent(LoggingEvent loggingEvent) {
		
		return logMapper.insert(loggingEvent) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(LoggingEvent loggingEvent) {
		
		return logMapper.insertSelective(loggingEvent) > 0 ? true : false;
	}

	@Override
	public List<LoggingEvent> selectByLoggingEventExample(LoggingEventExample loggingEventExample, Pager pager) {
	    //设置一页多少条数据
	    loggingEventExample.setLimit(pager.getPageSize()); 
	    //按时间降序排列
		loggingEventExample.setOrderByClause("timestmp desc"); 
		loggingEventExample.setOffset(new Long(pager.getOffset()));
		return logMapper.selectByExampleWithBLOBs(loggingEventExample);
	}

	@Override
	public LoggingEvent selectLoggingEventByPrimaryKey(Long id) {
		
		return logMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByLoggingEventExampleSelective(LoggingEvent loggingEvent,
			LoggingEventExample loggingEventExample) {
		
		return logMapper.updateByExampleSelective(loggingEvent, loggingEventExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByLoggingEventExample(LoggingEvent loggingEvent, LoggingEventExample loggingEventExample) {
		
		return logMapper.updateByExample(loggingEvent, loggingEventExample) > 0 ? true : false;
	}

	@Override
	public boolean updateLoggingEventByPrimaryKeySelective(LoggingEvent loggingEvent) {
		
		return logMapper.updateByPrimaryKeySelective(loggingEvent) >0 ? true : false;
	}

	@Override
	public boolean updateLoggingEventByPrimaryKey(LoggingEvent loggingEvent) {
		
		return logMapper.updateByPrimaryKey(loggingEvent) > 0 ? true : false;
	}

	@Override
	public boolean insertLog(LoggingEvent loggingEvent) {
		return logMapper.insertLog(loggingEvent) > 0 ? true : false;
	}

	

}
