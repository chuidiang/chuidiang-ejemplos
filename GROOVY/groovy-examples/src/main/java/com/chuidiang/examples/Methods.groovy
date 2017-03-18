package com.chuidiang.examples

/**
 * Created by Chuidiang on 18/03/2017.
 */
class Methods {
    static void main(String[]args){
        // This works....
        int a = 3.5
        println a

        // .... but this throws
        // a Runtime Exception :(
        method 3.5
    }

    static method (int a){
        println a
    }
}
