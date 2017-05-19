package com.chuidiang.examples

/**
 * Created by chuidiang on 18/05/17.
 */
class ExpandoExamples {
    static void main(String[]args){
        // Adding a method to java String
        String.metaClass.print = { println delegate }

        "Hello".print()

        // Capturing calls to not existing methods on Java String
        String.metaClass.methodMissing { String name, theArgs ->
            println "${name}(${theArgs}) doesn't exist  :("
        }

        "Hello".yeeee("haaaa")

        // Capturing calls to not existings methods on own classes
        MyClass myInstance = new MyClass();
        myInstance.someMethod("Some parameter", "Another Parameter")

        // A proxy for capturing all method calls of a class
        def proxy = ProxyMetaClass.getInstance(InterceptedCalculator)
        proxy.interceptor = new CalculatorInterceptor()

        proxy.use {
            InterceptedCalculator calculator = new InterceptedCalculator()
            println(calculator.add(1,2))
        }

        // proxy all Calculators.
        InterceptedCalculator.metaClass = proxy
        InterceptedCalculator calculator2 = new InterceptedCalculator()
        println(calculator2.add(-4,-5))
    }
}

class MyClass {
    def methodMissing(String name, args){
        println "${name}(${args}) doesn't exist  :("
    }
}

class InterceptedCalculator {
    public int add(int a, int b){
        a+b
    }
}

class CalculatorInterceptor implements Interceptor {

    @Override
    Object beforeInvoke(Object object, String methodName, Object[] arguments) {
        println("beforeInvoke : ${methodName}(${arguments})")
        if (arguments.size()>0){
            arguments[0]=33
        }
        return true
    }

    @Override
    Object afterInvoke(Object object, String methodName, Object[] arguments, Object result) {
        println("afterInvoke : ${methodName}(${arguments})")
        result
    }

    @Override
    boolean doInvoke() {
        println("doInvoke")
        true
    }
}
