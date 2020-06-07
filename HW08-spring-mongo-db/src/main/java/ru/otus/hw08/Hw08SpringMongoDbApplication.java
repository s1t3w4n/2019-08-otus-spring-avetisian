package ru.otus.hw08;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import ru.otus.hw08.repositories.BookRepository;

@SpringBootApplication
@EnableConfigurationProperties
public class Hw08SpringMongoDbApplication {

    public static void main(String[] args) {

//        SpringApplication.run(Hw08SpringMongoDbApplication.class, args);
        ApplicationContext context = SpringApplication.run(Hw08SpringMongoDbApplication.class);
        BookRepository repository = context.getBean(BookRepository.class);
        repository.findAll().forEach(System.out::println);
    }

}

