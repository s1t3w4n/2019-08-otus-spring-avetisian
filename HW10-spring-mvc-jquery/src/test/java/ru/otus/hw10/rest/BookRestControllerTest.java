package ru.otus.hw10.rest;

import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw10.models.Author;
import ru.otus.hw10.models.Book;
import ru.otus.hw10.models.Genre;
import ru.otus.hw10.service.LibraryService;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller based on rest Spring mvc for working with Books: ")
@WebMvcTest(BookRestController.class)
class BookRestControllerTest {

    private static final Author AUTHOR = new Author(1, "Alexander", "Pushkin");
    private static final Genre GENRE = new Genre(1, "novel");
    private static final Book BOOK = new Book(1, "Captain`s daughter", AUTHOR, GENRE);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService service;

    @DisplayName("Should return all books from service")
    @Test
    void shouldReturnAllBooks() throws Exception {
        given(service.readAllBooks())
                .willReturn(List.of(BOOK));
        this.mvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(BOOK.getTitle())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getLastName())))
                .andExpect(content().string(containsString(BOOK.getGenre().getGenre())));
    }

    @DisplayName("Should return all books IDs")
    @Test
    void shouldReturnAllIDs() throws Exception {
        given(service.getAllBooksIDs())
                .willReturn(List.of(1L, 2L, 3L));
        this.mvc.perform(get("/api/books/identifiers"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[1,2,3]")));
    }

    @DisplayName("Should return book by id")
    @Test
    void shouldReturnBookByID() throws Exception {
        given(service.readById(BOOK.getId()))
                .willReturn(Optional.of(BOOK));
        final Gson gson = new Gson();
        this.mvc.perform(get("/api/books/" + BOOK.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json(gson.toJson(BOOK)));
    }

    @DisplayName("Should correctly send delete post method by book id")
    @Test
    void shouldSendDeletePostMethod() throws Exception {
        this.mvc.perform(post("/api/books/delete/" + BOOK.getId()))
                .andExpect(status().isOk());
    }

}