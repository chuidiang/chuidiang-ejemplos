package com.chuidiang.examples.ejb.observer;

import java.io.Serializable;

public class DataObserved implements Serializable {
   @Override
   public String toString() {
      return "DataObserved [aNumber=" + aNumber + ", aString=" + aString + "]";
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
