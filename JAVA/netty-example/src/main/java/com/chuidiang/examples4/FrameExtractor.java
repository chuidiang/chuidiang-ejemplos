package com.chuidiang.examples4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class FrameExtractor extends ChannelInboundHandlerAdapter{

    private ByteBuf buf;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        buf= Unpooled.buffer();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        if(null!=buf) {
            buf.release();
            buf=null;
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        buf.writeBytes((ByteBuf)msg);
        int indexOf=-1;
        boolean somethingSent=false;
        do {
             indexOf = buf.indexOf(0, buf.readableBytes(), (byte) '\n');
             if (-1!=indexOf){
                 ByteBuf line = Unpooled.buffer();
                 buf.readBytes(line,indexOf+1);
                 ctx.fireChannelRead(line);
                 somethingSent=true;
                 buf.discardReadBytes();
             }
        } while (indexOf!=-1);
        if (!somethingSent){
            ctx.fireChannelRead(Unpooled.EMPTY_BUFFER);
        }
    }
}
