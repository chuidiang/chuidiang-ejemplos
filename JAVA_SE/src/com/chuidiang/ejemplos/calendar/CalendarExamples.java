package com.chuidiang.ejemplos.calendar;

import java.util.Calendar;
import java.util.Locale;

public class CalendarExamples {

   public static void main(String[] args) {
      today();
      setDate();
      getDate(Locale.getDefault());
      getDate(Locale.GERMAN);
      addSubstractDays();
      compareDates();
   }

   private static void compareDates() {
      Calendar today = Calendar.getInstance();
      Calendar after = Calendar.getInstance();
      after.add(Calendar.HOUR_OF_DAY, 2);

      Calendar before = Calendar.getInstance();
      before.add(Calendar.HOUR_OF_DAY, -5);

      System.out.println("Today is after today+2hours " + today.after(after));
      System.out.println("Today is before today+2hours " + today.before(after));
      System.out.println("Today is after today-5hours " + today.after(before));
      System.out
            .println("Today is before today-5hours " + today.before(before));
   }

   private static void getDate(Locale locale) {
      Calendar today = Calendar.getInstance(locale);
      System.out.println("Year : " + today.get(Calendar.YEAR));
      System.out.println("Month (0 is January): " + today.get(Calendar.MONTH));
      System.out.println("Month (String): "
            + today.getDisplayName(Calendar.MONTH, Calendar.SHORT,
                  locale));
      System.out.println("Day of Month : " + today.get(Calendar.DAY_OF_MONTH));
      System.out.println("Day of Week (0 is Sunday): "
            + today.get(Calendar.DAY_OF_WEEK));
      System.out.println("Day of Week (String): "
            + today.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG,
                  locale));
      System.out.println("Week of Year : "+today.get(Calendar.WEEK_OF_YEAR));
      System.out.println("Week of Month : "+today.get(Calendar.WEEK_OF_MONTH));
      System.out.println("Day of Year : " + today.get(Calendar.DAY_OF_YEAR));
      System.out.println("24-hour clock : " + today.get(Calendar.HOUR_OF_DAY));
      System.out.println("12-hour clock : " + today.get(Calendar.HOUR));
      System.out.println("AM/PM : " + today.get(Calendar.AM_PM));
      System.out.println("AM/PM : "
            + today.getDisplayName(Calendar.AM_PM, Calendar.LONG,
                  locale));
      System.out.println("Minutes : " + today.get(Calendar.MINUTE));
      System.out.println("Seconds : " + today.get(Calendar.SECOND));
      System.out.println("MiliSeconds : " + today.get(Calendar.MILLISECOND));
   }

   private static void today() {
      Calendar today = Calendar.getInstance();
      System.out.println("today : " + today.getTime());
   }

   private static void setDate() {
      Calendar sameDate = Calendar.getInstance();

      sameDate.set(Calendar.YEAR, 2010);
      // Month. 0 is January, 11 is November
      sameDate.set(Calendar.MONTH, Calendar.AUGUST);
      sameDate.set(Calendar.DAY_OF_MONTH, 23);

      // Either 12-hour clock plus AM/PM
      sameDate.set(Calendar.HOUR, 10);
      sameDate.set(Calendar.AM_PM, Calendar.PM);
      // or 24-hour clock
      sameDate.set(Calendar.HOUR_OF_DAY, 22);

      sameDate.set(Calendar.MINUTE, 36);
      sameDate.set(Calendar.SECOND, 22);
      sameDate.set(Calendar.MILLISECOND, 123);

      System.out.println("Some Date : " + sameDate.getTime());
   }

   private static void addSubstractDays() {
      Calendar today = Calendar.getInstance();
      today.add(Calendar.DAY_OF_MONTH, 20);
      System.out.println("Today plus 20 days : " + today.getTime());

      today = Calendar.getInstance();
      today.add(Calendar.DAY_OF_MONTH, -20);
      System.out.println("Today minus 20 days : " + today.getTime());

   }

}
