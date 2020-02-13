package ru.otus.hw04.shell.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.hw04.shell.dao.QuestionPrintAdapter;
import ru.otus.hw04.shell.service.LocaleService;
import ru.otus.hw04.shell.service.MessageSourceService;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Testing SimpleQuestion.class")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class SimpleQuestionTest {
    private static String body = "Some text question";
    private static String correctAnswer = "correct";
    private static List<String> wrongAnswers = new ArrayList<>();

    @EnableAutoConfiguration
    @Configuration
    static class TemporaryConfiguration {
        @Bean
        QuestionPrintAdapter questionPrintAdapter(MessageSourceService mss, LocaleService ls) {
            return new QuestionPrintAdapter(mss, ls);
        }

        @Bean
        MessageSourceService mss(MessageSource ms) {
            return new MessageSourceService(ms);
        }

        @Bean
        public MessageSource messageSource(@Value("${bundles}") String baseName) {
            ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
            ms.setBasename(baseName);
            ms.setDefaultEncoding("windows-1251");

            return ms;
        }

        @Bean
        LocaleService ls(@Value("${bundles}") String baseName) {
            return new LocaleService(baseName);
        }
    }

    @Autowired
    private QuestionPrintAdapter questionPrintAdapter;

    static {
        wrongAnswers.add("wrong1");
        wrongAnswers.add("wrong2");
        wrongAnswers.add("wrong2");
        wrongAnswers.add("wrong3");
        wrongAnswers.add("wrong4");
        wrongAnswers.add("correct");
    }

    private static Question question = new SimpleQuestion(body, correctAnswer, wrongAnswers);

    @DisplayName("-1 with inconsistent answer")
    @Test
    void shouldReturnMinusForInconsistentAnswer() {
        assertEquals(-1, question.rateTheAnswer("some answer"));
    }

    @DisplayName("Returning zero with consistent wrong answer")
    @Test
    void shouldReturnZeroForConsistentIncorrectAnswer() {
        String answer = wrongAnswers.get((int) (Math.random() * (wrongAnswers.size() - 1)));
        String questionText = question.printQuestion(questionPrintAdapter);
        String[] lines = questionText.split("\n");
        Pattern pattern = Pattern.compile("^.+" + answer + "$");

        String numberOfOption = "";
        for (int i = lines.length - wrongAnswers.size(); i < lines.length; i++) {
            Matcher matcher = pattern.matcher(lines[i]);
            if (matcher.find()) {
                Pattern pNumber = Pattern.compile("^\\d+");
                Matcher mNumber = pNumber.matcher(lines[i]);
                if (mNumber.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    numberOfOption = lines[i].substring(start, end);
                }

            }
        }
        assertEquals(0, question.rateTheAnswer(numberOfOption));
    }

    @DisplayName("Returning 100 with correct answer")
    @Test
    void shouldReturnTrueForCorrectAnswer() {
        assertEquals(100, question.rateTheAnswer(correctAnswer));
    }
}
