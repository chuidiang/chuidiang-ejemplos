package com.chuidiang.examples;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("theBean")
public class OneBean {
   @Value("${string:default text}")
   private String aString;
   
   @Value("${int:-22}")
   private int aInt;
   
   @Value("${bool:true}")
   private boolean aBool;
   
   public String getaString() {
      return aString;
   }
   public void setaString(String aString) {
      this.aString = aString;
   }
   public int getaInt() {
      return aInt;
   }
   public void setaInt(int aInt) {
      this.aInt = aInt;
   }
   public boolean isaBool() {
      return aBool;
   }
   public void setaBool(boolean aBool) {
      this.aBool = aBool;
   }
}
