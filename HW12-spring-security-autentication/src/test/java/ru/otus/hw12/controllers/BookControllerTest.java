package ru.otus.hw12.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import ru.otus.hw12.models.Author;
import ru.otus.hw12.models.Book;
import ru.otus.hw12.models.Comment;
import ru.otus.hw12.models.Genre;
import ru.otus.hw12.service.LibraryService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller based on classic Spring mvc for working wth Books: ")
@WebMvcTest(BookController.class)
class BookControllerTest {

    private static final Author AUTHOR = new Author(1, "Alexander", "Pushkin");
    private static final Genre GENRE = new Genre(1, "novel");
    private static final Book BOOK = new Book(1, "Captain`s daughter", AUTHOR, GENRE);
    private static final String ANY = "any";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService service;

    @DisplayName("Should render main page with list of books in the Library")
    @Test
    void shouldRenderMainPageWithBooks() throws Exception {
        given(service.readAllBooks())
                .willReturn(List.of(BOOK));
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(BOOK.getTitle())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getLastName())))
                .andExpect(content().string(containsString(BOOK.getGenre().getGenre())));
    }

    @WithMockUser(username = ANY)
    @DisplayName("Should render update page with current book data by get method")
    @Test
    void shouldRenderUpdatePageWithBookData() throws Exception {
        given(service.readById(BOOK.getId()))
                .willReturn(Optional.of(BOOK));
        this.mvc.perform(get("/update").param("id", Long.toString(BOOK.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(BOOK.getTitle())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getLastName())))
                .andExpect(content().string(containsString(BOOK.getGenre().getGenre())));
    }

    @DisplayName("Should update book by post method")
    @Test
    void shouldUpdateBook() throws Exception {
        given(service.updateBook(BOOK.getId(),
                BOOK.getTitle(),
                AUTHOR.getFirstName(),
                AUTHOR.getLastName(),
                GENRE.getGenre()))
                .willReturn(BOOK);

        final LinkedMultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.put("id", Collections.singletonList(Long.toString(BOOK.getId())));
        valueMap.put("title", Collections.singletonList(BOOK.getTitle()));
        valueMap.put("firstName", Collections.singletonList(BOOK.getAuthor().getFirstName()));
        valueMap.put("lastName", Collections.singletonList(BOOK.getAuthor().getLastName()));
        valueMap.put("genre", Collections.singletonList(BOOK.getGenre().getGenre()));

        this.mvc.perform(post("/update")
                .params(valueMap))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(username = ANY)
    @DisplayName("Should render create page by get method")
    @Test
    void shouldRenderCreatePage() throws Exception {
        this.mvc.perform(get("/create"))
                .andExpect(status().isOk());
    }

    @DisplayName("Should create book by post method")
    @Test
    void shouldCreateBook() throws Exception {
        given(service.createBook(BOOK.getTitle(),
                AUTHOR.getFirstName(),
                AUTHOR.getLastName(),
                GENRE.getGenre()))
                .willReturn(BOOK);

        final LinkedMultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.put("title", Collections.singletonList(BOOK.getTitle()));
        valueMap.put("firstName", Collections.singletonList(BOOK.getAuthor().getFirstName()));
        valueMap.put("lastName", Collections.singletonList(BOOK.getAuthor().getLastName()));
        valueMap.put("genre", Collections.singletonList(BOOK.getGenre().getGenre()));

        this.mvc.perform(post("/create")
                .params(valueMap))
                .andExpect(status().is3xxRedirection());
    }

    @WithMockUser(username = ANY)
    @DisplayName("Should render empty read page by get method with books ids")
    @Test
    void shouldRenderReadPage() throws Exception {
        given(service.getAllBooksIDs())
                .willReturn(List.of(1L, 2L, 3L));
        this.mvc.perform(get("/read"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<option value=\"1\">Book with id: 1</option>")))
                .andExpect(content().string(containsString("<option value=\"2\">Book with id: 2</option>")))
                .andExpect(content().string(containsString("<option value=\"3\">Book with id: 3</option>")));
    }

    @WithMockUser(username = ANY)
    @DisplayName("Should render read page by get method by current book id")
    @Test
    void shouldRenderReadPageByBookId() throws Exception {
        given(service.readById(BOOK.getId()))
                .willReturn(Optional.of(BOOK));
        given(service.getAllBooksIDs())
                .willReturn(List.of(1L, 2L, 3L));
        given(service.getBookComments(BOOK.getId()))
                .willReturn(List.of(
                        new Comment(1, "Nice", BOOK),
                        new Comment(2, "Bad", BOOK),
                        new Comment(3, "Good", BOOK)));

        this.mvc.perform(get("/read").param("id", Long.toString(BOOK.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book with id: 1</option>")))
                .andExpect(content().string(containsString("<option value=\"2\">Book with id: 2</option>")))
                .andExpect(content().string(containsString("<option value=\"3\">Book with id: 3</option>")))
                .andExpect(content().string(containsString(Long.toString(BOOK.getId()))))
                .andExpect(content().string(containsString(BOOK.getTitle())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(BOOK.getAuthor().getLastName())))
                .andExpect(content().string(containsString(BOOK.getGenre().getGenre())))
                .andExpect(content().string(containsString("Nice")))
                .andExpect(content().string(containsString("Bad")))
                .andExpect(content().string(containsString("Good")));
    }

    @DisplayName("Should correctly send delete post method by book id")
    @Test
    void shouldSendDeletePostMethod() throws Exception {
        this.mvc.perform(post("/delete").param("id", String.valueOf(BOOK.getId())))
                .andExpect(status().is3xxRedirection());
    }
}