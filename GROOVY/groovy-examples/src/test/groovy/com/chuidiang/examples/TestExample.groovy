package com.chuidiang.examples

import spock.lang.Specification

/**
 * Created by JAVIER on 25/03/2017.
 */
class TestExample extends Specification {
    def calculator = new Calculator()

    def setup() {
        println "setup"

        Person p = null
        println p?.name
    }

    def cleanup() {
        println "cleanup"
    }

    def setupSpec() {
        println "setupSpec"
    }

    def cleanupSpec() {
        println "cleanupSpec"
    }

    def "1+1=2" () {
        expect: "I add 1 and 1"
            2 == calculator.add(1,1)
    }

    def "2+2=4" () {
        when: "I add 2 and 2"
            def result=calculator.add(2,2)

        then:
            assert 2+2==result
    }

    def "javaCalculator" () {
        given: "a java calculator"
            def javaCalculator = new JavaCalculator();

        when: "I java add 1 and 1"
            def result = javaCalculator.add(1,1)

        then:
            assert 1+1 == result
    }

    def "negative substration should raise an Exception" () {
        when:
            def result = calculator.subs(1,2)

        then:
            thrown(NegativeResultException)
    }

    def "positive substration should raise an Exception" () {
        when:
            def result = calculator.subs(2,1)

        then:
            notThrown(NegativeResultException)
            1==result
    }

}
