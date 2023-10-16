package com.chuidiang.examples.spring_boot_mongodb_example;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Ejemplo de uso de MongoTemplate con Spring Boot y MongoDB
 *
 * @author fjabellan 11/10/2023
 */
@Component
public class BeanWithMongoRepository {

    /** personRepository lo inyecta Spring Boot */
    @Autowired
    IfzPersonRepository personRepository;

    /**
     * Comenzamos a hacer alguna operación CRUD con MongoDB cuando el contexto esté inicializado.
     * Lo hacemos en un método @PostConstruct para que comience el ejemplo de forma automatica,
     * sin necesidad de crear una interface de usuario o web services a los que haya que invocar para
     * provocar la ejecución del ejemplo.
     */
    @PostConstruct
    public void doCRUDOperations(){
        System.out.println("Operaciones CRUD con MongoPersistence");

        // Insertar dos POJO
        // Como no indicamos colección, lo inserta en una colección con el mismo nombre que el POJO
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Pedro", 1.70));
        persons.add(new Person("Monica", 1.90));
        Iterable<Person> people = personRepository.saveAll(persons);
        people.forEach(person -> System.out.println("Insertado : "+ person));

        // Contar documentos en la colección
        System.out.println("Personas en la coleccion : "+ personRepository.count());

        // Consulta de todos los POJO insertados
        people = personRepository.findAll();
        people.forEach(person -> System.out.println("Person Found : "+ person));

        // Consulta con query de POJO insertados
        persons = personRepository.findByName("Pedro");
        System.out.println(Arrays.toString(persons.toArray()));

        // Update de un POJO
        Person tallPerson = personRepository.findFirstByHeightGreaterThan(1.80);
        tallPerson.setHeight(2.0);
        personRepository.save(tallPerson);

        Optional<Person> monica = personRepository.findById("Monica");
        System.out.println("Persona modificada : "+monica.get());

        // Borrado de un POJO
        personRepository.deleteById("Pedro");
        people.forEach(person -> System.out.println("Despues de borrar : "+ person));

        // Borrado de la base de datos MongoDB completa
        personRepository.deleteAll();
        
        System.out.println("-----------------------------");
    }
}
