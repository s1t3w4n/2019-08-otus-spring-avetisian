package ru.otus.hw14.shell;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw14.services.LibraryService;

@ShellComponent
public class ShellCommands implements PromptProvider {
    private final LibraryService libraryService;

    public ShellCommands(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @ShellMethod(value = "JPA data", key = {"jpa", "j"})
    public String jpaData() {
        return libraryService.getJPAData();
    }

    @ShellMethod(value = "Mongo data", key = {"mongo", "m"})
    public String mongoData() {
        return libraryService.getMongoData();
    }

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("Batch:>>>");
    }
}
