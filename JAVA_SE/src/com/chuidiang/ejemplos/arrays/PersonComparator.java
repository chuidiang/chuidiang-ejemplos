package com.chuidiang.ejemplos.arrays;

import java.util.Comparator;

public class PersonComparator implements Comparator<Person>{

   @Override
   public int compare(Person o1, Person o2) {
      return (o1.surname.compareTo(o2.surname));
   }

}
