package com.chuidiang.examples

import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by JAVIER on 25/04/2017.
 */
class StackTest extends Specification{

    @Shared
    Stack stack = new Stack()

    def setupSpec() {
        // This code is executed only once before any test starts
        println "setupSpec"
    }

    def cleanupSpec() {
        // This code is executed only once after all test have finished
        println "cleanupSpec"
    }

    def setup(){
        // This is code is exectued before each test method
        println 'setup method'
    }

    def cleanup() {
        stack.clear()
    }

    def "push and pop single element"() {
        given:
            println 'given label'

        when:
            stack.push(11)

        then:
            11==stack.pop()
            0 == stack.size()

        cleanup:
            println 'cleanup label'
    }

    def "push two elements, pop last added"() {

        when:
            stack.push(11)
            stack.push(12)

        then:
            12 == stack.pop()
            1 == stack.size()
    }

    def "exception when empty"() {
        when:
            stack.pop()

        then:
            thrown(EmptyStackException.class)

    }
}
