package ru.otus.hw02.data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing MultipleQuestion.class")
class MultipleQuestionTest {

    private static String body = "Some text question";
    private static List<String> correctAnswers = new ArrayList<>();
    private static List<String> wrongAnswers = new ArrayList<>();

    static {
        correctAnswers.add("correct1");
        correctAnswers.add("correct2");
        correctAnswers.add("correct2");
        correctAnswers.add("correct3");
        correctAnswers.add("correct4");
        wrongAnswers.add("wrong1");
        wrongAnswers.add("wrong2");
        wrongAnswers.add("wrong2");
        wrongAnswers.add("wrong3");
        wrongAnswers.add("wrong4");
        wrongAnswers.add("correct4");
    }

    private static Question question = new MultipleQuestion(body, correctAnswers, wrongAnswers);

    @DisplayName("Class MultiplyQuestion is correctly created")
    @Test
    void shouldCorrectCreated() {
        Set<String> options = new HashSet<>(wrongAnswers);
        Set<String> correctOptions = new HashSet<>(correctAnswers);
        options.addAll(new HashSet<>(correctAnswers));

        assertAll(
                () -> assertEquals(body, question.getQuestionText()),
                () -> assertEquals(options, question.getOptions()),
                () -> assertEquals(correctOptions.size(), question.getQuantityOfRightOptions()));
    }

    @DisplayName("Question returning correct type")
    @Test
    void shouldReturnCorrectType() {
        assertEquals(QuestionType.MULTIPLE, question.getType());
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
        assertTrue(question.checkAnswer(correctAnswers.get((int) (Math.random() * (wrongAnswers.size() - 1)))));
    }
}
