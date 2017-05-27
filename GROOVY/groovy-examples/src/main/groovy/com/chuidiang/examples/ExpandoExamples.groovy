package com.chuidiang.examples

/**
 * Created by chuidiang on 18/05/17.
 */
class ExpandoExamples {
    static void main(String[]args){
        String firstString = new String("First String")

        // Adding a static method to java String
        String.metaClass.static.yabadaba = {
            println("doooooo!")
        }

        String.yabadaba()

        // Adding a method to java String
        String.metaClass.print = { println delegate }

        "Hello".print()

        // Adding a method to an object
        String hello = "Hello You"
        hello.metaClass.yourName = {String name -> return delegate + " " + name }
        println hello.yourName("Peter")

        // Capturing calls to not existing methods on Java String
        String.metaClass.methodMissing ({ String name, theArgs ->
            println "${name}(${theArgs}) doesn't exist  :("
            return "I don't exist"
        })

        println "Hello".yeeee("haaaa")
        println "Hello".yourName("Peter")

        // Capturing calls to not existings methods on own classes
        MyClass myInstance = new MyClass();
        myInstance.someMethod("Some parameter", "Another Parameter")


        println "firstString ... " + firstString.yeeee("haaaa")

        // A proxy for capturing all method calls of a class
        def proxy = ProxyMetaClass.getInstance(MyCalculator)
        proxy.interceptor = new CalculatorInterceptor()

        proxy.use {
            MyCalculator calculator = new MyCalculator()
            println(calculator.add(1,2))
        }

        // proxy all Calculators.
        MyCalculator.metaClass = proxy
        MyCalculator calculator2 = new MyCalculator()
        println("Invoking...." + calculator2.add(-4,-5))

        // Category
        use(StringCategory){
            println "Hello".shout()
        }
    }
}

class StringCategory {
    static String shout(String self){
        self.toUpperCase()
    }
}

class MyClass {
    def methodMissing(String name, args){
        println "${name}(${args}) doesn't exist  :("
    }
}

class MyCalculator {
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
        false
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
