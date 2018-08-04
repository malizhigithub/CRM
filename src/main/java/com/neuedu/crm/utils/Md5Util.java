/**
 * 
 */
package com.neuedu.crm.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author wanghaoyu
 *
 */
public class Md5Util {
    
    /**
     * 加密算法
     */
    private static String algorithmName = "md5";
    /**
     * 加密算法迭代加密次数
     */
    private static int hashIterations = 2;
    
    /**
     * 
     * 描述：对信息进行MD5盐值加密
     * @author wanghaoyu
     * @version 1.0
     * @param message
     * @param salt
     * @return String
     * @exception Nothing
     * @since 1.8
     *
     */
    public static String encrypt(String message, String salt){
        SimpleHash hash = new SimpleHash(algorithmName, message, salt, hashIterations);
        return hash.toHex();
    }
    
    
}
