package ru.otus.hw09.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw09.models.Author;
import ru.otus.hw09.models.Book;
import ru.otus.hw09.models.Comment;
import ru.otus.hw09.models.Genre;
import ru.otus.hw09.service.LibraryService;

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

    public static final Author author = new Author(1, "Alexander", "Pushkin");
    public static final Genre genre = new Genre(1, "novel");
    public static final Book book = new Book(1, "Captain`s daughter", author, genre);
    public static final String NEW_COMMENT = "New Comment";
    public static final String THERE_IS_NO_BOOK_WITH_SUCH_ID = "There is no book with such id";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService service;

    @DisplayName("Should load page with all comments")
    @Test
    void shouldLoadPageWithAllComments() throws Exception {
        given(service.readAllComments())
                .willReturn(List.of(
                        new Comment(1, "Nice", book),
                        new Comment(2, "Bad", book),
                        new Comment(3, "Good", book)));
        this.mvc.perform(get("/comments"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nice")))
                .andExpect(content().string(containsString("Bad")))
                .andExpect(content().string(containsString("Good")));
    }

    @DisplayName("Should render add comment page for current book")
    @Test
    void shouldRenderAddCommentPage() throws Exception {
        given(service.readById(book.getId()))
                .willReturn(Optional.of(book));
        this.mvc.perform(get("/comments/add").param("id", Long.toString(book.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Long.toString(book.getId()))))
                .andExpect(content().string(containsString(book.getTitle())))
                .andExpect(content().string(containsString(book.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(book.getAuthor().getLastName())))
                .andExpect(content().string(containsString(book.getGenre().getGenre())));
    }

    @DisplayName("Should sent post method request to add comment it a book")
    @Test
    void leaveComment() throws Exception {
        given(service.readById(book.getId()))
                .willReturn(Optional.of(book));
        given(service.getBookComments(book.getId()))
                .willReturn(List.of(
                        new Comment(1, "Nice", book),
                        new Comment(2, "Bad", book),
                        new Comment(3, "Good", book),
                        new Comment(4, NEW_COMMENT, book)
                ));
        this.mvc.perform(post("/comments/add")
                .param("id", Long.toString(book.getId()))
                .param("text", NEW_COMMENT))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(Long.toString(book.getId()))))
                .andExpect(content().string(containsString(book.getTitle())))
                .andExpect(content().string(containsString(book.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(book.getAuthor().getLastName())))
                .andExpect(content().string(containsString(book.getGenre().getGenre())))
                .andExpect(content().string(containsString("Nice")))
                .andExpect(content().string(containsString("Bad")))
                .andExpect(content().string(containsString("Good")))
                .andExpect(content().string(containsString(NEW_COMMENT)));
    }

    @Test
    void adviceTesting() throws Exception {
        given(service.readById(book.getId()))
                .willReturn(Optional.empty());
        this.mvc.perform(get("/comments/add").param("id", Long.toString(book.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(THERE_IS_NO_BOOK_WITH_SUCH_ID)));
    }
}