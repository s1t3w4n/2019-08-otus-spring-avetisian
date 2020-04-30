package ru.otus.hw05;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.hw05.dao.BookDao;
import ru.otus.hw05.domain.Book;

import java.sql.SQLException;

@SpringBootApplication
public class Hw05SpringJdbcApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Hw05SpringJdbcApplication.class, args).getBean(BookDao.class);
        Console.main(args);
    }

}
