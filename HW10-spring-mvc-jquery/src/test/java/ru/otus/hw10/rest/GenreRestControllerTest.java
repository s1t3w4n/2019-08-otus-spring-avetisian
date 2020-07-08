package ru.otus.hw10.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.hw10.models.Genre;
import ru.otus.hw10.service.LibraryService;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Controller based on rest Spring mvc for working with Genre: ")
@WebMvcTest(GenreRestController.class)
class GenreRestControllerTest {

    private static final Genre GENRE = new Genre(1, "novel");

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService service;

    @DisplayName("Should return all genre")
    @Test
    void shouldReturnAllGenre() throws Exception {
        given(service.getAllGenre())
                .willReturn(List.of(GENRE.getGenre()));
        this.mvc.perform(get("/api/genre"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[\"" + GENRE.getGenre() + "\"]")));
    }
}