package ru.otus.hw04.shell.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.Console;

import java.util.Objects;

@Service
public class ConsoleService implements Console {

    private final MessageSource ms;

    private final LocaleService ls;

    public ConsoleService(MessageSource ms, LocaleService ls) {
        this.ms = ms;
        this.ls = ls;
    }

    @Override
    public void print(String information) {
        if (Objects.nonNull(information)) {
            System.out.print(information);
        }
    }

    @Override
    public void print(String bundle, Object... variables) {
        print(ms.getMessage(bundle, variables, ls.getCurrentLocale()));
    }

}
