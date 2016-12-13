package com.chuidiang.examples

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Created by JAVIER on 12/12/2016.
 */
class AdderSpec extends Specification {
    @Unroll
    def "#a + #b == #c"() {
        setup:
           def adder = new Adder()

        expect:
            adder.add(a, b) == c

        where:
        a  | b   | c
        1  | 2   | 3
        42 | -12 | 30
        42 | 12 |  54
    }
}
