package com.chuidiang.examples

import groovy.transform.TypeChecked


/**
 * Created by JAVIER on 15/03/2017.
 */
@TypeChecked
class Files {
    static void main(String[] args){

        // File Read

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

        // File write
        new File('target/output.txt').write 'One line of the file\nAnother line '

        new File('target/output.txt').text < 'One line of the file\nAnother line '

        lines = ['one','two','three']
        def file = new File('target/output.txt')

        lines.each { line ->
            file << "$line\n"
        }

        lines.each { line ->
            file.append "$line\n"
        }

        file.withPrintWriter { writer ->
            lines.each { line ->
                writer.println line
            }
        }

        file.withPrintWriter('utf-8') { writer ->
            lines.each { line ->
                writer.println line
            }
        }

    }
}
