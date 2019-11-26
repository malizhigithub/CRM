package test.service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.pojo.Permission;
import com.neuedu.crm.pojo.PermissionExample;
import com.neuedu.crm.service.IPermissionService;
import com.neuedu.crm.service.IUserService;

public class TestPermission {
	
	protected ApplicationContext context;
    protected IPermissionService permissionService;
    
    static List<Permission> tree = null;
    
    @Before
    public void init(){
        try {
            String configLocation = "spring/applicationContext.xml";
            context = new ClassPathXmlApplicationContext(configLocation);
            permissionService = context.getBean(IPermissionService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSelectExample() throws JsonGenerationException, JsonMappingException, IOException {
    	Pager pager = new Pager(1, 10);
    	PermissionExample permissionExample = new PermissionExample();
    	permissionExample.createCriteria().andPidEqualTo(1);
    	
    	Long count = permissionService.countByPermissionExample(permissionExample);
		pager.setTotal(count.intValue());
		
		tree = permissionService.selectTreePermission();
		
		int[] array = {1,2,3,4};
		
		List<Permission> result = getUserPermission(tree, array);
		
		System.err.println(new ObjectMapper().writeValueAsString(result));
    	
    }
    
    /**
     * 过滤用户的菜单
     * @param permissions
     * @param array
     * @return
     */
    public List<Permission> getUserPermission(List<Permission> permissions, int[] array){
    	if(permissions == null) {
    		return null;
    	}
    
    	for(int i=permissions.size()-1; i>=0; i--) {
    		Permission permission = permissions.get(i);
    		boolean flag = false;
    		for (int a : array) {
				if(permission.getId() == a) {
					flag = true;
					permission.setChildPermission(getUserPermission(permission.getChildPermission(), array));
				}
			}
    		if(flag == false) {
    			permissions.remove(i);
    		}
    	}
    	
    	return permissions;
    }
    
    
  
}
