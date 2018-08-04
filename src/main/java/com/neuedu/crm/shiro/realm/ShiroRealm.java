package com.neuedu.crm.shiro.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.neuedu.crm.pojo.Permission;
import com.neuedu.crm.pojo.PermissionExample;
import com.neuedu.crm.pojo.Role;
import com.neuedu.crm.pojo.RolePermission;
import com.neuedu.crm.pojo.RolePermissionExample;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.IPermissionService;
import com.neuedu.crm.service.IRolePermissionService;
import com.neuedu.crm.service.IRoleService;
import com.neuedu.crm.service.IUserService;
import com.neuedu.crm.utils.RedisUtil;



/**
 * @author wanghaoyu
 *
 */
public class ShiroRealm extends AuthorizingRealm{

    private Logger logger = LoggerFactory.getLogger(ShiroRealm.class);
    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;
    @Autowired
    private IRolePermissionService rolePermissionService;
    @Autowired
    RedisUtil redisUtil;
    
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取当前登陆用户的账号
        String account = SecurityUtils.getSubject().getPrincipal().toString();
        if (account != null && !"".equals(account) ) {
            
            //从session域中获取当前登陆的用户对象
            User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
            
            //从redis缓存中查询是否已经有该info
            SimpleAuthorizationInfo info = (SimpleAuthorizationInfo)redisUtil.get("rolePermission-"+user.getRoleId());
            //如果info为空，则从数据库查询
            if(info == null){
                //权限信息对象info,用来存放查出的用户的所有的角色(role)及权限(permission)
                info = new SimpleAuthorizationInfo();
                
                //用于存放角色
                Set<String> roles = new HashSet<String>();
                //用于存放权限
                Set<String> permissions = new HashSet<String>();
                
                //查找角色
                Role role = roleService.selectRoleByPrimaryKey(user.getRoleId());
                //角色添加到角色集合中
                roles.add(role.getName());
                
                //查找该角色所拥有的权限ID
                RolePermissionExample rolePermissionExample = new RolePermissionExample();
                rolePermissionExample.createCriteria().andRoleIdEqualTo(role.getId());
                List<RolePermission> rolePermissions = rolePermissionService.selectByRolePermissionExample(rolePermissionExample);
                
                List<Integer> permissionIds = new ArrayList<Integer>();
                for (RolePermission rolePermission : rolePermissions) {
                    permissionIds.add(rolePermission.getPermissionId());
                }
                
                //根据权限ID获取权限对象
                PermissionExample permissionExample = new PermissionExample();
                permissionExample.createCriteria().andIdIn(permissionIds);
                List<Permission> list = permissionService.selectByPermissionExample(permissionExample);
                
                for (Permission permission2 : list) {
                    //把权限的url添加到权限集合中
                    if(permission2.getCode() != null && !"".equals(permission2.getCode())) {
                        permissions.add(permission2.getCode());
                    }  
                }
                
                
                logger.info("======角色："+ roles.toString());
                logger.info("======权限："+ permissions.toString());
                
                info.addRoles(roles);
                info.addStringPermissions(permissions);
                //把info存入redis缓存中,缓存时间为1800秒，30分钟
                redisUtil.set("rolePermission-" + user.getRoleId(), info, 1800L);
            }
            return info;
        }
        return null;
    }
    
    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = (String) token.getPrincipal();
        User user = userService.findByAccount(account);
        if(user == null){
            //抛出没找到账号异常
            throw new UnknownAccountException();
        //如果账号的状态为锁定的话
        }else if(user.getStatus() == 1){
            //抛出账号被锁定异常
            throw new LockedAccountException();
        }
        String salt2 = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                account,
                user.getPassword(),
                getName()
                );
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(account+salt2));
        return authenticationInfo;
    }

}
