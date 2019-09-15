package data;

public class MultipleQuestion implements Question {
    @Override
    public void printQuestion() {

    }

    @Override
    public int getMark(String answer) {
        return 0;
    }

    @Override
    public boolean checkAnswer(String answer) {
        return false;
    }
}
