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

import static org.springframework.shell.standard.ShellOption.NULL;


@ShellComponent
public class ShellCommands implements PromptProvider {

    private final LocaleService languageList;

    private boolean attributeLocale = false;
    private boolean showLanguages = false;

    private final MessageSourceService mss;

    private final QuizService quizService;


    public ShellCommands(LocaleService localeService,
                         MessageSourceService messageSourceService,
                         QuizService quizService) {
        this.languageList = localeService;
        this.mss = messageSourceService;
        this.quizService = quizService;
    }

    @ShellMethod(value = "Set Locale", key = ("l"))
    @ShellMethodAvailability(value = "isLanguageAvailable")
    public String language(@ShellOption(defaultValue = "1") Integer l) {
        Locale locale = languageList.getAvailableLocales(l);
        languageList.setCurrentLocale(locale);
        attributeLocale = true;
        return mss.getMessage("locale.chosen", locale, locale.getDisplayName(locale));
    }

    @ShellMethod(value = "List of available languages", key = ("ll"))
    public String languageList() {
        String message = languageList.getLanguageList().getLaguageMessage();
        showLanguages = true;
        return mss.getMessage("locale.available", languageList.getCurrentLocale(), message);
    }

    @ShellMethod(value = "Quiz", key = {"a", "answer"})
    @ShellMethodAvailability(value = "isQuizAvailable")
    public String answer(@ShellOption(defaultValue = NULL) String answer) {
        return quizService.continueQuiz(answer);
    }

    @Override
    public AttributedString getPrompt() {
        if (!attributeLocale) {
            return new AttributedString(mss.getMessage("locale.choose", languageList.getCurrentLocale()));
        } else {
            return new AttributedString(mss.getMessage("locale.answer", languageList.getCurrentLocale()));
        }
    }

    private Availability isLanguageAvailable() {
        return showLanguages ? Availability.available() :
                Availability.unavailable(mss.getMessage("language.show", languageList.getCurrentLocale()));
    }

    private Availability isQuizAvailable() {
        return attributeLocale ? Availability.available() :
                Availability.unavailable(mss.getMessage("language.choose", languageList.getCurrentLocale()));
    }
}
