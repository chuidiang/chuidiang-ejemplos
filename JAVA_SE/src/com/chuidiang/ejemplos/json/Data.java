package com.chuidiang.ejemplos.json;

import java.util.Date;

public class Data {

   public Data(String aString, int aInt, Integer aInteger, Date aDate) {
      super();
      this.aString = aString;
      this.aInt = aInt;
      this.aInteger = aInteger;
      this.aDate = aDate;
   }

   private String aString;
   private int aInt;
   private Integer aInteger;
   private Date aDate;

   @Override
   public String toString() {
      return "Data [aString=" + aString + ", aInt=" + aInt + ", aInteger="
            + aInteger + ", aDate=" + aDate + "]";
   }

}
