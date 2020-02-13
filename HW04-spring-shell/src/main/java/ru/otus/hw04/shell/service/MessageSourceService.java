package ru.otus.hw04.shell.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageSourceService {

    private final MessageSource ms;

    public MessageSourceService(MessageSource ms) {
        this.ms = ms;
    }

    public String getMessage(String bundle, Locale locale) {
        return ms.getMessage(bundle, null, locale);
    }

    public String getMessage(String bundle, Locale locale, Object... variables) {
        return ms.getMessage(bundle, variables, locale);
    }
}
