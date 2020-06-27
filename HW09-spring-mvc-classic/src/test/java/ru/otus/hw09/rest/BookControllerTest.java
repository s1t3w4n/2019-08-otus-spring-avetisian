package ru.otus.hw09.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import ru.otus.hw09.models.Author;
import ru.otus.hw09.models.Book;
import ru.otus.hw09.models.Comment;
import ru.otus.hw09.models.Genre;
import ru.otus.hw09.service.LibraryService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("Controller based on classic Spring mvc for working wth Books: ")
@WebMvcTest(BookController.class)
class BookControllerTest {

    public static final Author author = new Author(1, "Alexander", "Pushkin");
    public static final Genre genre = new Genre(1, "novel");
    public static final Book book = new Book(1, "Captain`s daughter", author, genre);

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService service;

    @DisplayName("Should render main page with list of books in the Library")
    @Test
    void shouldRenderMainPageWithBooks() throws Exception {
        given(service.readAllBooks())
                .willReturn(List.of(book));
        this.mvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(book.getTitle())))
                .andExpect(content().string(containsString(book.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(book.getAuthor().getLastName())))
                .andExpect(content().string(containsString(book.getGenre().getGenre())));
    }

    @DisplayName("Should render update page with current book data by get method")
    @Test
    void shouldRenderUpdatePageWithBookData() throws Exception {
        given(service.readById(book.getId()))
                .willReturn(Optional.of(book));
        this.mvc.perform(get("/update").param("id", Long.toString(book.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(book.getTitle())))
                .andExpect(content().string(containsString(book.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(book.getAuthor().getLastName())))
                .andExpect(content().string(containsString(book.getGenre().getGenre())));
    }

    @DisplayName("Should update book by post method")
    @Test
    void shouldUpdateBook() throws Exception {
        given(service.updateBook(book.getId(),
                book.getTitle(),
                author.getFirstName(),
                author.getLastName(),
                genre.getGenre()))
                .willReturn(book);

        final LinkedMultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.put("id", Collections.singletonList(Long.toString(book.getId())));
        valueMap.put("title", Collections.singletonList(book.getTitle()));
        valueMap.put("firstName", Collections.singletonList(book.getAuthor().getFirstName()));
        valueMap.put("lastName", Collections.singletonList(book.getAuthor().getLastName()));
        valueMap.put("genre", Collections.singletonList(book.getGenre().getGenre()));

        this.mvc.perform(post("/update")
                .params(valueMap))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(book.getTitle())))
                .andExpect(content().string(containsString(book.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(book.getAuthor().getLastName())))
                .andExpect(content().string(containsString(book.getGenre().getGenre())));
    }

    @DisplayName("Should render create page by get method")
    @Test
    void shouldRenderCreatePage() throws Exception {
        this.mvc.perform(get("/create"))
                .andExpect(status().isOk());
    }

    @DisplayName("Should create book by post method")
    @Test
    void shouldCreateBook() throws Exception {
        given(service.createBook(book.getTitle(),
                author.getFirstName(),
                author.getLastName(),
                genre.getGenre()))
                .willReturn(book);

        final LinkedMultiValueMap<String, String> valueMap = new LinkedMultiValueMap<>();
        valueMap.put("title", Collections.singletonList(book.getTitle()));
        valueMap.put("firstName", Collections.singletonList(book.getAuthor().getFirstName()));
        valueMap.put("lastName", Collections.singletonList(book.getAuthor().getLastName()));
        valueMap.put("genre", Collections.singletonList(book.getGenre().getGenre()));

        this.mvc.perform(post("/create")
                .params(valueMap))
                .andExpect(status().is3xxRedirection());
    }

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

    @DisplayName("Should render read page by get method by current book id")
    @Test
    void shouldRenderReadPageByBookId() throws Exception {
        given(service.readById(book.getId()))
                .willReturn(Optional.of(book));
        given(service.getAllBooksIDs())
                .willReturn(List.of(1L, 2L, 3L));
        given(service.getBookComments(book.getId()))
                .willReturn(List.of(
                        new Comment(1, "Nice", book),
                        new Comment(2, "Bad", book),
                        new Comment(3, "Good", book)));

        this.mvc.perform(get("/read").param("id", Long.toString(book.getId())))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Book with id: 1</option>")))
                .andExpect(content().string(containsString("<option value=\"2\">Book with id: 2</option>")))
                .andExpect(content().string(containsString("<option value=\"3\">Book with id: 3</option>")))
                .andExpect(content().string(containsString(Long.toString(book.getId()))))
                .andExpect(content().string(containsString(book.getTitle())))
                .andExpect(content().string(containsString(book.getAuthor().getFirstName())))
                .andExpect(content().string(containsString(book.getAuthor().getLastName())))
                .andExpect(content().string(containsString(book.getGenre().getGenre())))
                .andExpect(content().string(containsString("Nice")))
                .andExpect(content().string(containsString("Bad")))
                .andExpect(content().string(containsString("Good")));
    }

    @DisplayName("Should correctly send delete post method by book id")
    @Test
    void shouldSendDeletePostMethod() throws Exception {
        this.mvc.perform(post("/delete").param("id", String.valueOf(book.getId())))
                .andExpect(status().is3xxRedirection());
    }
}