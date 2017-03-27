package com.chuidiang.examples

import groovy.transform.TypeChecked

class Collections {
    static void main(String[] args) {

        int [] c = [1,2,3,5]
        int [] d = [2,4]
        println c+d

        List a = [1,2,3,4]
        List b = [2,5]
        List result = a-b
        println result

        a = [1,2,3,4]
        b = [2,4]
        a.removeAll b
        println a

        a = [1,2,3,4]
        a.removeAll { value -> value%2==0}
        println a


        a = [1,1,3,4,5,2,4,1,5,7]
        a.unique()
        println a


        println 'Array'
        Integer[] array = [1, 2, 3, 4]
        Integer[] anotherArray = [5, 6, 7, 8]
        doThingsWithArrays (array, anotherArray)



        println 'List'
        List strings = ['hello', 'world']
        List moreStrings = ["more hello", "more world"]
        doThingsWithCollections(strings, moreStrings)

        println 'Set'
        Set integers = [1, 2, 3, 1, 2, 3]
        Set moreIntegers = [3, 4, 5]
        doThingsWithCollections(integers, moreIntegers)

        println 'Map'
        Map map = ['one': 1, 'two': 2, 'three': 3]
        Map anotherMap = ['one': -1, 'four': 4]
        doThingsWithMaps(map, anotherMap)

        def list = [1, 1, 1, 2, 2, 2, 3, 3, 3]
        def set = list.toSet()
        println " $list  ->  $set "

        println 'boolean'

        list = []
        if (list) {
            println 'It has data'
        } else {
            println 'It hasn\'t data'
        }
    }

    static void doThingsWithArrays (Integer[] collection1, Integer[] collection2) {
        println collection1.getClass()
        println " $collection1  -> size =  ${collection1.size()}"
        collection1.each {
            value -> println value
        }
        for (value in collection1) {
            println value
        }

        println(collection1 + collection2)

    }

    static void doThingsWithCollections(Collection collection1, Collection collection2){
        collection1 << 'one more'
        println " one more ->  ${collection1}"

        println collection1.getClass()    // It's java.util.ArrayList
        println " $collection1   -> size =  ${collection1.size()}"
        collection1.each {
            aString -> println aString
        }
        collection1.eachWithIndex {
            entry,
            int i -> println "strings[$i] = $entry"
        }


        println 'Another List'


        println collection2.getClass()    // It's java.util.ArrayList
        println collection2
        for (aString in collection2) {
            println aString
        }

        Collection allStrings = collection1 + collection2
        println " Sum of two lists  ${allStrings}"

        collection1.addAll(collection2)
        println " addAll()  ${collection1}"

        println collection1.toSet()


        def result = collection1.findAll{value -> return value.toString().contains('hello')}
        println result
    }

    static void doThingsWithMaps(Map map, Map anotherMap){
        println map.getClass()
        println map
        map.each {
            item -> println "$item.key = $item.value"
        }
        for (item in map) {
            println "$item.key = $item.value"
        }
        println " Sum of Maps  ${anotherMap + map} "

    }
}