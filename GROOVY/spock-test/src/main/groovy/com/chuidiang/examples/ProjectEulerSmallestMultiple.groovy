package com.chuidiang.examples

def max=1L
(2..20).each({i -> max = max*i})

for (long value = 1; value<max; value++) {

    def found = true
    for (div in (2..20)) {
        if ((value % div) != 0) {
            //println ""+value+" no divisible por "+div
            found = false
            break
        }
    }
    if (found) {
        println value
        break
    }
}




