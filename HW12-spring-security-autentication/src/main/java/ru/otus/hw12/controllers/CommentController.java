package ru.otus.hw12.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.hw12.exceptions.NotFoundException;
import ru.otus.hw12.models.Book;
import ru.otus.hw12.models.Comment;
import ru.otus.hw12.service.LibraryService;

import java.util.List;

@Controller
public class CommentController {
    private final LibraryService service;

    public CommentController(LibraryService service) {
        this.service = service;
    }

    @GetMapping("/comments")
    public String commentsListPage(Model model) {
        final List<Comment> comments = service.readAllComments();
        model.addAttribute("comments", comments);
        return "commentsList";
    }

    @GetMapping("/comments/add")
    public String commentAddPage(@RequestParam("id") long bookId, Model model) {
        final Book book = service.readById(bookId).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "addComment";
    }

    @PostMapping("/comments/add")
    public ModelAndView leaveComment(
            @RequestParam("id") long bookId,
            @RequestParam("text") String text,
            ModelMap model) {
        final Book book = service.readById(bookId).orElseThrow(NotFoundException::new);
        service.leaveCommentToBook(book, text);
        final List<Comment> comments = service.getBookComments(bookId);
        model.addAttribute("book", book);
        model.addAttribute("comments", comments);
        return new ModelAndView("read", model);
    }
}
