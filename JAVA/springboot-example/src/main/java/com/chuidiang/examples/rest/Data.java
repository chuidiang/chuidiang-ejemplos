package com.chuidiang.examples.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Data {

   private List<Greeting> dataList = new ArrayList<Greeting>();
   private static final String[] content = new String[] { "Pedro", "Juan",
         "Antonio" };
   private static int counter = 0;

   public Data() {
      for (int i = 0; i < content.length; i++) {
         dataList.add(new Greeting(counter++, content[i]));
      }
   }

   public Greeting getGreeting(int index) throws IndexOutOfBoundsException {
      return dataList.get(index);
   }

   public List<Greeting> getGreetings() {
      return dataList;
   }

   public Greeting addGreeting(String content) {
      Greeting greeting = new Greeting(counter++, content);
      dataList.add(greeting);
      return greeting;
   }

   public void removeGreeting(int index) throws IndexOutOfBoundsException {
      dataList.remove(index);
   }

   public Greeting updateGreeting(int index, Greeting greeting)
         throws IndexOutOfBoundsException {
      dataList.set(index, greeting);
      return dataList.get(index);
   }
}
