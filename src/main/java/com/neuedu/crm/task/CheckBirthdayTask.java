/**
 * 
 */
package com.neuedu.crm.task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerCare;
import com.neuedu.crm.pojo.CustomerCareExample;
import com.neuedu.crm.pojo.CustomerCareExample.Criteria;
import com.neuedu.crm.pojo.CustomerExample;
import com.neuedu.crm.pojo.Linkman;
import com.neuedu.crm.pojo.LinkmanExample;
import com.neuedu.crm.service.ICustomerCareService;
import com.neuedu.crm.service.ICustomerService;
import com.neuedu.crm.service.ILinkmanService;

/**
 * 描述：用于定义检测联系人是否已经快到了
 * @author wanghaoyu
 *
 */
@Component
public class CheckBirthdayTask {
    
    @Autowired
    ILinkmanService linkmanService;
    
    @Autowired
    ICustomerCareService customerCareService;
    
    @Autowired
    ICustomerService customerService;
    /**
     * 
     * 描述：用于检测用户的生日是否在30天内，每天零点检查一次
     * @author wanghaoyu
     * @version 1.0 
     * @exception Nothing
     * @since 1.8
     *
     */
    @Scheduled(cron = "0 0 0 * * ?") 
    public void checkBirthday(){
        //查找所有的联系人
        List<Linkman> linkmans =  linkmanService.selectByLinkmanExample(new LinkmanExample());
        //获取当前日期
        LocalDate now = LocalDate.now();
        //循环判断生日是否快到了
        for (Linkman linkman : linkmans) {
            LocalDate dateOfBirthday = linkman.getBirthday();
            //把生日的年份换成今年，以方便计算天数
            LocalDate  birthday = LocalDate.of(now.getYear(), dateOfBirthday.getMonth(), dateOfBirthday.getDayOfMonth());
            //计算生日离今天还有多少天
            long betweenDays = now.until(birthday, ChronoUnit.DAYS);
            //判断是不是在天数以内
            if( betweenDays >= 0 && betweenDays <= 30 ){
                //如果在30天内，就要插入一条数据到客户关怀表中
                //先检测今年是否已经有了该用户的客户关怀数据
                CustomerCareExample customerCareExample = new CustomerCareExample();
                Criteria criteria = customerCareExample.createCriteria();
                //判断当前的联系人
                criteria.andLinkmanIdEqualTo(linkman.getId());
                //判断记录是否为今年所生成的
                criteria.andCreateTimeBetween(LocalDateTime.of(now.getYear(), 1, 1,0,0,0), LocalDateTime.of(now.getYear(), 12, 31, 23, 59, 59));
                List<CustomerCare> customerCares = customerCareService.selectByCustomerCareExample(customerCareExample);
                //如果存在该记录，就要判断用户的生日是否发生了改变
                //改变了就要删除原来的记录，再插入新的，不改变即表示不需要再重新插入
                if(customerCares.size() > 0){
                    CustomerCare customerCare = customerCares.get(0);
                    //如果生日发生了改变，即要删除原来的记录，重新生成新的客户关怀数据
                    if( ! customerCare.getBirthday().equals(linkman.getBirthday())){
                        //如果删除成功，继续添加新的记录
                        if(customerCareService.deleteByPrimaryKey(customerCare.getId())){
                            CustomerCare newCustomerCare = new CustomerCare();
                            newCustomerCare.setLinkmanId(linkman.getId());
                            newCustomerCare.setBirthday(linkman.getBirthday());
                            newCustomerCare.setCreateTime(LocalDateTime.now());
                            newCustomerCare.setStatus("未处理");
                            
                            //查找该联系人所属的客户
                            CustomerExample customerExample = new CustomerExample();
                            customerExample.createCriteria().andIdEqualTo(linkman.getCustomerId());
                            Customer customer = customerService.selectByCustomerExample(customerExample).get(0);
                            //再根据客户便可以知道该联系人属于哪一个客户经理
                            newCustomerCare.setManagerId(customer.getManagerId());
                            
                            //插入数据
                            customerCareService.insertCustomerCare(newCustomerCare);
                        }
                    }
                    else{
                        
                    }
                //没有记录，便要重新插入数据
                }else{
                    CustomerCare newCustomerCare = new CustomerCare();
                    newCustomerCare.setLinkmanId(linkman.getId());
                    newCustomerCare.setBirthday(linkman.getBirthday());
                    newCustomerCare.setCreateTime(LocalDateTime.now());
                    newCustomerCare.setStatus("未处理");
                    
                    //查找该联系人所属的客户
                    CustomerExample customerExample = new CustomerExample();
                    customerExample.createCriteria().andIdEqualTo(linkman.getCustomerId());
                    Customer customer = customerService.selectByCustomerExample(customerExample).get(0);
                    //再根据客户便可以知道该联系人属于哪一个客户经理
                    newCustomerCare.setManagerId(customer.getManagerId());
                    
                    //插入数据
                    customerCareService.insertCustomerCare(newCustomerCare);
                }
            }
        }
    }
    
    
}
