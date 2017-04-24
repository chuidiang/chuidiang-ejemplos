package com.chuidiang.examples

/**
 * Created by JAVIER on 25/03/2017.
 */
class Calculator {
    static int counter=0;
    Calculator(){
        counter++
        println "new instance of Calculator : $counter"
    }
    int add (int a, int b){
        return a+b
    }

    int subs (int a, int b){
        if (b>a) throw new NegativeResultException()
        return a-b
    }
}
