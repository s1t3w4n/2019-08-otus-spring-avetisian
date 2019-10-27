package ru.otus.hw03.app;

import ru.otus.hw03.data.Question;
import ru.otus.hw03.exceptions.AnswerException;

public interface MarkCalc {
    int getResult();
    boolean checkAnswer(String answer, Question question, OptionsShuffler optionsShuffler) throws AnswerException;
}
