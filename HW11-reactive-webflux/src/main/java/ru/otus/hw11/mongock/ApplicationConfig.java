package ru.otus.hw11.mongock;

import com.github.cloudyrock.mongock.SpringMongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ApplicationConfig {

    private static final String CHANGELOGS_PACKAGE = "ru.otus.hw08.mongock.changelog";

    @Bean
    public SpringMongock springMongock(MongoTemplate mongoTemplate, Environment springEnvironment) {
        return new SpringMongockBuilder(mongoTemplate, CHANGELOGS_PACKAGE)
                .setSpringEnvironment(springEnvironment)
                .setLockQuickConfig()
                .build();
    }
}
