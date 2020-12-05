package com.chuidiang.examples.spring_jms.common;

import lombok.*;

/**
 * @author fjabellan
 * @date 28/11/2020
 */
@Getter @Setter
@ToString(callSuper = true)
public class Data {
    private Data(){

    }
    public Data(int value, String string){
        this.value=value;
        this.string=string;
    }
    private int value;
    private String string;
}
