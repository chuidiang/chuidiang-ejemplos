package com.chuidiang.ejemplos.jackson;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JackonPolymorphicMain {

   public static void main(String[] args) throws JsonProcessingException {
      ArrayList<AnInterface> childs = new ArrayList<>(2);
      childs.add(new AChildClass());
      childs.add(new AnotherChildClass());

      
      doBadMapper(childs);
      doGoodMapper(childs);


   }

   private static void doGoodMapper(ArrayList<AnInterface> childs)
         throws JsonProcessingException {
      ObjectMapper theGoodMapper = new ObjectMapper();
      theGoodMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
      theGoodMapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
      theGoodMapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);
      theGoodMapper.setVisibility(PropertyAccessor.SETTER, Visibility.NONE);
      theGoodMapper.setVisibility(PropertyAccessor.CREATOR, Visibility.NONE);
      theGoodMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

      String theJsonText = theGoodMapper.writeValueAsString(childs);
      System.out.println(theJsonText);
      try {
         ArrayList<AnInterface> reconstructedChilds = theGoodMapper.readValue(
               theJsonText, new TypeReference<ArrayList<AnInterface>>() {
               });
         System.out.println("The good mapper works ! ");
         System.out.println(reconstructedChilds);
      } catch (IOException e) {
         System.err.println("Bad mapper fails " + e.getMessage());
      }
   }

   private static void doBadMapper(ArrayList<AnInterface> childs)
         throws JsonProcessingException {
      ObjectMapper theBadMapper = new ObjectMapper();
      theBadMapper.setVisibility(PropertyAccessor.GETTER, Visibility.NONE);
      theBadMapper.setVisibility(PropertyAccessor.IS_GETTER, Visibility.NONE);
      theBadMapper.setVisibility(PropertyAccessor.SETTER, Visibility.NONE);
      theBadMapper.setVisibility(PropertyAccessor.CREATOR, Visibility.NONE);
      theBadMapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
      String theJsonText = theBadMapper.writeValueAsString(childs);
      System.out.println(theJsonText);
      try {
         ArrayList<AnInterface> reconstructedChilds = theBadMapper.readValue(
               theJsonText, new TypeReference<ArrayList<AnInterface>>() {
               });
         System.out.println(reconstructedChilds);
      } catch (IOException e) {
         System.err.println("Bad mapper fails " + e.getMessage());
      }
   }

}
