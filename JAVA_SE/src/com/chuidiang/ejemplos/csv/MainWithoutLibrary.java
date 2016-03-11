package com.chuidiang.ejemplos.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * Example to parse simple CSV files
 * @author Chuidiang
 *
 */
public class MainWithoutLibrary {
   
   public static final String SEPARATOR=";";
   public static final String QUOTE="\"";
   
   public static void main(String[] args) {
      
      BufferedReader br = null;
      
      try {
         
         br =new BufferedReader(new FileReader("files/Libro1.csv"));
         String line = br.readLine();
         while (null!=line) {
            String [] fields = line.split(SEPARATOR);
            System.out.println(Arrays.toString(fields));
            
            fields = removeTrailingQuotes(fields);
            System.out.println(Arrays.toString(fields));
            
            line = br.readLine();
         }
         
      } catch (Exception e) {
         System.err.println("Error! "+e.getMessage());
      } finally {
         if (null!=br){
            try {
               br.close();
            } catch (IOException e) {
               System.err.println("Error closing file !! "+e.getMessage());
            }
         }
      }
      
   }
   
   private static String[] removeTrailingQuotes(String[] fields) {
      String result[] = new String[fields.length];
      for (int i=0;i<result.length;i++){
         result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
      }
      return result;
   }

}
