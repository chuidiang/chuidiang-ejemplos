package com.chuidiang.multicast.example;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.*;

public class MulticastClient {

    public static void main(String[] args) throws InterruptedException, SocketException {
        new MulticastClient().start();
    }

    private MyHandler myHandler = new MyHandler();

    public void start() throws InterruptedException, SocketException {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
//            .channelFactory(new ChannelFactory<NioDatagramChannel>() {
//                @Override
//                public NioDatagramChannel newChannel() {
//                    return new NioDatagramChannel(InternetProtocolFamily.IPv4);
//                }
//            })
                .channel(NioDatagramChannel.class)
//            .localAddress(localAddress, groupAddress.getPort())
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel ch) throws Exception {
                        ch.pipeline().addLast(myHandler);
                    }
                })
                .option(ChannelOption.SO_REUSEADDR,true);
        //.option(ChannelOption.IP_MULTICAST_IF,ni);


        NioDatagramChannel ch = (NioDatagramChannel)b.bind(1234).sync().channel();
//        ch.joinGroup(groupAddress, ni).sync();

        new Thread(){
            public void run() {
                while (true){
                    try {
                        Thread.sleep(1000);
                        myHandler.sendMessage("Hola desde netty");
                        System.out.println("Envio Hola");
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        ch.closeFuture().await();
    }

}
