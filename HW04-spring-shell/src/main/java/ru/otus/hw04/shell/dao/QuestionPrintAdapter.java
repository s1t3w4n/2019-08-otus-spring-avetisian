package ru.otus.hw04.shell.dao;

import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.service.LocaleService;
import ru.otus.hw04.shell.service.MessageSourceService;

@Service
public class QuestionPrintAdapter {
    private final MessageSourceService messageSourceService;
    private final LocaleService localeService;

    public QuestionPrintAdapter(MessageSourceService messageSourceService, LocaleService localeService) {
        this.messageSourceService = messageSourceService;
        this.localeService = localeService;
    }

    public String print(String bundle, Object... variables) {
        return messageSourceService.getMessage(bundle, localeService.getCurrentLocale(), variables);
    }

    public String print(String bundle) {
        return messageSourceService.getMessage(bundle, localeService.getCurrentLocale());
    }
}
