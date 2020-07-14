package ru.otus.hw10.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw10.models.Author;
import ru.otus.hw10.service.LibraryService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Controller based on rest Spring mvc for working with Authors: ")
@WebMvcTest(AuthorRestController.class)
class AuthorRestControllerTest {

    private static final Author AUTHOR = new Author(1, "Alexander", "Pushkin");

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService service;

    @DisplayName("Should return all author first names")
    @Test
    void shouldReturnAllAuthorFirstNames() throws Exception {
        given(service.getAllFirstNames())
                .willReturn(List.of(AUTHOR.getFirstName()));
        this.mvc.perform(get("/api/authors/first-names"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[\"" + AUTHOR.getFirstName() + "\"]")));
    }

    @DisplayName("Should return all author last names")
    @Test
    void shouldReturnAllAuthorLastNames() throws Exception {
        given(service.getAllLastNames())
                .willReturn(List.of(AUTHOR.getLastName()));
        this.mvc.perform(get("/api/authors/last-names"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[\"" + AUTHOR.getLastName() + "\"]")));
    }
}