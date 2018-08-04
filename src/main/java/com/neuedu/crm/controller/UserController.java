/**
 * 
 */
package com.neuedu.crm.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.Role;
import com.neuedu.crm.pojo.RoleExample;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.pojo.UserExample;
import com.neuedu.crm.pojo.UserExample.Criteria;
import com.neuedu.crm.service.IRoleService;
import com.neuedu.crm.service.IUserService;
import com.neuedu.crm.utils.MailUtil;
import com.neuedu.crm.utils.Operation;
import com.neuedu.crm.utils.RandomStringUtil;
import com.neuedu.crm.utils.RedisUtil;

/**
 * @author wanghaoyu
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    IUserService userService;
    
    @Autowired
    IRoleService roleService;
    
    @Autowired
    RedisUtil redisUtil;
    
    @Autowired
    MailUtil mailUtil;
    
    Logger logger = LoggerFactory.getLogger(UserController.class);
    
    /**
     * 描述：查找用户
     * @param page
     * @param limit
     * @param user
     * @return
     */
    @Operation(name="查找用户")
    @RequiresPermissions("1001")
    @RequestMapping("/findUser")
    @ResponseBody
    public Map<String, Object> findUser(Integer page,Integer limit, User user){
        
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        //创建用户模板类
        UserExample userExample = new UserExample();
        //创建查询准则
        Criteria criteria = userExample.createCriteria();
        if(page == null || page <= 0) {
            page = 1;
        }
        if(limit == null || limit <= 0) {
            limit = 10;
        }
        
        //判断user类的条件是否为空
        if(user.getRealName() != null) {
             criteria.andRealNameLike("%"+user.getRealName()+"%");
        }
        
        if(user.getRoleId() != null) {
            logger.info("获得到的角色编号为：" + user.getRoleId());
            criteria.andRoleIdEqualTo(user.getRoleId());
        }
        
        if(user.getAccount() != null) {
            criteria.andAccountLike("%"+user.getAccount()+"%");
        }
        
        Long offset = new Long((page - 1) * limit);
        
        Long count = userService.countByExample(userExample);
        
        userExample.setLimit(limit);
        userExample.setOffset(offset);
        
        List<User> list = userService.findByExample(userExample);
        
        logger.info(list.toString());
        
        map.put("data", list);
        map.put("code", 0);
        map.put("msg", "success");
        map.put("count", count);
        return map;
    }
    
    /**
     * 
     * 描述：根据编号查找用户
     * @author wanghaoyu
     * @version 1.0
     * @param id
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="根据编号查找用户")
    @RequiresAuthentication
    @RequestMapping("/findUserById")
    @ResponseBody
    public Map<String, Object> findUserById(Integer id){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        String msg = "";
        User user = userService.findById(id);
        if(user != null){
             user.setPassword(null);
             user.setSalt(null);
            success = true;
        }else{
            msg = "读取用户数据出错，请稍后再试！";
        }
        map.put("success", success);
        map.put("user", user);
        map.put("msg",msg);
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：根据用户ID删除用户
     * @author wanghaoyu
     * @version 1.0
     * @param id 用户编号
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="删除用户")
    @RequiresPermissions("1004")
    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String, Object> deleteUser(Integer id){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        //删除成功
        if(userService.deleteById(id) == true) {
            success = true;
        }
        map.put("success", success);
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：编辑用户
     * @author wanghaoyu
     * @version 1.0
     * @param user 要编辑的用户信息
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="修改用户")
    @RequiresPermissions(value={"1002","13002"}, logical=Logical.OR)
    @RequestMapping("/editUser")
    @ResponseBody
    public Map<String, Object> editUser(User user){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        String msg = "";
        //修改成功
        if(userService.edit(user) == true) {
            success = true;
            msg = "修改成功！";
        }else {
            msg = "修改失败！";
        }
        map.put("success", success);
        map.put("msg", msg);
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：添加用户
     * @author wanghaoyu
     * @version 1.0
     * @param user 要添加的用户信息
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="添加用户")
    @RequiresPermissions("1003")
    @RequestMapping("/addUser")
    @ResponseBody
    public Map<String, Object> addUser(User user){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        
        //设置user的初始密码为123456
        user.setPassword("123456");
        
        //添加
        if(userService.save(user) == true) {
            success = true;
        }
        map.put("success", success);
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：查找所有的角色
     * @author wanghaoyu
     * @version 1.0
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="后台查找角色列表")
    @RequiresAuthentication
    @RequestMapping("/findRoles")
    @ResponseBody
    public Map<String, Object> findRoles(){
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<Role> roles = roleService.selectByRoleExample(new RoleExample());
        map.put("success", true);
        map.put("list", roles);
        logger.info(roles.toString());
        return map;
    }
    
    /**
     * 
     * 描述：查找除当前登录的客户经理外的所有的客户经理
     * @author wanghaoyu
     * @version 1.0
     * @param request
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="后台查找其他客户经理角色")
    @RequiresAuthentication
    @RequestMapping("/findOthersManager")
    @ResponseBody
    public Map<String, Object> findOthersManager(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        //获取当前登录用户
        User user = (User)request.getSession().getAttribute("user");
        //创建用户模块
        UserExample userExample = new UserExample();
        //创建查询准则
        Criteria criteria =  userExample.createCriteria();
        //如果当前用户为客户经理
        if(user.getRoleId() == 1) {
            criteria.andIdNotEqualTo(user.getId());
        }
        criteria.andRoleIdEqualTo(1);
        List<User> users = userService.findByExample(userExample);
        success = true; 
        
        map.put("success", success);
        map.put("list", users);
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：检查用户账号是否可用
     * @author wanghaoyu
     * @version 1.0
     * @param account
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="检查用户账号")
    @RequestMapping("/checkUserAccount")
    @ResponseBody
    public Map<String, Object> checkUserAccount(String account){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        //没有找到该账号名，说明账号可用
        if(userService.findByAccount(account) == null) {
            success = true;
        }
        map.put("success", success);
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：检查用户邮箱是否可用
     * @author wanghaoyu
     * @version 1.0
     * @param email
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="检查用户邮箱")
    @RequestMapping("/checkUserEmail")
    @ResponseBody
    public Map<String, Object> checkUserEmail(String email){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        //没有找到该账号名，说明邮箱可用
        if(userService.findByEmail(email) == null) {
            success = true;
        }
        map.put("success", success);
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：根据session域里面的用户编号查找当前登录的用户
     * @author wanghaoyu
     * @version 1.0
     * @param id
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="查找当前登录的用户")
    @RequiresPermissions("13001")
    @RequestMapping("/findCurrentUser")
    @ResponseBody
    public Map<String, Object> findCurrentUser(Integer id){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        String msg = "";
        //根据ID查找用户
        User user = userService.findById(id);
        if(user != null){
            user.setPassword(null);
            user.setSalt(null);
            success = true;
            msg = "查找成功！";
        }else{
            success = false;
            msg = "查找失败！";
        }
        map.put("success", success);
        map.put("code",0);
        map.put("data", user);
        map.put("msg", msg);
        return map;
    }
    
    
    /**
     * 
     * 描述：用户修改密码
     * @author wanghaoyu
     * @version 1.0
     * @param user
     * @return Map<String,Object>
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="修改用户密码")
    @RequiresPermissions("14001")
    @RequestMapping("/editUserPasswd")
    @ResponseBody
    public Map<String, Object> editUserPasswd(User user,String oldPassword){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        String msg = "";
        if(userService.editPasswd(user,oldPassword)) {
            success = true;
            msg = "修改成功！";
        }else {
            success = false;
            msg = "修改失败！";
        }
        map.put("success", success);
        map.put("msg", msg);
        map.put("code", 0);
        return map;
    }
    
    /**
     * 
     * 描述：获取忘记密码时的验证码
     * @author wanghaoyu
     * @version 1.0
     * @param email
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="获取找回密码所需的验证码")
    @RequestMapping("/getForgotPasswdCode")
    @ResponseBody
    public Map<String, Object> getForgotPasswdCode(String email){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        String msg = "";
        if(userService.findByEmail(email) == null){
            //找不到该邮箱的账号
            msg = "不存在的账号！";
        }else{
            try {
                //生成长度为4的随机数字
                String code = RandomStringUtil.getRandomCode(4, 0);
                //存到redis缓存中，有效时间为5分钟
                //先删除原来已存在的验证码
                redisUtil.remove(email);
                redisUtil.set(email, code, 300L);
                //发送邮件
                mailUtil.send(email, "找回密码", "验证码：" + code + "(验证码有效期为5分钟)");
                success = true;
                msg = "发送验证码成功！";
            } catch (Exception e) {
                e.printStackTrace();
                msg = "获取验证码失败!";
            }
        }
        map.put("success", success);
        map.put("code", 0);
        map.put("msg", msg);
        return map;
    }
    
    /**
     * 
     * 描述： 重设密码
     * @author wanghaoyu
     * @version 1.0
     * @param email
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="重设密码")
    @RequestMapping("/resetPasswd")
    @ResponseBody
    public Map<String, Object> resetPasswd(String email, String code, String password){
        Map<String, Object> map = new HashMap<String, Object>(16);
        boolean success = false;
        String msg = "";
        try {
            //先根据邮箱获取相应用户的信息
            User user = userService.findByEmail(email);
            if(user == null){
                msg = "不存在的账号！";
            }else{
                //从redis缓存中获取验证码
                if(redisUtil.exists(email)){
                    String redisCode = (String) redisUtil.get(email);
                    if(redisCode.equals(code)){
                        //验证码正确，修改密码
                        User editUser = new User();
                        editUser.setId(user.getId());
                        editUser.setPassword(password);
                        if(userService.edit(editUser)){
                            msg = "修改成功！";
                            success = true;
                        }else{
                            msg = "修改失败！";
                        }
                    }else{
                        msg = "验证码错误！";
                    }
                }else{
                    msg = "验证码已过期，请重新获取！";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "服务器出了小差，请稍等...";
        }
        map.put("success", success);
        map.put("code", 0);
        map.put("msg", msg);
        return map;
    }
    
    
}
