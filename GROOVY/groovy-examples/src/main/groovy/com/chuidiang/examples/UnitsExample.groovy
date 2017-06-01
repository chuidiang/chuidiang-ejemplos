package com.chuidiang.examples

/**
 * Created by chuidiang on 1/06/17.
 */
class UnitsExample {
    static void main(String [] args) {
        use(ConverterCategory) {
            println 1.m - 2.cm + 3.mm
            println 1.2.m + 3.4.cm -3.2.mm
        }
    }

}

class ConverterCategory {
    static Number getm (Number self) {
        self*1000
    }

    static Number getcm (Number self){
        self*10
    }

    static Number getdm (Number self){
        self*100
    }

    static Number getmm (Number self){
        self
    }
}
