package ru.otus.hw02;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import ru.otus.hw02.app.Attempt;
import ru.otus.hw02.app.AttemptImpl;
import ru.otus.hw02.app.MarkCalc;
import ru.otus.hw02.app.StandardMarkCalcImpl;
import ru.otus.hw02.console.Console;
import ru.otus.hw02.dao.QuestionsDao;

import java.io.IOException;

@PropertySource("classpath:application.properties")
@ComponentScan
@Configuration
public class MainHW02 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainHW02.class);
        Attempt attempt = context.getBean(Attempt.class);
        attempt.start();
    }


    @Bean
    public MarkCalc getMarkCalc() {
        return new StandardMarkCalcImpl();
    }


    @Bean
    public Attempt getAttempt(Console console, QuestionsDao dao, MarkCalc calc, @Value("${offset}")int offset) throws IOException {
        return new AttemptImpl(console, dao, calc, offset);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/il8n/bundle");
        ms.setDefaultEncoding("windows-1251");

        return ms;
    }
}


