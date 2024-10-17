package com.chuidiang.examples.sse_back_demo.controller;

/**
 * @author fjabellan 17/10/2024
 */
public class Data {
    public Data(String name, int value){
        this.name=name;
        this.value=value;
    }
    public Data(){

    }

    private String name;
    private int value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
