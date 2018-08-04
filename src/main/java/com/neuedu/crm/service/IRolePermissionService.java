package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.RolePermission;
import com.neuedu.crm.pojo.RolePermissionExample;
/**
 * 
 * @author MaLizhi
 * @date 2018/07/24
 */
public interface IRolePermissionService {

	/**
	 * 根据RolePermissionExample统计条数
	 * @author malizhi
	 * @param rolePermissionExample
	 * @return long
	 * @version 1.0
	 * @exception Nothing
	 */
    public long countByRolePermissionExample(RolePermissionExample rolePermissionExample);

    /**
     * 根据RolePermissionExample删除rolePermission
     * @author malizhi
     * @param rolePermissionExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean deleteByRolePermissionExample(RolePermissionExample rolePermissionExample);

    /**
     * 根据主键id删除rolePermission
     * @author malizhi
     * @param id
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean deleteRolePermissionByPrimaryKey(Integer id);

    /**
     * 插入一条数据，如果字段为空，插入的字段也为空
     * @author malizhi
     * @param rolePermission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean insertRolePermission(RolePermission rolePermission);

    /**
     * 插入一条数据，如果参数rolePermission中字段为空，代表字段为数据库默认值
     * @author malizhi
     * @param rolePermission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean insertRolePermissionSelective(RolePermission rolePermission);

    /**
     * 根据RolePermissionExample查找
     * @author malizhi
     * @param rolePermissionExample
     * @return List<RolePermission>
     * @version 1.0
     * @exception Nothing
     */
    public List<RolePermission> selectByRolePermissionExample(RolePermissionExample rolePermissionExample);

    /**
     * 根据主键id查找RolePermission
     * @author malizhi
     * @param id
     * @return RolePermission
     * @version 1.0
     * @exception Nothing
     */
    public RolePermission selectRolePermissionByPrimaryKey(Integer id);

    /**
     * 更新RolePermission，如果传入的参数rolePermission中字段为空，代表不更新此字段，RolePermissionExample为where条件
     * @author malizhi
     * @param rolePermission
     * @param rolePermissionExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateByRolePermissionExampleSelective(RolePermission rolePermission, RolePermissionExample rolePermissionExample);

    /**
     * 更新RolePermission，如果传入的参数rolePermission中字段为空，代表此字段也更新为空，RolePermissionExample为where条件
     * @author malizhi
     * @param rolePermission
     * @param rolePermissionExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateByRolePermissionExample(RolePermission rolePermission, RolePermissionExample rolePermissionExample);

    /**
     * 更新RolePermission，如果传入的参数rolePermission中字段为空，代表不更新此字段
     * @author malizhi
     * @param rolePermission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateRolePermissionByPrimaryKeySelective(RolePermission rolePermission);

    /**
     * 更新RolePermission,如果传入的参数rolePermission中字段为空，那么相应字段也更新为空
     * @author malizhi
     * @param rolePermission
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateRolePermissionByPrimaryKey(RolePermission rolePermission);

    
    /**
     * 描述：分配权限给角色
     * @author HuangQingwen
     * @date 2018/07/24
     * @version 1.0
     * @param permissionIds
     * @param roleId
     * @return int
     * @since 1.8
     *
     */
	public int allotPermission(Integer[] permissionIds, Integer roleId);
	
}
