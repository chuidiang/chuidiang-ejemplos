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

public class MulticastClient {

    public static void main(String[] args) throws InterruptedException, SocketException, UnknownHostException {
        new MulticastClient().start();
    }

    private ClientHandler myHandler = new ClientHandler();

    public void start() throws InterruptedException, SocketException, UnknownHostException {
        NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getByName("127.0.0.1"));
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group)
            .channelFactory(new ChannelFactory<NioDatagramChannel>() {
                @Override
                public NioDatagramChannel newChannel() {
                    return new NioDatagramChannel(InternetProtocolFamily.IPv4);
                }
            })
//                .channel(NioDatagramChannel.class)
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel ch) throws Exception {
                        ch.pipeline().addLast(myHandler);
                    }
                })
                .option(ChannelOption.SO_REUSEADDR,true);
//        .option(ChannelOption.IP_MULTICAST_IF,ni);


        NioDatagramChannel ch = (NioDatagramChannel)b.bind("127.0.0.1",1234).sync().channel();
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
