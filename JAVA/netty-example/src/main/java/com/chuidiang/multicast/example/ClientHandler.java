package com.chuidiang.multicast.example;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class ClientHandler extends ChannelInboundHandlerAdapter{
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx=ctx;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        this.ctx=null;
    }

    public void sendMessage(String message){
        if (null==ctx) return;
        ByteBuf buff = ctx.alloc().buffer();
        buff.writeCharSequence(message,Charset.defaultCharset());
        DatagramPacket packet = new DatagramPacket(buff, new InetSocketAddress("239.255.27.1",1234));

        ctx.writeAndFlush(packet);
    }
}
