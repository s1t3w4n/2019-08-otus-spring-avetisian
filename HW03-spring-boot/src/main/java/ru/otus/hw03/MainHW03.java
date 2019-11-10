package ru.otus.hw03;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.hw03.app.Attempt;
import ru.otus.hw03.app.AttemptImpl;
import ru.otus.hw03.app.MarkCalc;
import ru.otus.hw03.app.StandardMarkCalcImpl;
import ru.otus.hw03.console.Console;
import ru.otus.hw03.dao.QuestionsDao;

import java.io.IOException;

@SpringBootApplication
public class MainHW03 {
    public static void main(String[] args) {
        SpringApplication.run(MainHW03.class, args).getBean(Attempt.class).start();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/il8n/bundle");
        ms.setDefaultEncoding("windows-1251");

        return ms;
    }

    @Bean
    public MarkCalc getMarkCalc() {
        return new StandardMarkCalcImpl();
    }

    @Bean
    public Attempt getAttempt(Console console, QuestionsDao dao, MarkCalc calc, @Value("${offset}")int offset) throws IOException {
        return new AttemptImpl(console, dao, calc, offset);
    }
}
