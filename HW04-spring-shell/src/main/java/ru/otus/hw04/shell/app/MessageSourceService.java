package ru.otus.hw04.shell.app;

import java.util.Locale;

public interface MessageSourceService {
    String getMessage(String bundle, Locale locale);

    String getMessage(String bundle, Locale locale, Object... variables);
}
