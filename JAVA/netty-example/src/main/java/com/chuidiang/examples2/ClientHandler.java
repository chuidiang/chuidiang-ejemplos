package com.chuidiang.examples2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

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
        try {
            ByteBuf buf =(ByteBuf)msg;
            CharSequence text = buf.readCharSequence(buf.readableBytes(),
                    Charset.defaultCharset());
            System.out.println(text);
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    public void sendMessage(String msg){
        if (null==ctx)
            return;
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeCharSequence(msg,Charset.defaultCharset());
        ctx.write(buf);
        ctx.flush();
    }
}
