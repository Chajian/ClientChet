package com.yezi.chet.community.netty.handle;

import com.yezi.chet.control.operation.GetAllThingOpeartion;
import com.yezi.chet.control.operation.BaseOperation;
import com.yezi.chet.control.operation.CommunityTry;
import com.yezi.chet.control.operation.FailOpeartion;
import com.yezi.chet.control.operation.GetFriendsInfoOpeartion;
import com.yezi.chet.control.operation.LoginOpeartion;
import com.yezi.chet.control.operation.RegisterOperation;
import com.yezi.chet.control.operation.SearchFriendsOpeartion;
import com.yezi.chet.data.SendInfo;
import com.yezi.chet.data.constant.Permission;
import com.yezi.chet.sql.MySQLliteOpen;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


/**
 *  监听数据包从而作出应答
 */
@ChannelHandler.Sharable
public class MonitorHandle extends ChannelInboundHandlerAdapter {

    public static MonitorHandle monitorHandle = null;
    public BaseOperation operationSocket = new BaseOperation();
    public MySQLliteOpen mySQLliteOpen;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            SendInfo data = (SendInfo) msg;
            int type = data.getData().getType();
            switch (type){
                case Permission.COMMUNITY_LOGIN:
                    operationSocket = new LoginOpeartion();
                    break;

                case Permission.COMMUNITY_TRY:
                    operationSocket = new CommunityTry();
                    break;

                case Permission.COMMUNITY_REGISTER:
                    operationSocket = new RegisterOperation();
                    break;

                case Permission.FAIL:
                    operationSocket = new FailOpeartion();
                    break;

                case Permission.GET_ALL_THING:
                    operationSocket = new GetAllThingOpeartion(mySQLliteOpen);
                    break;

                case Permission.ADD_FRIEND_AGREE:

                    break;

                case Permission.SEARCH_ADD_FRIENDS:
                    operationSocket = new SearchFriendsOpeartion();
                    break;

                case Permission.GET_FRIENDS_INFO:
                    operationSocket = new GetFriendsInfoOpeartion();
                    break;
            }
            operationSocket.opeartion(data);
        }
        catch (Exception e){
            e.printStackTrace();
//            Log.e("sdfsdf","");
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    public MySQLliteOpen getMySQLliteOpen() {
        return mySQLliteOpen;
    }

    public void setMySQLliteOpen(MySQLliteOpen mySQLliteOpen) {
        this.mySQLliteOpen = mySQLliteOpen;
    }

    public BaseOperation getOperationSocket() {
        return operationSocket;
    }

    public static MonitorHandle getMonitorHandle() {
        if(monitorHandle == null)
            monitorHandle = new MonitorHandle();
        return monitorHandle;
    }
}
