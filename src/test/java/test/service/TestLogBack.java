package test.service;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.crm.pojo.LoggingEvent;
import com.neuedu.crm.pojo.LoggingEventExample;
import com.neuedu.crm.service.ILoggingEventService;


public class TestLogBack {
	
	Logger logger = LoggerFactory.getLogger(TestLogBack.class);
	
	String configLocation="config/applicationContext.xml";
	
	ILoggingEventService loggingService;
	
	@Before
	public void init() {
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);
		loggingService = context.getBean(ILoggingEventService.class);
	}
	
	@Test
	public void testLogback() {
		
//		LoggingEventExample loggingEventExample = new LoggingEventExample();
//		List<LoggingEvent> list = loggingService.selectByLoggingEventExample(loggingEventExample, 1L, 10L);
//		for (LoggingEvent loggingEvent : list) {
//			System.err.println(loggingEvent.getFormattedMessage());
//		}
	}
}
