package com.chuidiang.examples

import spock.lang.Specification

/**
 * Created by JAVIER on 25/04/2017.
 */
class StackTest extends Specification{

    Stack stack = new Stack()

    def "push and pop single element"() {

        when:
            stack.push(11)

        then:
            11==stack.pop()
            0 == stack.size()
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
