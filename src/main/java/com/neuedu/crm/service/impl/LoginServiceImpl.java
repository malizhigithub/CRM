package com.neuedu.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.UserMapper;
import com.neuedu.crm.pojo.User;
import com.neuedu.crm.pojo.UserExample;
import com.neuedu.crm.pojo.UserExample.Criteria;
import com.neuedu.crm.service.ILoginService;
/**
 * 
 * @author 
 * @date 2018/07/24
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class LoginServiceImpl implements ILoginService {

    @Autowired
    UserMapper userMapper;
    
    @Override
    public User login(User user) {
        //1. 实例化user的模板类
        UserExample userExample = new UserExample();
        //2. 创建查询准则
        Criteria criteria  = userExample.createCriteria();
        //3. 添加条件
        criteria.andAccountEqualTo(user.getAccount());
        criteria.andPasswordEqualTo(user.getPassword());
        //4. 进行模板查询
        List<User> users =  userMapper.selectByExample(userExample);
        //5. 根据查询的结果返回数据
        if(users.size() > 0){
            return users.get(0);
        }else {
            return null;
        }
    }

}
