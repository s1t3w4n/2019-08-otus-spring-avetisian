package app;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandartMarkCalcImpl implements MarkCalc {

    private static final int MAX_RESULT = 100;

    private List<Integer> questionMarks;

    public StandartMarkCalcImpl() {
        questionMarks = new ArrayList<>();
    }

    @Override
    public void addMark(int rightOptionsQuantity, boolean... answerResults) {
        int answerWeight = (int) Math.ceil(MAX_RESULT / rightOptionsQuantity);
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
    public int getResult() {
        long sum = 0;
        for (Integer questionMark : questionMarks) {
            sum += questionMark;
        }

        return (int) (sum / questionMarks.size());
    }

}
