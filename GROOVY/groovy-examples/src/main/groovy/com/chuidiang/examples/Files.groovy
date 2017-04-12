package com.chuidiang.examples



/**
 * Created by JAVIER on 15/03/2017.
 */
class Files {
    static void main(String[] args){
        new File("pom.xml").eachLine { line -> println line}

        new File("pom.xml").eachLine ( "utf-8") {
            line -> println line}

        def lines = new File('pom.xml') as String[]
        println lines

        lines = new File('pom.xml').collect {it}
        println lines

        println "--------------------"
        println new File("pom.xml").text
        println new File("pom.xml").getText('utf-8')


        // copy text file
        def sourceFile = new File("pom.xml")
        new File("target/pom.copy.xml").withPrintWriter { writer ->
            sourceFile.eachLine { line ->
                writer.println(line)
            }
        }

        def destinationFile = new File("target/pom.copy.2.xml")
        destinationFile << sourceFile.text

        lines = ['uno','dos','tres']
        File file = new File('target/output.txt')
        lines.each {
            file.text < it
        }
    }
}
