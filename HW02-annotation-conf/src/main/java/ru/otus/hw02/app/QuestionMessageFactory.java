package ru.otus.hw02.app;


import ru.otus.hw02.data.Question;
import ru.otus.hw02.exceptions.EmptyAnswerException;
import ru.otus.hw02.exceptions.AnswerException;
import ru.otus.hw02.exceptions.WrongFormatAnswerException;
import ru.otus.hw02.exceptions.WrongNumberAnswerException;

import java.util.*;

class QuestionMessageFactory {


    static String getTask(Question question) {
        switch (question.getType()) {
            case SIMPLE:
                return "Print number of correct one answer";
            case MULTIPLE:
                return "Print number of correct one or more answers";
            case OPEN:
                return "Print answer";
            default:
                return null;
        }
    }

    static String getOptions(Question question, OptionsShuffler shuffler) {
        switch (question.getType()) {
            case SIMPLE:
                return buildOptionsMessage(shuffler, question);
            case MULTIPLE:
                return buildOptionsMessage(shuffler, question);
            case OPEN:
                return null;
            default:
                return null;
        }
    }

    static boolean checkAnswer(String answer, Question question, OptionsShuffler optionsShuffler) throws AnswerException {
        if (Objects.isNull(answer) || answer.isEmpty()) {
            throw new EmptyAnswerException("You must print something\n");
        }
        switch (question.getType()) {
            case SIMPLE:
                try {
                    int number = Integer.parseInt(answer);
                    String option = optionsShuffler.getAnswer(number);
                    if (Objects.nonNull(option)) {
                        return true;
                    } else {
                        throw new WrongNumberAnswerException("You have to choose one of the offered options\n");
                    }
                } catch (NumberFormatException e) {
                    throw new WrongFormatAnswerException("You have to print only number of correct option\n");
                }
            case MULTIPLE:
                Set<String> answerOptions = new HashSet<>(Arrays.asList(answer.split(";")));
                Set<Integer> numbers = new HashSet<>();
                for (String option : answerOptions) {
                    try {
                        int number = Integer.parseInt(option);
                        numbers.add(number);
                    } catch (NumberFormatException e) {
                        throw new WrongFormatAnswerException("You have to print only a numbers of correct options\n");
                    }
                }
                for (Integer number : numbers) {
                    if (Objects.isNull(optionsShuffler.getAnswer(number))) {
                        throw new WrongNumberAnswerException("There is no such numbers in options\n");
                    }
                }
                return true;
            case OPEN:
                return true;
            default:
                return false;
        }
    }

    private static String buildOptionsMessage(OptionsShuffler shuffler, Question question) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, String> entry :
                shuffler.shuffleAnswers(question.getOptions()).getSequence().entrySet()) {
            builder.append(entry.getKey())
                    .append(". ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return builder.toString();
    }
}