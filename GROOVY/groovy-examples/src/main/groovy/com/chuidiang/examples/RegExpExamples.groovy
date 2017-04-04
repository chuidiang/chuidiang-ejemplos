package com.chuidiang.examples

/**
 * Created by JAVIER on 04/04/2017.
 */
class RegExpExamples {
    static void main(String [] args){
        // Contains pattern
        assert "abc" =~ /b/

        // Match patter exactly
        assert 'abc' ==~ /abc/
        assert ! ('abc' ==~ /b/ )

        // group
        assert 'aba' ==~ /(.)b\1/
        assert ! ('abc' ==~ /(.)b\1/)

        // extract matches
        def matcher = 'abc ebf' =~ /.b./
        matcher.each {
            println it
        }

        // extrack groups
        matcher = 'abc ebf' =~ /(.)b(.)/
        matcher.each {
            println "In ${it[0]}, I found ${it[1]} and ${it[2]}"
        }
    }
}
