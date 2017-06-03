package com.chuidiang.examples;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by chuidiang on 3/06/17.
 */
public class SomeLittleClass implements Serializable {
    private int id;
    private Integer someInteger;
    private String someString;
    private Date someDate;

    public SomeLittleClass(int id){
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getSomeInteger() {
        return someInteger;
    }

    public void setSomeInteger(Integer someInteger) {
        this.someInteger = someInteger;
    }

    public String getSomeString() {
        return someString;
    }

    public void setSomeString(String someString) {
        this.someString = someString;
    }

    public Date getSomeDate() {
        return someDate;
    }

    public void setSomeDate(Date someDate) {
        this.someDate = someDate;
    }

    @Override
    public String toString() {
        return "SomeLittleClass{" +
                "id=" + id +
                ", someInteger=" + someInteger +
                ", someString='" + someString + '\'' +
                ", someDate=" + someDate +
                '}';
    }

    public static SomeLittleClass newInstance(int value){
        SomeLittleClass instance = new SomeLittleClass(value);
        instance.setSomeString("String"+value);
        instance.setSomeInteger(value+10);
        instance.setSomeDate(new Date());
        return instance;
    }
}
