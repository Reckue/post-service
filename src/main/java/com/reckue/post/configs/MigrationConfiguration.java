package com.reckue.post.configs;

import com.github.mongobee.Mongobee;
import com.reckue.post.properties.MongoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Class MongobeeConfig sets up the settings for mongobee.
 *
 * @author Artur Magomedov
 */
@Configuration
public class MigrationConfiguration {

    @Bean
    public Mongobee mongobee(MongoTemplate mongoTemplate, MongoConfiguration mongoConfiguration) {
        Mongobee runner = new Mongobee(mongoConfiguration.getUri());
        runner.setMongoTemplate(mongoTemplate);
        runner.setChangeLogsScanPackage("com.example.demobd.db.migrations");
        runner.setEnabled(true);
        return runner;
    }
}
