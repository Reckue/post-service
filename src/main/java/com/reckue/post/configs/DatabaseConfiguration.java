package com.reckue.post.configs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.Nonnull;

@EnableMongoAuditing
@EnableMongoRepositories("com.reckue.post.repositories")
@Configuration
public class DatabaseConfiguration extends AbstractMongoClientConfiguration {

    @Value("${DATABASE.HOST}")
    private String host;

    @Value("${DATABASE.PORT}")
    private String port;

    @Value("${DATABASE.NAME}")
    private String database;

    @Nonnull
    @Override
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://" + this.host + ":" + this.port);
    }

    @Nonnull
    @Override
    protected String getDatabaseName() {
        return this.database;
    }

    @Bean
    @Nonnull
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), database);
    }
}
