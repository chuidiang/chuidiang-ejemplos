package com.chuidiang.examples2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {
    ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf buf =(ByteBuf)msg;
        String text = buf.toString(Charset.defaultCharset());
        System.out.print(text);
        for (Channel channel : clients){
            if (!ctx.channel().equals(channel)) {
                ByteBuf bufToSend = ctx.alloc().buffer();
                bufToSend.writeCharSequence(text, Charset.defaultCharset());
                channel.writeAndFlush(bufToSend);
            }
        };
        buf.release();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        clients.add(ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        clients.remove(ctx.channel());
    }
}
