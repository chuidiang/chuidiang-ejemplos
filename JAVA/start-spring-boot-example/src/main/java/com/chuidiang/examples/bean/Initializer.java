package com.chuidiang.examples.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.IntStream;


@Component
public class Initializer {
    private static Logger log = LoggerFactory.getLogger(Initializer.class);

    public Initializer(PersonRepository repository) {
        IntStream.range(0,10).forEach( i -> {
            Person data = new Person();
            data.setName("Name " + i);
            data.setBirthday(new Date());
            repository.save(data);
            log.info(data.toString() + " saved");
        });
    }
}
