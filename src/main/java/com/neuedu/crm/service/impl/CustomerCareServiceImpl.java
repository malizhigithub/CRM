package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.CustomerCareMapper;
import com.neuedu.crm.mapper.CustomerMapper;
import com.neuedu.crm.mapper.LinkmanMapper;
import com.neuedu.crm.mapper.UserMapper;
import com.neuedu.crm.pojo.CustomerCare;
import com.neuedu.crm.pojo.CustomerCareExample;
import com.neuedu.crm.pojo.Linkman;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.ICustomerCareService;
/**
 * 
 * @author HuangWanzong
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CustomerCareServiceImpl implements ICustomerCareService{

	@Autowired
	private CustomerCareMapper customerCareMapper;
	
	@Autowired
	private LinkmanMapper linkmanMapper;
	
	@Autowired
    private CustomerMapper customerMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public long countByCustomerCareExample(CustomerCareExample customerCareExample) {
		return customerCareMapper.countByExample(customerCareExample);
	}

	@Override
	public boolean deleteByCustomerCareExample(CustomerCareExample customerCareExample) {
		return customerCareMapper.deleteByExample(customerCareExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return customerCareMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertCustomerCare(CustomerCare customerCare) {
		return customerCareMapper.insert(customerCare) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(CustomerCare customerCare) {
		return customerCareMapper.insertSelective(customerCare) > 0 ? true : false;
	}

	@Override
	public List<CustomerCare> selectByCustomerCareExample(CustomerCareExample customerCareExample) {
		return customerCareMapper.selectByExample(customerCareExample);
	}

	@Override
	public CustomerCare selectCustomerCareByPrimaryKey(Integer id) {
		return customerCareMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByCustomerCareExampleSelective(CustomerCare customerCare, CustomerCareExample customerCareExample) {
		return customerCareMapper.updateByExampleSelective(customerCare, customerCareExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByCustomerCareExample(CustomerCare customerCare, CustomerCareExample customerCareExample) {
		return customerCareMapper.updateByExample(customerCare, customerCareExample) > 0 ? true : false;
	}

	@Override
	public boolean updateCustomerCareByPrimaryKeySelective(CustomerCare customerCare) {
		return customerCareMapper.updateByPrimaryKeySelective(customerCare) > 0 ? true : false;
	}

	@Override
	public boolean updateCustomerCareByPrimaryKey(CustomerCare customerCare) {
		return customerCareMapper.updateByPrimaryKey(customerCare) > 0 ? true : false ;
	}

    /** 
     * @see com.neuedu.crm.service.ICustomerCareService#selectCustomerCareByManagerId(java.lang.Integer)
     */
    @Override
    public List<CustomerCare> selectCustomerCareByManagerId(Integer id) {
        List<CustomerCare> customerCares;
        try {
            CustomerCareExample customerCareExample = new CustomerCareExample();
            if (id != null) {
                customerCareExample.createCriteria().andManagerIdEqualTo(id);
            }
            customerCares = customerCareMapper.selectByExample(customerCareExample);
            for (CustomerCare customerCare : customerCares) {
                //封装联系人对象
                Linkman linkman = linkmanMapper.selectByPrimaryKey(customerCare.getLinkmanId());
                //联系人对象里面再封装客户信息
                linkman.setCustomer(customerMapper.selectByPrimaryKey(linkman.getCustomerId()));
                customerCare.setLinkman(linkman);
                //根据客户经理ID查找用户
                User user = userMapper.selectByPrimaryKey(customerCare.getManagerId());
                //加密信息置空
                user.setPassword(null);
                user.setSalt(null);
                //封装客户经理对象
                customerCare.setManager(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return customerCares;
    }

}
