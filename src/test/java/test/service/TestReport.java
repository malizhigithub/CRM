package test.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import com.neuedu.crm.mapper.ReportMapper;
import com.neuedu.crm.pojo.Report;


public class TestReport {
	
	private SqlSession sqlSession;
	
	private ReportMapper reportMapper;
	
	@Before //加载mybatis 的运行环境
	public void init() throws IOException {
		String resource = "mybatis/mybatis-config-test.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		sqlSession = sqlSessionFactory.openSession();
		//2. 获得接口
		reportMapper = sqlSession.getMapper(ReportMapper.class);
	}
	
	/**
	 * 统计公司n天内新增的客户量
	 * @throws JsonGenerationException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	//@Test
	public void testCountCustomerIncrease() throws JsonGenerationException, JsonMappingException, IOException {
		int day = 7;
		List<Map<String, Object>> result = reportMapper.countCustomerIncrease(day);
		
		Map<String, Object> maps = new HashMap<String, Object>();
		
		maps.put("data", result);
		
		System.err.println(new ObjectMapper().writeValueAsString(maps));
	}
	
	
	//@Test
	public void testCountCustomerByDate() throws ParseException, JsonGenerationException, JsonMappingException, IOException {
	    List<Map<String, Object>> result;
	    Report report = new Report();
	    report.setStartTime(new SimpleDateFormat("yy-MM-dd").parse("2018-07-01"));
	    report.setResultDateFormat(Report.TIME_YEAR);
	    report.setTimeFormat(Report.TIME_YEAR);
	    report.setEndTime(new SimpleDateFormat("yy-MM-dd").parse("2018-7-10"));
	    
	    result = reportMapper.countCustomerByDate(report);
	    System.out.println("result:");
	    System.out.println(new ObjectMapper().writeValueAsString(result));
	}
	
    //@Test
    public void testCountCustomerByCategory() throws ParseException, JsonGenerationException, JsonMappingException, IOException {
        List<Map<String, Object>> result;
        Report report = new Report();
        report.setStartTime(new SimpleDateFormat("yy-MM-dd").parse("2018-07-01"));
        report.setResultDateFormat(Report.TIME_YEAR_MONTH);
        report.setTimeFormat(Report.TIME_YEAR_MONTH);
        report.setEndTime(new SimpleDateFormat("yy-MM-dd").parse("2018-7-10"));

        report.setCategory(Report.CATEGORY_SOURCE);
        
        result = reportMapper.countCustomerByCategory(report);
        System.out.println("result:");
        System.out.println(new ObjectMapper().writeValueAsString(result));
    }	
    
    //@Test
    public void testCountManagerCustomer() throws JsonGenerationException, JsonMappingException, IOException, ParseException{
    	
    	Report report = new Report();
        report.setStartTime(new SimpleDateFormat("yy-MM-dd").parse("2018-7-24"));
      
    	List<Map<String, Object>> result = reportMapper.countManagerCustomerRank(report);
    	System.err.println(new ObjectMapper().writeValueAsString(result));
    }
    
    @Test
    public void testCountManagerService() throws JsonGenerationException, JsonMappingException, IOException {
        Report report = new Report();
        report.setUserId(19);
        report.setService(Report.SERVICE_TYPE);
        report.setDay(365);
        List<Map<String, Object>> result = reportMapper.countManagerSerivce(report);
        System.err.println(new ObjectMapper().writeValueAsString(result));
    }
}
