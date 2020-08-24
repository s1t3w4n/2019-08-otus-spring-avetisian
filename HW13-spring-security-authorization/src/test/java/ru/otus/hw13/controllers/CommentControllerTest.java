package ru.otus.hw13.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw13.models.Author;
import ru.otus.hw13.models.Book;
import ru.otus.hw13.models.Comment;
import ru.otus.hw13.models.Genre;
import ru.otus.hw13.service.LibraryService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller based on classic Spring mvc for working with Comments: ")
@WebMvcTest(CommentController.class)
class CommentControllerTest {

    private static final Author AUTHOR = new Author(1, "Alexander", "Pushkin");
    private static final Genre GENRE = new Genre(1, "novel");
    private static final Book BOOK = new Book(1, "Captain`s daughter", AUTHOR, GENRE);
    private static final String NEW_COMMENT = "New Comment";
    private static final String THERE_IS_NO_BOOK_WITH_SUCH_ID = "There is no book with such id";
    private static final String ANY = "any";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService service;

    @WithMockUser(username = ANY)
    @DisplayName("Should load page with all comments")
    @Test
    void shouldLoadPageWithAllComments() throws Exception {
        given(service.readAllComments())
                .willReturn(List.of(
                        new Comment(1, "Nice", BOOK),
                        new Comment(2, "Bad", BOOK),
                        new Comment(3, "Good", BOOK)));
        this.mvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nice")))
                .andExpect(content().string(containsString("Bad")))
                .andExpect(content().string(containsString("Good")));
    }

    @WithMockUser(username = ANY)
    @DisplayName("Should render add comment page for current book")
    @Test
    void shouldRenderAddCommentPage() throws Exception {
        given(service.readById(BOOK.getId()))
                .willReturn(Optional.of(BOOK));
        this.mvc.perform(get("/comments/add").param("id", Long.toString(BOOK.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Long.toString(BOOK.getId()))))
                .andExpect(content().string(containsString(BOOK.getTitle())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getLastName())))
                .andExpect(content().string(containsString(BOOK.getGenre().getGenre())));
    }

    @WithMockUser(username = ANY)
    @DisplayName("Should sent post method request to add comment it a book")
    @Test
    void leaveComment() throws Exception {
        given(service.readById(BOOK.getId()))
                .willReturn(Optional.of(BOOK));
        given(service.getBookComments(BOOK.getId()))
                .willReturn(List.of(
                        new Comment(1, "Nice", BOOK),
                        new Comment(2, "Bad", BOOK),
                        new Comment(3, "Good", BOOK),
                        new Comment(4, NEW_COMMENT, BOOK)
                ));
        this.mvc.perform(post("/comments/add")
                .param("id", Long.toString(BOOK.getId()))
                .param("text", NEW_COMMENT))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Long.toString(BOOK.getId()))))
                .andExpect(content().string(containsString(BOOK.getTitle())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getLastName())))
                .andExpect(content().string(containsString(BOOK.getGenre().getGenre())))
                .andExpect(content().string(containsString("Nice")))
                .andExpect(content().string(containsString("Bad")))
                .andExpect(content().string(containsString("Good")))
                .andExpect(content().string(containsString(NEW_COMMENT)));
    }

    @WithMockUser(username = ANY)
    @Test
    void adviceTesting() throws Exception {
        given(service.readById(BOOK.getId()))
                .willReturn(Optional.empty());
        this.mvc.perform(get("/comments/add").param("id", Long.toString(BOOK.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(THERE_IS_NO_BOOK_WITH_SUCH_ID)));
    }
}