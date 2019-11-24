package ru.otus.hw04.shell.shell;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw04.shell.service.LocaleService;

import java.util.Locale;

@ShellComponent
public class ShellCommands implements PromptProvider {

    //private final Locale locale;

    public ShellCommands(LocaleService ls) {
        /*this.locale =*/ getLocale(ls);
    }

    @ShellMethod(value = "Printer")
    public String print(String text) {
        return text;
    }

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("choose-locale:>");
    }

    private void getLocale(LocaleService ls) {
        for (String s : ls.getLanguageList()) {
            System.out.println(s);
        }
    }

}
