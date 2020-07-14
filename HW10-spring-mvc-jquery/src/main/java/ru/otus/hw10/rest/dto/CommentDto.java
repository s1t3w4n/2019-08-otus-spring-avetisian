package ru.otus.hw10.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.hw10.models.Comment;

@Data
@AllArgsConstructor
public class CommentDto {
    private long id;
    private String text;
    private String title;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getText(), comment.getBook().getTitle());
    }
}
