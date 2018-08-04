package com.neuedu.crm.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.ServiceMapper;
import com.neuedu.crm.mapper.ServiceTransferMapper;
import com.neuedu.crm.mapper.UserMapper;
import com.neuedu.crm.pojo.ServiceTransfer;
import com.neuedu.crm.pojo.ServiceTransferExample;
import com.neuedu.crm.service.IServiceTransferService;

/**
 * 
 * @author WangHaoyu
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class ServiceTransferServiceImpl implements IServiceTransferService {
	
	@Autowired
	private ServiceTransferMapper serviceTransferMapper;
	
	@Autowired
	private ServiceMapper serviceMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public long countByServiceTransferExample(ServiceTransferExample serviceTransferExample) {
		return serviceTransferMapper.countByExample(serviceTransferExample);
	}

	@Override
	public boolean deleteByServiceTransferExample(ServiceTransferExample serviceTransferExample) {
		return serviceTransferMapper.deleteByExample(serviceTransferExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return serviceTransferMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertServiceTransfer(ServiceTransfer serviceTransfer) {
	    boolean success = false;
	    try {
	        //插入新的服务转移记录，同时更新服务数据的处理人
	        serviceTransfer.setTime(LocalDateTime.now());
	        com.neuedu.crm.pojo.Service service = new com.neuedu.crm.pojo.Service();
	        service.setId(serviceTransfer.getServiceId());
	        service.setHandler(serviceTransfer.getNewManagerId());
	        serviceTransferMapper.insert(serviceTransfer);
	        serviceMapper.updateByPrimaryKeySelective(service);
	        success = true;
        } catch (Exception e) {
            throw new RuntimeException();
        }  
		return success;
	}

	@Override
	public boolean insertSelective(ServiceTransfer serviceTransfer) {
		return serviceTransferMapper.insertSelective(serviceTransfer) > 0 ? true : false;
	}

	@Override
	public List<ServiceTransfer> selectByServiceTransferExample(ServiceTransferExample serviceTransferExample) {
		List<ServiceTransfer> serviceTransfers = serviceTransferMapper.selectByExample(serviceTransferExample);
        //对数据进行二次封装
		for (ServiceTransfer serviceTransfer : serviceTransfers) {
		   serviceTransfer.setOldManager(userMapper.selectByPrimaryKey(serviceTransfer.getOldManagerId()));
		   serviceTransfer.setNewManager(userMapper.selectByPrimaryKey(serviceTransfer.getNewManagerId()));
        }
		return serviceTransfers;
	}

	@Override
	public ServiceTransfer selectServiceTransferByPrimaryKey(Integer id) {
		return serviceTransferMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateByServiceTransferExampleSelective(ServiceTransfer serviceTransfer, ServiceTransferExample serviceTransferExample) {
		return serviceTransferMapper.updateByExampleSelective(serviceTransfer, serviceTransferExample) > 0 ? true : false;
	}

	@Override
	public boolean updateByServiceTransferExample(ServiceTransfer serviceTransfer, ServiceTransferExample serviceTransferExample) {
		return serviceTransferMapper.updateByExample(serviceTransfer, serviceTransferExample) > 0 ? true : false;
	}

	@Override
	public boolean updateServiceTransferByPrimaryKeySelective(ServiceTransfer serviceTransfer) {
		return serviceTransferMapper.updateByPrimaryKeySelective(serviceTransfer) > 0 ? true : false;
	}

	@Override
	public boolean updateServiceTransferByPrimaryKey(ServiceTransfer serviceTransfer) {
		return serviceTransferMapper.updateByPrimaryKey(serviceTransfer) > 0 ? true : false ;
	}

}