package com.neuedu.crm.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.CustomerMapper;
import com.neuedu.crm.mapper.SaleOpportunityMapper;
import com.neuedu.crm.mapper.UserMapper;
import com.neuedu.crm.pojo.Customer;
import com.neuedu.crm.pojo.CustomerExample;
import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.pojo.SaleOpportunity;
import com.neuedu.crm.pojo.SaleOpportunityExample;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.pojo.UserExample;
import com.neuedu.crm.pojo.SaleOpportunityExample.Criteria;
import com.neuedu.crm.service.ISaleOpportunityService;

/**
 * 
 * @author WangHaoyu
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class SaleOpportunityServiceImpl implements ISaleOpportunityService {

	@Autowired
	private SaleOpportunityMapper saleOpportunityMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private UserMapper userMapper;
	
	org.slf4j.Logger logger = LoggerFactory.getLogger(SaleOpportunityServiceImpl.class);
	
	@Override
	public long countBySaleOpportunityExample(SaleOpportunityExample saleOpportunityExample) {
		return saleOpportunityMapper.countByExample(saleOpportunityExample);
	}

	@Override
	public boolean deleteBySaleOpportunityExample(SaleOpportunityExample saleOpportunityExample) {
		return saleOpportunityMapper.deleteByExample(saleOpportunityExample) > 0 ? true : false;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		return saleOpportunityMapper.deleteByPrimaryKey(id) > 0 ? true : false;
	}

	@Override
	public boolean insertSaleOpportunity(SaleOpportunity saleOpportunity) {
		return saleOpportunityMapper.insert(saleOpportunity) > 0 ? true : false;
	}

	@Override
	public boolean insertSelective(SaleOpportunity saleOpportunity) {
		return saleOpportunityMapper.insertSelective(saleOpportunity) > 0 ? true : false;
	}

	@Override
	public List<SaleOpportunity> selectBySaleOpportunityExample(SaleOpportunityExample saleOpportunityExample) {
		return saleOpportunityMapper.selectByExample(saleOpportunityExample);
	}

	@Override
	public List<SaleOpportunity> selectBySaleOpportunitySelectiveAndPager(SaleOpportunity saleOpportunity,Pager pager,HttpServletRequest request){
		SaleOpportunityExample opportunityExample = new SaleOpportunityExample();
		Criteria criteria = opportunityExample.createCriteria();
		
		//where条件
		if(saleOpportunity.getId()!=null){
			criteria.andIdEqualTo(saleOpportunity.getId());
		}
		//成功机率
		if(saleOpportunity.getSuccess()!=null){
			criteria.andSuccessGreaterThan(saleOpportunity.getSuccess());
		}
		//是否删除
		if(saleOpportunity.getDeleteStatus()!=null){
			criteria.andDeleteStatusEqualTo(saleOpportunity.getDeleteStatus());
		}
		//销售状态
		if(saleOpportunity.getStatus()!=null){
			criteria.andStatusLike("%"+saleOpportunity.getStatus()+"%");
		}
		//客户姓名
		if(saleOpportunity.getCustomer()!=null&&saleOpportunity.getCustomer().getName()!=null){
			//获取客户姓名对应的客户Id
			//logger.info(saleOpportunity.getCustomer().getName().toString());
			CustomerExample customerExample = new CustomerExample();
			com.neuedu.crm.pojo.CustomerExample.Criteria criteria2 = customerExample.createCriteria();
			criteria2.andNameLike("%"+saleOpportunity.getCustomer().getName()+"%");
			List<Customer> list = customerMapper.selectByExample(customerExample);
			//把Id集合交给example
			List<Integer> arrayList = new ArrayList<>();
			for(Customer c:list){
				arrayList.add(c.getId());
			}
			if(arrayList.size()==0){
				return null;
			}
			criteria.andCustomerIdIn(arrayList);
		}
		//创建人
		/*if(saleOpportunity.getCreaterUser()!=null&&saleOpportunity.getCreaterUser().getRealName()!=null){
			//获取创建人对应的UserId
			UserExample userExample = new UserExample();
			com.neuedu.crm.pojo.UserExample.Criteria criteria3 = userExample.createCriteria();
			criteria3.andRealNameLike("%"+saleOpportunity.getCreaterUser().getRealName()+"%");
			List<User> list = userMapper.selectByExample(userExample);
			//把Id集合交给example
			List<Integer> arrayList = new ArrayList<>();
			for(User user:list){
				arrayList.add(user.getId());
			}
			if(arrayList.size()==0){
				return null;
			}
		}*/
		
		// 被指派人
		if (saleOpportunity.getManager()!=null&&saleOpportunity.getManager().getRealName() != null) {
			// 获取被指派人对应的UserId
			UserExample userExample = new UserExample();
			com.neuedu.crm.pojo.UserExample.Criteria criteria4 = userExample.createCriteria();
			criteria4.andRealNameLike("%" + saleOpportunity.getManager().getRealName() + "%");
			List<User> list = userMapper.selectByExample(userExample);
			// 把Id集合交给example
			List<Integer> arrayList = new ArrayList<>();
			for (User user : list) {
				arrayList.add(user.getId());
			}
			//如果不判断为空，sql语法会报异常
			if(arrayList.size()==0){
				return null;
			}
			criteria.andManagerIdIn(arrayList);
		}
		
		
		
		/*if(saleOpportunity.getCustomerId()!=null){
			criteria.andCustomerIdEqualTo(saleOpportunity.getCustomerId());
		}
		if(saleOpportunity.getContactId()!=null){
			criteria.andContactIdEqualTo(saleOpportunity.getCustomerId());
		}
		if(saleOpportunity.getContactPhone()!=null){
			criteria.andContactPhoneEqualTo(saleOpportunity.getContactPhone());
		}*/
		
		//不显示已删除(软删除)的销售机会   0：未删除  1：已删除
		criteria.andDeleteStatusNotEqualTo(1);
		//如果是主管，能查看所有的销售机会，如果是经理，只能查看自己的销售机会
		User u = (User) request.getSession().getAttribute("user");
		if(u.getRoleId()==1){
			criteria.andManagerIdEqualTo(u.getId());
		}
		
		//客户不能为空
		criteria.andCustomerIdIsNotNull();
		
		
		//分页条件
		opportunityExample.setLimit(pager.getPageSize());
		opportunityExample.setOffset(new Long(pager.getOffset()));
		Long count = this.countBySaleOpportunityExample(opportunityExample);
		pager.setTotal(count.intValue());
		
		return this.selectBySaleOpportunityExample(opportunityExample);
		
		/*//如果客户被删除，对应的销售机会也要被删除
		List<SaleOpportunity> list2 = new ArrayList<>();
		list2 = list;
		
		int i = 0;
		for(SaleOpportunity o:list2){
			if(o.getCustomerId()==null){
				list.remove(i);
				o.setDeleteStatus(1);
				this.updateSaleOpportunityByPrimaryKey(o);
			}
			i++;
		}
		
		return list;*/
	}
	
	
	@Override
	public SaleOpportunity selectSaleOpportunityByPrimaryKey(Integer id) {
		return saleOpportunityMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateBySaleOpportunityExampleSelective(SaleOpportunity saleOpportunity, SaleOpportunityExample saleOpportunityExample) {
		return saleOpportunityMapper.updateByExampleSelective(saleOpportunity, saleOpportunityExample) > 0 ? true : false;
	}

	@Override
	public boolean updateBySaleOpportunityExample(SaleOpportunity saleOpportunity, SaleOpportunityExample saleOpportunityExample) {
		return saleOpportunityMapper.updateByExample(saleOpportunity, saleOpportunityExample) > 0 ? true : false;
	}

	@Override
	public boolean updateSaleOpportunityByPrimaryKeySelective(SaleOpportunity saleOpportunity) {
		return saleOpportunityMapper.updateByPrimaryKeySelective(saleOpportunity) > 0 ? true : false;
	}

	@Override
	public boolean updateSaleOpportunityByPrimaryKey(SaleOpportunity saleOpportunity) {
		return saleOpportunityMapper.updateByPrimaryKey(saleOpportunity) > 0 ? true : false ;
	}

	/** (non-Javadoc)
	 * @see com.neuedu.crm.service.ISaleOpportunityService#deleteSaleOpportunitiesByPrimaryKey(java.lang.String)
	 */
	@Override
	public boolean deleteSaleOpportunitiesByPrimaryKey(String ids) {
            //先把ids字符串根据规则分割开
            String[] idsStrings = ids.split("-");
            for (String idString : idsStrings) {
                Integer id = Integer.parseInt(idString);
                SaleOpportunity saleOpportunity = new SaleOpportunity();
                saleOpportunity.setId(id);
                saleOpportunity.setDeleteStatus(1);
                //如果删除失败，则直接抛出异常
                if(saleOpportunityMapper.updateByPrimaryKeySelective(saleOpportunity) < 1){
                	return false;
                	}
            	}
        return true;
	}

}
