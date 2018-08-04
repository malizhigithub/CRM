package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.CustomerTransferMapper;
import com.neuedu.crm.mapper.UserMapper;
import com.neuedu.crm.pojo.CustomerTransfer;
import com.neuedu.crm.pojo.CustomerTransferExample;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.ICustomerTransferService;
/**
 * 
 * @author WangHaoyu
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class CustomerTransferServiceImpl implements ICustomerTransferService{
	@Autowired
	private CustomerTransferMapper customerTransferMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public long countByCustomerTransferExample(CustomerTransferExample customerTransferExample) {
		return customerTransferMapper.countByExample(customerTransferExample);
	}

	@Override
	public boolean deleteByCustomerTransferExample(CustomerTransferExample customerTransferExample) {
		return customerTransferMapper.deleteByExample(customerTransferExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return customerTransferMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertCustomerTransfer(CustomerTransfer customerTransfer) {
		return customerTransferMapper.insert(customerTransfer) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(CustomerTransfer customerTransfer) {
		return customerTransferMapper.insertSelective(customerTransfer) > 0 ? true : false;
	}

	@Override
	public List<CustomerTransfer> selectByCustomerTransferExample(CustomerTransferExample customerTransferExample) {
		
	    List<CustomerTransfer> list = customerTransferMapper.selectByExample(customerTransferExample);
	    
	    for(CustomerTransfer transfer:list) {
	        try {
	            User oldManager = userMapper.selectByPrimaryKey(transfer.getOldManagerId());
	            if(oldManager != null) {
	                oldManager.setPassword(null);
	                oldManager.setSalt(null);
	            }
	            transfer.setOldManager(oldManager);
	            
	            User newManager = userMapper.selectByPrimaryKey(transfer.getNewManagerId());
	            if(newManager != null) {
	                newManager.setPassword(null);
	                newManager.setSalt(null);
	            }
	            transfer.setNewManager(newManager);
	        }catch (Exception e) {
            }
	    }
	    return list;
	}

	@Override
	public CustomerTransfer selectCustomerTransferByPrimaryKey(Integer id) {
	    CustomerTransfer transfer = customerTransferMapper.selectByPrimaryKey(id);
	    try {
            User oldManager = userMapper.selectByPrimaryKey(transfer.getOldManagerId());
            if(oldManager != null) {
                oldManager.setPassword(null);
                oldManager.setSalt(null);
            }
            transfer.setOldManager(oldManager);
            
            User newManager = userMapper.selectByPrimaryKey(transfer.getNewManagerId());
            if(newManager != null) {
                newManager.setPassword(null);
                newManager.setSalt(null);
            }
            transfer.setNewManager(newManager);
        }catch (Exception e) {
        }
		return transfer;
	}

	@Override
	public boolean updateByCustomerTransferExampleSelective(CustomerTransfer customerTransfer, CustomerTransferExample customerTransferExample) {
		return customerTransferMapper.updateByExampleSelective(customerTransfer, customerTransferExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByCustomerTransferExample(CustomerTransfer customerTransfer, CustomerTransferExample customerTransferExample) {
		return customerTransferMapper.updateByExample(customerTransfer, customerTransferExample) > 0 ? true : false;
	}

	@Override
	public boolean updateCustomerTransferByPrimaryKeySelective(CustomerTransfer customerTransfer) {
		return customerTransferMapper.updateByPrimaryKeySelective(customerTransfer) > 0 ? true : false;
	}

	@Override
	public boolean updateCustomerTransferByPrimaryKey(CustomerTransfer customerTransfer) {
		return customerTransferMapper.updateByPrimaryKey(customerTransfer) > 0 ? true : false ;
	}

}
