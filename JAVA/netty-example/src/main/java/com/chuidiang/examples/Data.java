package com.chuidiang.examples;

import java.util.Date;
import java.util.Random;

/**
 * Created by chuidiang on 29/10/17.
 */
public class Data {
    public int id;
    public String name;
    public Date date;

    public Data (){
        id = new Random().nextInt();
        name = "The name "+Math.random();
        date = new Date();
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
