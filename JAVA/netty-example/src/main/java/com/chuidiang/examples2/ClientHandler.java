package com.chuidiang.examples2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.net.SocketAddress;
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
        System.out.println(((ByteBuf)msg).toString(Charset.defaultCharset()));
        ((ByteBuf)msg).release();
    }

    public void sendMessage(Object msg){
        if (null==ctx)
            return;
        ByteBuf buf = Unpooled.copiedBuffer(((String)msg).getBytes());
        ChannelFuture channelFuture = ctx.write(buf);
        ctx.flush();
    }
}
