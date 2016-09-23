package com.chuidiang.ejemplos.java_nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ReadFile {

   public static void main(String[] args) throws IOException {
      Path path = Paths
            .get("src/com/chuidiang/ejemplos/java_nio/ReadFile.java");
      FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
      ByteBuffer buffer = ByteBuffer.allocate(100);

      try {
         int bytesRead = channel.read(buffer);
         while (bytesRead != -1) {
            buffer.flip();
            while (buffer.hasRemaining()) {
               System.out.print((char) buffer.get());
            }
            buffer.compact();
            bytesRead = channel.read(buffer);
         }
      } catch (Exception e) {
         channel.close();
      }
   }

}
