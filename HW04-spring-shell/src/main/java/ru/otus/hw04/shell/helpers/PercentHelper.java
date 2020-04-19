package ru.otus.hw04.shell.helpers;

public final class PercentHelper {

    private static final double MAX_PERCENT = 100;

    public static int calculateMultipleQuestionResult(int rightAnsweredOptionsCount, int rightOptions) {
        if (rightAnsweredOptionsCount == rightOptions) {
            return (int) MAX_PERCENT;
        } else {
            return ((int) Math.ceil(MAX_PERCENT / rightOptions) * rightAnsweredOptionsCount);
        }
    }

    public static int getMaxPercent() {
        return (int) MAX_PERCENT;
    }
}
