package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.CustomerMapper;
import com.neuedu.crm.mapper.FollowUpMapper;
import com.neuedu.crm.pojo.FollowUp;
import com.neuedu.crm.pojo.FollowUpExample;
import com.neuedu.crm.service.IFollowUpService;
import com.neuedu.crm.service.IUserService;
/**
 * 
 * @author HuangWanzong
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class FollowUpServiceImpl implements IFollowUpService {

	@Autowired
	private FollowUpMapper followUpMapper;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public long countByFollowUpExample(FollowUpExample followUpExample) {
		return followUpMapper.countByExample(followUpExample);
	}

	@Override
	public boolean deleteByFollowUpExample(FollowUpExample followUpExample) {
		return followUpMapper.deleteByExample(followUpExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return followUpMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertFollowUp(FollowUp followUp) {
		return followUpMapper.insert(followUp) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(FollowUp followUp) {
		return followUpMapper.insertSelective(followUp) > 0 ? true : false;
	}

	@Override
	public List<FollowUp> selectByFollowUpExample(FollowUpExample followUpExample) {
	    List<FollowUp> list = followUpMapper.selectByExample(followUpExample);
	    for(FollowUp followUp : list) {
	        if(followUp.getCustomerId() != null) {
                followUp.setCustomer(customerMapper.selectByPrimaryKey(followUp.getCustomerId()));
            }
            if(followUp.getManagerId() != null) {
                followUp.setManager(userService.findById(followUp.getManagerId()));
            } 
	    }
		return list;
	}

	@Override
	public FollowUp selectFollowUpByPrimaryKey(Integer id) {
	    FollowUp followUp = followUpMapper.selectByPrimaryKey(id);
	    if(followUp != null) {
	        if(followUp.getCustomerId() != null) {
	            followUp.setCustomer(customerMapper.selectByPrimaryKey(followUp.getCustomerId()));
	        }
	        if(followUp.getManagerId() != null) {
	            followUp.setManager(userService.findById(followUp.getManagerId()));
	        }
	    }
		return followUp;
	}

	@Override
	public boolean updateByFollowUpExampleSelective(FollowUp followUp, FollowUpExample followUpExample) {
		return followUpMapper.updateByExampleSelective(followUp, followUpExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByFollowUpExample(FollowUp followUp, FollowUpExample followUpExample) {
		return followUpMapper.updateByExample(followUp, followUpExample) > 0 ? true : false;
	}

	@Override
	public boolean updateFollowUpByPrimaryKeySelective(FollowUp followUp) {
		return followUpMapper.updateByPrimaryKeySelective(followUp) > 0 ? true : false;
	}

	@Override
	public boolean updateFollowUpByPrimaryKey(FollowUp followUp) {
		return followUpMapper.updateByPrimaryKey(followUp) > 0 ? true : false ;
	}

}
