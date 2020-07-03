package ru.otus.hw10.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw10.rest.dto.CommentDto;
import ru.otus.hw10.service.LibraryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CommentRestController {
    private final LibraryService service;

    public CommentRestController(LibraryService service) {
        this.service = service;
    }

    @GetMapping("api/comments")
    public List<CommentDto> getAllComments() {
        return service.readAllComments().stream().map(CommentDto::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("api/comments/book")
    public List<CommentDto> getBookComments(@RequestParam long id) {
        return service.getBookComments(id).stream().map(CommentDto::toDto)
                .collect(Collectors.toList());
    }
}
