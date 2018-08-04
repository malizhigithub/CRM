package com.neuedu.crm.service;


import com.neuedu.crm.pojo.User;

/**
 * @author HuangWanzong
 * @date 2018/07/24
 */
public interface ILoginService {
    
    /**
     * 描述：登录
     * @author 
     * @date 2018/07/24
     * @version 1.0
     * @param user
     * @return User
     * @since 1.8
     *
     */
    public User login(User user);
}
