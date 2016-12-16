package com.chuidiang.examples

def prev = 1L
def act = 2L
def count = 0L

while (act < 4000000L){
    if (act%2 == 0){
        count += act
    }
    def val = prev+act
    prev=act
    act=val
}
print count
