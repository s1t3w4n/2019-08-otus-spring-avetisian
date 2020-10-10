//package ru.otus.hw13.repositories;
//
//import lombok.val;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import ru.otus.hw13.models.Author;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DisplayName("Repository based on Spring Data for working with Authors: ")
//@DataJpaTest
//class AuthorRepositoryTest {
//
//    private static final long DEFAULT_AUTHOR_ID = 1L;
//    private static final String DEFAULT_AUTHOR_FIRST_NAME = "Alexander";
//    private static final String DEFAULT_AUTHOR_LAST_NAME = "Pushkin";
//
//    @Autowired
//    private AuthorRepository authorRepository;
//
//    @DisplayName("Should return Author with current full name")
//    @Test
//    void shouldReturnAuthorByFullName() {
//        val optionalAuthor = authorRepository.getByFirstNameAndLastName(DEFAULT_AUTHOR_FIRST_NAME, DEFAULT_AUTHOR_LAST_NAME);
//        val expectedAuthor = new Author(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_FIRST_NAME, DEFAULT_AUTHOR_LAST_NAME);
//        Assertions.assertThat(optionalAuthor).isPresent().get()
//                .isEqualToComparingFieldByField(expectedAuthor);
//    }
//
//
//    @DisplayName("Should Not return Author with current full name")
//    @Test
//    void shouldNotReturnAuthorByFullName() {
//        val optionalAuthor = authorRepository.getByFirstNameAndLastName("asdasd", "zxczxcxzc");
//        assertThat(optionalAuthor.isEmpty());
//    }
//}