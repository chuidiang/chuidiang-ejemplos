package com.chuidiang.examples.osgi_server;

import com.chuidiang.examples.osgi_interface.Adder;

public class AdderImpl implements Adder {

   @Override
   public int add(int a, int b) {
      return a+b;
   }

}
