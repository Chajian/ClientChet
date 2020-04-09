package com.yezi.chet.control;

import android.util.Log;

import com.yezi.chet.community.socket.Community;
import com.yezi.chet.control.operation.BaseOperation;
import com.yezi.chet.control.operation.CommunityTry;
import com.yezi.chet.control.operation.RegisterOperation;
import com.yezi.chet.data.ApplicationData;
import com.yezi.chet.data.constant.Permission;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *  监听返回数据，并且做出反应
 *
 */
public class SocketMonitor implements Monitor,Runnable {

    public static SocketMonitor socketMonitor = null;
    public BaseOperation operationSocket = new BaseOperation();
    ObjectInputStream inputStream;
    ObjectOutputStream outputStream;
    private SocketMonitor(){

    }

    @Override
    public void run() {
        while(true) {
            onListen();
        }
    }

    @Override
    public void onListen() {
        Socket socket = Community.getCommunity().getSocket();
        if(socket!=null&&!socket.isConnected())
            return;
        try {
            if(inputStream == null)
                inputStream = new ObjectInputStream(socket.getInputStream());
            ApplicationData data = (ApplicationData) inputStream.readObject();
            int type = Integer.parseInt(data.getInfo()[2]);
            switch (type){
                case Permission.COMMUNITY_LOGIN:

                break;

                case Permission.COMMUNITY_TRY:
                    operationSocket = new CommunityTry();
                break;

                case Permission.COMMUNITY_REGISTER:
                    operationSocket = new RegisterOperation();
                    break;
            }
//            operationSocket.opeartion(data);
            Thread.sleep(100);
        }
        catch (Exception e){
            e.printStackTrace();
//            Log.e("sdfsdf","");
        }
    }

    public static SocketMonitor getSocketMonitor() {
        if(socketMonitor == null)
            socketMonitor = new SocketMonitor();
        return socketMonitor;
    }
}
