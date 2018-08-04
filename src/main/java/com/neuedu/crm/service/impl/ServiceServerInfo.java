package com.neuedu.crm.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.NetFlags;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.DatabaseMetaData;
import com.neuedu.crm.pojo.CpuInfoVo;
import com.neuedu.crm.pojo.ServerInfo;
import com.neuedu.crm.service.IServerInfoService;
import com.neuedu.crm.utils.SigarUtils;

@Service
@Transactional(rollbackFor=Exception.class)
public class ServiceServerInfo implements IServerInfoService{

    public ServerInfo serverInfo = null;
    
    public ServerInfo getServerInfo() {
        System.out.println("java.library.path:" + System.getProperty("java.library.path"));
        
        if(serverInfo == null) {
            serverInfo = new ServerInfo();
        }

        try { 
            
            SigarUtils.initSigar();
            //服务信息
            Properties props=System.getProperties();
            Runtime runTime = Runtime.getRuntime();
            
            InetAddress address;
            address = InetAddress.getLocalHost();
            InetAddress.getLocalHost().getHostAddress();
            serverInfo.setServerIP(address.getHostAddress());
            
            serverInfo.setServerTime(new Date());
          //操作系统
            serverInfo.setOsName(props.getProperty("os.name")+"  "+props.getProperty("os.arch"));
            serverInfo.setOsVersion(props.getProperty("os.version"));
            serverInfo.setUserName(props.getProperty("user.name"));
            serverInfo.setUserHome(props.getProperty("user.home"));
            Calendar cal = Calendar.getInstance();
            TimeZone timeZone = cal.getTimeZone();
            serverInfo.setOsTimeZone(timeZone.getDisplayName());
                

            //Cpu
            Sigar sigar = new Sigar();
            CpuInfo cpuInfos[] = sigar.getCpuInfoList();
            serverInfo.setCpuNum(cpuInfos.length);
            CpuPerc cpuList[] = sigar.getCpuPercList();
            List<CpuInfoVo> cpuVoList = new ArrayList<CpuInfoVo>();
            for(int m=0;m<cpuList.length;m++){
                CpuInfoVo cpuInfoVo = new CpuInfoVo();
                cpuInfoVo.setCpuMhz(cpuInfos[m].getMhz());
                String cpuIdle = String.format("%.2f",cpuList[m].getIdle()*100)+"%";
                cpuInfoVo.setCpuIdle(cpuIdle);
                String cpuCombined = String.format("%.2f",cpuList[m].getCombined()*100)+"%";
                cpuInfoVo.setCpuCombined(cpuCombined);
                cpuVoList.add(cpuInfoVo);
            }
            serverInfo.setCpuList(cpuVoList);

                
            //网卡信息
            String netInfos[] = sigar.getNetInterfaceList();
            List<NetInterfaceConfig> netList = new ArrayList<NetInterfaceConfig>();
            for(int i=0;i<netInfos.length;i++){
                NetInterfaceConfig netInfo = sigar.getNetInterfaceConfig(netInfos[i]); 
                if((NetFlags.LOOPBACK_ADDRESS.equals(netInfo.getAddress()))     //127.0.0.1 
                        || (netInfo.getFlags() == 0)                            //标识为0
                        || (NetFlags.NULL_HWADDR.equals(netInfo.getHwaddr()))   //MAC地址不存在
                        || (NetFlags.ANY_ADDR.equals(netInfo.getAddress()))     //0.0.0.0
                        ){
                    continue;
                }
                netList.add(netInfo);
            }
            serverInfo.setNetNum(netList.size());
            serverInfo.setNetList(netList);
                
            //物理内存
            Mem mem = sigar.getMem();  
            serverInfo.setMemTotal((mem.getTotal()/(1024*1024))+"M");
            serverInfo.setMemUsed((mem.getUsed()/(1024*1024))+"M");
            serverInfo.setMemFree((mem.getFree()/(1024*1024))+"M");
                
            //java配置
            serverInfo.setJavaPath(props.getProperty("java.home"));
            serverInfo.setJavaVendor(props.getProperty("java.vendor"));
            serverInfo.setJavaVersion(props.getProperty("java.version"));
            serverInfo.setJavaName(props.getProperty("java.specification.name"));
            serverInfo.setJvmVersion(props.getProperty("java.vm.version"));
            serverInfo.setJvmTotalMemory((int)(runTime.totalMemory()/(1024*1024))+"M");
            serverInfo.setJvmFreeMemory((int)(runTime.freeMemory()/(1024*1024))+"M");
            
            //数据库信息
            findDatabase(serverInfo);
            

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return serverInfo;
        
    }
    
  //数据库连接信息
    public void findDatabase(ServerInfo serverInfo){
        try{
            Properties properties = PropertiesLoaderUtils.loadAllProperties("properties/db_config.properties");
            String driver = properties.getProperty("driver");
            String jdbcUrl = properties.getProperty("url");
            serverInfo.setJdbcUrl(jdbcUrl);
            String username = properties.getProperty("dbusername");
            String password = properties.getProperty("password");
            Class.forName(driver);
            Connection con = (Connection) DriverManager.getConnection(jdbcUrl,username,password);
            DatabaseMetaData metaData = (DatabaseMetaData) con.getMetaData();
            serverInfo.setDatabaseType(metaData.getDatabaseProductName());
            serverInfo.setDatabaseVersion(metaData.getDatabaseProductVersion());
            serverInfo.setDatabaseDriver(metaData.getDriverName());
            serverInfo.setDriverVersion(metaData.getDriverVersion());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<CpuInfoVo> getCpuInfo() {
        //Cpu
        List<CpuInfoVo> cpuVoList = new ArrayList<CpuInfoVo>();
        try {
            Sigar sigar = new Sigar();
            CpuInfo cpuInfos[];
            cpuInfos = sigar.getCpuInfoList();
            CpuPerc cpuList[] = sigar.getCpuPercList();
            
            for(int m=0;m<cpuList.length;m++){
                CpuInfoVo cpuInfoVo = new CpuInfoVo();
                cpuInfoVo.setCpuMhz(cpuInfos[m].getMhz());
                String cpuIdle = String.format("%.2f",cpuList[m].getIdle()*100)+"%";
                cpuInfoVo.setCpuIdle(cpuIdle);
                String cpuCombined = String.format("%.2f",cpuList[m].getCombined()*100)+"%";
                cpuInfoVo.setCpuCombined(cpuCombined);
                cpuVoList.add(cpuInfoVo);
            }
        } catch (SigarException e) {
            e.printStackTrace();
        }
        return cpuVoList;
    }

    @Override
    public Map<String, Object> getMemoryInfo() {
        //物理内存
        Map<String, Object> map = new HashMap<String, Object>(16);
        Sigar sigar = new Sigar();
        Mem mem;
        try {
            mem = sigar.getMem();
            map.put("total", (mem.getTotal()/(1024*1024))+"M");
            map.put("used", (mem.getUsed()/(1024*1024))+"M");
            map.put("free", (mem.getFree()/(1024*1024))+"M");
        } catch (SigarException e) {
            e.printStackTrace();
        }  
        return map;
    }
}
