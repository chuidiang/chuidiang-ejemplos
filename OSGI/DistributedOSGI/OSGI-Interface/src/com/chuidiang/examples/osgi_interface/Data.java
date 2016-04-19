package com.chuidiang.examples.osgi_interface;

import java.io.IOException;
import java.io.Serializable;

public class Data implements Serializable {
   public double value;
   
   private void readObject(java.io.ObjectInputStream stream)
         throws IOException, ClassNotFoundException
    {
      System.out.println("Me construyen");
       value = stream.readDouble();
    }

    private void writeObject(java.io.ObjectOutputStream stream)
         throws IOException
    {
       System.out.println("Me destruyen");
       stream.writeDouble(value);
    }

   @Override
   public String toString() {
      return "Data [value=" + value + "]";
   }
}
