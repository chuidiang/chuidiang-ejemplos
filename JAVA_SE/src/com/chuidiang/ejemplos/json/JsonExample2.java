package com.chuidiang.ejemplos.json;

import java.util.Date;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class JsonExample2 {

   public static void main(String[] args) {
      // to JSON primitive
      JsonPrimitive primitive = new JsonPrimitive(Boolean.TRUE);
      System.out.println(primitive);
      
      // to json object
      JsonObject object = new JsonObject();
      object.addProperty("name", "Juan");
      object.addProperty("age", 22);
      object.addProperty("birthday", new Date().toString());
      System.out.println(object.toString());

      // to json array
      JsonArray array = new JsonArray();
      array.add(object);
      array.add(object);
      System.out.println(array.toString());

      // Parse from primitive
      JsonParser parser = new JsonParser();
      
      JsonElement elementPimitive = parser.parse("true");
      if (elementPimitive.isJsonPrimitive() &&
            elementPimitive.getAsJsonPrimitive().isBoolean()){
         System.out.println(elementPimitive.getAsBoolean());
      }
      
      JsonElement elementObject = parser.parse("{'name':'Juan','age':22,'birthday':'Wed Feb 26 20:39:53 CET 2014'}");
      System.out.println(elementObject.getAsJsonObject().get("name").getAsString());
      
      JsonElement arrayElement = parser.parse(
            "[{'name':'Juan','age':22,'birthday':'Wed Feb 26 20:44:23 CET 2014'},"
            + "{'name':'Juan','age':22,'birthday':'Wed Feb 26 20:39:53 CET 2014'}]");
      System.out.println(arrayElement.getAsJsonArray().size());
      System.out.println(arrayElement.getAsJsonArray().get(0).getAsJsonObject().get("age").getAsInt());
   }

}
