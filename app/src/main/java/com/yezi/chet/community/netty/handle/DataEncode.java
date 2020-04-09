package com.yezi.chet.community.netty.handle;

import com.yezi.chet.data.SendInfo;
import com.yezi.chet.tools.ByteObjConverter;

import java.util.Arrays;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Data编码器
 */
public class DataEncode extends MessageToByteEncoder {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        byte[] datas = null;
        if(o instanceof SendInfo) {
            datas = ByteObjConverter.objectToByte(o);
        }
        else {
            datas = (byte[]) o;
        }
        byteBuf.writeInt(datas.length);
        byteBuf.writeBytes(datas);
        channelHandlerContext.flush();

    }
}
