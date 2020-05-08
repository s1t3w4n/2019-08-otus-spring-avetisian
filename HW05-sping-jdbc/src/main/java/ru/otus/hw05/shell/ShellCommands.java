package ru.otus.hw05.shell;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw05.service.LibraryService;

@ShellComponent
public class ShellCommands implements PromptProvider {

    private final LibraryService libraryService;

    public ShellCommands(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @ShellMethod(value = "Create Book", key = ("c"))
    public String create(@ShellOption String tittle,
                         @ShellOption String firstName,
                         @ShellOption String lastName,
                         @ShellOption String genre) {
        return libraryService.createBook(tittle, firstName, lastName, genre);
    }

    @ShellMethod(value = "Read Book", key = ("r"))
    public String read(@ShellOption long id) {
        return libraryService.readById(id);
    }

    @ShellMethod(value = "Update Book", key = ("u"))
    public String update(@ShellOption long id,
                         @ShellOption String tittle,
                         @ShellOption String firstName,
                         @ShellOption String lastName,
                         @ShellOption String genre) {
        return libraryService.updateBook(id, tittle, firstName, lastName, genre);
    }

    @ShellMethod(value = "Delete Book", key = ("d"))
    public String delete(@ShellOption long id) {
        return libraryService.deleteById(id);
    }

    @ShellMethod(value = "Look the library", key = ("l"))
    public String lookTheLibrary() {
        return libraryService.readAllBooks();
    }

    @Override
    public AttributedString getPrompt() {
        return new AttributedString("LIBRARY:>>>");
    }
}
