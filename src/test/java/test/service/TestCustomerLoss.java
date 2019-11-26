package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.crm.pojo.Orders;
import com.neuedu.crm.service.ICustomerLossService;
import com.neuedu.crm.service.IOrdersService;
import com.neuedu.crm.service.IUserService;

public class TestCustomerLoss {
	protected ApplicationContext context;
    protected ICustomerLossService lossService;
    IOrdersService ordersService;
    
    private Logger logger = LoggerFactory.getLogger(TestUserService.class);
    
    @Before
    public void init(){
        try {
            String configLocation = "spring/applicationContext.xml";
            context = new ClassPathXmlApplicationContext(configLocation);
            lossService = context.getBean(ICustomerLossService.class);
            ordersService = context.getBean(IOrdersService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSelectByCustomerLossExample(){
    	
    	List<Orders> orders = ordersService.selectOrdersGroupByCustomerId();
    	System.err.println(orders.toString());
    }
}	
