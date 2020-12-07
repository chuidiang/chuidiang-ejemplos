package com.chuidiang.examples.spring_jms.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author fjabellan
 * @date 28/11/2020
 */
@Getter @Setter
@ToString
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
