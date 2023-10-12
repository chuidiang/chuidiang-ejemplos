package com.chuidiang.examples.spring_boot_mongodb_example;

import org.springframework.data.annotation.Id;

/**
 * @author Chuidiang
 * @date 12/10/2023
 */
public class Person {
    @Id
    private String name;
    private Double heigh;

    public Person(String name, Double heigh) {
        this.name = name;
        this.heigh = heigh;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", heigh=" + heigh +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeigh() {
        return heigh;
    }

    public void setHeigh(Double heigh) {
        this.heigh = heigh;
    }
}
