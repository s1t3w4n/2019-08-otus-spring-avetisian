package ru.otus.hw09.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw09.exceptions.NotFoundException;
import ru.otus.hw09.models.Book;
import ru.otus.hw09.models.Comment;
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
        final List<Book> books = service.readAllBooks();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/update")
    public String editPage(@RequestParam("id") long id, Model model) {
        final Book book = service.readById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "update";
    }

    @PostMapping("/update")
    public String updateBook(
            @RequestParam("id") long id,
            @RequestParam("title") String title,
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("genre") String genre,
            Model model
    ) {
        final Book saved = service.updateBook(id, title, firstName, lastName, genre);
        model.addAttribute(saved);
        return "update";
    }

    @GetMapping("/create")
    public String createPage() {
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
        return new RedirectView("read");
    }

    @GetMapping("/read")
    public String readPage() {
        return "read";
    }

    @GetMapping(value = "/read", params = {"id"})
    public String readPage(long id, Model model) {
        final Book read = service.readById(id).orElseThrow(NotFoundException::new);
        final List<Comment> comments = service.getBookComments(id);
        model.addAttribute("comments", comments);
        model.addAttribute(read);
        return "read";
    }

    @GetMapping("/delete")
    public ModelAndView deletePage(@RequestParam("id") long id, ModelMap model) {
        service.deleteById(id);
        final List<Book> books = service.readAllBooks();
        model.addAttribute("books", books);
        return new ModelAndView("list", model);
    }
}
