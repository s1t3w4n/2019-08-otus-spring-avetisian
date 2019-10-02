package ru.otus.hw02.app;

public interface MarkCalc {
    public void addMark(int rightOptionsQuantity, boolean... answerResults);
    public int getResult();
}
