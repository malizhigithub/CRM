package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.Role;
import com.neuedu.crm.pojo.RoleExample;
/**
 * 
 * @author MaLizhi
 * @date 2018/07/24
 */
public interface IRoleService {

	/**
	 * 根据example统计role条数
	 * @author malizhi
	 * @param roleExample
	 * @return long
	 * @version 1.0
	 * @exception Nothing
	 */
	public long countByRoleExample(RoleExample roleExample);

	/**
	 * 根据example删除role
	 * @author malizhi
	 * @param roleExample
	 * @return boolean
	 * @version 1.0
	 * @exception Nothing
	 */
    public boolean deleteByRoleExample(RoleExample roleExample);

    /**
     * 根据主键删除role
     * @author malizhi
     * @param id
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean deleteRoleByPrimaryKey(Integer id);

    /**
     * 插入一条role，字段为空，插入相应的字段也为空
     * @author malizhi
     * @param role
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean insertRole(Role role);

    /**
     * 插入一条role，参数role中，如果字段为null，代表不插入此字段
     * @author malizhi
     * @param role
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean insertRoleSelective(Role role);

    /**
     * 根据example查找role
     * @author malizhi
     * @param roleExample
     * @return List<Role>
     * @version 1.0
     * @exception Nothing
     */
    public List<Role> selectByRoleExample(RoleExample roleExample);

    /**
     * 根据主键id查找role
     * @author malizhi
     * @param id
     * @return Role
     * @version 1.0
     * @exception Nothing
     */
    public Role selectRoleByPrimaryKey(Integer id);

    /**
     * 更新role，参数role中，如果某些字段为空，代表不更新此字段，example代表where条件
     * @author malizhi
     * @param role
     * @param roleExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateByRoleExampleSelective(Role role,RoleExample roleExample);

    /**
     * 更新role，参数role中，如果某些字段为空，代表相应的字段也更新为空，example代表where条件
     * @author malizhi
     * @param role
     * @param roleExample
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateByRoleExample(Role role, RoleExample roleExample);

    /**
     * 更新role，传入的参数中，如果某些字段为空，代表不更新此字段
     * @author malizhi
     * @param role
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateRoleByPrimaryKeySelective(Role role);

    /**
     * 更新role，传入的参数中，如果某些字段为空，代表相应字段也更新为空
     * @author malizhi
     * @param role
     * @return boolean
     * @version 1.0
     * @exception Nothing
     */
    public boolean updateRoleByPrimaryKey(Role role);
	
}
