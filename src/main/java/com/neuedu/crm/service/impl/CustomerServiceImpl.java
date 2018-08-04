package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.CustomerMapper;
import com.neuedu.crm.mapper.LinkmanMapper;
import com.neuedu.crm.mapper.ProductMapper;
import com.neuedu.crm.mapper.UserMapper;
import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerExample;
import com.neuedu.crm.pojo.Linkman;
import com.neuedu.crm.pojo.Product;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.ICustomerService;
/**
 * 
 * @author WangHaoyu
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CustomerServiceImpl implements ICustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private LinkmanMapper linkmanMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	
	@Override
	public long countByCustomerExample(CustomerExample customerExample) {
		return customerMapper.countByExample(customerExample);
	}

	@Override
	public boolean deleteByCustomerExample(CustomerExample customerExample) {
		if(customerMapper.deleteByExample(customerExample) > 0) {
		    return true;
		}else {
            return false;
        }
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
	    
	    Customer customer = new Customer();
	    customer.setId(id);
	    customer.setDeleteStatus(1);
	    
		if(customerMapper.updateByPrimaryKeySelective(customer) > 0) {
		    return true;
		}else {
            return false;
        }
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		if(customerMapper.insert(customer) > 0) {
		    return true;
		}else {
            return false;
        }
	}

	@Override
	public boolean insertSelective(Customer customer) {
		if(customerMapper.insertSelective(customer) > 0) {
		    return true;
		}else {
		    return false;
        }
		
	}

    @Override
    public boolean insertSelective(Customer customer,Linkman linkman) {
        //插入客户数据
        if(customerMapper.insertSelective(customer) > 0) {
            
            CustomerExample example = new CustomerExample();
            example.createCriteria().andNameEqualTo(customer.getName());
            
            customer = customerMapper.selectByExample(example).get(0);
            
            linkman.setCustomerId(customer.getId());
            linkman.setLevel(0);
            
            linkmanMapper.insertSelective(linkman);
            
            return true;
        }else {
            return false;
        }
        
    }	
	
	@Override
	public List<Customer> selectByCustomerExample(CustomerExample customerExample) {
	    
	    List<Customer> list = customerMapper.selectByExample(customerExample);
	    
	    for(Customer customer:list) {
	        try {
	            Product product = productMapper.selectByPrimaryKey(customer.getProductId());
	            customer.setProduct(product);
	            
	            User creater = userMapper.selectByPrimaryKey(customer.getCreater());  
	            if(creater != null) {
	                creater.setPassword(null);
	                creater.setSalt(null);
	            }
	            customer.setCreaterObject(creater);
	            
	            User manager = userMapper.selectByPrimaryKey(customer.getManagerId());
	            if(manager != null) {
	                manager.setPassword(null);
	                manager.setSalt(null);
	            }
	            customer.setManager(manager);
	        }catch (Exception e) {
            }
	    }
		return list;
	}

	@Override
	public Customer selectCustomerByPrimaryKey(Integer id) {
	    Customer customer = customerMapper.selectByPrimaryKey(id);
	    try {
            Product product = productMapper.selectByPrimaryKey(customer.getProductId());
            customer.setProduct(product);
            
            User creater = userMapper.selectByPrimaryKey(customer.getCreater());  
            if(creater != null) {
                creater.setPassword(null);
                creater.setSalt(null);
            }
            customer.setCreaterObject(creater);
            
            User manager = userMapper.selectByPrimaryKey(customer.getManagerId());
            if(manager != null) {
                manager.setPassword(null);
                manager.setSalt(null);
            }
            customer.setManager(manager);
        }catch (Exception e) {
        }
		return customer;
	}

	@Override
	public boolean updateByCustomerExampleSelective(Customer customer, CustomerExample customerExample) {
		if(customerMapper.updateByExampleSelective(customer, customerExample) > 0) {
		    return true;
		}else {
		    return false;
		}
	}

	@Override
	public boolean updateByCustomerExample(Customer customer, CustomerExample customerExample) {
		if(customerMapper.updateByExample(customer, customerExample) > 0) {
		    return true;
		}else {
            return false;
        }
	}

	@Override
	public boolean updateCustomerByPrimaryKeySelective(Customer customer) {
		if(customerMapper.updateByPrimaryKeySelective(customer) > 0) {
		    return true;
		}else {
            return false;
        }
	}

	@Override
	public boolean updateCustomerByPrimaryKey(Customer customer) {
		if(customerMapper.updateByPrimaryKey(customer) > 0) {
		    return true;
		}else {
		    return false;
		}
	}

}
