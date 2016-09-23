package com.chuidiang.ejemplos.java_nio;

public class LinePrinter  {
   int counter = 1;
   public void println(String theString){
      System.out.println(counter++ +" " + theString);
   }
}
