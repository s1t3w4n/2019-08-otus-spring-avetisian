package ru.otus.hw04.shell.dao;

import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.QuestionPrintAdapter;
import ru.otus.hw04.shell.app.LocaleService;
import ru.otus.hw04.shell.app.MessageSourceService;

@Service
public class QuestionPrintAdapterImpl implements QuestionPrintAdapter {
    private final MessageSourceService messageSourceService;
    private final LocaleService localeService;

    public QuestionPrintAdapterImpl(MessageSourceService messageSourceService, LocaleService localeService) {
        this.messageSourceService = messageSourceService;
        this.localeService = localeService;
    }

    @Override
    public String print(String bundle, Object... variables) {
        return messageSourceService.getMessage(bundle, localeService.getCurrentLocale(), variables);
    }

    @Override
    public String print(String bundle) {
        return messageSourceService.getMessage(bundle, localeService.getCurrentLocale());
    }
}
