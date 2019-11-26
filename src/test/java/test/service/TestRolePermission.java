package test.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.crm.service.IRolePermissionService;

public class TestRolePermission {
	
	ApplicationContext context;
	IRolePermissionService service;
	
	@Before
	public void init() {
		context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		service = context.getBean(IRolePermissionService.class);
	}
	
	/**
	 * 测试批量插入  角色权限表
	 */
	@Test
	public void testInsertRolePermission() { 
		
		Integer[] array = {1,2,3,4};
		Integer roleId = 1;
		int count = service.allotPermission(array, roleId);
		System.err.println("+++++++++++++++++++++++++"+count);
	}
}
