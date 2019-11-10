package ru.otus.hw03.exceptions;

import ru.otus.hw03.app.OptionsShuffler;
import ru.otus.hw03.data.Question;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ResultFactory {

    public static boolean[] getResult(String answer, Question question, OptionsShuffler optionsShuffler) throws AnswerException {
        switch (question.getType()) {
            case SIMPLE:
                try {
                    int number = Integer.parseInt(answer);
                    String option = optionsShuffler.getAnswer(number);
                    if (Objects.nonNull(option)) {
                        return new boolean[]{
                                question.checkAnswer(option)
                        };
                    } else {
                        throw new WrongNumberAnswerException("print.exception.simple.wrong.options");
                    }
                } catch (NumberFormatException e) {
                    throw new WrongFormatAnswerException("print.exception.simple.wrong.format");
                }

            case MULTIPLE:
                Set<String> answerOptions = new HashSet<>(Arrays.asList(answer.split(";")));
                Set<Integer> numbers = new HashSet<>();
                for (String option : answerOptions) {
                    try {
                        int number = Integer.parseInt(option);
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        throw new WrongFormatAnswerException("print.exception.multiple.wrong.format");
                    }
                }
                for (Integer number : numbers) {
                    if (Objects.isNull(optionsShuffler.getAnswer(number))) {
                        throw new WrongNumberAnswerException("print.exception.multiple.wrong.options");
                    }
                }
                boolean[] booleans = new boolean[numbers.size()];
                int i = 0;
                for (Integer number : numbers) {
                    String option = optionsShuffler.getAnswer(number);
                    booleans[i] = question.checkAnswer(option);
                    i++;
                }
                return booleans;
            case OPEN:
                return new boolean[]{
                        question.checkAnswer(answer)
                };
            default:
                return null;
        }
    }
}
