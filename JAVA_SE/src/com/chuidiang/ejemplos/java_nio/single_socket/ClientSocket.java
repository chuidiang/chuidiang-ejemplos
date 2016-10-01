package com.chuidiang.ejemplos.java_nio.single_socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ClientSocket {
   public static void main(String[] args) {

      try (SocketChannel channel = SocketChannel
            .open(new InetSocketAddress("127.0.0.1", 5557))) {

         // Non blocking mode
         // channel.configureBlocking(false);

         String[] lines = new String[] { "line 1", "line 2", "line 3" };
         ByteBuffer buffer = ByteBuffer.allocate(100);
         for (String line : lines) {
            buffer.put(line.getBytes());
            buffer.flip();

            // loop is only needed if channel is in non blocking mode.
            while (buffer.hasRemaining()) {
               channel.write(buffer);
            }

            buffer.clear();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
