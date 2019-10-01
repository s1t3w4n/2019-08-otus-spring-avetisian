import app.*;
import console.Console;
import console.ConsoleImpl;
import dao.CSVQuestionsDaoImpl;
import dao.QuestionsDao;
import data.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
//import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.util.List;
@PropertySource("classpath:application.properties")
@Component
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
    private Console getConsole() {
        return new ConsoleImpl();
    }

    @Bean
    private MarkCalc getMarkCalc() {
        return new StandardMarkCalcImpl();
    }


    @Bean
    private Attempt getAttempt(Console console, List<Question> questions, MarkCalc calc) {
        return new AttemptImpl(console, questions, calc);
    }

    @Bean
    private QuestionsDao getDao(Console console, @Value("${db.url}") String value) {
        try {
            return new CSVQuestionsDaoImpl(value);
        } catch (FileNotFoundException e) {
            console.print(e.getMessage());
        }
        return null;
    }

    @Bean
    private List<Question> getQuestions(QuestionsDao dao) {
        return dao.loadQuestions();
    }
}
