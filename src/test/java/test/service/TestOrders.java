package test.service;

import java.time.Duration;
import java.time.LocalDateTime;
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

public class TestOrders {
	protected ApplicationContext context;
    protected IOrdersService ordersService;
    
    private Logger logger = LoggerFactory.getLogger(TestUserService.class);
    
    @Before
    public void init(){
        try {
            String configLocation = "spring/applicationContext.xml";
            context = new ClassPathXmlApplicationContext(configLocation);
            ordersService = context.getBean(IOrdersService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void testSelectOrdersGroupByCustomerId(){
    	List<Orders> orders = ordersService.selectOrdersGroupByCustomerId();
    	for (Orders o : orders) {
			if(o.getDate() != null){
				/**
				 * 有历史订单的客户
				 */
				Duration duration = Duration.between(o.getLocalDateTimeDate(), LocalDateTime.now());
				System.err.println(duration.toMinutes());
			}
		}
    }
}
