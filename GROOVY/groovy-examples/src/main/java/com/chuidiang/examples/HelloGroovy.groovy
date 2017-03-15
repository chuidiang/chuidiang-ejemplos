package com.chuidiang.examples

/**
 * Created by JAVIER on 15/03/2017.
 */
class HelloGroovy {
    static void main(String [] args){
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
    }
}
