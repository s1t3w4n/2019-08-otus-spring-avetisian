package ru.otus.hw04.shell.shell;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw04.shell.service.LocaleService;
import ru.otus.hw04.shell.service.MSService;
import ru.otus.hw04.shell.service.QuizService;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;


@ShellComponent
public class ShellCommands implements PromptProvider {

    private final LocaleService ls;

    private boolean attributeLocale = true;

    private final MSService mss;

    private final QuizService qs;


    public ShellCommands(LocaleService ls, MSService mss, QuizService qs) {
        this.ls = ls;
        this.mss = mss;
        this.qs = qs;
    }

    @ShellMethod(value = "Set Locale", key = ("l"))
    public String language(@ShellOption(defaultValue = "1") Integer l) {
        Locale locale = ls.getAvailableLocales(l);
        ls.setCurrentLocale(locale);
        attributeLocale = false;
        return mss.getMessage("locale.chosen", locale, locale.getDisplayName(locale));
    }

    @ShellMethod(value = "List of available languages", key = ("ll"))
    public String languageList() {
        StringBuilder sb = new StringBuilder("\n");
        for (Map.Entry<Integer, String> entry : ls.getLanguageList().entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue());
            sb.append("\n");
        }
        return mss.getMessage("locale.available", ls.getCurrentLocale(), sb.toString());
    }

    @ShellMethod(value = "Quiz", key = ("q"))
    public String quiz() {
        String start;
        try {
            start = qs.start();
            return start;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "isn't working";
    }

    @Override
    public AttributedString getPrompt() {
        if (attributeLocale) {
            return new AttributedString(mss.getMessage("locale.choose", ls.getCurrentLocale()));
        } else {
            return new AttributedString(mss.getMessage("locale.answer", ls.getCurrentLocale()));
        }
    }

}
