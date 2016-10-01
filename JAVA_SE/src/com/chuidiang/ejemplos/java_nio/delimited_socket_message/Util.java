package com.chuidiang.ejemplos.java_nio.delimited_socket_message;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Util {
   public static void print(ByteBuffer bb) {
      System.out.println("position =" + bb.position() + ", rmaining="
            + bb.remaining() + ", limit=" + bb.limit());
   }
   public static void close(Closeable closeable){
      try {
         closeable.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
