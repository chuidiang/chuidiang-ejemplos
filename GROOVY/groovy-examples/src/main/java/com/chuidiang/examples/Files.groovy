package com.chuidiang.examples

/**
 * Created by JAVIER on 15/03/2017.
 */
class Files {
    static void main(String[] args){
        new File("pom.xml").eachLine { line -> println line}
        println "--------------------"
        println new File("pom.xml").text
    }
}
