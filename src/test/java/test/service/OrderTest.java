/**
 * 
 */
package test.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.crm.pojo.OrderItem;
import com.neuedu.crm.pojo.OrderItemExample;
import com.neuedu.crm.pojo.OrderItemExample.Criteria;
import com.neuedu.crm.pojo.Orders;
import com.neuedu.crm.service.IOrderItemService;
import com.neuedu.crm.service.IOrdersService;

/**
 * @author malizhi
 *
 */
public class OrderTest {
	protected ApplicationContext context;
    protected IOrdersService ordersService;
    protected IOrderItemService orderItemService;
    private Logger logger = LoggerFactory.getLogger(OrderTest.class);
    private Random random = new Random();
    
    /**
     * 初始化环境
     * @author malizhi void
     * @version 1.0
     * @exception Nothing
     */
    @Before
    public void init(){
        try {
            String configLocation = "classpath:spring/applicationContext.xml";
            context = new ClassPathXmlApplicationContext(configLocation);
            ordersService = context.getBean(IOrdersService.class);
            orderItemService = context.getBean(IOrderItemService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 用于向订单表随机插入数据
     * @author malizhi void
     * @version 1.0
     * @exception Nothing
     */
    @Test
    public void test01(){
    	//订单400条，每条订单10条订单子项
    	for(int i=0;i<400;i++){
    		Orders order = new Orders();
    		//order.setDate(LocalDateTimeUtil.parse(LocalDateTimeUtil.formatNow("yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss"));
    		order.setDate(LocalDateTime.now());
    		if(i<20){
    			order.setAddress("广州");
    			order.setCustomerId(1);
    		}else if(i<50){
    			order.setAddress("深圳");
    			order.setCustomerId(2);
    		}else if(i<100){
				order.setAddress("佛山");
				order.setCustomerId(3);
			}else if(i<200){
				order.setAddress("东莞");
				order.setCustomerId(6);
			}else{
				order.setAddress("福建");
				order.setCustomerId(8);
			}
    		
    		Double price = (double) (random.nextInt(100)+100);
    		order.setPrice(price);
    		order.setStatus(1);
    		
    		ordersService.insertSelective(order);
    	}
    }
	
    /**
     * 用于向订单子项随机插入数据
     * @author malizhi void
     * @version 1.0
     * @exception Nothing
     */
    @Test
    public void test2(){
    	for(int i=1;i<=400;i++){
    		for(int j=1;j<=10;j++){
    			DecimalFormat dcmFmt = new DecimalFormat("#.##");
    			OrderItem orderItem = new OrderItem();
    	    	orderItem.setNum(random.nextInt(999)+random.nextInt(5)*1000);
    	    	orderItem.setUnit("元");
    	    	double unitPrice = random.nextDouble()+random.nextInt(10);
    	    	//price = Double.parseDouble(dcmFmt.format(random.nextFloat()))+random.nextInt(10);
    	    	unitPrice =new BigDecimal(unitPrice).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    	    	orderItem.setUnitPrice(unitPrice);
    	    	
    	    	double price = orderItem.getNum()*orderItem.getUnitPrice();
    	    	price = new BigDecimal(price).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    	    	orderItem.setPrice(price);
    	    	orderItem.setProductId(j);
    	    	orderItem.setOrdersId(i);
    	    	orderItemService.insertOrderItem(orderItem);
    		}
    	}
    }
    
    
    /**
     * 订单价格的更新
     * @author malizhi void
     * @version 1.0
     * @exception Nothing
     */
    @Test
    public void test03(){
    	Double totalPrice = (double) 0;
    	for(int i=1;i<=400;i++){
    		totalPrice = 0.00;
    		OrderItemExample orderItemExample = new OrderItemExample();
    		Criteria criteria = orderItemExample.createCriteria();
    		criteria.andOrdersIdEqualTo(i);
    		List<OrderItem> list = orderItemService.selectByOrderItemExample(orderItemExample);
    		for(OrderItem o:list){
    			totalPrice += o.getPrice();
    		}
    		totalPrice =  new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
    		Orders orders = new Orders();
    		orders.setId(i);
    		orders.setPrice(totalPrice);
    		ordersService.updateOrderByPrimaryKeySelective(orders);
    	}
    }
    
	
}
