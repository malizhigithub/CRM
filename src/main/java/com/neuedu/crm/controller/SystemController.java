package com.neuedu.crm.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.pojo.CpuInfoVo;
import com.neuedu.crm.pojo.ServerInfo;
import com.neuedu.crm.service.IServerInfoService;
import com.neuedu.crm.utils.Operation;

/**
 * 
 * @author HuangWanzong
 * @date 2018/07/26
 */
@Controller
@RequestMapping("/system")
public class SystemController {
    
    @Autowired
    private IServerInfoService serverService;
    
    @Operation(name="查询系统信息")
    @RequestMapping("info")
    @ResponseBody
    public Map<String, Object> getSystemInfo(HttpServletRequest request){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        ServerInfo serverInfo = serverService.getServerInfo();

        InetAddress address;
        try {
            address = InetAddress.getLocalHost();
            serverInfo.setServerURL(
                    request.getScheme()                 //请求头
                    +"://" + address.getHostAddress()   //服务器地址  
                    + ":"   
                    + request.getServerPort()           //端口号  
                    + request.getContextPath());        //项目名称 
            ServletContext application = request.getSession().getServletContext();
            serverInfo.setServerType(application.getServerInfo());


        } catch (UnknownHostException e) {
        }

        map.put("serverInfo", serverInfo);
        return map;
    }
    
    @Operation(name="查询cpu信息")
    @RequestMapping("cpu")
    @ResponseBody
    public Map<String, Object> getCpuInfo(String time){
        Map<String, Object> map = new HashMap<String,Object>(16);
        
        List<CpuInfoVo> list = serverService.getCpuInfo();
        
        map.put("time", time);
        map.put("cpuList", list);
        return map;
    }
    
    @Operation(name="查询cpu信息")
    @RequestMapping("memory")
    @ResponseBody
    public Map<String, Object> getMemoryInfo(String time){
        Map<String, Object> map = null;
        map = serverService.getMemoryInfo();
        map.put("success", true);
        map.put("time", time);
        return map;
    }
    
}
