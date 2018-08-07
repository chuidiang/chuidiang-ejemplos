package com.chuidiang.multicast.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.*;
import java.util.Enumeration;

public class MulticastServer {

    public static void main(String[] args) throws InterruptedException, SocketException, UnknownHostException {
        new MulticastServer().start();
    }

    public void start() throws InterruptedException, SocketException, UnknownHostException {

        NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getByName("127.0.0.1"));

        InetSocketAddress groupAddress = new InetSocketAddress(
                "239.255.27.1", 1234);

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
            .channelFactory(new ChannelFactory<NioDatagramChannel>() {
                @Override
                public NioDatagramChannel newChannel() {
                    return new NioDatagramChannel(InternetProtocolFamily.IPv4);
                }
            })
//            .channel(NioDatagramChannel.class)
            .handler(new ChannelInitializer<NioDatagramChannel>() {
                @Override
                protected void initChannel(NioDatagramChannel ch) throws Exception {
                    ch.pipeline().addLast(new ServerHandler());
                }
            })
            .option(ChannelOption.SO_REUSEADDR,true);

        NioDatagramChannel ch = (NioDatagramChannel)b.bind(groupAddress.getPort()).sync().channel();
        ch.joinGroup(groupAddress,ni).sync();

        ch.closeFuture().await();
    }
}
