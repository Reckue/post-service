//package com.reckue.post.configs;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import javax.annotation.Nonnull;
//
//@Configuration
//@EnableMongoRepositories("com.reckue.post.repositories")
//public class DatabaseConfiguration extends AbstractMongoClientConfiguration {
//
//    @Value("${MONGO_URI}")
//    private String path;
//
//    @Value("${MONGO_PORT}")
//    private String port;
//
//    @Value("${MONGO_DBNAME}")
//    private String name;
//
//    @Nonnull
//    @Override
//    public MongoClient mongoClient() {
//        return MongoClients.create(path + ":" + port);
//    }
//
//    @Nonnull
//    @Override
//    protected String getDatabaseName() {
//        return "mongo-develop";
//    }
//
//    @Bean
//    @Nonnull
//    public MongoTemplate mongoTemplate() {
//        return new MongoTemplate(mongoClient(), name);
//    }
//}
