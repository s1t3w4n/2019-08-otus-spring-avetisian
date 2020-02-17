package ru.otus.hw04.shell.app;

import java.util.Locale;
import java.util.Map;

public interface LocaleService {
    Map<Integer, String> getLanguageList();

    Locale getAvailableLocales(Integer number);

    Locale getCurrentLocale();

    void setCurrentLocale(Locale currentLocale);
}
