package ru.otus.hw05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw05.domain.Author;
import ru.otus.hw05.domain.Book;
import ru.otus.hw05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbc;
    private static final String BASE_QUERY;

    static {
        BASE_QUERY = "select books.book_id,authors.author_id, authors.first_name, authors.last_name,genre.genre_id, genre.genre,books.title from books inner join genre on books.genre_id = genre.genre_id inner join authors on books.author_id = authors.author_id ";
    }

    public BookDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long insert(Book book) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author_id", book.getAuthor().getAuthorId());
        params.addValue("genre_id", book.getGenre().getGenreId());
        params.addValue("title", book.getTitle());
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into books (author_id, genre_id, title) values (:author_id,:genre_id,:title)",
                params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Book getById(long id) {
        final MapSqlParameterSource params = new MapSqlParameterSource("book_id", id);
        return jdbc.queryForObject(BASE_QUERY +
                "where book_id = :id;", params, new BookMapper());
    }

    @Override
    public void update(Book book) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("book_id", book.getBookId());
        params.addValue("author_id", book.getAuthor().getAuthorId());
        params.addValue("genre_id", book.getGenre().getGenreId());
        params.addValue("title", book.getTitle());
        jdbc.update(
                "update books set auhtor_id = :auhtor_id, genre_id = :genre_id, title = :title where book_id = :book_id",
                params);
    }

    @Override
    public void deleteById(long id) {
        final MapSqlParameterSource params = new MapSqlParameterSource("book_id", id);
        jdbc.update("delete from books where id = :book_id", params);
    }

    public List<Book> getAll() {
        return jdbc.query(BASE_QUERY + ";", new BookMapper());
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(resultSet.getLong("book_id"),
                    resultSet.getString("title"),
                    new Author(resultSet.getLong("author_id"),
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name")),
                    new Genre(resultSet.getLong("genre_id"),
                            resultSet.getString("genre")));
        }
    }
}
