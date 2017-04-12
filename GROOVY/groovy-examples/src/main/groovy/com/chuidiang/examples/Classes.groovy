package com.chuidiang.examples

import groovy.transform.ToString

/**
 * Created by JAVIER on 25/03/2017.
 */
class Classes {
    static void main(String [] args){
        AClass a = new AClass(anInt:2, aDate:new Date())
        AClass b = new AClass()
        println a
        println a.getAnInt()
        println b
        println b.getaDate()

        a.with {
            println anInt
            println anString
            println aDate
            println something
            println aDouble
        }

        a.anInt = 3
        println a.anInt

        a.setAnInt(11)
        println a.getAnInt()
    }

}

@ToString(includeNames = true, excludes = ['something','aDouble'])
class AClass {
    int anInt
    String anString
    Date aDate
    def something
    double aDouble
}

