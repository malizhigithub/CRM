package com.neuedu.crm.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neuedu.crm.utils.RedisUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghaoyu
 */
public class MethodCacheInterceptor implements MethodInterceptor {
    private Logger logger = LoggerFactory.getLogger(MethodCacheInterceptor.class);
    private RedisUtil redisUtils;
    /**
     * 不加入缓存的service名称
     */
    private List<String> targetNamesList; 
    /**
     * 不加入缓存的方法名称
     */
    private List<String> methodNamesList; 
    /**
     * 缓存默认的过期时间
     */
    private Long defaultCacheExpireTime; 

    public void setRedisUtils(RedisUtil redisUtils) {
        this.redisUtils = redisUtils;
    }

    /**
     * 初始化读取不需要加入缓存的类名和方法名称
     */
    public MethodCacheInterceptor() {
        try {
            // 分割字符串 这里没有加入任何方法
            String[] targetNames = {};
            String[] methodNames = {};

            // 加载过期时间设置
            defaultCacheExpireTime = 500L;
            
            // 创建list
            targetNamesList = new ArrayList<String>(targetNames.length);
            methodNamesList = new ArrayList<String>(methodNames.length);
            Integer maxLen = targetNames.length > methodNames.length ? targetNames.length
                    : methodNames.length;
            // 将不需要缓存的类名和方法名添加到list中
            for (int i = 0; i < maxLen; i++) {
                if (i < targetNames.length) {
                    targetNamesList.add(targetNames[i]);
                }
                if (i < methodNames.length) {
                    methodNamesList.add(methodNames[i]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object value = null;

        String targetName = invocation.getThis().getClass().getName();
        String methodName = invocation.getMethod().getName();
        // 判断是否需要加入缓存
        if (!isAddCache(targetName, methodName)) {
            // 执行方法返回结果
            return invocation.proceed();
        }
        //Object[] arguments = invocation.getArguments();
        String key = getCacheKey(targetName, methodName, null);
        logger.debug("redisKey: " + key);
        try {
            // 判断是否有缓存
            if (redisUtils.exists(key)) {
                logger.info("从缓存中读取");
                return redisUtils.get(key);
            }
            logger.info("缓存中没有该数据");
            // 写入缓存
            value = invocation.proceed();
            if (value != null) {
                final String tkey = key;
                logger.info("key的值为：" + key);
                final Object tvalue = value;
                logger.info("value的值为：" + value);
                new Thread(new Runnable() {
                    @Override
                    public void run() {                 
                            redisUtils.set(tkey, tvalue, defaultCacheExpireTime);
                    }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (value == null) {
                return invocation.proceed();
            }
        }
        return value;
    }

    /**
     * 是否加入缓存
     *
     * @return
     */
    private boolean isAddCache(String targetName, String methodName) {
        boolean flag = true;
        if (targetNamesList.contains(targetName)
                || methodNamesList.contains(methodName)) {
            flag = false;
        }
        return flag;
    }

    /**
     * 创建缓存key
     *
     * @param targetName
     * @param methodName
     * @param arguments
     */
    private String getCacheKey(String targetName, String methodName,
                               Object[] arguments) {
        StringBuffer sbu = new StringBuffer();
        sbu.append(targetName).append("_").append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sbu.append("_").append(arguments[i]);
            }
        }
        return sbu.toString();
    }

}