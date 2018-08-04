package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.PermissionMapper;
import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.pojo.Permission;
import com.neuedu.crm.pojo.PermissionExample;
import com.neuedu.crm.service.IPermissionService;

/**
 * 
 * @author malizhi
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class PermissionServiceImpl implements IPermissionService {

	@Autowired
	private PermissionMapper permissionMapper;
	
	/**
	 * 根据example统计permission条数
	 * @author malizhi
	 * @param permissionExample+
	 * @return long
	 * @version 1.0
	 * @exception Nothing
	 */
	@Override
	public long countByPermissionExample(PermissionExample permissionExample){
		return permissionMapper.countByExample(permissionExample);
	}

	/**
	 * 根据example删除permission
	 * @author malizhi
	 * @param permissionExample
	 * @return boolean
	 * @version 1.0
	 * @exception Nothing
	 */
	@Override
    public boolean deleteByPermissionExample(PermissionExample permissionExample){
    	int ret = permissionMapper.deleteByExample(permissionExample);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据主键删除permission
     * @author malizhi
     * @param id
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean deletePermissionByPrimaryKey(Integer id){
    	int ret = permissionMapper.deleteByPrimaryKey(id);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 插入一条permission，如果字段为空，那么插入null
     * @author malizhi
     * @param permission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean insertPermission(Permission permission){
    	int ret = permissionMapper.insert(permission);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 插入一条permission，如果字段为空，插入的是数据库中字段的默认值
     * @author malizhi
     * @param permission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean insertPermissionSelective(Permission permission){
    	int ret = permissionMapper.insertSelective(permission);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据example查找permission
     * @author huangqingwen
     * @param permissionExample
     * @return List<Permission>
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public List<Permission> selectByPermissionExample(PermissionExample permissionExample, Pager pager){
    	permissionExample.setLimit(pager.getPageSize());
    	permissionExample.setOffset(new Long(pager.getOffset()));
    	permissionExample.setOrderByClause("type asc");
    	return permissionMapper.selectByExample(permissionExample);
    }

    /**
     * 根据主键id查找permission
     * @author malizhi
     * @param id
     * @return Permission
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public Permission selectPermissionByPrimaryKey(Integer id){
    	return permissionMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新permission，参数permission中如果某个字段为空代表不更新此字段，example为where条件
     * @author malizhi
     * @param permission
     * @param permissionExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean updateByPermissionExampleSelective(Permission permission,PermissionExample permissionExample){
    	int ret = permissionMapper.updateByExampleSelective(permission, permissionExample);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 更新permission，参数permission中如果某个字段为空代表此字段也更新为空，example为where条件
     * @author malizhi
     * @param permission
     * @param permissionExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean updateByPermissionExample(Permission permission, PermissionExample permissionExample){
    	int ret = permissionMapper.updateByExample(permission, permissionExample);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据主键id更新permission，参数permission中某些字段为空，代表不更新此字段
     * @author malizhi
     * @param permission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean updatePermissionByPrimaryKeySelective(Permission permission){
    	int ret = permissionMapper.updateByPrimaryKeySelective(permission);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据主键id更新permission，参数permission中如果有字段为空，代表更新为null
     * @author malizhi
     * @param permission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
	@Override
    public boolean updatePermissionByPrimaryKey(Permission permission){
    	int ret = permissionMapper.updateByPrimaryKey(permission);
    	if(ret!=0){
    		return true;
    	}else{
    		return false;
    	}
    }

	@Override
	public List<Permission> selectTreePermission() {
		return permissionMapper.selectTreePermission();
	}

	@Override
	public List<Permission> selectChildPermission(Integer id) {
		return permissionMapper.selectChildPermission(id);
	}

	@Override
	public boolean updatePermissionSetTopPermission(Permission permission) {
		
		return permissionMapper.setTopPermission(permission) > 0 ? true : false;
	}

	@Override
	public List<Permission> selectByPermissionExample(PermissionExample permissionExample) {
		
		return permissionMapper.selectByExample(permissionExample);
	}
	
}
