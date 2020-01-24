package ru.otus.hw04.shell.dao;

import org.springframework.stereotype.Service;
import ru.otus.hw04.shell.service.LocaleService;
import ru.otus.hw04.shell.service.MSService;

@Service
public class QuestionPrintAdapter {
    private final MSService mss;
    private final LocaleService ls;

    public QuestionPrintAdapter(MSService mss, LocaleService ls) {
        this.mss = mss;
        this.ls = ls;
    }

    public String print(String bundle, Object... variables) {
        return mss.getMessage(bundle, ls.getCurrentLocale(), variables);
    }

    public String print(String bundle) {
        return mss.getMessage(bundle, ls.getCurrentLocale());
    }
}
