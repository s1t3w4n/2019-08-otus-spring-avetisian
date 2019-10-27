package ru.otus.hw02.app;

import ru.otus.hw02.data.Question;
import ru.otus.hw02.exceptions.AnswerException;

public interface MarkCalc {
    int getResult();
    boolean checkAnswer(String answer, Question question, OptionsShuffler optionsShuffler) throws AnswerException;
}
