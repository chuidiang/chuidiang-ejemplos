package com.chuidiang.examples3;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class ClientHandler extends ChannelInboundHandlerAdapter{
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx=ctx;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ctx=null;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }

    public void sendMessage(String msg){
        if (null==ctx)
            return;
        ChannelFuture channelFuture = ctx.write(msg);
        ctx.flush();
    }
}
