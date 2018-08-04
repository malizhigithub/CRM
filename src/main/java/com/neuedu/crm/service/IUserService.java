package com.neuedu.crm.service;

import java.util.List;

import com.neuedu.crm.pojo.User;
import com.neuedu.crm.pojo.UserExample;
/**
 * 
 * @author WangHaoyu
 * @date 2018/07/24
 */
public interface IUserService {
 
    /**
     * 
     * 描述：根据用户名查找用户
     * @author wanghaoyu
     * @version 1.0
     * @param account
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    public User findByAccount(String account);
    
    /**
     * 
     * 描述：添加用户
     * @author wanghaoyu
     * @version 1.0
     * @param user
     * @return boolean
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean save(User user);

    /**
     * 
     * 描述：根据id删除用户
     * @author wanghaoyu
     * @version 1.0
     * @param id
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean deleteById(Integer id);
    
    /**
     * 
     * 描述：根据id查找用户
     * @author wanghaoyu
     * @version 1.0
     * @param id
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    public User findById(Integer id);
    
    /**
     * 
     * 描述：根据模板查找用户
     * @author wanghaoyu
     * @version 1.0
     * @param userExample 用户查询模板
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    public List<User> findByExample(UserExample userExample);
    
    /**
     * 
     * 描述：根据模板统计数量
     * @author wanghaoyu
     * @version 1.0
     * @param userExample
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    public long countByExample(UserExample userExample);
    /**
     * 
     * 描述：根据id修改用户信息
     * @author wanghaoyu
     * @version 1.0
     * @param user
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    public boolean edit(User user);
    
    /**
     * 
     * 描述:对用户的密码进行加密
     * @author wanghaoyu
     * @version 1.0
     * @param user
     * @return User
     * @exception Nothing
     * @since 1.8
     *
     */
    public User encryptPassword(User user);

    /**
     * 
     * 描述：修改用户密码
     * @author WangHaoyu
     * @date 2018/07/24
     * @version 1.0
     * @param user
     * @param oldPassword
     * @return boolean
     * @since 1.8
     *
     */
    public boolean editPasswd(User user,String oldPassword);

    /**
     * 
     * 描述：根据邮箱查找用户
     * @author wanghaoyu
     * @version 1.0
     * @param email
     * @return User
     * @exception Nothing
     * @since 1.8
     *
     */
    public User findByEmail(String email);
    
}
