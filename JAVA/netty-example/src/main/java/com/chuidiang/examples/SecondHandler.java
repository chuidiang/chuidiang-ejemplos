package com.chuidiang.examples;

import com.sun.org.apache.xpath.internal.SourceTree;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by chuidiang on 28/10/17.
 */
public class SecondHandler extends ChannelDuplexHandler {
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println(Thread.currentThread().getName()+" Soy el Segundo handler "+msg);
        ReferenceCountUtil.release(msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
    }

    public void send(Data data) {

        if (null!=ctx){
            System.out.println(Thread.currentThread().getName()+ " send "+data);
            ctx.writeAndFlush(data);
        }
    }
}
