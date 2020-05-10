package ru.orus.hw06;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;


@SpringBootApplication
public class Hw06SpringJpaApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(Hw06SpringJpaApplication.class, args);
        Console.main(args);
    }

}
