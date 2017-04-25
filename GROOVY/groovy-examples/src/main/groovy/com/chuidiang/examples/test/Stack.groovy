package com.chuidiang.examples.test

/**
 * Created by JAVIER on 25/04/2017.
 */
class Stack {
    java.util.Stack internalQueue = [] as java.util.Stack

    def pop(){
        internalQueue.pop()
    }

    def push(item){
        internalQueue.push(item)
    }

    int size() {
        internalQueue.size()
    }
}
