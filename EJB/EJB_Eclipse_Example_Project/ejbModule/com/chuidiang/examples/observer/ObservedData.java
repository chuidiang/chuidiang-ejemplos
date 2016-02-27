package com.chuidiang.examples.observer;

public class ObservedData {
   @Override
   public String toString() {
      return "ObservedData [aNumber=" + aNumber + ", aString=" + aString + "]";
   }
   private int aNumber;
   private String aString;
   public int getaNumber() {
      return aNumber;
   }
   public void setaNumber(int aNumber) {
      this.aNumber = aNumber;
   }
   public String getaString() {
      return aString;
   }
   public void setaString(String aString) {
      this.aString = aString;
   }
   
}
