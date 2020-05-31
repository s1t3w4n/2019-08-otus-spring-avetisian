package ru.otus.hw08.bee;

import com.github.mongobee.Mongobee;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import ru.otus.hw08.bee.changelog.DatabaseChangelog;

@Configuration
public class MongoBeeConfig {
    @Bean
    public Mongobee mongobee(Environment environment, MongoClient mongo) {
        Mongobee runner = new Mongobee(mongo);
        runner.setDbName("library");
        runner.setChangeLogsScanPackage(DatabaseChangelog.class.getPackage().getName());
        runner.setSpringEnvironment(environment);
        return runner;
    }
}
