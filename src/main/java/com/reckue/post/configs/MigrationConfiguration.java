package com.reckue.post.configs;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Class MongobeeConfig sets up the settings for mongobee.
 *
 * @author Kamila Meshcheryakova
 */
@Configuration
public class MigrationConfiguration {

    /**
     * This method creates an instance of the Mongobee object and tells about the package to scan for changes.
     *
     * @return runner the object of Mongobee class
     */
    @Bean
    public Mongobee mongobee(@Value("${DATABASE.HOST}") String host,
                             @Value("${DATABASE.PORT}") String port,
                             @Value("${DATABASE.NAME}") String database) {
        Mongobee runner = new Mongobee("mongodb://" + host + ":" + port + "/" + database);
        runner.setChangeLogsScanPackage("com.reckue.post.migrations");
        return runner;
    }
}
