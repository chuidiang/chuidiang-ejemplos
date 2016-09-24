package com.chuidiang.ejemplos.java_nio;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Stream;

public class WriteFile {

   public static void main(String[] args) throws IOException {
      writeWithFileChannel();
      writeWithFilesAndStream();
   }

   private static void writeWithFiles() {
      String[] lines = new String[] { "line 1", "line 2", "line 2" };
      Path path = Paths.get("outputfile.txt");
      try (BufferedWriter br = Files.newBufferedWriter(path,
            Charset.defaultCharset(), StandardOpenOption.CREATE)) {
         for (String line : lines) {
            br.write(line);
            br.newLine();
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static void writeWithFilesAndStream() {
      String[] lines = new String[] { "line 1", "line 2", "line 2" };
      Path path = Paths.get("outputfile.txt");
      try (BufferedWriter br = Files.newBufferedWriter(path,
            Charset.defaultCharset(), StandardOpenOption.CREATE)) {
         Arrays.stream(lines).forEach((s) -> {
            try {
               br.write(s);
               br.newLine();
            } catch (IOException e) {
               throw new UncheckedIOException(e);
            }

         });
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private static void writeWithFileChannel() throws IOException {
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
