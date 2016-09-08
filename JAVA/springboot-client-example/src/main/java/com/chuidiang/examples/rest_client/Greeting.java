package com.chuidiang.examples.rest_client;
public class Greeting {

   @Override
   public String toString() {
      return "Greeting [id=" + id + ", content=" + content + "]";
   }

   private long id;
   private String content;

   public Greeting(){
      
   }
   public Greeting(long id, String content) {
       this.id = id;
       this.content = content;
   }

   public long getId() {
       return id;
   }

   public String getContent() {
       return content;
   }
   
   public void setContent(String content) {
      this.content = content;
   }
}