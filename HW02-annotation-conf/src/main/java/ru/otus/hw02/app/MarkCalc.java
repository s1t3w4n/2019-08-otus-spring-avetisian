package ru.otus.hw02.app;

import ru.otus.hw02.data.Question;
import ru.otus.hw02.exceptions.AnswerException;

public interface MarkCalc {
    //void addMark(int rightOptionsQuantity, boolean... answerResults);
    int getResult();
    boolean checkAnswer(String answer, Question question, OptionsShuffler optionsShuffler) throws AnswerException;
}
