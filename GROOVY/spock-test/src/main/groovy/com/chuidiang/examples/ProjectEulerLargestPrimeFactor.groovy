package com.chuidiang.examples

def value = 600851475143
def max = 1


for (int i=2;i<600851475143/2; i++) {
    while (value%i == 0){
        max=i
        value = value/i as long
    }
    if (i>value) break
}
print max