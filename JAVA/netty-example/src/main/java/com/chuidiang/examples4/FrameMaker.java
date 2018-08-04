package com.chuidiang.examples4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.ReferenceCountUtil;

public class FrameMaker extends ChannelOutboundHandlerAdapter{
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ByteBuf buff = ctx.alloc().buffer();
        buff.writeBytes((ByteBuf)msg);
        buff.writeByte('\n');
        ctx.write(buff,promise);
        ReferenceCountUtil.release(msg);
    }
}
