package com.chuidiang.examples.spring_boot_mongodb_example;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @author fjabellan 11/10/2023
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    @Qualifier("my_data_base")
    public MongoTemplate getMyMongoTemplate(MongoClient mongoClient){
        return new MongoTemplate(mongoClient,"my_data_base");
    }

    @Bean
    @Qualifier("my_other_data_base")
    public MongoTemplate getMyOtherMongoTemplate(MongoClient mongoClient){
        return new MongoTemplate(mongoClient,"my_other_base");
    }

}
