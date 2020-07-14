package ru.otus.hw11.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import ru.otus.hw11.exceptions.NotFoundException;
import ru.otus.hw11.models.Book;
//import ru.otus.hw11.service.LibraryService;

@Controller
public class BookController {

//    private final LibraryService service;

//    public BookController(LibraryService service) {
//        this.service = service;
//    }

    @GetMapping("/")
    public String listPage() {
        return "list";
    }

//    @GetMapping("/update")
//    public String editPage(@RequestParam("id") long id, Model model) {
//        final Book book = service.readById(id).orElseThrow(NotFoundException::new);
//        model.addAttribute("book", book);
//        return "update";
//    }
//
//    @PostMapping("/update")
//    public String updateBook(
//            @RequestParam("id") long id,
//            @RequestParam("title") String title,
//            @RequestParam("firstName") String firstName,
//            @RequestParam("lastName") String lastName,
//            @RequestParam("genre") String genre,
//            Model model
//    ) {
//        final Book saved = service.updateBook(id, title, firstName, lastName, genre);
//        model.addAttribute(saved);
//        return "update";
//    }
//
//    @GetMapping("/create")
//    public String createPage() {
//        return "create";
//    }
//
//    @GetMapping("/read")
//    public String readPage() {
//        return "read";
//    }

}
