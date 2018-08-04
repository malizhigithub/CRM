package com.neuedu.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.Permission;
import com.neuedu.crm.pojo.Role;
import com.neuedu.crm.pojo.RoleExample;
import com.neuedu.crm.pojo.RoleExample.Criteria;
import com.neuedu.crm.pojo.RolePermission;
import com.neuedu.crm.pojo.RolePermissionExample;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.IPermissionService;
import com.neuedu.crm.service.IRolePermissionService;
import com.neuedu.crm.service.IRoleService;
import com.neuedu.crm.utils.Operation;
import com.neuedu.crm.utils.RedisUtil;

/**
 * 
 * @author wanghaoyu
 *
 */
@Controller
@RequestMapping("/role")
public class RoleController {

	Logger logger = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	IRoleService roleService;

	@Autowired
	IRolePermissionService rolePermissionService;
	
	@Autowired
	IPermissionService permissionService;
	
	@Autowired
	RedisUtil redisUtil;

	/**
	 * 
	 * 描述：分页加条件查询Role
	 * 
	 * @author wanghaoyu
	 * @version 1.0
	 * @param page
	 * @param limit
	 * @param role
	 * @return Map<String,Object>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@Operation(name = "查询职位")
	@RequiresPermissions("4001")
	@RequestMapping("/findRoles")
	@ResponseBody
	public Map<String, Object> findRoles(Integer page, Integer limit, Role role) {
		Map<String, Object> map = new HashMap<String, Object>(16);

		// 创建角色模板类
		RoleExample roleExample = new RoleExample();
		// 创建查询准则
		Criteria criteria = roleExample.createCriteria();
		// 判断role中的条件
		if (role.getName() != null) {
			criteria.andNameLike("%" + role.getName() + "%");
		}
		// 分页数据合法性判断
		if (page == null || page <= 0) {
			page = 1;
		}
		if (limit == null || limit <= 0) {
			limit = 10;
		}
		// 偏移值，即从第几条数据开始查
		Long offset = new Long((page - 1) * limit);
		// 数据总条数
		Long count = roleService.countByRoleExample(roleExample);

		roleExample.setOffset(offset);
		roleExample.setLimit(limit);

		List<Role> roles = roleService.selectByRoleExample(roleExample);

		logger.info(roles.toString());

		// 封装数据，返回到前台
		map.put("data", roles);
		map.put("code", 0);
		map.put("msg", "success");
		map.put("count", count);
		return map;
	}

	/**
	 * 
	 * 描述：根据职位编号删除职位
	 * 
	 * @author wanghaoyu
	 * @version 1.0
	 * @param id
	 * @return Map<String,Object>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@Operation(name = "删除职位")
	@RequiresPermissions("4004")
	@RequestMapping("/deleteRole")
	@ResponseBody
	public Map<String, Object> deleteRole(Integer id) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		// 用于判断返回给前台的结果
		boolean success = false;
		// 删除结果判断
		if (roleService.deleteRoleByPrimaryKey(id)) {
			success = true;
		}
		map.put("success", success);
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * 描述：编辑职位
	 * 
	 * @author wanghaoyu
	 * @version 1.0
	 * @param role
	 * @return Map<String,Object>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@Operation(name = "编辑职位")
	@RequiresPermissions("4002")
	@RequestMapping("/editRole")
	@ResponseBody
	public Map<String, Object> editRole(Role role) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		// 用于判断返回给前台的结果
		boolean success = false;
		if (roleService.updateRoleByPrimaryKey(role)) {
			success = true;
		}
		map.put("success", success);
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * 描述：添加职位
	 * 
	 * @author wanghaoyu
	 * @version 1.0
	 * @param role
	 * @return Map<String,Object>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@Operation(name = "添加职位")
	@RequiresPermissions("4003")
	@RequestMapping("/addRole")
	@ResponseBody
	public Map<String, Object> addRole(Role role) {
		Map<String, Object> map = new HashMap<String, Object>(16);
		// 用于判断返回给前台的结果
		boolean success = false;
		if (roleService.insertRole(role)) {
			success = true;
		}
		map.put("success", success);
		map.put("code", 0);
		return map;
	}

	/**
	 * 
	 * 描述：查询角色所拥有的权限id
	 * 
	 * @author huangqingwen
	 * @version 1.0
	 * @param rolePermission
	 * @return Map<String,Object>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@Operation(name = "查询角色所拥有的权限id")
	@RequiresAuthentication
	@RequestMapping("/findRolePermissionId")
	@ResponseBody
	public Map<String, Object> findRolePermissionId(RolePermission rolePermission) {

		Map<String, Object> maps = new HashMap<String, Object>(16);

		// 1. 获取该角色id下的所有权限id
		RolePermissionExample example = new RolePermissionExample();
		example.createCriteria().andRoleIdEqualTo(rolePermission.getRoleId());
		List<RolePermission> list = rolePermissionService.selectByRolePermissionExample(example);
		maps.put("result", list);
		return maps;
	}

	/**
	 * 
	 * 描述：分配权限给特定的角色
	 * 
	 * @author huangqingwen
	 * @version 1.0
	 * @param permissionId，
	 *            roleId
	 * @return Map<String,Object>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@Operation(name = "分配权限")
	@RequiresPermissions("4005")
	@RequestMapping("allotPermission")
	@ResponseBody
	public Map<String, Object> allotPermission(HttpServletRequest request, @RequestParam(value = "permissionIds[]", defaultValue="") Integer[] permissionIds,
			@RequestParam(value = "roleId") Integer roleId) {

		Map<String, Object> maps = new HashMap<String, Object>(16);

		//从session中获取用户角色
        //User user = (User)request.getSession().getAttribute("user");
		
		int result = rolePermissionService.allotPermission(permissionIds, roleId);
		
		//权限发生改变，删除权限相关的缓存
		//删除相应角色菜单的缓存
		redisUtil.remove("roleMenu-"+roleId);
		//删除权限相关的缓存
		redisUtil.remove("rolePermission-"+roleId);

		
		if (permissionIds.length > 0) {
			if (result == permissionIds.length) {
				maps.put("code", 0);
			} else {
				maps.put("code", 200);
				maps.put("msg", "分配失败!");
				return maps;
			}
		}
		maps.put("code", 0);
		return maps;
	}
	
	/**
	 * 
	 * 描述：获取用户角色的权限菜单
	 * 
	 * @author huangqingwen
	 * @version 1.0
	 * @param request
	 * @return Map<String,Object>
	 * @exception Nothing
	 * @since 1.8
	 *
	 */
	@SuppressWarnings("unchecked")
    @Operation(name="获取用户角色的权限菜单")
	@RequiresAuthentication
	@RequestMapping("/getRolePermissionMenu")
	@ResponseBody
	public Map<String, Object> getRolePermissionMenu(HttpServletRequest request){
		
		Map<String, Object> maps = new HashMap<String, Object>(16);
		
		//从session中获取用户角色
		User user = (User)request.getSession().getAttribute("user");
		
		List<Permission> userPermissions = null;
		
		logger.info(user.toString());
		
	    //先从redis缓存中查找角色权限菜单
		String roleMenu = "roleMenu-";
		if(redisUtil.exists(roleMenu+user.getRoleId())) {
		    userPermissions = (List<Permission>) redisUtil.get("roleMenu-"+user.getRoleId());
		}else {	  
		    //如果没有在缓存中找到，则从数据库中查找
		    //获取权限树
	        List<Permission> permissions = permissionService.selectTreePermission();
	        
	        //查询该用户的角色所拥有的权限id
	        RolePermissionExample rolePermissionExample = new RolePermissionExample();
	        rolePermissionExample.createCriteria().andRoleIdEqualTo(user.getRoleId());
	        List<RolePermission> rolePermissions = rolePermissionService.selectByRolePermissionExample(rolePermissionExample);
	        
	        //将用户的权限id放到数组中
	        int size = rolePermissions.size();
	        Integer[] array = new Integer[size];
	        for(int i = 0 ; i < size ; i ++ ) {
	            array[i] = rolePermissions.get(i).getPermissionId();
	        }
	        
	        //过滤用户的权限
	        userPermissions = getUserPermission(permissions, array);
	        
	        //把查找到的数据存入到redis缓存中
	        redisUtil.set("roleMenu-"+user.getRoleId(), userPermissions, 1800L);
		}
		
		maps.put("permission", userPermissions);
		maps.put("code", 0);
		return maps;
	}
	
	
	/**
     * 过滤用户的菜单
     * @param permissions
     * @param array
     * @return
     * @author huangqingwen
     */
    public List<Permission> getUserPermission(List<Permission> permissions, Integer[] array){
    	if(permissions == null) {
    		return null;
    	}
    
    	for(int i=permissions.size()-1; i>=0; i--) {
    		Permission permission = permissions.get(i);
    		boolean flag = false;
    		for (Integer a : array) {
				if(permission.getId().equals(a)) {
					flag = true;
					permission.setChildPermission(getUserPermission(permission.getChildPermission(), array));
				}
			}
    		//把类型为功能的permission也移除掉
    		if(flag == false || permission.getType().equals(1)) { 
    			permissions.remove(i);
    		}
    	}
    	
    	return permissions;
    }
}
