package com.chuidiang.examples.spring_boot_mongodb_example;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Ejemplo de uso de MongoClient con Spring Boot y MongoDB
 *
 * @author fjabellan 11/10/2023
 */
@Component
public class BeanWithMongoClient {

    /** MongoClient lo inyecta Spring Boot */
    @Autowired
    MongoClient mongoClient;

    /**
     * Comenzamos a hacer alguna operación CRUD con MongoDB cuando el contexto esté inicializado
     * Lo hacemos en un método @PostConstruct para que comience el ejemplo de forma automatica,
     * sin necesidad de crear una interface de usuario o web services a los que haya que invocar para
     * provocar la ejecución del ejemplo.
     * No hacemos todas las operaciones CRUD. Si quieres más detalles puedes consultar
     * https://chuidiang.org/index.php?title=Conectar_Java_con_MongoDB_-_Ejemplo_CRUD
     */
    @PostConstruct
    public void doSomeCRUDOperations(){
        System.out.println("Operaciones CRUD con MongoClient");

        // Conexión con una base de datos de MongoDB
        final MongoDatabase database = mongoClient.getDatabase("chuidiang-test-mongo-client");

        // Obtener una colección
        final MongoCollection<Document> collection = database.getCollection("collection-test");

        // Insertar un documento
        Document document = new Document("name", "Pedro");
        final InsertOneResult insertOneResult = collection.insertOne(document);
        System.out.println("Insertado : "+insertOneResult.getInsertedId());

        // Consulta del documento insertado
        final FindIterable<Document> documents = collection.find();
        documents.forEach(documentFound -> System.out.println(documentFound));

        // Borrado de la base de datos MongoDB completa
        database.drop();

        System.out.println("-----------------------------");
    }
}
