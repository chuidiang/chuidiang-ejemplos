package com.chuidiang.examples.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue
    long id;
    private String name;
    private Date birthday;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Car> cars;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", cars=" + cars +
                '}';
    }
}
