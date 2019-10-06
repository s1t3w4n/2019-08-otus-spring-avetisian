package ru.otus.hw02.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing SimpleQuestion.class")
class SimpleQuestionTest {
    private static String body = "Some text question";
    private static String correctAnswer = "correct";
    private static List<String> wrongAnswers = new ArrayList<>();

    static {
        wrongAnswers.add("wrong1");
        wrongAnswers.add("wrong2");
        wrongAnswers.add("wrong2");
        wrongAnswers.add("wrong3");
        wrongAnswers.add("wrong4");
        wrongAnswers.add("correct");
    }

    private static Question question = new SimpleQuestion(body, correctAnswer, wrongAnswers);

    @DisplayName("Class OpenQuestion is correctly created")
    @Test
    void shouldCorrectCreated() {
        Set<String> options = new HashSet<>(wrongAnswers);
        options.add(correctAnswer);
        assertAll(
                () -> assertEquals(body, question.getQuestionText()),
                () -> assertEquals(options, question.getOptions()),
                () -> assertEquals(1, question.getQuantityOfRightOptions()));
    }

    @DisplayName("Question returning correct type")
    @Test
    void shouldReturnCorrectType() {
        assertEquals(QuestionType.SIMPLE, question.getType());
    }

    @DisplayName("Returning false with inconsistent answer")
    @Test
    void shouldReturnFalseForInconsistentAnswer() {
        assertFalse(question.checkAnswer("some answer"));
    }

    @DisplayName("Returning false with consistent wrong answer")
    @Test
    void shouldReturnFalseForConsistentIncorrectAnswer() {
        assertFalse(question.checkAnswer(wrongAnswers.get((int) (Math.random() * (wrongAnswers.size() - 1)))));
    }

    @DisplayName("Returning true with correct answer")
    @Test
    void shouldReturnTrueForCorrectAnswer() {
        assertTrue(question.checkAnswer(correctAnswer));
    }
}
