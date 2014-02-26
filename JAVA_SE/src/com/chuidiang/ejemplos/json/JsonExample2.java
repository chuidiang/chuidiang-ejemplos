package com.chuidiang.ejemplos.json;

import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonExample2 {

   public static void main(String[] args) {
      JsonObject object = new JsonObject();
      object.addProperty("name", "Juan");
      object.addProperty("age", 22);
      object.addProperty("birthday", new Date().toString());
      System.out.println(object.toString());

      
      JsonParser parser = new JsonParser();
      JsonElement element = parser.parse("{'name':'Juan','age':22,'birthday':'Wed Feb 26 20:39:53 CET 2014'}");
      System.out.println(element.getAsJsonObject().get("name"));
      
      
      JsonArray array = new JsonArray();
      array.add(object);
      array.add(element);
      System.out.println(array.toString());
      
      JsonElement arrayElement = parser.parse("[{'name':'Juan','age':22,'birthday':'Wed Feb 26 20:44:23 CET 2014'},{'name':'Juan','age':22,'birthday':'Wed Feb 26 20:39:53 CET 2014'}]");
      System.out.println(arrayElement.getAsJsonArray().size());
      System.out.println(arrayElement.getAsJsonArray().get(0).getAsJsonObject().get("age"));
   }

}
