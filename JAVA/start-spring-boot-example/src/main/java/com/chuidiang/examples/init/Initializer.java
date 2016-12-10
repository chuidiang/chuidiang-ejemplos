package com.chuidiang.examples.init;

import com.chuidiang.examples.bean.Car;
import com.chuidiang.examples.bean.Person;
import com.chuidiang.examples.bean.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashSet;
import java.util.stream.IntStream;


public class Initializer {
    private static Logger log = LoggerFactory.getLogger(Initializer.class);

    private PersonRepository repository;
    public Initializer(PersonRepository repository) {
        this.repository=repository;
            IntStream.range(0,10)
                    .mapToObj(this::createPerson)
                    .map(this::save)
                    .map(this::addCar)
                    .map(this::save)
                    .forEach(p -> log.info(p.toString()));
        }

    private Person save(Person person) {
        repository.save(person);
        return person;
    }

    private Person createPerson(int i) {
        Person aPerson = new Person();
        aPerson.setName("Name " + i);
        aPerson.setBirthday(new Date());
        return aPerson;
    }

    private Person addCar(Person person){
        Car car = new Car();
        car.setMarca("Marca de "+person.getName());
        car.setModelo("Modelo de "+person.getName());
        if (null==person.getCars()){
            person.setCars(new HashSet<>());
        }
        person.getCars().add(car);
        return person;
    }
}
