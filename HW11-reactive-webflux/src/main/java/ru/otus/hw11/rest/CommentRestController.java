package ru.otus.hw11.rest;

import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.otus.hw11.models.Book;
import ru.otus.hw11.models.Comment;
import ru.otus.hw11.models.dto.CommentDto;
import ru.otus.hw11.repositories.BookRepository;
import ru.otus.hw11.repositories.CommentRepository;

@RestController
@AllArgsConstructor
public class CommentRestController {
    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;

    @PostMapping("/api/comments")
    public Mono<String> saveComment(CommentDto dto) {
        return bookRepository.findById(dto.getBookId())
                .flatMap(book -> commentRepository.save(new Comment(ObjectId.get().toString(), dto.getText(), book)))
                .map(Comment::getBook)
                .map(Book::getId);
    }
}