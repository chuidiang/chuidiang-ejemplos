package com.chuidiang.examples

max = 0

(100..999).each { i ->
    (100..999).each { j ->
        def value = (i * j)
        def asString = value as String
        if (asString == asString.reverse()){
            if (value>max){
                max=value
            }
        }
    }
}
print max
