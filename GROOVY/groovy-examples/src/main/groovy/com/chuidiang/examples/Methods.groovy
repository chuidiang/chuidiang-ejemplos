package com.chuidiang.examples

import groovy.transform.TypeChecked

/**
 * Created by Chuidiang on 18/03/2017.
 */
class Methods {
    // @TypeChecked
    static void main(String[]args){
        // This works....
        int a = 3.5
        println a

        // .... but this throws
        // a Runtime Exception :(
        //method 3.5

        println ( method2 ({ it.toUpperCase()}))

        String str1="World"
        clos.call()
    }

    static method (int a){
        println a
    }

    static String method2 ( Closure a ){
        a("a Parameter")
    }

    static String str1 = "static"
    static Closure clos = {println "Hello ${str1}"}
}
