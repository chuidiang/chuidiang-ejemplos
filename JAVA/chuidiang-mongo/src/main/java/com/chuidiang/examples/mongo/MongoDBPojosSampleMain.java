package com.chuidiang.examples.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

/**
 * Ejemplo base de datos mongo desde java
 * 
 * @author Chuidiang
 *         Sept 2023
 */
public class MongoDBPojosSampleMain {
    public static void main(String[] args) {
        // Cadena de conexion con la base de datos mongo
        String uri = "mongodb://172.17.0.2:27017";
        PojoCodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(pojoCodecProvider));

        // Se abre la conexión
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Obtener o crear una base de datos
            MongoDatabase database = mongoClient.getDatabase("My_Data_Base").withCodecRegistry(pojoCodecRegistry);

            // Obtener o crear una colección
            MongoCollection<Person> collection = database.getCollection("my_collection", Person.class);

            // Insertar algún dato en la colección
            Person pojoData = new Person("Pedro", 22);
            InsertOneResult insertOneResult = collection.insertOne(pojoData);
            System.out.println("\n--- insert one result ---");
            System.out.println(insertOneResult.getInsertedId());

            // Insertar varios datos de golpe
            List<Person> severalData = new ArrayList<>();
            severalData.add(new Person("Juan", 21));
            severalData.add(new Person("Elena", 23));
            InsertManyResult insertManyResult = collection.insertMany(severalData);
            System.out.println("\n--- insert many result ---");
            System.out.println(insertManyResult.getInsertedIds());
            
            // Consultar la colección
            FindIterable<Person> allCollection = collection.find();
            System.out.println("\n--- find() result ---");
            allCollection.forEach(person -> System.out.println(person));

            // Consultar por una key
            Bson filter = Filters.eq("name", "Pedro");
            FindIterable<Person> elementsFound = collection.find(filter);
            System.out.println("\n--- find(filter) result ---");
            elementsFound.forEach(person -> System.out.println(person));

            // Modificar un elemento de la colección
            Bson newAge = Updates.set("age", 23);
            UpdateResult updateOne = collection.updateOne(filter, newAge);
            elementsFound = collection.find(filter);
            System.out.println("\n--- updateOne() result ---");
            System.out.println(updateOne.getMatchedCount());
            elementsFound.forEach(person -> System.out.println(person));

            // Borrar por key
            DeleteResult deleteResult = collection.deleteMany(filter);
            System.out.println("\n--- Number of deleted elements with filter ---");
            System.out.println(deleteResult.getDeletedCount());

            // Borrar todo
            deleteResult = collection.deleteMany(Filters.empty());
            System.out.println("\n--- Number of deleted elements with empty filter ---");
            System.out.println(deleteResult.getDeletedCount());

            // Eliminar la coleccion
            collection.drop();

            // Eliminar la base de datos
            database.drop();

            // Cierre de la conexión. No es necesario puesto que usamos try-with-resources
            // mongoClient.close();
        }
    }
}