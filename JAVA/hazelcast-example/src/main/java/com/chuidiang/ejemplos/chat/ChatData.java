package com.chuidiang.ejemplos.chat;

import java.io.Serializable;
import java.util.Date;

public class ChatData implements Serializable {
   @Override
   public String toString() {
      return "" + date + " " + user + ": " + text;
   }
   public Date date;
   public String user;
   public String text;
}
