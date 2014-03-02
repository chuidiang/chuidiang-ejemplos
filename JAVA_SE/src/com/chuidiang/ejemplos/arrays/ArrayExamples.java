package com.chuidiang.ejemplos.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayExamples {

   public static void main(String[] args) {
      primitiveTypesArray();
      objectArray();
      systemArrayCopy();
      sortArray();
      personSearch();
      arraysCopyOf();
      arraysEquals();
      arraysFill();
   }

   private static void arraysFill() {
      System.out.println("fill() examples");

      String[] array = new String[10];
      Arrays.fill(array, 2, 5, "hello");
      System.out.println(Arrays.toString(array));

      Arrays.fill(array, "good bye");
      System.out.println(Arrays.toString(array));
   }

   private static void arraysEquals() {
      System.out.println("equals() vs deepEquals() examples");

      String[] objectArray = { new String("uno"), new String("dos") };
      String[] anotherObjectArray = { new String("uno"), new String("dos") };
      System.out.println("Subarrays are equals : "+Arrays.equals(objectArray, anotherObjectArray));

      Object[] oneSubArray = { "cuatro-1", "cuatro-2" };
      Object[] anotherSubArray = { "cuatro-1", "cuatro-2" };
      Object[] oneArray = { "uno", "dos", "tres", oneSubArray };
      Object[] anotherArray = { "uno", "dos", "tres", anotherSubArray };
      System.out.println("equals " + Arrays.equals(oneArray, anotherArray));
      System.out.println("deepEquals " + Arrays.deepEquals(oneArray, anotherArray));
   }

   private static void arraysCopyOf() {
      System.out.println("Arrays.copyOf() example");
      double[] sourceArray = { 1.1, 2.2, 3.3, 4.4 };
      double[] destinationArray = Arrays.copyOf(sourceArray, 5);
      System.out.println("Copy : "+Arrays.toString(destinationArray));

      double[] anotherDestinationArray = Arrays.copyOfRange(sourceArray, 3, 10);
      System.out.println("Another copy : " +Arrays.toString(anotherDestinationArray));
   }

   private static void sortArray() {
      System.out.println("sort() example");
      Double[] anArray = new Double[5];
      for (int i = 0; i < anArray.length; i++) {
         anArray[i] = Math.random();
      }
      System.out.println("Unsorted : "+Arrays.toString(anArray));
      Arrays.sort(anArray, new Comparator<Double>() {

         @Override
         public int compare(Double o1, Double o2) {
            if (o2 > o1) {
               return 1;
            }
            if (o1 > o2) {
               return -1;
            }
            return 0;
         }
      });
      System.out.println("Sorted * "+Arrays.toString(anArray));
   }

   private static void personSearch() {
      System.out.println("binarySearc() example");
      String[] surname = { "Garcia", "Gomez", "Lopez", "Rubio", "Fernandez",
            "Roxas", "Morales" };
      Person[] personArray = new Person[surname.length];
      for (int i = 0; i < surname.length; i++) {
         personArray[i] = new Person();
         personArray[i].name = "Name" + i;
         personArray[i].surname = surname[i];
         personArray[i].age = (int) (Math.random() * 20 + 10);
      }
      Arrays.sort(personArray, new PersonComparator());
      System.out.println("Sorted persons : "+Arrays.toString(personArray));

      Person searchedPerson = new Person();
      searchedPerson.surname = "Rubio";
      System.out.println("Rubio's position : "+Arrays.binarySearch(personArray, searchedPerson,
            new PersonComparator()));
   }

   private static void systemArrayCopy() {
      System.out.println("System.arrayCopy() examples");
      Boolean[] sourceArray = new Boolean[10];
      for (int i = 0; i < sourceArray.length; i++) {
         sourceArray[i] = Boolean.TRUE;
      }
      Boolean[] destinationArray = new Boolean[4];
      System.arraycopy(sourceArray, 0, destinationArray, 0, 4);
      System.out.println("Copy of source : "+Arrays.toString(destinationArray));

      Person aPerson = new Person();
      aPerson.name = "Juan";
      aPerson.surname = "Lopez";
      aPerson.age = 31;
      Person[] sourcePersonArray = { aPerson };
      Person[] destinationPersonArray = new Person[1];
      System.arraycopy(sourcePersonArray, 0, destinationPersonArray, 0, 1);
      System.out.println("It's same instance = "
            + (sourcePersonArray[0] == destinationPersonArray[0]));
      sourcePersonArray[0].name = "Pedro";
      System.out.println("Destination is modified too : "+destinationPersonArray[0].name);
   }

   private static void objectArray() {
      System.out.println("Array of Objects");
      // Not initialized object array
      Boolean[] anArray = new Boolean[10];
      // Default value is null
      System.out.println("Not intitialized : "+Arrays.toString(anArray));

      // Initialization
      for (int i = 0; i < anArray.length; i++) {
         anArray[i] = Boolean.TRUE;
      }
      System.out.println("Initialized : "+Arrays.toString(anArray));

      // Creation and Intialization
      Boolean[] anotherArray = { Boolean.TRUE, Boolean.FALSE, Boolean.FALSE,
            Boolean.TRUE };
      System.out.println("Iinitialized : "+Arrays.toString(anotherArray));
   }

   private static void primitiveTypesArray() {
      System.out.println("Array of primitive types");
      // No initialized array
      int[] anArray = new int[10];
      System.out.println("Not initialized : "+Arrays.toString(anArray));

      // Creating and initializing an array
      int[] anotherArray = { 1, 33, 55, 33, 22, 55 };
      System.out.println("Initialized : "+Arrays.toString(anotherArray));
   }

}
