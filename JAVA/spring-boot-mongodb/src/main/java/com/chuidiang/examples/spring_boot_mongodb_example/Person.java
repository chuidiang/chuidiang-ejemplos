package com.chuidiang.examples.spring_boot_mongodb_example;

import org.springframework.data.annotation.Id;

/**
 * @author Chuidiang
 * @date 12/10/2023
 */
public class Person {
    @Id
    private String name;
    private Double height;

    public Person(String name, Double height) {
        this.name = name;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                " name='" + name + '\'' +
                ", height=" + height +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
