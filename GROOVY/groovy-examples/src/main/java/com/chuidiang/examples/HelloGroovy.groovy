package com.chuidiang.examples

/**
 * Created by JAVIER on 15/03/2017.
 */
class HelloGroovy {
    static void main(String [] args){

        // This line compiles, but doesn't run.
        // println 1, 3

        println "Hello World!!"

        def array = [1,2,3]
        println array
        for (var in array){
            println var
        }

        def range = 0..5
        println range

        def hash = ["uno":1, "dos":2, "tres":3]
        for (number in hash){
            println number.key + " - " + number
        }

        def aString = "World"
        println "Hello \${aString}"
        println "Hello ${aString}"

        // a is statically typed
        // b is dynamically typed
        int a = 3
        def b = 3
        println a + " " + b

        a = 54.3
        b = 54.3
        println a + " " + b  // a prints integer 54

        // Repeat Hello three times
        println "Hello "*3

        println "-"+"Hello".center(30)+"-"

        range = 10..5
        println range.getFrom()

        regularExpressions()

    }

    private static void regularExpressions() {
        if ('Groovy' =~ 'G') {
            println "Are equals"
        } else {
            println "Are not equals"
        }
        if ('Groovy' ==~ 'Gro{2}vy') {
            println "Are equals"
        } else {
            println "Are not equals"
        }
    }
}
