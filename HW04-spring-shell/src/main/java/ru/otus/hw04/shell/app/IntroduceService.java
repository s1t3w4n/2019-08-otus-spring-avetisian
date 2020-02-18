package ru.otus.hw04.shell.app;

public interface IntroduceService {
    boolean isIntroduced();

    String introduce(String data);

    String askName();

    String askSurname();
}
