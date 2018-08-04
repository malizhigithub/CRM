package com.neuedu.crm.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.neuedu.crm.pojo.LoggingEvent;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.ILoggingEventService;
import com.neuedu.crm.service.impl.LogServiceImpl;
import com.neuedu.crm.utils.Operation;


/**
 *   
    * @ClassName: LogAop
    * @Description: TODO(日志aop)
    * @author 黄清文
    * @date 2018年7月18日
    *
 */
@Component
@Aspect
public class LogAop {
	
	private Logger logger = LoggerFactory.getLogger(LogAop.class);
	
	@Autowired
	private ILoggingEventService loggingEventService;

	@Pointcut("execution( * com.neuedu.crm.controller.*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void before(JoinPoint joinPoint) {
		//HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}

	@After("log()")
	public void after(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		// controller类
		Class<? extends Object> z = joinPoint.getTarget().getClass(); 

		String controllerOperation = z.getName();

		if (z.isAnnotationPresent(Operation.class)) {
		    // 当前controller操作的名称
			controllerOperation = z.getAnnotation(Operation.class).name(); 
		}

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		// 当前被访问的方法
		Method method = signature.getMethod(); 

		// z类下的所有方法
		Method[] methods = z.getMethods(); 
		String methodOperation = "";
		for (Method m : methods) {
			if (m.equals(method)) {
				methodOperation = m.getName();
				if (method.isAnnotationPresent(Operation.class)) {
				    // 当前执行方法操作的名称)
					methodOperation = method.getAnnotation(Operation.class).name(); 
					User user = (User) request.getSession().getAttribute("user");
					LoggingEvent loggingEvent = new LoggingEvent();
					if(user != null){
						System.err.println(user.getAccount() + " 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
								+ request.getRemoteHost());
						
						loggingEvent.setTimestmp(new Long(System.currentTimeMillis()));
						loggingEvent.setFormattedMessage(user.getAccount() + " 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
								+ request.getRemoteHost());
					}else{
						System.err.println("游客      执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
								+ request.getRemoteHost());
						loggingEvent.setTimestmp(new Long(System.currentTimeMillis()));
						loggingEvent.setFormattedMessage("游客   执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
								+ request.getRemoteHost());
					}
					System.err.println(System.currentTimeMillis()+"-------------------");
					try {
						loggingEventService.insertLog(loggingEvent);  //日志插入
					}catch (Exception e) {
						System.err.println(e.getMessage());
						System.err.println("日志插入错误！");
					}
				}
			}
		}
		
	}

	/**
	 * 
	 * 描述：@AfterReturning("log()")
	 * @author huangqingwen
	 * @date 
	 * @version 1.0
	 * @param joinPoint void
	 * @since 1.8
	 *
	 */
	public void afterReturing(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		// controller类
		Class<? extends Object> z = joinPoint.getTarget().getClass(); 

		String controllerOperation = z.getName();

		if (z.isAnnotationPresent(Operation.class)) {
		    // 当前controller操作的名称
			controllerOperation = z.getAnnotation(Operation.class).name(); 
		}

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		// 当前被访问的方法
		Method method = signature.getMethod(); 

		// z类下的所有方法
		Method[] methods = z.getMethods(); 
		String methodOperation = "";
		for (Method m : methods) {
			if (m.equals(method)) {
				methodOperation = m.getName();
				if (method.isAnnotationPresent(Operation.class)) {
				    // 当前执行方法操作的名称)
					methodOperation = method.getAnnotation(Operation.class).name(); 
				}
			}
		}
		User user = (User) request.getSession().getAttribute("user");
		if(user != null){
			logger.info(user.getAccount() + " 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
					+ request.getRemoteHost());
			System.err.println(user.getAccount() + " 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
					+ request.getRemoteHost());
		}else{
			logger.info( "未知用户        执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
					+ request.getRemoteHost());
			System.err.println("未知用户        执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作！ ip地址为"
					+ request.getRemoteHost());
		}
		
	}

	/**
	 * 描述：@AfterThrowing(pointcut="log()", throwing="e")
	 * @author huangwanzong
	 * @date 2018/07/24
	 * @version 1.0
	 * @param joinPoint
	 * @param e void
	 * @since 1.8
	 *
	 */
	public void afterThrowing(JoinPoint joinPoint, Throwable e) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		// controller类
		Class<? extends Object> z = joinPoint.getTarget().getClass(); 

		String controllerOperation = z.getName();

		if (z.isAnnotationPresent(Operation.class)) {
		    // 当前controller操作的名称
			controllerOperation = z.getAnnotation(Operation.class).name(); 
		}

		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		// 当前被访问的方法
		Method method = signature.getMethod(); 

		// z类下的所有方法
		Method[] methods = z.getMethods(); 
		String methodOperation = "";
		for (Method m : methods) {
			if (m.equals(method)) {
				methodOperation = m.getName();
				if (method.isAnnotationPresent(Operation.class)) {
				    // 当前执行方法操作的名称)
					methodOperation = method.getAnnotation(Operation.class).name(); 
				}
			}
		}
		User user = (User) request.getSession().getAttribute("user");
		logger.info(user.getAccount() + " 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作 出现了异常！异常信息："+e.getMessage()+ "   ip地址为"
				+ request.getRemoteAddr());
		System.err.println(user.getAccount() + " 执行了 " + controllerOperation + " 下的  " + methodOperation + " 操作 出现了异常！ 异常信息："+e.getMessage()+"ip地址为"
				+ request.getRemoteAddr());
	}
}
