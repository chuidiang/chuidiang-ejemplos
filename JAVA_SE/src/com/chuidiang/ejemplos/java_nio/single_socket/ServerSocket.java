package com.chuidiang.ejemplos.java_nio.single_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocket {
   public static void main(String[] args) throws IOException {
      ServerSocketChannel server = ServerSocketChannel.open();
      server.bind(new InetSocketAddress("127.0.0.1", 5557));
      while (true) {
         SocketChannel client = server.accept();
         new Thread(new Client(client)).start();
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
         while (channel.read(buffer) > 0) {
            
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
         try {
            channel.close();
            System.out.println("Channel closed");
         } catch (IOException e1) {
            e1.printStackTrace();
         }
      }
   }

}
