package com.neuedu.crm.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neuedu.crm.pojo.User;
import com.neuedu.crm.service.IUserService;
import com.neuedu.crm.utils.Operation;

/**
 * 
 * @author wanghaoyu
 * 
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    
    @Autowired
    private IUserService userService;
    
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    
    /**
     * 
     * 描述：登陆
     * @author wanghaoyu
     * @version 1.0
     * @param user 用户
     * @param verifyCode 验证码
     * @param request 请求
     * @return Map<String,Object> map封装要返回到前端的信息
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="登陆")
    @RequestMapping("/login")
    @ResponseBody
    public Map<String, Object> login(User user, String verifyCode, 
            HttpServletRequest request){

    	Map<String, Object> maps = new HashMap<String, Object>(16);
    	
    	//1. 验证验证码
    	if(verifyCode == null || "".equals(verifyCode) ) {
    		maps.put("code", 200);
    		maps.put("msg", "请输入验证码");
    		return maps;
    	}
    	verifyCode = verifyCode.toLowerCase();
    	String sessionCode = (String) request.getSession().getAttribute("verifyCode");
    	if( sessionCode == null || "".equals(sessionCode)){
    		maps.put("code", 200);
    		maps.put("msg", "验证码获取失败! 请刷新页面!");
    		return maps;
    	}
    	sessionCode = sessionCode.toLowerCase();
    	
    	if(!sessionCode.equals(verifyCode)) {
    		maps.put("code", 200);
    		maps.put("msg", "验证码不正确!");
    		return maps;
    	}
    	
    	//2.判断用户账号密码
        Subject subject = SecurityUtils.getSubject();
        
        System.err.println("==============================" +subject.isAuthenticated());
        
        //判断用户是否已经登陆
        if(!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(),user.getPassword());
            try {
                subject.login(token);
                //验证成功
                //登陆成功后的用户账号信息
                String account =  (String)subject.getPrincipal();
                
                //根据用户账号，查询用户，并保存到session域中
                User loginUser = userService.findByAccount(account);
  
                LocalDateTime now = LocalDateTime.now();
                
                //如果上次登陆的时间为空，则说明这次是首次登陆，前台应该跳转到修改密码界面
                if(loginUser.getLastLoginTime() == null) {
                    maps.put("firstLogin", true);
                }
                //修改最后一次登录的时间,并保存到数据库
                loginUser.setLastLoginTime(now);
                loginUser.setPassword(null);
                
                //新创建对象用来更新user的最后一次登录时间
                User updateUser = new User();
                updateUser.setId(loginUser.getId());
                updateUser.setLastLoginTime(now);
                userService.edit(updateUser);
                
                request.getSession().setAttribute("user", loginUser);
                logger.info("登陆的用户信息:" + loginUser.toString());

                request.getSession().setAttribute("user", loginUser);
                maps.put("code", 0); 
              //账号被锁定
            } catch(LockedAccountException e){
                maps.put("code",200);
                maps.put("msg","账号已被锁定，请联系管理员进行处理！");
              //不存在的账号
            } catch(UnknownAccountException e) {
                maps.put("code",200);
                maps.put("msg","账号或密码错误!");
              //密码错误
            } catch(IncorrectCredentialsException e) {
                maps.put("code",200);
                maps.put("msg","账号或密码错误!");
            }catch (Exception e) {
                e.printStackTrace();
                maps.put("code", 200);
                maps.put("msg", "系统出了小差，请稍等...");
            }
        }else {
        	maps.put("code", 0);
        }
        return maps;
    }
    
    /**
     * 
     * 描述：
     * @author wanghaoyu
     * @version 1.0
     * @param request request请求
     * @return ModelAndView
     * @exception Nothing
     * @since 1.8
     *
     */
    @Operation(name="注销")
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView view = new ModelAndView();
        //获取shiro中的用户
        Subject subject = SecurityUtils.getSubject();
        //获取用户账号信息
        String account = (String) subject.getPrincipal();
        if(account != null) {
            //登出
            subject.logout();
            //同时删除session中的用户信息
            request.getSession().removeAttribute("user");
            request.getSession().invalidate();
        }
        //重定向到登陆页面
        view.setViewName("redirect:/pages/login.jsp");
        return view;
    }
    
    
    
    
}
