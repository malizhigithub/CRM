package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.Pager;
import com.neuedu.crm.pojo.Permission;
import com.neuedu.crm.pojo.PermissionExample;
/**
 * 
 * @author malizhi
 *
 */
public interface IPermissionService {
	
	/**
	 * 根据example统计permission条数
	 * @author malizhi
	 * @param permissionExample+
	 * @return long
	 * @version 1.0
	 * @exception Nothing
	 */
	public long countByPermissionExample(PermissionExample permissionExample);

	/**
	 * 根据example删除permission
	 * @author malizhi
	 * @param permissionExample
	 * @return boolean
	 * @version 1.0
	 * @exception Nothing
	 */
    public boolean deleteByPermissionExample(PermissionExample permissionExample);

    /**
     * 根据主键删除permission
     * @author malizhi
     * @param id
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean deletePermissionByPrimaryKey(Integer id);

    /**
     * 插入一条permission，如果字段为空，那么插入null
     * @author malizhi
     * @param permission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean insertPermission(Permission permission);

    /**
     * 插入一条permission，如果字段为空，插入的是数据库中字段的默认值
     * @author malizhi
     * @param permission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean insertPermissionSelective(Permission permission);

    /**
     * 根据example查找permission 分页
     * @author huangqingwen
     * @param permissionExample
     * @param pager
     * @return List<Permission>
     * @version 1.0
     * @exception Nothing
     */
    public List<Permission> selectByPermissionExample(PermissionExample permissionExample, Pager pager);

    /**
     * 根据example查找permission 
     * @author huangqingwen
     * @param permissionExample
     * @param pager
     * @return List<Permission>
     * @version 1.0
     * @exception Nothing
     */
    public List<Permission> selectByPermissionExample(PermissionExample permissionExample);

    
    /**
     * 根据主键id查找permission
     * @author malizhi
     * @param id
     * @return Permission
     * @version 1.0
     * @exception Nothing
     */
    public Permission selectPermissionByPrimaryKey(Integer id);

    /**
     * 更新permission，参数permission中如果某个字段为空代表不更新此字段，example为where条件
     * @author malizhi
     * @param permission
     * @param permissionExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateByPermissionExampleSelective(Permission permission,PermissionExample permissionExample);

    /**
     * 更新permission，参数permission中如果某个字段为空代表此字段也更新为空，example为where条件
     * @author malizhi
     * @param permission
     * @param permissionExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateByPermissionExample(Permission permission, PermissionExample permissionExample);

    /**
     * 根据主键id更新permission，参数permission中某些字段为空，代表不更新此字段
     * @author malizhi
     * @param permission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updatePermissionByPrimaryKeySelective(Permission permission);

    /**
     * 根据主键id更新permission，参数permission中如果有字段为空，代表更新为null
     * @author malizhi
     * @param permission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updatePermissionByPrimaryKey(Permission permission);
    
    /**
     * 查询权限树
     * @author huangqingwen
     * @param 
     * @return List<Permission>
     * @version 1.0
     * @exception Nothing
     */
    public List<Permission> selectTreePermission();
    
    /**
     * 描述：根据当前权限的id查询该权限下的所有子权限
     * @author HuangQingwen
     * @date 2018/07/24
     * @version 1.0
     * @param id
     * @return List<Permission>
     * @since 1.8
     *
     */
    public List<Permission> selectChildPermission(Integer id);
	
    /**
     * 描述：设置当前权限为顶级权限
     * @author HuangQingwen
     * @date 2018/07/24
     * @version 1.0
     * @param permission
     * @return boolean
     * @since 1.8
     *
     */
    public boolean updatePermissionSetTopPermission(Permission permission);
}
