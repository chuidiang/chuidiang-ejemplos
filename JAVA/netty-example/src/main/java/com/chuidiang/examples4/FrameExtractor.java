package com.chuidiang.examples4;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class FrameExtractor extends ChannelInboundHandlerAdapter{

    private ByteBuf buf;

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        buf= Unpooled.buffer();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
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
            boolean somethingSent = false;
            while (-1!=indexOf) {
                    ByteBuf line = ctx.alloc().directBuffer();
                    buf.readBytes(line, indexOf);
                    buf.readByte(); // Leemos el retorno de carro para eliminarlo.
                    ctx.fireChannelRead(line);
                    somethingSent = true;
                    buf.discardReadBytes();

            }
            if (!somethingSent) {
                ctx.fireChannelRead(Unpooled.EMPTY_BUFFER);
            }
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
