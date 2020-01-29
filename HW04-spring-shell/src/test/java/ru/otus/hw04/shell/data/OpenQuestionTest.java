package ru.otus.hw04.shell.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing OpenQuestion.class")
class OpenQuestionTest {
    private static String body = "Some text question";
    private static List<String> answers = new ArrayList<>();

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

    private static Question openQuestion = new OpenQuestion(body, answers);




    @DisplayName("Checking that answer is correct with variable chars")
    @Test
    void variableCharsAnswer() {
        assertEquals(100, openQuestion.rateTheAnswer("4nsw3r6"));
    }

    @Test
    void variableCharsAnswer2() {
        assertEquals(100, openQuestion.rateTheAnswer("cheese"));
    }


}
