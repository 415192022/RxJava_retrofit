package com.li.rr.util.socket;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by LMW on 2016/8/24.
 */
public class SocketUtils {
    private static SocketUtils socketUtils;
    private Context context;
    private final int PORT = 12345;
    private Socket socketClient;
    private Socket socketServer;
    private ServerSocket serverSocket;

    private SocketUtils(Context context) {
        this.context = context;
    }

    private static SocketUtils getSocketUtils() {
        return socketUtils;
    }

    private Socket getSocketClient() {
        return socketClient;
    }

    public Socket getSocketServer() {
        return socketServer;
    }

    public void setSocketClient(Socket socketClient) {
        this.socketClient = socketClient;
    }

    public void setSocketServer(Socket socketServer) {
        this.socketServer = socketServer;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * 获得单利对象
     *
     * @param context
     * @return
     */
    public static SocketUtils getInstance(Context context) {
        if (null == socketUtils) {
            synchronized (SocketUtils.class) {
                if (null == socketUtils) {
                    socketUtils = new SocketUtils(context);
                }
            }
        }
        return socketUtils;
    }

    public enum CLIENT_SERVER {
        CLIENT, SERVER;
    }

    /**
     * IP格式化
     *
     * @param i
     * @return
     */
    private String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + ((i >> 24) & 0xFF);
    }

    /**
     * 获得主机IP
     *
     * @param client_server
     * @return
     */
    public String getHostIp(CLIENT_SERVER client_server) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        if (client_server.equals(CLIENT_SERVER.CLIENT) || client_server == CLIENT_SERVER.CLIENT) {
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String IPAddress = intToIp(wifiInfo.getIpAddress());
            System.out.println("IPAddress-->>" + IPAddress);
            return IPAddress;
        } else if (client_server.equals(CLIENT_SERVER.SERVER) || client_server == CLIENT_SERVER.SERVER) {
            DhcpInfo dhcpinfo = wifiManager.getDhcpInfo();
            String serverAddress = intToIp(dhcpinfo.serverAddress);
            System.out.println("serverAddress-->>" + serverAddress);
            return serverAddress;
        } else {
            return "未获取到IP地址";
        }
    }


    /**
     * 建立连接
     */
    public SocketUtils connect(CLIENT_SERVER client_server, int port) throws Exception {
        socketConnect(getHostIp(client_server), port);
        return getSocketUtils();
    }

    private SocketUtils socketConnect(String ip, int port) throws Exception {
        if (null == socketClient) {
            socketClient = new Socket(ip, port);
        }
        return getSocketUtils();
    }

    /**
     * 上传文件
     *
     * @param filePath
     * @throws Exception
     */
    public void upLoadFile(String filePath) throws Exception {
        if (null == getSocketClient()) {
            throw new Exception();
        }
        InputStream inputStream = new FileInputStream(new File(filePath));
        OutputStream outputStream = getSocketClient().getOutputStream();
        byte[] buffer = new byte[1 * 1024];
        int lenth;
        while ((lenth = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, lenth);
        }
        outputStream.flush();
        outputStream.close();
        getSocketClient().close();
    }

    /**
     * 接收文件
     *
     * @param saveFilePath
     * @throws IOException
     */
    private void receiveFile(String saveFilePath) throws IOException {
        InputStream inputStream = getSocketServer().getInputStream();
        File file = new File(saveFilePath);
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[4 * 1024];
        int lenth;
        while ((lenth = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, lenth);
        }
        outputStream.flush();
        outputStream.close();
        getSocketServer().close();
        setSocketServer(null);
        Toast.makeText(context, "文件接收完毕", Toast.LENGTH_SHORT).show();
        System.out.println("文件接收完毕");
    }

    /**
     * 打开Socket服务
     *
     * @throws Exception
     */
    public void openServer(String savaFilePath) {
        new ReceiveThread(savaFilePath).start();
    }

    private void openSocketServer(String savaFilePath) throws Exception {
        System.out.println("进入openSocketServer(方法)");
        serverSocket = new ServerSocket(PORT);
        Toast.makeText(context, "准备连接", Toast.LENGTH_SHORT).show();
        System.out.println("准备连接");
        socketServer = serverSocket.accept();
        Toast.makeText(context, "有客户端连接", Toast.LENGTH_SHORT).show();
        System.out.println("有客户端连接");
        receiveFile(savaFilePath);
        openSocketServer(savaFilePath);
    }

    public class ReceiveThread extends Thread {
        private String savaFilePath;

        public ReceiveThread(String savaFilePath) {
            this.savaFilePath = savaFilePath;
        }

        @Override
        public void run() {
            super.run();
            try {
                openSocketServer(savaFilePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
