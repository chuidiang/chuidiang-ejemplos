package com.chuidiang.examples.mongo;

/**
 * Ejemplo base de datos mongo desde java
 * El POJO para insertar/consultar/modificar/borrar en mongo bd
 * 
 * @author Chuidiang
 *         Sept 2023
 */
public class Person {
    private int age;
    private String name;

    public Person() {
        // Necesario para bson
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int height) {
        this.age = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person [age=" + age + ", name=" + name + "]";
    }
    
}
