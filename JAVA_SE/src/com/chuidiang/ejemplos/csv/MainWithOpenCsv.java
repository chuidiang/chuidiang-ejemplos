package com.chuidiang.ejemplos.csv;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class MainWithOpenCsv {

   public static final char SEPARATOR=';';
   public static final char QUOTE='"';

   public static void main(String[] args) {

      CSVReader reader = null;
      try {
         reader = new CSVReader(new FileReader("files/Libro2.csv"),SEPARATOR,QUOTE);
         String[] nextLine=null;
         
         while ((nextLine = reader.readNext()) != null) {
            System.out.println(Arrays.toString(nextLine));
         }
         
      } catch (Exception e) {
         System.err.println("Error!! " + e.getMessage());
      } finally {
         if (null != reader) {
            try {
               reader.close();
            } catch (IOException e) {
               System.err.println("Error closing file!! " + e.getMessage());
            }
         }
      }
   }

}
