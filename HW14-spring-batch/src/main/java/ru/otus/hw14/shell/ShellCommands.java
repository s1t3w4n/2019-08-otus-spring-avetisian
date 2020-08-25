package ru.otus.hw14.shell;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw14.services.LibraryService;

@AllArgsConstructor
@ShellComponent
public class ShellCommands implements PromptProvider {
    private final LibraryService libraryService;

    @ShellMethod(value = "JPA data", key = {"jpa", "j"})
    public String jpaData() {
        return libraryService.getJPAData();
    }

    @ShellMethod(value = "Mongo data", key = {"mongo", "m"})
    public String mongoData() {
        return libraryService.getMongoData();
    }

    @SneakyThrows
    @ShellMethod(value = "Transfer", key = {"transfer", "t"})
    public void transfer() {
        libraryService.dbMigration();
    }

    @SneakyThrows
    @ShellMethod(value = "Clean", key = {"clean", "c"})
    public void clean() {
        libraryService.clean();
    }

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("Batch:>>>");
    }
}
