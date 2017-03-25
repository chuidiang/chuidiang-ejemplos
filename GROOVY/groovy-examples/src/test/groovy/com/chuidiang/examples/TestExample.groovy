package com.chuidiang.examples

import spock.lang.Specification

/**
 * Created by JAVIER on 25/03/2017.
 */
class TestExample extends Specification {
    def "adder" () {
        given: "a calculator"
            def calculator = new Calculator()

        when: "I add 1 and 1"
            def result = calculator.add(1,1)

        then:
           assert 1+1 == result

    }
}
