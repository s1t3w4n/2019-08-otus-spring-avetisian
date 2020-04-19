package ru.otus.hw04.shell.app;

import ru.otus.hw04.shell.helpers.LocalesHolder;

import java.util.Locale;

public interface LocaleService {
    LocalesHolder getLanguageList();

    Locale getAvailableLocales(Integer number);

    Locale getCurrentLocale();

    void setCurrentLocale(Locale currentLocale);
}
