package com.chuidiang.examples

/**
 * Created by JAVIER on 04/04/2017.
 */
class RegExpExamples {
    static void main(String [] args){
        def a = /b/
        println a.getClass()

        a = ~/b/
        println a.getClass()

        a = ~"b"
        println a.getClass()

        // Contains pattern
        assert "wsdl2java" =~ "\\d"        // true, "wsdl2java" contiene un dígito \d
        assert "wsdl2java" =~ /\d/         // true, "wsdl2java" contiene un dígito \d


        // Match patter exactly
        assert "123" ==~ "\\d+"        // true, "123" contiene uno o más dígitos y nada más.
        assert "123" ==~ /\d+/         // true, "wsdl2java" contiene uno o más dígitos y nada más


        assert 'abc' ==~ /abc/
        assert ! ('abc' ==~ /b/ )

        // group
        assert 'aba' ==~ /(.)b\1/
        assert ! ('abc' ==~ /(.)b\1/)

        // extract matches
        def matcher = '11/3/2017' =~ /\d+/
        matcher.each {
            println it
        }

        // extract groups
        matcher = 'abc ebf' =~ /(.)b(.)/
        matcher.each {
            println "In ${it[0]}, I found ${it[1]} and ${it[2]}"
        }

        matcher = "11/3/2017 4/10/2015 y 22/1/2018" =~ $/(\d{1,2})/(\d{1,2})/(\d{4})/$
        matcher.each {
            println "date = "+it[0]
            println "day = "+it[1]
            println "month = "+it[2]
            println "year = "+it[3]
        }

        def day = /\d{1,2}/
        def month = /\d{1,2}/
        def year = /\d{4}/

        matcher = "11/3/2017 4/10/2015 y 22/1/2018" =~ /($day)\/($month)\/($year)/

        matcher.each {
            println "date = "+it[0]
            println "day = "+it[1]
            println "month = "+it[2]
            println "year = "+it[3]
        }

        // Pattern
        def digitPattern = ~/\d+/
        def dateMatcher = digitPattern.matcher("11/3/2017")
        dateMatcher.each {println it}



        def pattern = ~/abc/
        assert ! pattern.matcher('b').matches()
        assert pattern.matcher('abc').matches()

        // switch case with patterns
        switch ('abc'){
            case ~/a/ :
                println "No"
                break;
            case ~/abc/:
                println "Yes"
                break;
            case ~/c/:
                println "No"
                break;
            default:
                println "Default"
        }
    }
}
