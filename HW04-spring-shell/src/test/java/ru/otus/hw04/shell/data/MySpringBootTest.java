package ru.otus.hw04.shell.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.hw04.shell.dao.creators.QuestionCreator;
import ru.otus.hw04.shell.dao.creators.SimpleQuestionCreator;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DisplayName("Testing question beans")
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
class MySpringBootTest {

    @EnableAutoConfiguration
    @Configuration
    static class TemporaryConfiguration {
        @Bean
        QuestionCreator simple() {
            return new SimpleQuestionCreator();
        }
    }

    @Autowired
    private List<QuestionCreator> creators;

    @DisplayName("Contains only one creator of SimpleQuestions")
    @Test
    void shouldContainsOnlySimpleCreator() {
        assertEquals(creators.get(0).getType(), QuestionType.SIMPLE);
    }
}
