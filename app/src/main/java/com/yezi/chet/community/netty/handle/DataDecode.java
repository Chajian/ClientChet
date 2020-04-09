package com.yezi.chet.community.netty.handle;

import com.yezi.chet.tools.ByteBufToBytes;
import com.yezi.chet.tools.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Data解码器
 */
public class DataDecode extends ByteToMessageDecoder {

    int shuzi = 0;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {  //这个HEAD_LENGTH是我们用于表示头长度的字节数。  由于上面我们传的是一个int类型的值，所以这里HEAD_LENGTH的值为4.
            return;
        }
        byteBuf.markReaderIndex();
        int nummber = byteBuf.readInt();
        if(nummber < 0) {
            return;
        }
        else if(byteBuf.readableBytes() < nummber){
            byteBuf.resetReaderIndex();
            return;
        }

        try {
            byte[] bytes = new byte[nummber];
            byteBuf.readBytes(bytes);
            Object o = ByteObjConverter.byteToObject(bytes);
            list.add(o);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
