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

public class WriteFile {

   public static void main(String[] args) throws IOException {
      writeWithFileChannel();
      writeWithFiles();
      writeWithFilesAndStream();
      writeWithFileChannelMultiple();
   }

   private static void writeWithFiles() {
      String[] lines = new String[] { "line 1", "line 2", "line 2" };
      Path path = Paths.get("outputfile.txt");
      try (BufferedWriter br = Files.newBufferedWriter(path,
            Charset.defaultCharset(), StandardOpenOption.WRITE)) {
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
      try (BufferedWriter bw = Files.newBufferedWriter(path,
            Charset.defaultCharset(), StandardOpenOption.WRITE)) {
         Arrays.stream(lines).forEach((s) -> {
            try {
               bw.write(s);
               bw.newLine();
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

   private static void writeWithFileChannelMultiple() throws IOException {
      Path path = Paths.get("target/outputfile.txt");
      try (FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE,
              StandardOpenOption.CREATE)){
         ByteBuffer [] buffers = {ByteBuffer.wrap("Hello World 1".getBytes()),
            ByteBuffer.wrap("Hello 2".getBytes()), ByteBuffer.wrap("HW 3".getBytes())};

         channel.write(buffers);
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
