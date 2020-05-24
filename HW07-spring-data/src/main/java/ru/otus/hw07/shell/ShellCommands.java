package ru.otus.hw07.shell;

import org.jline.utils.AttributedString;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.hw07.services.LibraryService;

import java.util.Scanner;

@ShellComponent
public class ShellCommands implements PromptProvider {
    private final LibraryService libraryService;

    public ShellCommands(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @ShellMethod(value = "Leave an opinion", key = ("o"))
    public String leaveOpinion(@ShellOption long bookId) {
        String book = libraryService.readById(bookId);
        if (!book.equals(libraryService.getNoSuchIdMessage())) {
            System.out.println("Print your comment to a book:");
            System.out.println(book);
            Scanner scanner = new Scanner(System.in);
            return libraryService.leaveCommentToBook(bookId, scanner.nextLine());
        } else {
            return book;
        }
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
