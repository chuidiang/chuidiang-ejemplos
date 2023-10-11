package com.chuidiang.examples.spring_boot_mongodb_example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import jakarta.annotation.PostConstruct;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @author fjabellan 11/10/2023
 */
@Component
public class ABean {
    @Autowired
    @Qualifier("my_other_data_base")
    MongoTemplate mongoTemplate;

    @Autowired
    MongoClient mongoClient;

    public ABean(){
        System.out.println("Voy!!!!!!!!");

    }

    @PostConstruct
    public void voy(){
        System.out.println("mongo client "+mongoClient);
        System.out.println("mongo template "+mongoTemplate);

        MongoDatabase mongoDatabase = mongoTemplate.getDb();
        System.out.println(mongoDatabase.getName());
        MongoCollection<Document> collection = mongoDatabase.getCollection("my-collection");

        Document document = new Document();
        document.put("name","Pedro");

        InsertOneResult insertOneResult = collection.insertOne(document);
        System.out.println(insertOneResult.getInsertedId());
    }
}
