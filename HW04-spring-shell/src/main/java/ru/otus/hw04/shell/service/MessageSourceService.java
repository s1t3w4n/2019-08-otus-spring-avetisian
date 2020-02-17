package ru.otus.hw04.shell.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageSourceService {

    private final MessageSource messageSource;

    public MessageSourceService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String bundle, Locale locale) {
        return messageSource.getMessage(bundle, null, locale);
    }

    public String getMessage(String bundle, Locale locale, Object... variables) {
        return messageSource.getMessage(bundle, variables, locale);
    }
}
