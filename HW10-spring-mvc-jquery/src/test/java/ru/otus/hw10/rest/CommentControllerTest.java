package ru.otus.hw10.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw10.models.Author;
import ru.otus.hw10.models.Book;
import ru.otus.hw10.models.Genre;
import ru.otus.hw10.page.CommentController;
import ru.otus.hw10.service.LibraryService;

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

    public static final Author AUTHOR = new Author(1, "Alexander", "Pushkin");
    public static final Genre GENRE = new Genre(1, "novel");
    public static final Book BOOK = new Book(1, "Captain`s daughter", AUTHOR, GENRE);
    public static final String NEW_COMMENT = "New Comment";
    public static final String THERE_IS_NO_BOOK_WITH_SUCH_ID = "There is no book with such id";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService service;

    @DisplayName("Should load page with all comments")
    @Test
    void shouldLoadPageWithAllComments() throws Exception {
        this.mvc.perform(get("/comments"))
                .andExpect(status().isOk());
    }

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

    @DisplayName("Should sent post method request to add comment it a book")
    @Test
    void leaveComment() throws Exception {
        given(service.readById(BOOK.getId()))
                .willReturn(Optional.of(BOOK));
        this.mvc.perform(post("/comments/add")
                .param("id", Long.toString(BOOK.getId()))
                .param("text", NEW_COMMENT))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void adviceTesting() throws Exception {
        given(service.readById(BOOK.getId()))
                .willReturn(Optional.empty());
        this.mvc.perform(get("/comments/add").param("id", Long.toString(BOOK.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(THERE_IS_NO_BOOK_WITH_SUCH_ID)));
    }
}