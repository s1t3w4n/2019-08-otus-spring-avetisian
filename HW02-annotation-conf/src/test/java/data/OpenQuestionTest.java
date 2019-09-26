package data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
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
    }

    private static Question openQuestion = new OpenQuestion(body, answers);


    @DisplayName("Checking that class OpenQuestion is correctly created")
    @Test
    void shouldCorrectCreated() {
        assertAll(
                () -> assertEquals(body, openQuestion.getQuestionText()),
                () -> assertEquals(new HashSet<>(answers), openQuestion.getOptions()),
                () -> assertEquals(1, openQuestion.getQuantityOfRightOptions()));
    }

    @DisplayName("Checking that answer is correct without variable options")
    @Test
    void correctSimpleAnswer() {
        assertTrue(openQuestion.checkAnswer("answer1"));
    }

    @DisplayName("Checking that answer is wrong without variable options")
    @Test
    void wrongSimpleAnswer() {
        assertFalse(openQuestion.checkAnswer("wrong answer"));
    }

    @DisplayName("Checking that answer is correct with variable ending")
    @Test
    void variableEndingAnswer() {
        assertTrue(openQuestion.checkAnswer("answer_any_ending"));
    }

    @DisplayName("Checking that answer is correct with variable ending and chars")
    @Test
    void variableEndingAndCharsAnswer() {
        assertTrue(openQuestion.checkAnswer("4nsw3r_any_ending"));
    }

    @DisplayName("Checking that answer is correct with variable chars")
    @Test
    void variableCharsAnswer() {
        assertTrue(openQuestion.checkAnswer("4nsw3r4"));
    }

    @DisplayName("Checking that answer is wrong with short length")
    @Test
    void shortAnswer() {
        assertFalse(openQuestion.checkAnswer("ans"));
    }

    @DisplayName("Answer returning correct type")
    @Test
    void shouldReturnCorrectType() {
        assertEquals(QuestionType.OPEN, openQuestion.getType());
    }

}
