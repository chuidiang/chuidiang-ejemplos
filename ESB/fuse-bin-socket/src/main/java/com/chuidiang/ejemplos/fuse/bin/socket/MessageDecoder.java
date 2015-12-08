package com.chuidiang.ejemplos.fuse.bin.socket;

import java.util.LinkedList;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;


public class MessageDecoder extends FrameDecoder {

	public MessageDecoder () {
		super(true);
	}

	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {

	   System.out.println(ctx.canHandleUpstream());
			List<List<Byte>> objectsDecoded = new LinkedList<>();
			
			int initialIndex = buffer.readerIndex();
			int readableBytes = buffer.readableBytes();
			List<Byte> bytes = new LinkedList<>();

			for (int i = initialIndex; i < initialIndex + readableBytes; i++) {
				if (buffer.getByte(i) == 0) {
					buffer.readerIndex(i + 1);
					objectsDecoded.add(bytes);
					bytes = new LinkedList<>();
				} else {
					bytes.add(buffer.getByte(i));
				}
			}
			
			if (objectsDecoded.size()>0){
				return objectsDecoded;
			}
			
			return null;
		
	}
}
