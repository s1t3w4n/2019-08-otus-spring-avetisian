package ru.otus.hw12.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw12.exceptions.NotFoundException;
import ru.otus.hw12.models.Book;
import ru.otus.hw12.models.Comment;
import ru.otus.hw12.service.LibraryService;

import java.util.List;

@Controller
public class BookController {

    private final LibraryService service;

    public BookController(LibraryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        final List<Book> books = service.readAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/update")
    public String editPage(@RequestParam("id") long id, Model model) {
        final Book book = service.readById(id).orElseThrow(NotFoundException::new);
        final var firstNames = service.getAllFirstNames();
        final var lastNames = service.getAllLastNames();
        final var genre = service.getAllGenre();
        model.addAttribute("book", book);
        model.addAttribute("firstNames", firstNames);
        model.addAttribute("lastNames", lastNames);
        model.addAttribute("genre", genre);
        return "update";
    }

    @PostMapping("/update")
    public RedirectView updateBook(
            @RequestParam("id") long id,
            @RequestParam("title") String title,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("genre") String genre,
            RedirectAttributes attributes
    ) {
        final Book saved = service.updateBook(id, title, firstName, lastName, genre);
        attributes.addAttribute("id", saved.getId());
        return new RedirectView("/read");
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        final var firstNames = service.getAllFirstNames();
        final var lastNames = service.getAllLastNames();
        final var genre = service.getAllGenre();
        model.addAttribute("firstNames", firstNames);
        model.addAttribute("lastNames", lastNames);
        model.addAttribute("genre", genre);
        return "create";
    }

    @PostMapping("/create")
    public RedirectView createBook(
            @RequestParam("title") String title,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("genre") String genre,
            RedirectAttributes attributes) {
        final Book book = service.createBook(title, firstName, lastName, genre);
        attributes.addAttribute("id", book.getId());
        return new RedirectView("/read");
    }

    @GetMapping("/read")
    public String readPage(Model model) {
        final List<Long> allBooksIDs = service.getAllBooksIDs();
        model.addAttribute("identifiers", allBooksIDs);
        return "read";
    }

    @GetMapping(value = "/read", params = {"id"})
    public String readPage(long id, Model model) {
        final Book read = service.readById(id).orElseThrow(NotFoundException::new);
        final List<Comment> comments = service.getBookComments(id);
        final List<Long> allBooksIDs = service.getAllBooksIDs();
        model.addAttribute("identifiers", allBooksIDs);
        model.addAttribute("comments", comments);
        model.addAttribute(read);
        return "read";
    }

    @PostMapping("/delete")
    public RedirectView deleteBook(@RequestParam("id") long id) {
        service.deleteById(id);
        return new RedirectView("/");
    }
}
