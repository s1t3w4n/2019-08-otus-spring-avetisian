package ru.otus.hw05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw05.domain.Author;
import ru.otus.hw05.domain.Book;
import ru.otus.hw05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;

    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    /*@Override
    public boolean insert(Book book) {
        final Map<String, Object> params = new HashMap<>();
        params.put("book_id", book.getBookId(),
                "")
    }*/
    @SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
    @Override
    public Book getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbc.queryForObject("select books.book_id," +
                "authors.author_id, authors.first_name, authors.last_name," +
                "genre.genre_id, genre.genre," +
                "books.title from books " +
                "inner join genre on books.genre_id = genre.genre_id " +
                "inner join authors on books.author_id = authors.author_id " +
                "where book_id = :id;", params, new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long bookId = resultSet.getLong("book_id");
            String title = resultSet.getString("title");
            Author author = new Author(
                    resultSet.getLong("author_id"),
                    resultSet.getString("last_name"),
                    resultSet.getString("first_name")
            );
            Genre genre = new Genre(
                    resultSet.getLong("genre_id"),
                    resultSet.getString("genre"));
            return new Book(bookId, title, author, genre);
        }
    }
}
