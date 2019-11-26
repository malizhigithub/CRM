/**
 * 
 */
package test.service;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.crm.pojo.User;
import com.neuedu.crm.pojo.UserExample;
import com.neuedu.crm.service.IUserService;

/**
 * @author wanghaoyu
 *
 */
public class TestUserService {
    protected ApplicationContext context;
    protected IUserService userService;
    
    private Logger logger = LoggerFactory.getLogger(TestUserService.class);
    
    @Before
    public void init(){
        try {
            String configLocation = "spring/applicationContext.xml";
            context = new ClassPathXmlApplicationContext(configLocation);
            logger.error("0000000000000000"+context);
            userService = context.getBean(IUserService.class);
            logger.error("+++++++11111111111111"+userService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //@Test
    public void testFindByExample(){
        UserExample userExample = new UserExample();
        List<User> users= userService.findByExample(userExample);
        if(users.size() > 0) {
            for (User user : users) {
                logger.info(user.toString());
            }
        }else {
            logger.info("不存在该用户");
        }
    }
    
    @Test
    public void testAddUser(){
        User user = new User();
        user.setAccount("testtest");
        user.setPassword("123456");
        user.setRealName("wagg");
        logger.error("-----------------222222222222"+userService);
        if( userService.save(user) == true) {
            logger.info("添加成功");
        }
        else {
            logger.info("添加失败");
        }
    }
    
    @Test
    public void testeditUser(){
        User user = new User();
        user.setId(19);
        user.setAccount("wang");
        if( userService.edit(user) == true) {
            logger.info("添加成功");
        }
        else {
            logger.info("添加失败");
        }
    }
    
    @Test
    public void testdelUser(){
        User user = new User();
        if( userService.deleteById(19) == true) {
            logger.info("删除成功");
        }
        else {
            logger.info("删除失败");
        }
    }
    
}
