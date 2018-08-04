package com.neuedu.crm.utils;



import org.hyperic.sigar.Sigar;

import java.security.CodeSource;
import java.security.ProtectionDomain;
 
 
public class SigarUtils {
    public final static Sigar sigar = initSigar();
 
    public static Sigar initSigar() {
        try {


            String path = System.getProperty("java.library.path");
            ProtectionDomain pd = Sigar.class.getProtectionDomain();
            CodeSource cs = pd.getCodeSource();
            System.out.println("cs: "+ cs.getLocation());
            String jarUrl = cs.getLocation().toString();
            String sigarLibPath = jarUrl.substring(0, jarUrl.lastIndexOf("/"));
            System.out.println("sigarLibPath: " + sigarLibPath);
            // 为防止java.library.path重复加，此处判断了一下
            if (!path.contains(sigarLibPath)) {
                if (isOSWin()) {
                    path += ";" + sigarLibPath;
                } else {
                    path += ":" + sigarLibPath;
                }
                System.setProperty("java.library.path", path);
            }
            return new Sigar();
        } catch (Exception e) {
            return null;
        }
    }
 
    public static boolean isOSWin() {// OS 版本判断
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("win") >= 0) {
            return true;
        } else
            return false;
    }
}
