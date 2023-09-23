package com.chuidiang.examples.mongo;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertManyResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

/**
 * Ejemplo base de datos mongo desde java
 * En este ejemplo se usan org.bson.Document como datos.
 *
 * @author Chuidiang 
 * Sept 2023
 */
public class MongoDBSampleMain {

    public static void main(String[] args) {
        // Cadena de conexion con la base de datos mongo
        String uri = "mongodb://127.0.0.1:27017";

        // Se abre la conexión
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Obtener o crear una base de datos
            MongoDatabase database = mongoClient.getDatabase("My_Data_Base");

            // Obtener o crear una colección
            MongoCollection<Document> collection = database.getCollection("my_collection");

            // Insertar algún dato en la colección
            Document data = new Document().append("name", "Pedro").append("age", 22);
            InsertOneResult insertOneResult = collection.insertOne(data);
            System.out.println("\n--- insert one result ---");
            System.out.println(insertOneResult.getInsertedId());

            // Insertar varios datos de golpe
            List<Document> severalData = new ArrayList<>();
            severalData.add(new Document().append("name", "Elena").append("age", 33));
            severalData.add(new Document().append("name", "Ana").append("age", 41));
            InsertManyResult insertManyResult = collection.insertMany(severalData);
            System.out.println("\n--- insert many result ---");
            System.out.println(insertManyResult.getInsertedIds());


            // Consultar la colección
            FindIterable<Document> allCollection = collection.find();
            System.out.println("\n--- find() result ---");
            allCollection.forEach(document -> System.out.println(document));

            // Consultar por una key
            Bson filter = Filters.eq("name", "Pedro");
            FindIterable<Document> elementsFound = collection.find(filter);
            System.out.println("\n--- find(filter) result ---");
            elementsFound.forEach(document -> System.out.println(document));

            // Modificar un elemento de la colección
            Bson newAge = Updates.set("age", 23);
            UpdateResult updateOne = collection.updateOne(filter, newAge);
            elementsFound = collection.find(filter);
            System.out.println("\n--- updateOne() result ---");
            System.out.println(updateOne.getMatchedCount());
            elementsFound.forEach(document -> System.out.println(document));


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