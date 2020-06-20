package ru.otus.hw09.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw09.exceptions.NotFoundException;
import ru.otus.hw09.models.Book;
import ru.otus.hw09.service.LibraryService;

import java.util.List;

@Controller
public class BookController {

    private final LibraryService service;

    public BookController(LibraryService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Book> books = service.readAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") long id, Model model) {
        Book book = service.readById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "edit";
    }

    @PostMapping("/edit")
    public String saveBook(
            @RequestParam("id") long id,
            @RequestParam("title") String title,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("genre") String genre,
            Model model
    ) {
        Book saved = service.updateBook(id, title, firstName, lastName, genre);
        model.addAttribute(saved);
        return "edit";
    }

    @GetMapping("/create")
    public String createPage() {
        return "create";
    }

    @PostMapping("/create")
    public String createBook(
            @RequestParam("title") String title,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("genre") String genre) {
        service.createBook(title, firstName, lastName, genre);
        return "create";
    }
}
