package ru.otus.hw04.shell.shell;

import org.jline.utils.AttributedString;
import org.springframework.shell.Availability;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw04.shell.app.LocaleService;
import ru.otus.hw04.shell.app.MessageSourceService;
import ru.otus.hw04.shell.app.QuizService;

import java.util.Locale;
import java.util.Map;

import static org.springframework.shell.standard.ShellOption.NULL;


@ShellComponent
public class ShellCommands implements PromptProvider {

    private final LocaleService ls;

    private boolean attributeLocale = false;
    private boolean showLanguages = false;

    private final MessageSourceService mss;

    private final QuizService quizService;


    public ShellCommands(LocaleService localeService,
                         MessageSourceService messageSourceService,
                         QuizService quizService) {
        this.ls = localeService;
        this.mss = messageSourceService;
        this.quizService = quizService;
    }

    @ShellMethod(value = "Set Locale", key = ("l"))
    @ShellMethodAvailability(value = "isLanguageAvailable")
    public String language(@ShellOption(defaultValue = "1") Integer l) {
        Locale locale = ls.getAvailableLocales(l);
        ls.setCurrentLocale(locale);
        attributeLocale = true;
        return mss.getMessage("locale.chosen", locale, locale.getDisplayName(locale));
    }

    @ShellMethod(value = "List of available languages", key = ("ll"))
    public String languageList() {
        StringBuilder sb = new StringBuilder("\n");
        for (Map.Entry<Integer, String> entry : ls.getLanguageList().entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue());
            sb.append("\n");
        }
        showLanguages = true;
        return mss.getMessage("locale.available", ls.getCurrentLocale(), sb.toString());
    }

    @ShellMethod(value = "Quiz", key = {"a", "answer"})
    @ShellMethodAvailability(value = "isQuizAvailable")
    public String answer(@ShellOption(defaultValue = NULL) String answer) {
        return quizService.continueQuiz(answer);
    }

    @Override
    public AttributedString getPrompt() {
        if (!attributeLocale) {
            return new AttributedString(mss.getMessage("locale.choose", ls.getCurrentLocale()));
        } else {
            return new AttributedString(mss.getMessage("locale.answer", ls.getCurrentLocale()));
        }
    }

    private Availability isLanguageAvailable() {
        return showLanguages ? Availability.available() :
                Availability.unavailable(mss.getMessage("language.show", ls.getCurrentLocale()));
    }

    private Availability isQuizAvailable() {
        return attributeLocale ? Availability.available() :
                Availability.unavailable(mss.getMessage("language.choose", ls.getCurrentLocale()));
    }
}
