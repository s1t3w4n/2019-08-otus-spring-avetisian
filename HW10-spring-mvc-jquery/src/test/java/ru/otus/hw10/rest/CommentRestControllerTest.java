package ru.otus.hw10.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw10.models.Author;
import ru.otus.hw10.models.Book;
import ru.otus.hw10.models.Comment;
import ru.otus.hw10.models.Genre;
import ru.otus.hw10.service.LibraryService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller based on rest Spring mvc for working with Comments: ")
@WebMvcTest(CommentRestController.class)
class CommentRestControllerTest {

    private static final Author AUTHOR = new Author(1, "Alexander", "Pushkin");
    private static final Genre GENRE = new Genre(1, "novel");
    private static final Book BOOK = new Book(1, "Captain`s daughter", AUTHOR, GENRE);
    private static final String NICE = "Nice";
    private static final String BAD = "Bad";
    private static final String GOOD = "Good";
    private static final Comment NICE_COMMENT = new Comment(1, NICE, BOOK);
    private static final Comment BAD_COMMENT = new Comment(2, BAD, BOOK);
    private static final Comment GOOD_COMMENT = new Comment(3, GOOD, BOOK);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService service;

    @DisplayName("Should return all the comments from service")
    @Test
    void shouldReturnAllTheComments() throws Exception {
        given(service.readAllComments())
                .willReturn(List.of(
                        NICE_COMMENT,
                        BAD_COMMENT,
                        GOOD_COMMENT));
        this.mvc.perform(get("/api/comments"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(NICE)))
                .andExpect(content().string(containsString(BAD)))
                .andExpect(content().string(containsString(GOOD)));
    }

    @DisplayName("Should return all bool's comments from service")
    @Test
    void shouldReturnAllBookComments() throws Exception {
        given(service.getBookComments(BOOK.getId()))
                .willReturn(List.of(
                        NICE_COMMENT,
                        BAD_COMMENT,
                        GOOD_COMMENT));
        this.mvc.perform(get("/api/comments/book").param("id", Long.toString(BOOK.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(NICE)))
                .andExpect(content().string(containsString(BAD)))
                .andExpect(content().string(containsString(GOOD)));
    }
}