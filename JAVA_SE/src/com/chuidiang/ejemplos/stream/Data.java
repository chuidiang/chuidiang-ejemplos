package com.chuidiang.ejemplos.stream;

import java.util.Date;

/**
 * @author Chuidiang
 * @date 16/04/2023
 */
public class Data {
    public long timeStamp;
    public int value;
    public Data(long timeStamp, int value){
        this.timeStamp =timeStamp;
        this.value = value;
    }
    public String toString(){
        return new Date(timeStamp).toString() + " " + value;
    }
}
