package com.chuidiang.examples;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.List;


/**
 * Created by chuidiang on 28/10/17.
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter { // (1)

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) { // (2)
        // Discard the received data silently.
        String msgAsString = (((ByteBuf)msg).toString(Charset.defaultCharset()));
        System.out.print("Soy el primer handler " + msgAsString);
        ((ByteBuf) msg).release(); // (3)
        ctx.fireChannelRead(msgAsString);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}
