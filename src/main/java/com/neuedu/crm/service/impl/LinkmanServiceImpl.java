package com.neuedu.crm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.CustomerMapper;
import com.neuedu.crm.mapper.LinkmanMapper;
import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerExample;
import com.neuedu.crm.pojo.Linkman;
import com.neuedu.crm.pojo.LinkmanExample;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.pojo.CustomerExample.Criteria;
import com.neuedu.crm.service.ILinkmanService;
/**
 * 
 * @author malizhi
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class LinkmanServiceImpl implements ILinkmanService {

	@Autowired
	private LinkmanMapper linkmanMapper;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	/**
	 * 根据linkmanExample统计条数
	 * @author malizhi
	 * @param linkmanExample
	 * @return long
	 * @version 1.0
	 * @exception Nothing
	 * 
	 */
	@Override
	public long countByLinkmanExample(LinkmanExample linkmanExample){
		return linkmanMapper.countByExample(linkmanExample);
	}

	/**
	 * 根据linkmanExample删除Linkman
	 * @author malizhi
	 * @param linkmanExample
	 * @return boolean
	 * @version 1.0
	 * @exception Nothing
	 */
	@Override
    public boolean deleteByLinkmanExample(LinkmanExample linkmanExample){
    	int ret = linkmanMapper.deleteByExample(linkmanExample);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据主键id删除Linkman
     * @author malizhi
     * @param id
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean deleteLinkmanByPrimaryKey(Integer id){
	    
	    Linkman linkman = linkmanMapper.selectByPrimaryKey(id);
	    if(linkman.getLevel() == 0) {
	        return false;
	    }
	    
    	int ret = linkmanMapper.deleteByPrimaryKey(id);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 插入一条Linkman数据，如果参数linkman中字段为空，代表此字段插入为空
     * @author malizhi
     * @param record
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean insertLinkman(Linkman linkman){
    	int ret = linkmanMapper.insert(linkman);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 插入一条Linkman数据，如果参数linkman中字段为空，代表此字段为数据库默认值
     * @author malizhi
     * @param linkman
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean insertLinkmanSelective(Linkman linkman){
    	int ret = linkmanMapper.insertSelective(linkman);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据linkmanExample查找Linkman
     * @author malizhi
     * @param example
     * @return List<Model>
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public List<Linkman> selectByLinkmanExample(LinkmanExample linkmanExample){
    	return linkmanMapper.selectByExample(linkmanExample);
    }

    /**
     * 
     * @author malizhi
     * @param id
     * @return Linkman
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public Linkman selectLinkmanByPrimaryKey(Integer id){
	    //封装linkman信息
	    Linkman linkman = null;
	    try {
            linkman = linkmanMapper.selectByPrimaryKey(id);
            //封装客户信息
            linkman.setCustomer(customerMapper.selectByPrimaryKey(linkman.getCustomerId()));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    	return linkman;
    }

    /**
     * 更新Linkman，参数linkman中，如有有字段为空，代表不更新此字段，linkmanExample参数为where条件
     * @author malizhi
     * @param linkman
     * @param linkmanExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean updateByLinkmanExampleSelective(Linkman linkman, LinkmanExample linkmanExample){
    	int ret = linkmanMapper.updateByExampleSelective(linkman, linkmanExample);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 更新Linkman，参数linkman中，如有有字段为空，代表对应也更新为空，linkmanExample参数为where条件
     * @author malizhi
     * @param linkman
     * @param linkmanExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean updateByLinkmanExample(Linkman linkman, LinkmanExample linkmanExample){
    	int ret = linkmanMapper.updateByExample(linkman, linkmanExample);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据主键更新Linkman，参数linkman中，如果字段为空，代表不更新此字段
     * @author malizhi
     * @param linkman
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean updateLinkmanByPrimaryKeySelective(Linkman linkman){
    	int ret = linkmanMapper.updateByPrimaryKeySelective(linkman);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据主键更新Linkman，参数linkman中，如果字段为空，代表相应字段也更新为空
     * @author malizhi
     * @param linkman
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean updateLinkmanByPrimaryKey(Linkman linkman){
    	int ret = linkmanMapper.updateByPrimaryKey(linkman);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }


    @Override
    public List<Linkman> selectLinkmanByUser(User user) {
        List<Linkman> linkmans = new ArrayList<Linkman>();
        try {
            //创建客户模板类
            CustomerExample customerExample = new CustomerExample();
            //创建客户查询准则
            Criteria customerCriteria =  customerExample.createCriteria();
            
            //获取当前客户经理所拥有的客户以及其下所有的联系人
            if(user.getRoleId() == 1){
                customerCriteria.andManagerIdEqualTo(user.getId()); 
            }
            
            //查找属于该客户经理的所有客户
            List<Customer> customers = customerMapper.selectByExample(customerExample);
            //查找所有用户下的联系人
            for (Customer customer : customers) {
              //创建联系人模板类
                LinkmanExample linkmanExample = new LinkmanExample();
                //创建联系人查询准则
                com.neuedu.crm.pojo.LinkmanExample.Criteria linkmanCriteria = linkmanExample.createCriteria();
                linkmanCriteria.andCustomerIdEqualTo(customer.getId());
                for(Linkman linkman : linkmanMapper.selectByExample(linkmanExample)){
                    //为每一个联系人封装客户实体
                    linkman.setCustomer(customer);
                    linkmans.add(linkman);
                }
            }
        } catch (Exception e) {
           throw new RuntimeException();
        }
        return linkmans;
    }
	
}
