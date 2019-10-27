package ru.otus.hw02.console;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ConsoleImpl implements Console {
    private final Scanner scanner;
    private final Locale locale;
    private final MessageSource messageSource;

    ConsoleImpl(MessageSource messageSource) {
        scanner = new Scanner(System.in);
        this.messageSource = messageSource;
        locale = getLocale();
    }

    @Override
    public String read() {
        return scanner.nextLine();
    }

    @Override
    public void print(String message) {
        if (Objects.nonNull(message)) {
            System.out.print(message);
        }
    }

    @Override
    public void print(String bundle, Object... variables) {
        print(messageSource.getMessage(bundle, variables, locale));
    }

    private Locale getLocale() {
        int language;
        List<String> languages = new ArrayList<>();
        languages.add("English");
        languages.add("Russian");
        do {
            print("Choose your language: " + "\n");
            for (int i = 1; i <= languages.size(); i++) {
                print(i + ": " + languages.get(i - 1) + "\n");
            }
            print("Print number of language: ");
            if (scanner.hasNextInt()) {
                language = scanner.nextInt();
            } else {
                language = -1;
                print("Incorrect type \"" + read() + "\"\n");
            }
        } while (0 >= language || language > languages.size());
        print(read());
        switch (languages.get(language - 1)) {
            case "English":
                return new Locale("en");
            case "Russian":
                return new Locale("ru", "RU");
            default:
                return Locale.getDefault();
        }
    }
}
