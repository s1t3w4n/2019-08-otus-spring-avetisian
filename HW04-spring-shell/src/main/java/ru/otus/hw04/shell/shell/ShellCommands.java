package ru.otus.hw04.shell.shell;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw04.shell.service.LocaleService;

import java.util.Locale;
import java.util.Map;


@ShellComponent
public class ShellCommands implements PromptProvider {
    private final LocaleService ls;

    public ShellCommands(LocaleService ls) {
        this.ls = ls;
        languageList();
    }

    @ShellMethod(value = "Set Locale", key = ("l"))
    public String language(@ShellOption(defaultValue = "1") Integer l) {
        Locale locale = ls.getLocale(l);
        return "Chosen " + locale.getDisplayName(locale) + " language";
    }

    @ShellMethod(value = "List of available languages", key = ("ll"))
    public String languageList() {
        StringBuilder sb = new StringBuilder("Available languages:\n");
        for (Map.Entry<Integer, String> entry : ls.getLanguageList().entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("choose-locale:>");
    }

}
