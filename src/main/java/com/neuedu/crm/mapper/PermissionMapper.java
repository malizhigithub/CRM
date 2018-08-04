package com.neuedu.crm.mapper;

import java.util.List;

import com.neuedu.crm.pojo.Permission;
import com.neuedu.crm.pojo.PermissionExample;

/**
 * PermissionMapper继承基类
 * @author MybatisGenerator
 */
public interface PermissionMapper extends MyBatisBaseDao<Permission, Integer, PermissionExample> {
	
	/**
	 * 查询父级权限
	 * @param pid
	 * @return
	 * @author huangqingwen
	 */
	public Permission selectParentPermissionByPid(Integer pid);
	
	/**
	 * 查询权限树结构
	 * @param 
	 * @return  List<Permission>
	 * @author huangqingwen
	 */
	public List<Permission> selectTreePermission();
	
	/**
	* 描述：根据当前id查询改id下的所有子权限
    * @Title: selectChildPermission
    * @Description: TODO(根据当前id查询改id下的所有子权限)
    * @param @param id
    * @param @return    参数
    * @return List<Permission>    返回类型
    * @throws
    * @author huangqingwen
	*/
	public List<Permission> selectChildPermission(Integer id);

	/**
	* 描述： 设置当前权限为顶级权限
    * @Title: selectChildPermission
    * @Description: TODO(设置当前权限为顶级权限)
    * @param permission
    * @return int
    * @throws
    * @author huangqingwen
	*/
	public int setTopPermission(Permission permission);
}