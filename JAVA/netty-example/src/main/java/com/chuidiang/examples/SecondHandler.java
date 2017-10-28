package com.chuidiang.examples;

import com.sun.org.apache.xpath.internal.SourceTree;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by chuidiang on 28/10/17.
 */
public class SecondHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println("Soy el Segundo handler "+msg);
        ReferenceCountUtil.release(msg);
    }
}
