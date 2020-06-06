package com.reckue.post.configs;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Class MongobeeConfig sets up the settings for mongobee.
 *
 * @author Kamila Meshcheryakova
 */
@Configuration
public class MigrationConfiguration {

    @Value("${MONGO_URI}")
    private String path;

    @Value("${MONGO_PORT}")
    private String port;

    @Value("${MONGO_DBNAME}")
    private String name;

    /**
     * This method creates an instance of the Mongobee object and tells about the package to scan for changes.
     *
     * @return runner the object of Mongobee class
     */
    @Bean
    public Mongobee mongobee() {
        Mongobee runner = new Mongobee(path + ":" + port +"/" + name);
        runner.setChangeLogsScanPackage("com.reckue.post.migrations");
        return runner;
    }
}
