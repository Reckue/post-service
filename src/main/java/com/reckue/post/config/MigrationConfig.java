package com.reckue.post.config;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Class MigrationConfig sets up the settings for mongobee.
 *
 * @author Kamila Meshcheryakova
 */
@EnableMongoAuditing
@EnableMongoRepositories("com.reckue.post.repository")
@Configuration
public class MigrationConfig {

    /**
     * This method creates an instance of the Mongobee object and tells about the package to scan for changes.
     *
     * @return runner the object of Mongobee class
     */
    @Bean
    public Mongobee mongobee(@Value("${spring.data.mongodb.host}") String host,
                             @Value("${spring.data.mongodb.port}") String port,
                             @Value("${spring.data.mongodb.database}") String database) {
        Mongobee runner = new Mongobee("mongodb://" + host + ":" + port + "/" + database);
        runner.setChangeLogsScanPackage("com.reckue.post.migrations");
        return runner;
    }
}
