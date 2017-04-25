package com.chuidiang.examples.test

import com.chuidiang.examples.NegativeResultException

/**
 * Created by JAVIER on 25/04/2017.
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
        return a-b
    }

}
