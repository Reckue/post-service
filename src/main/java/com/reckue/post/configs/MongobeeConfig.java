package com.reckue.post.configs;

import com.github.mongobee.Mongobee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class MongobeeConfig sets up the settings for mongobee.
 *
 * @author Kamila Meshcheryakova
 */
@Configuration
public class MongobeeConfig {

    /**
     * This method creates an instance of the Mongobee object and tells about the package to scan for changes.
     *
     * @return runner the object of Mongobee class
     */
    @Bean
    public Mongobee mongobee() {
        Mongobee runner = new Mongobee("mongodb://localhost:27017/mongo-local");
        runner.setChangeLogsScanPackage(
                "com.reckue.post.migrations");

        return runner;
    }
}
