package ru.otus.hw08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class Hw08SpringMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw08SpringMongoDbApplication.class, args);
    }

}

