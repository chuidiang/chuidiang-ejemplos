package com.chuidiang.ejemplos.string;

public class RemoveSpacesFromString {

   public static void main(String[] args) {
      String text = "   Esto es un texto con espacios y \t tabuladores y \n retornos de carro  \n  ";
      
      
      // Elimina todos los espacios, pero no los tabuladores ni retornos
      System.out.println("--"+text.replace(" ","")+"--");
      System.out.println();
      
      // Elimina espacios, tabuladores, retornos
      System.out.println("--"+text.replaceAll("\\s","")+"--");
      System.out.println();
      
      // Elimina espacios delante y detras
      System.out.println("--"+text.trim()+"--");
      System.out.println();
      
      // Elimina espacios, tabuladores y retornos delante.
      System.out.println("--"+text.replaceAll("^\\s*","")+"--");
      System.out.println();
      
      // Elimina espacios, tabuladores y retornos detrás.
      System.out.println("--"+text.replaceAll("\\s*$","")+"--");
      System.out.println();
   }

}
