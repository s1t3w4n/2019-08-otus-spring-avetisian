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
import ru.otus.hw04.shell.app.LocaleService;
import ru.otus.hw04.shell.app.MessageSourceService;
import ru.otus.hw04.shell.app.QuestionPrintAdapter;
import ru.otus.hw04.shell.dao.QuestionPrintAdapterImpl;
import ru.otus.hw04.shell.service.LocaleServiceImpl;
import ru.otus.hw04.shell.service.MessageSourceServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing OpenQuestion.class")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class OpenQuestionTest {
    private static String body = "Some text question";
    private static List<String> answers = new ArrayList<>();
    @Autowired
    private static QuestionPrintAdapter questionPrintAdapter;

    static {
        answers.add("answer1");
        answers.add("answer2");
        answers.add("answer2");
        answers.add("answer3");
        answers.add("answer*");
        answers.add("*nsw*r4");
        answers.add("*nsw*r*");
        answers.add("cheese*");
    }

    @EnableAutoConfiguration
    @Configuration
    static class TemporaryConfiguration {
        @Bean
        QuestionPrintAdapter questionPrintAdapter(MessageSourceService mss, LocaleService ls) {
            return new QuestionPrintAdapterImpl(mss, ls);
        }

        @Bean
        MessageSourceService mss(MessageSource ms) {
            return new MessageSourceServiceImpl(ms);
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
            return new LocaleServiceImpl(baseName);
        }
    }

    private static Question openQuestion = new OpenQuestion(questionPrintAdapter, body, answers);

    @DisplayName("Answer is correct without variable options")
    @Test
    void correctSimpleAnswer() {
        assertEquals(100, openQuestion.rateTheAnswer("answer1"));
    }

    @DisplayName("Answer is wrong without variable options")
    @Test
    void wrongSimpleAnswer() {
        assertEquals(0, openQuestion.rateTheAnswer("wrong answer"));
    }

    @DisplayName("Answer is correct with a variable ending")
    @Test
    void variableEndingAnswer() {
        assertEquals(100, openQuestion.rateTheAnswer("answer_any_ending"));
    }

    @DisplayName("Answer is correct with either variable a ending and chars")
    @Test
    void variableEndingAndCharsAnswer() {
        assertEquals(100, openQuestion.rateTheAnswer("4nsw3r_any_ending"));
    }

    @DisplayName("Answer is correct with variable chars")
    @Test
    void variableCharsAnswer() {
        assertEquals(100, openQuestion.rateTheAnswer("4nsw3r4"));
    }

    @DisplayName("Answer is wrong with short length")
    @Test
    void shortAnswer() {
        assertEquals(0, openQuestion.rateTheAnswer("ans"));
    }

    @DisplayName("Returning true if we have one optional variable and answering without optional ending")
    @Test
    void oneOptionalVariableAnswer() {
        assertEquals(100, openQuestion.rateTheAnswer("cheese"));
    }
}
