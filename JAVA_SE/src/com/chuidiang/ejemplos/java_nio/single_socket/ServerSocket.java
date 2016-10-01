package com.chuidiang.ejemplos.java_nio.single_socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.chuidiang.ejemplos.java_nio.delimited_socket_message.Util;

public class ServerSocket {
   public static void main(String[] args) {

      try (ServerSocketChannel server = ServerSocketChannel.open()) {
         server.bind(new InetSocketAddress("0.0.0.0", 5557));
         while (true) {
            SocketChannel client = server.accept();
            new Thread(new Client(client)).start();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}

class Client implements Runnable {
   private SocketChannel channel;

   public Client(SocketChannel channel) {
      this.channel = channel;
   }

   @Override
   public void run() {
      System.out.println("client connected");
      ByteBuffer buffer = ByteBuffer.allocate(100);
      try {
         // read bytes into buffer
         while (channel.read(buffer) > -1) {

            // buffer position to 0 and limit to actual position.
            buffer.flip();

            // get all bytes
            while (buffer.hasRemaining()) {
               System.out.print((char) buffer.get());
            }
            System.out.println();

            // clear buffer.
            buffer.clear();
         }
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         Util.close(channel);
         System.out.println("Channel closed");
      }
   }

}
