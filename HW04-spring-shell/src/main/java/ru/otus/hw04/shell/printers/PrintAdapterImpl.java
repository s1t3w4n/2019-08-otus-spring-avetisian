package ru.otus.hw04.shell.printers;

import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.app.PrintAdapter;
import ru.otus.hw04.shell.app.LocaleService;
import ru.otus.hw04.shell.app.MessageSourceService;

@Service
public class PrintAdapterImpl implements PrintAdapter {
    private final MessageSourceService messageSourceService;
    private final LocaleService localeService;

    public PrintAdapterImpl(MessageSourceService messageSourceService, LocaleService localeService) {
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

    private String[] takeQuestionValues(String[] questionParts) {
        String[] parts = new String[questionParts.length - 1];
        if (questionParts.length - 1 >= 0) System.arraycopy(questionParts, 1, parts, 0, questionParts.length - 1);
        return parts;
    }
}
