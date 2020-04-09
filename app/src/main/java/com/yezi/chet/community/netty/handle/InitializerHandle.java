package com.yezi.chet.community.netty.handle;

import android.content.Context;

import com.yezi.chet.sql.MySQLliteOpen;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;

@ChannelHandler.Sharable
public class InitializerHandle extends ChannelInitializer<Channel> {

    MonitorHandle monitorHandle;

    public InitializerHandle(MySQLliteOpen mySQLliteOpen) {
        monitorHandle = MonitorHandle.getMonitorHandle();
        monitorHandle.setMySQLliteOpen(mySQLliteOpen);
    }

    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new DataEncode());
        channel.pipeline().addFirst(new DataDecode());
        channel.pipeline().addLast(monitorHandle);
    }

    public MonitorHandle getMonitorHandle() {
        return monitorHandle;
    }
}
