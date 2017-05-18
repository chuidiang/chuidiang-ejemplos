package com.chuidiang.examples

/**
 * Created by chuidiang on 18/05/17.
 */
class ExpandoExamples {
    static void main(String[]args){
        // Adding a method to java String
        String.metaClass.print = { println delegate }

        "Hello".print()

        // Capturing calls to not existing methods on Java String
        String.metaClass.methodMissing { String name, theArgs ->
            println "${name}(${theArgs}) doesn't exist  :("
        }

        "Hello".yeeee("haaaa")

        // Capturing calls to not existings methods on own classes
        MyClass myInstance = new MyClass();
        myInstance.someMethod("Some parameter", "Another Parameter")

    }
}

class MyClass {
    def methodMissing(String name, args){
        println "${name}(${args}) doesn't exist  :("
    }
}
