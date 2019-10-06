package ru.otus.hw02.app;

import ru.otus.hw02.data.Question;
import ru.otus.hw02.exceptions.AnswerException;
import ru.otus.hw02.exceptions.EmptyAnswerException;
import ru.otus.hw02.exceptions.WrongFormatAnswerException;
import ru.otus.hw02.exceptions.WrongNumberAnswerException;

import java.util.*;

public class StandardMarkCalcImpl implements MarkCalc {

    private static final double MAX_RESULT = 100;

    private List<Integer> questionMarks;

    public StandardMarkCalcImpl() {
        questionMarks = new ArrayList<>();
    }

    //@Override
    private void addMark(int rightOptionsQuantity, boolean... answerResults) {
        double answerWeight = Math.ceil(MAX_RESULT / rightOptionsQuantity);
        int mark = 0;
        for (boolean ar : answerResults) {
            if (ar) {
                mark += answerWeight;
            } else {
                mark = -100;
            }
        }
        if (mark > 100) {
            questionMarks.add(100);
        } else if (mark < 0) {
            questionMarks.add(0);
        } else {
            questionMarks.add(mark);
        }
    }

    @Override
    public boolean checkAnswer(String answer, Question question, OptionsShuffler optionsShuffler) throws AnswerException {
        if (Objects.isNull(answer) || answer.isEmpty()) {
            throw new EmptyAnswerException("print.exception.empty");
        }
        switch (question.getType()) {
            case SIMPLE:
                try {
                    int number = Integer.parseInt(answer);
                    String option = optionsShuffler.getAnswer(number);
                    if (Objects.nonNull(option)) {
                        addMark(question.getQuantityOfRightOptions(), question.checkAnswer(option));
                        return true;
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
                addMark(question.getQuantityOfRightOptions(), booleans);
                return true;
            case OPEN:
                addMark(question.getQuantityOfRightOptions(), question.checkAnswer(answer));
                return true;
            default:
                return false;
        }
    }

    @Override
    public int getResult() {
        long sum = 0;
        for (Integer questionMark : questionMarks) {
            sum += questionMark;
        }

        return (int) (sum / questionMarks.size());
    }

}
