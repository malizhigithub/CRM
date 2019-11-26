package test.service;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neuedu.crm.service.IReportService;

public class TestReportService {
	protected ApplicationContext context;
    protected IReportService reportService;
    
    private Logger logger = LoggerFactory.getLogger(IReportService.class);
    
    @Before
    public void init(){
        try {
            String configLocation = "spring/applicationContext.xml";
            context = new ClassPathXmlApplicationContext(configLocation);
            reportService = context.getBean(IReportService.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    @Test
    public void testCountCustomerByYear() throws JsonGenerationException, JsonMappingException, IOException {

    }
    
}
