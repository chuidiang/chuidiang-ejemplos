package com.chuidiang.examples.spring_jms.common;

import com.chuidiang.examples.spring_jms.common.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author fjabellan
 * @date 28/11/2020
 */
@ToString(callSuper = true)
public class SonData extends Data {
    private SonData() {
        super(0,null);
    }
    public SonData(double theDouble, int value, String string){
        super(value,string);
        this.theDouble=theDouble;
    }
    @Getter
    @Setter
    private double theDouble;
}
