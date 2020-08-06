package ru.otus.hw11.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.servlet.view.RedirectView;
//import ru.otus.hw11.exceptions.NotFoundException;
//import ru.otus.hw11.models.Book;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.hw11.repositories.CommentRepository;
//import ru.otus.hw11.service.LibraryService;

@Controller
public class CommentController {

    private final CommentRepository repository;

    public CommentController(CommentRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/comments")
    public String commentsListPage() {
        return "commentsList";
    }

    @GetMapping("/book/{id}/comments/add")
    public String commentAddPage(@PathVariable String id) {
        return "addComment";
    }
//
//    @PostMapping("/comments/add")
//    public RedirectView leaveComment(
//            @RequestParam("id") long bookId,
//            @RequestParam("text") String text,
//            RedirectAttributes attributes) {
//        final Book book = service.readById(bookId).orElseThrow(NotFoundException::new);
//        service.leaveCommentToBook(book, text);
//        attributes.addAttribute("id", bookId);
//        return new RedirectView("/read");
//    }
}
