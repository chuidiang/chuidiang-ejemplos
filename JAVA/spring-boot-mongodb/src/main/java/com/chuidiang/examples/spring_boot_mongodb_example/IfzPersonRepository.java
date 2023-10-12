package com.chuidiang.examples.spring_boot_mongodb_example;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Chuidiang
 * @date 12/10/2023
 */
public interface IfzPersonRepository extends CrudRepository<Person, String> {
    List<Person> findByName(String name);
    Person findFirstByHeighGreaterThan(double heigh);
}
