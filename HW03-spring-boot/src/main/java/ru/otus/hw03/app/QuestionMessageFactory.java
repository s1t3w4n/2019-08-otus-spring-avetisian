package ru.otus.hw03.app;


import ru.otus.hw03.data.Question;

import java.util.Map;

class QuestionMessageFactory {

    static String getTask(Question question) {
        switch (question.getType()) {
            case SIMPLE:
                return "print.task.simple";
            case MULTIPLE:
                return "print.task.multiple";
            case OPEN:
                return "print.task.open";
            default:
                return null;
        }
    }

    static String getOptions(Question question, OptionsShuffler shuffler) {
        switch (question.getType()) {
            case SIMPLE:
                return buildOptionsMessage(shuffler);
            case MULTIPLE:
                return buildOptionsMessage(shuffler);
            case OPEN:
                return null;
            default:
                return null;
        }
    }

    private static String buildOptionsMessage(OptionsShuffler shuffler) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<Integer, String> entry :
                shuffler.getSequence().entrySet()) {
            builder.append(entry.getKey())
                    .append(". ")
                    .append(entry.getValue())
                    .append("\n");
        }
        return builder.toString();
    }
}