package com.chuidiang.examples.test

/**
 * Created by JAVIER on 29/04/2017.
 */
class StringComparision {
    static void main(String [] args){
        String a = new String('hola')
        String b = new String('hola')
        String c = null

        assert a==b
        assert c!=b
        assert b!=c

        b=""

        // A null or empty String is false
        assert a
        assert !b
        assert !c
    }
}
