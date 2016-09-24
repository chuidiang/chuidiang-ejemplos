package com.chuidiang.ejemplos.java_nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class ReadFile {

   public static String fileName = "src/com/chuidiang/ejemplos/java_nio/ReadFile.java";

   public static void main(String[] args) throws IOException {
      readAsBytes();
      readAsLines();
   }

   private static void readAsLines() {
      // Standard println
      try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
         stream.forEach(System.out::println);
      } catch (IOException e) {
         e.printStackTrace();
      }

      // My own method of my own class
      LinePrinter printer = new LinePrinter();
      try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
         stream.forEach(printer::println);
      } catch (IOException e) {
         e.printStackTrace();
      }

      // This works also
      // stream.forEach((s)->System.out.println(s));

      // With BufferedReader
      try (BufferedReader reader = Files
            .newBufferedReader(Paths.get(fileName))) {
         reader.lines().forEach(System.out::println);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private static void readAsBytes() {
      Path path = Paths
            .get("src/com/chuidiang/ejemplos/java_nio/ReadFile.java");

      ByteBuffer buffer = ByteBuffer.allocate(100);
      String fileEncoding = System.getProperty("file.encoding");

      try (FileChannel channel = FileChannel.open(path,
            StandardOpenOption.READ)) {

         while (channel.read(buffer) > 0) {
            buffer.flip();
            System.out.print(Charset.forName(fileEncoding).decode(buffer));
            buffer.compact();

         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
