package com.yezi.chet.community.socket;

import android.util.Log;

import java.io.IOException;
import java.net.Socket;

/**
 * Community接口的实现
 * @author yezi
 * @version 1.0
 * @see com.yezi.chet.community.Community
 */
public class Community implements com.yezi.chet.community.Community {
    public static Community community = null;
    private Socket socket = null;
    private String ip = "192.168.0.104";
    private int port = 8002;
    private boolean is_community = false;
    private Community(){
        createCommunity(ip,port);
    }


    @Override
    public void createCommunity(String ip,int port) {
            try {
                socket = new Socket(ip, port);
            } catch (IOException e) {
                Log.e("服务器连接出错",e.getMessage());
            }
    }

    public static Community getCommunity() {
        if(community == null)
            community = new Community();
        return community;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Socket getSocket() {
        return socket;
    }
}
