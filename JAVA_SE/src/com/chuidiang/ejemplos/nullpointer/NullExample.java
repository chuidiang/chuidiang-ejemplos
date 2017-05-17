package com.chuidiang.ejemplos.nullpointer;

import java.io.File;

/**
 * Created by chuidiang on 17/05/17.
 */
public class NullExample {
    public static void main(String[] args) {
        NullExample a = new NullExample();
        a.method1(a);

        // Para que de NullPointerException
        // a.method1(null);

        // Para que de NullPointerException
        // new File((String)null);
    }

    public void method1(NullExample b){
        b.method2();
    }

    public void method2(){
        System.out.println("Hello!!");
    }


}
