package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;


import com.neuedu.crm.pojo.CpuInfoVo;
import com.neuedu.crm.pojo.ServerInfo;

public interface IServerInfoService {
    /**
     * 描述：获取系统的基本信息
     * @author huangwanzong
     * @version 1.0
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    public ServerInfo getServerInfo();
    
    /**
     * 描述：获取cpu信息
     * @author huangwanzong
     * @version 1.0
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    public List<CpuInfoVo> getCpuInfo();
    
    /**
     * 描述：获取内存信息
     * @author huangwanzong
     * @version 1.0
     * @return 
     * @exception Nothing
     * @since 1.8
     *
     */
    public Map<String,Object> getMemoryInfo();
}
