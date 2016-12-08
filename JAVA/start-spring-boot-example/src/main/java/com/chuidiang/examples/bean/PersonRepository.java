package com.chuidiang.examples.bean;


import org.springframework.data.repository.CrudRepository;

/**
 * Created by JAVIER on 08/12/2016.
 */
public interface PersonRepository extends CrudRepository<Person, Long > {
}
