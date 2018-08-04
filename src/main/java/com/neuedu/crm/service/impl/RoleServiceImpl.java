package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.RoleMapper;
import com.neuedu.crm.pojo.Role;
import com.neuedu.crm.pojo.RoleExample;
import com.neuedu.crm.service.IRoleService;

/**
 * 
 * @author malizhi
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class RoleServiceImpl implements IRoleService {

    @Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 根据example统计role条数
	 * @author malizhi
	 * @param roleExample
	 * @return long
	 * @version 1.0
	 * @exception Nothing
	 */
    @Override
	public long countByRoleExample(RoleExample roleExample){
		return roleMapper.countByExample(roleExample);
	}

	/**
	 * 根据example删除role
	 * @author malizhi
	 * @param oleExample
	 * @return int
	 * @version 1.0
	 * @exception Nothing
	 */
    @Override
    public boolean deleteByRoleExample(RoleExample roleExample){
    	int ret = roleMapper.deleteByExample(roleExample);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据主键删除role
     * @author malizhi
     * @param id
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean deleteRoleByPrimaryKey(Integer id){
    	int ret = roleMapper.deleteByPrimaryKey(id);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 插入一条role，字段为空，插入相应的字段也为空
     * @author malizhi
     * @param role
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean insertRole(Role role){
    	int ret = roleMapper.insert(role);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 插入一条role，参数role中，如果字段为null，代表不插入此字段
     * @author malizhi
     * @param role
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean insertRoleSelective(Role role){
    	int ret = roleMapper.insertSelective(role);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据example查找role
     * @author malizhi
     * @param roleExample
     * @return List<Role>
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public List<Role> selectByRoleExample(RoleExample roleExample){
    	return roleMapper.selectByExample(roleExample);
    }

    /**
     * 根据主键id查找role
     * @author malizhi
     * @param id
     * @return Role
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public Role selectRoleByPrimaryKey(Integer id){
    	return roleMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新role，参数role中，如果某些字段为空，代表不更新此字段，example代表where条件
     * @author malizhi
     * @param record
     * @param example
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean updateByRoleExampleSelective(Role role,RoleExample roleExample){
    	int ret = roleMapper.updateByExampleSelective(role, roleExample);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 更新role，参数role中，如果某些字段为空，代表相应的字段也更新为空，example代表where条件
     * @author malizhi
     * @param role
     * @param roleExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean updateByRoleExample(Role role, RoleExample roleExample){
    	int ret = roleMapper.updateByExample(role, roleExample);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 更新role，传入的参数中，如果某些字段为空，代表不更新此字段
     * @author malizhi
     * @param role
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean updateRoleByPrimaryKeySelective(Role role){
    	int ret = roleMapper.updateByPrimaryKeySelective(role);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 更新role，传入的参数中，如果某些字段为空，代表相应字段也更新为空
     * @author malizhi
     * @param role
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    @Override
    public boolean updateRoleByPrimaryKey(Role role){
    	int ret = roleMapper.updateByPrimaryKey(role);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }
	
}
