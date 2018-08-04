package com.neuedu.crm.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.neuedu.crm.utils.RequestUtil;

/**
 * 描述：全局的异常处理器
 * @author wanghaoyu
 *
 */
@ControllerAdvice
public class MyExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 
     * 描述：处理全局Controller所抛出的未授权异常，并进行重定向
     * @author wanghaoyu
     * @version 1.0
     * @param e
     * @return String
     * @exception Nothing
     * @since 1.8
     *
     */
    @ExceptionHandler({AuthorizationException.class})
    @ResponseBody
    public Object handleAuthorizationException(HttpServletRequest request, Exception e) {
        logger.info("捕捉到未授权异常！");
        //判断请求类型是否为ajax请求
        if(RequestUtil.isAjaxRequest(request)) {
            logger.info("这是个ajax请求");
            Map<String, Object> map = new HashMap<String,Object>(16);
            map.put("code", -1);
            map.put("msg", "抱歉，你没有执行该操作的权限！");
            map.put("success", false);
            return map;
        }else {
            logger.info("这是个普通请求");
            return new ModelAndView("redirect:/views/error/unauthorized.jsp");
        }
    }
    
}
