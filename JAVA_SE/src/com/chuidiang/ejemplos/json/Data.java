package com.chuidiang.ejemplos.json;

import java.util.Date;

public class Data {
   
   @Override
   public String toString() {
      return "Data [aString=" + aString + ", aInt=" + aInt + ", aInteger="
            + aInteger + ", aDate=" + aDate + "]";
   }
   public Data(String aString, int aInt, Integer aInteger, Date aDate) {
      super();
      this.aString = aString;
      this.aInt = aInt;
      this.aInteger = aInteger;
      this.aDate = aDate;
   }
   private String aString="a String";
   private int aInt=22;
   private Integer aInteger=new Integer(11);
   private Date aDate = new Date();
}
