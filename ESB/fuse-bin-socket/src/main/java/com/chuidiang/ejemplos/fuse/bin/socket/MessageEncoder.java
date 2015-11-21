package com.chuidiang.ejemplos.fuse.bin.socket;

import java.util.Arrays;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

public class MessageEncoder extends OneToOneEncoder{

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (msg instanceof List<?>){
			List<Byte> bytes = (List<Byte>)msg;
			byte [] buffer = new byte[bytes.size()+1];
			for (int i=0;i<bytes.size();i++){
				buffer[i]=bytes.get(i);
			}
			buffer[bytes.size()]=0;
			return ChannelBuffers.wrappedBuffer(buffer);
		}
		
		return null;
	}


}
