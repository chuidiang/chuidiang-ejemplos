package com.chuidiang.ejemplos.java_nio.delimited_socket_message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Comentario para javadoc
 */
public class ServerSocket {
   /** Çomentario para javadoc */
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
         while (channel.read(buffer) > -1) {
            buffer.flip();
            int beginMessage=0;
            int endMessage=0;
            while (buffer.hasRemaining()) {
               if (buffer.get()==ClientSocket.DELIMITER_BYTE){
                  endMessage = buffer.position();
                  byte[] data = new byte[endMessage-beginMessage];
                  buffer.position(beginMessage);
                  buffer.get(data,0,data.length);
                  System.out.println("-"+new String(data));
                  beginMessage=buffer.position();
               }
            }
            buffer.compact();
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
