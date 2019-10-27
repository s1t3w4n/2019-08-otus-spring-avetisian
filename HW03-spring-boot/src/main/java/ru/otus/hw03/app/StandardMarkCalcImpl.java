package ru.otus.hw03.app;

import ru.otus.hw03.data.Question;
import ru.otus.hw03.exceptions.*;

import java.util.*;

public class StandardMarkCalcImpl implements MarkCalc {

    private static final double MAX_RESULT = 100;

    private List<Integer> questionMarks;

    public StandardMarkCalcImpl() {
        questionMarks = new ArrayList<>();
    }

    @Override
    public boolean checkAnswer(String answer, Question question, OptionsShuffler optionsShuffler) throws AnswerException {
        if (Objects.isNull(answer) || answer.isEmpty()) {
            throw new EmptyAnswerException("print.exception.empty");
        }
        addMark(question.getQuantityOfRightOptions(),
                Objects.requireNonNull(ResultFactory.getResult(answer, question, optionsShuffler)));
        return true;
    }

    @Override
    public int getResult() {
        long sum = 0;
        for (Integer questionMark : questionMarks) {
            sum += questionMark;
        }

        return (int) (sum / questionMarks.size());
    }

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
}
