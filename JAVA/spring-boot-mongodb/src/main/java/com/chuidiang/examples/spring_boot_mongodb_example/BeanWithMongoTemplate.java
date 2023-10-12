package com.chuidiang.examples.spring_boot_mongodb_example;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Ejemplo de uso de MongoTemplate con Spring Boot y MongoDB
 *
 * @author fjabellan 11/10/2023
 */
@Component
public class BeanWithMongoTemplate {

    /** MongoTemplate lo inyecta Spring Boot */
    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * Comenzamos a hacer alguna operación CRUD con MongoDB cuando el contexto esté inicializado.
     * Lo hacemos en un método @PostConstruct para que comience el ejemplo de forma automatica,
     * sin necesidad de crear una interface de usuario o web services a los que haya que invocar para
     * provocar la ejecución del ejemplo.
     */
    @PostConstruct
    public void doCRUDOperations(){
        System.out.println("Operaciones CRUD con MongoTemplate");

        // Conexión con una base de datos de MongoDB
        // Coge la que esté configurada por defecto en application.properties con
        // la propiedad spring.data.mongodb.database
        // Si no está definida, la base de datos es "test"
        final MongoDatabase database = mongoTemplate.getDb();
        System.out.println("Base de Datos por defecto para MongoTemplate "+database.getName());

        // Insertar dos POJO
        // Como no indicamos colección, lo inserta en una colección con el mismo nombre que el POJO
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Pedro", 1.70));
        persons.add(new Person("Monica", 1.90));
        Collection<Person> people = mongoTemplate.insertAll(persons);
        System.out.println("Insertado "+ Arrays.toString(people.toArray()));

        // Verificamos que la colección se llama "person"
        System.out.println("Colecciones : "+ Arrays.toString(mongoTemplate.getCollectionNames().toArray()));

        // Verificamos que hay dos documentos en la colección
        System.out.println("Personas en la coleccion : "+mongoTemplate.estimatedCount(Person.class));

        // Consulta de todos los POJO insertados
        final List<Person> all = mongoTemplate.findAll(Person.class);
        System.out.println("Personas : "+ Arrays.toString(all.toArray()));

        // Consulta con query de POJO insertados
        Query query = Query.query(Criteria.where("name").is("Pedro"));
        people = mongoTemplate.find(query, Person.class);
        people.forEach(personFound -> System.out.println("Query : "+personFound));

        // Update de un POJO
        query = Query.query(Criteria.where("heigh").gt(1.80));
        Update update = Update.update("heigh", 2.0);
        mongoTemplate.updateFirst(query, update, Person.class);

        query = Query.query(Criteria.where("name").is("Monica"));
        System.out.println("Persona modificada : "+mongoTemplate.findOne(query, Person.class));

        // Borrado de un POJO
        query = Query.query(Criteria.where("name").is("Pedro"));
        DeleteResult remove = mongoTemplate.remove(query, Person.class);
        System.out.println("Deleted : "+remove.getDeletedCount());

        // Borrado de la base de datos MongoDB completa
        mongoTemplate.dropCollection(Person.class);
        
        System.out.println("-----------------------------");
    }
}
