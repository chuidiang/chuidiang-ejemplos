package com.chuidiang.examples

import groovy.transform.ToString

/**
 * Created by JAVIER on 25/03/2017.
 */
class Classes {
    static void main(String [] args){
        AClass a = new AClass(anInt:2, aDate:new Date())
        println a
        println a.getAnInt()

        a.with {
            println anInt
            println anString
            println aDate
            println something
            println aDouble
        }
    }

    @ToString(includeNames = true)
    static class AClass {
        int anInt
        String anString
        Date aDate
        def something
        double aDouble
    }
}
