package com.chuidiang.examples;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.ByteBufferInput;
import com.esotericsoftware.kryo.io.Input;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * Created by chuidiang on 29/10/17.
 */
public class KryoDecoderHandler extends ReplayingDecoder<Void> {
    private Kryo kryo = new Kryo();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read");
        super.channelRead(ctx, msg);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("decode ");
        Object data = null;
        try {
            int length = in.readInt();
            byte [] buff = new byte[length];
            in.readBytes(buff);
            data = kryo.readClassAndObject(new Input(buff));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        if (null!=data) {
            System.out.println("Llega data"+data);
            out.add(data);
        }
    }
}
