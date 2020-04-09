package com.yezi.chet.control;

import android.util.Log;

import com.yezi.chet.community.socket.Community;
import com.yezi.chet.data.ApplicationData;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 *  sender接口的实现，用于发送包给服务器
 */
public class SocketSender implements Sender,Runnable {

    public static SocketSender socketSender;
    public static ObjectOutputStream objectOutputStream;
    List<ApplicationData> sockets = new ArrayList<ApplicationData>();

    private SocketSender() {
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
                sendOperation();
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("发送数据包时出错!");
            }
        }
    }

    @Override
    public synchronized void put(ApplicationData info) {
        sockets.add(info);
        Log.e("添加咯","撒地方");
    }

    public static SocketSender getSocketSender() {
        if(socketSender == null)
            socketSender = new SocketSender();
        return socketSender;
    }

    //处理发送数据的包
    public void sendOperation() throws IOException{
        Iterator iterator = sockets.iterator();
        while(iterator.hasNext()){
            Socket revier = Community.getCommunity().getSocket();
            ApplicationData data = (ApplicationData) iterator.next();
            if(objectOutputStream == null)
                objectOutputStream = new ObjectOutputStream(revier.getOutputStream());
            objectOutputStream.writeObject(data);
            objectOutputStream.write(null);
            objectOutputStream.flush();
            iterator.remove();
//                outputStream.close();
            Log.e("发送数据成功","发送数据:"+data.type);
            }
        }

}
