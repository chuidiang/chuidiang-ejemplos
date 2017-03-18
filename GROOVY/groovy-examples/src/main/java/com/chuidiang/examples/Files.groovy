package com.chuidiang.examples

/**
 * Created by JAVIER on 15/03/2017.
 */
class Files {
    static void main(String[] args){
        new File("pom.xml").eachLine { line -> println line}

        println "--------------------"
        println new File("pom.xml").text


        // copy text file
        def sourceFile = new File("pom.xml")
        new File("pom.copy.xml").withPrintWriter { writer ->
            sourceFile.eachLine { line ->
                writer.println(line)
            }
        }

        def destinationFile = new File("pom.copy.2.xml")
        destinationFile << sourceFile.text
    }
}
