package com.yezi.chet.community.netty;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.util.Log;

import com.yezi.chet.community.Community;
import com.yezi.chet.community.netty.handle.InitializerHandle;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.sql.MySQLliteOpen;

import java.net.InetSocketAddress;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class CommunityBoot implements Community {

    public static CommunityBoot boot;
    Bootstrap bootstrap;
    EventLoopGroup eventExecutors;
    String ip;
    int port;
    private static Channel channel;
    InitializerHandle initializerHandle;
    Context context;
    MySQLliteOpen mySQLliteOpen;

    private CommunityBoot() {
        bootstrap = new Bootstrap();
    }

    @Override
    public void createCommunity(String ip, int port) {
        this.ip = ip;
        this.port = port;
        initializerHandle = new InitializerHandle(mySQLliteOpen);
        ChannelFuture f = null;
        eventExecutors = new NioEventLoopGroup();
        try {
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(ip, port))
                    .handler(initializerHandle);

            f = bootstrap.connect().sync();
            channel = f.channel();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
//        finally {
//            if(null != f){
//                if(null != f.channel() && f.channel().isOpen())
//                    f.channel().close();
//            }
//            System.out.println("重新连接");
//            createCommunity(this.ip,this.port);
//        }
    }

    public static CommunityBoot getBoot() {
        if(boot == null)
            boot = new CommunityBoot();
        return boot;
    }

    public void senderData(SendInfo sendInfo){
        channel.writeAndFlush(sendInfo);
    }

    public void senderData(byte[] sendInfo){
        channel.writeAndFlush(sendInfo);;
    }

    public void senderData(SendInfo sendInfo, Handler handler){
        senderData(sendInfo);
        initializerHandle.getMonitorHandle().getOperationSocket().setHandle(handler);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        this.mySQLliteOpen = new MySQLliteOpen(context);
    }

    public InitializerHandle getInitializerHandle() {
        return initializerHandle;
    }

    public MySQLliteOpen getMySQLliteOpen() {
        return mySQLliteOpen;
    }

    public void setMySQLliteOpen(MySQLliteOpen mySQLliteOpen) {
        this.mySQLliteOpen = mySQLliteOpen;
    }
}
