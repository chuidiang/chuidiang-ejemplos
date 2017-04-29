package com.chuidiang.examples

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by JAVIER on 28/04/2017.
 */
class CalculatorTest extends Specification {
    Calculator calculator = new Calculator()

    @Unroll
    def "#a + #b = #c"() {
        expect:
            calculator.add(a,b) == c

        where:
            a|b || c
            1|1 || 2
            1|2 || 3
            2|1 || 3
    }

    def "otro a + b = c"() {
        expect:
        calculator.add(a,b) == c
        println "$a + $b = $c"

        where:
        a << [1,2,1]
        b << [1,1,2]
        c << [2,3,3]
    }

}
