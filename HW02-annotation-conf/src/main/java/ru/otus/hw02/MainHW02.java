package ru.otus.hw02;

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
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@ComponentScan
@Configuration
public class MainHW02 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainHW02.class);
        Attempt attempt = context.getBean(Attempt.class);
        attempt.start();
    }

    /*@Bean
    PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/


    @Bean
    public MarkCalc getMarkCalc() {
        return new StandardMarkCalcImpl();
    }


    @Bean
    public Attempt getAttempt(Console console, QuestionsDao dao, MarkCalc calc, MessageSource ms) throws IOException {
        return new AttemptImpl(console, dao, calc, ms);
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("/il8n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

//    @Bean
//    private QuestionsDao getDao(@Value("${db.url}") String value) throws FileNotFoundException {
//            return new CSVQuestionsDaoImpl(value);
//    }

}


