package com.chuidiang.examples;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chuidiang on 3/06/17.
 */
public class SomeMediumClass {
    private String[] stringArray;
    private List<SomeLittleClass> list;

    public SomeMediumClass(){
        stringArray = new String[] {"uno","dos","tres","cuatro"};
        list = new ArrayList<>(10);
        for (int i=0;i<10;i++){
            list.add(SomeLittleClass.newInstance(i));
        }
    }

    @Override
    public String toString() {
        return "SomeMediumClass{" +
                "stringArray=" + Arrays.toString(stringArray) +
                ", list=" + list +
                '}';
    }
}
