package com.chuidiang.examples4;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class FrameExtractor extends ChannelInboundHandlerAdapter{

    private ByteBuf buf;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        buf= ctx.alloc().buffer();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        if(null!=buf) {
            buf.release();
            buf=null;
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try {
            buf.writeBytes((ByteBuf) msg);
            int indexOf = buf.indexOf(0, buf.readableBytes(), (byte) '\n');
            while (-1!=indexOf) {
                ByteBuf line = ctx.alloc().buffer();
                buf.readBytes(line, indexOf);
                buf.readByte(); // Leemos el retorno de carro para eliminarlo.
                ctx.fireChannelRead(line);
                buf.discardReadBytes();
                indexOf = buf.indexOf(0, buf.readableBytes(), (byte) '\n');
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
