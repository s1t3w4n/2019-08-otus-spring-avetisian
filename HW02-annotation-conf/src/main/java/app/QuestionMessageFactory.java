package app;


import data.Question;
import exceptions.AnswerException;
import exceptions.WrongFormatAnswerException;
import exceptions.WrongNumberAnswerException;

import java.util.Map;
import java.util.Objects;

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
        switch (question.getType()) {
            case SIMPLE:
                try {
                    int number = Integer.parseInt(answer);
                    if (Objects.nonNull(optionsShuffler.getAnswer(number))) {
                        return true;
                    } else {
                        throw new WrongNumberAnswerException("You have to choose one of the offered options");
                    }
                } catch (NumberFormatException e) {
                    throw new WrongFormatAnswerException("You have to print only number of correct option");
                }
            case MULTIPLE:
                return false;
            case OPEN:
                return false;
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