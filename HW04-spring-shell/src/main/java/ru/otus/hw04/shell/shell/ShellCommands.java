package ru.otus.hw04.shell.shell;

import org.jline.utils.AttributedString;
import org.springframework.context.MessageSource;
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

    private boolean attributeLocale = true;

    private final MessageSource messageSource;

    public ShellCommands(LocaleService ls, MessageSource messageSource) {
        this.ls = ls;
        this.messageSource = messageSource;
    }

    @ShellMethod(value = "Set Locale", key = ("l"))
    public String language(@ShellOption(defaultValue = "1") Integer l) {
        Locale locale = ls.getAvailableLocales(l);
        ls.setCurrentLocale(locale);
        attributeLocale = false;
        return messageSource.getMessage("locale.chosen",
                new Object[]{locale.getDisplayName(locale)},
                locale);
    }

    @ShellMethod(value = "List of available languages", key = ("ll"))
    public String languageList() {
        StringBuilder sb = new StringBuilder("\n");
        for (Map.Entry<Integer, String> entry : ls.getLanguageList().entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue());
            sb.append("\n");
        }
        return messageSource.getMessage("locale.available",
                new Object[]{sb.toString()},
                ls.getCurrentLocale());
    }

    @Override
    public AttributedString getPrompt() {
        if (attributeLocale) {
            return new AttributedString(messageSource.getMessage("locale.choose",
                    null,
                    ls.getCurrentLocale()));
        } else {
            return new AttributedString(messageSource.getMessage("locale.answer",
                    null,
                    ls.getCurrentLocale()));
        }
    }

}
