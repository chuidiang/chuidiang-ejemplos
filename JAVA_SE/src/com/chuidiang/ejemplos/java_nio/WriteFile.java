package com.chuidiang.ejemplos.java_nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class WriteFile {

   public static void main(String[] args) throws IOException {
      Path path = Paths.get("outputfile.txt");
      FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE,
            StandardOpenOption.CREATE);

      try {
         ByteBuffer buffer = ByteBuffer.wrap("Hello World".getBytes());
         channel.write(buffer);
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         channel.close();
      }
   }

}
