package ru.otus.hw10.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw10.exceptions.NotFoundException;
import ru.otus.hw10.models.Book;
import ru.otus.hw10.service.LibraryService;

@Controller
public class CommentController {
    private final LibraryService service;

    public CommentController(LibraryService service) {
        this.service = service;
    }

    @GetMapping("/comments")
    public String commentsListPage() {
        return "commentsList";
    }

    @GetMapping("/comments/add")
    public String commentAddPage(@RequestParam("id") long bookId, Model model) {
        final Book book = service.readById(bookId).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "addComment";
    }

    @PostMapping("/comments/add")
    public RedirectView leaveComment(
            @RequestParam("id") long bookId,
            @RequestParam("text") String text,
            RedirectAttributes attributes) {
        final Book book = service.readById(bookId).orElseThrow(NotFoundException::new);
        service.leaveCommentToBook(book, text);
        attributes.addAttribute("id", bookId);
        return new RedirectView("/read");
    }
}
