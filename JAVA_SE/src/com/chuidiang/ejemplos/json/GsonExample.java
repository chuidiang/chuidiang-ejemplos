package com.chuidiang.ejemplos.json;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExample {

   private static Data[] dataArray;
   private static List<Data> dataList;

   public static void main(String[] args) {
      initData();
      toJsonExamples();
      fromJsonExamples();
      dateFormatExamples();
   }

   private static void initData() {
      dataArray = new Data[3];
      for (int i = 0; i < dataArray.length; i++) {
         dataArray[i] = new Data("a String " + i, i, new Integer(i + 2),
               new Date());
      }
      dataList = new LinkedList<>();
      for (Data dato : dataArray) {
         dataList.add(dato);
      }
   }

   private static void toJsonExamples() {
      Gson gson = new Gson();

      System.out.println(gson.toJson(new Data("string", 10, null, new Date())));
      System.out.println(gson.toJson(dataArray));
      System.out.println(gson.toJson(dataList));
   }

   private static void fromJsonExamples() {
      Gson gson = new Gson();
      String json = "{'aString':'from Parsed String','aInt':33,'aInteger':null,'aDate':'Feb 26, 2014 7:35:23 PM'}";

      Data parsedData = gson.fromJson(json, Data.class);
      System.out.println(parsedData.toString());

      String jsonList = gson.toJson(dataList);

      List<Data> parsedDataList = (List<Data>) gson.fromJson(jsonList, List.class);
      for (int i = 0; i < parsedDataList.size(); i++) {
         System.out.print(parsedDataList.get(i));
      }
      System.out.println();

      Data[] parsedDataArray = gson.fromJson(jsonList, Data[].class);
      for (Data data : parsedDataArray) {
         System.out.print(data);
      }
      System.out.println();
   }

   private static void dateFormatExamples() {
      Gson gson = new GsonBuilder().setDateFormat("dd/MM/yy HH:mm:ss").create();

      System.out.println(gson.toJson(new Date()));
   }

}
