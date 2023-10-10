package com.chuidiang.examples.mongo.query;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Chuidiang
 * @date 09/10/2023
 */
public class QueryExample {
    public static void main(String[] args) {
        String uri = "mongodb://127.0.0.1:27017";

        // Se abre la conexión
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // Obtener o crear una base de datos
            MongoDatabase database = mongoClient.getDatabase("My_Data_Base");

            // Obtener o crear una colección
            MongoCollection<Document> collection = database.getCollection("my_collection");

            doSomeInserts(collection);

            System.out.println("Obtener todos los documentos de una colección.");
            FindIterable<Document> documents = collection.find();
            documents.forEach(document -> {
                System.out.println(document.toJson());
            });

            System.out.println("Obtener resultado por ObjectId");
            Bson filter = Filters.eq("_id", insertedIds.get(0));
            documents = collection.find(filter);
            documents.forEach(document -> System.out.println(document));

            System.out.println("Obtener los valores del Document");
            filter = Filters.eq("_id", insertedIds.get(0));
            documents = collection.find(filter);
            documents.forEach(document -> {
                System.out.println(document.getObjectId("_id"));
                System.out.println(document.getString("name"));
                System.out.println(document.getString("email"));
                System.out.println(document.getDate("birthday"));
                System.out.println(document.getDouble("height"));
                System.out.println(document.getInteger("brothers"));

                System.out.println("En formato JSON");
                System.out.println(document.toJson());
            });

            System.out.println("Obtener resultado por campo");
            filter = Filters.eq("name", "Pedro");
            documents = collection.find(filter);
            documents.forEach(document -> System.out.println(document));

            System.out.println("Obtener resultado de campo no relleno");
            filter = Filters.exists("email", false);
            documents = collection.find(filter);
            documents.forEach(document -> System.out.println(document));

            System.out.println("Obtener resultado por fecha");
            Calendar date = Calendar.getInstance();
            date.set(1993, 5, 3);
            filter = Filters.gt("birthday", date.getTime());
            documents = collection.find(filter);
            documents.forEach(document -> System.out.println(document));

            System.out.println("Obtener resultado con varias condiciones");
            date = Calendar.getInstance();
            date.set(1993, 3, 3);
            Bson filter1 = Filters.gt("birthday", date.getTime());
            date.set(1993,7,3);
            Bson filter2 = Filters.lt("birthday", date.getTime());
            documents = collection.find(Filters.and(filter1, filter2));
            documents.forEach(document -> System.out.println(document));


            // Eliminar la coleccion
            collection.drop();

            // Eliminar la base de datos
            database.drop();

            // Cierre de la conexión. No es necesario puesto que usamos try-with-resources
            // mongoClient.close();
        }

    }

    private static List<ObjectId> insertedIds = new ArrayList<>();
    private static List<Date> birthday = new ArrayList<>();

    private static void doSomeInserts(MongoCollection<Document> collection) {
        String [] name = {"Pedro", "Elena", "Fernando", "Pablo", "Ana", "María"};
        String [] email = {"pedro@gmail.com", null, "fernando@gmail.com", "pablo@hotmail.com", "ana@hotmail.com", "maría@hotmail.com"};
        String [] city = {"Madrid", "New York", "Barcelona", "Cádiz", "París", "Huesca"};
        double [] height = {1.76, 1.67, 1.51, 1.70, 1.89, 1.91};
        int [] brothers = {1, 2, 0, 5, 1, 3};

        for (int i=0; i<name.length; i++){
            Document document = new Document();
            document.put("name", name[i]);
            if (null!=email[i]) {
                document.put("email", email[i]);
            }
            document.put("city", city[i]);
            document.put("height", height[i]);
            document.put("brothers", brothers[i]);
            Calendar randomDate = Calendar.getInstance();
            randomDate.set(1993, (int)(Math.random()*12), 3);
            document.put("birthday", randomDate.getTime());
            birthday.add(randomDate.getTime());
            InsertOneResult insertOneResult = collection.insertOne(document);
            insertedIds.add(insertOneResult.getInsertedId().asObjectId().getValue());
        }
    }
}
