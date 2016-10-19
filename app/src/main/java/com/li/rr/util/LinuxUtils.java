package com.li.rr.util;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2016/5/28.
 */
public class LinuxUtils {
    private static LinuxUtils linuxUtils;
    protected LinuxUtils() {
    }
    public static LinuxUtils getInstance() {
        if (null == linuxUtils) {
            synchronized (LinuxUtils.class) {
                if (null == linuxUtils) {
                    linuxUtils = new LinuxUtils();
                }
            }
        }
        return linuxUtils;
    }

    public void executeLinuxCmd(String cmd){
        try {
            Process localProcess = Runtime.getRuntime().exec("su");
            OutputStream localOutputStream = localProcess.getOutputStream();
            DataOutputStream localDataOutputStream = new DataOutputStream(localOutputStream);
            localDataOutputStream.writeBytes(cmd);
            localDataOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connectWifiDeBug(String port){
        executeLinuxCmd("setprop service.adb.tcp.port "+port);
        executeLinuxCmd("stop adbd");
        executeLinuxCmd("start adbd");
    }
    public void disconnectWifiDeBug(String port){
        executeLinuxCmd("setprop service.adb.tcp.port "+port);
        executeLinuxCmd("stop adbd");
    }
}
