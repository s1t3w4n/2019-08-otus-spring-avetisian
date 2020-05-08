package ru.otus.hw05.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw05.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Repository
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public class AuthorDaoImpl implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;

    public AuthorDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long insert(Author author) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("first_name", author.getFirstName());
        params.addValue("last_name", author.getLastName());
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into authors (first_name, last_name) values (:first_name, :last_name);",
                params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Author getById(long id) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author_id", id);
        try {
            return jdbc.queryForObject("select * from authors where author_id = :author_id;",
                    params, new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Author getByFullName(String firstName, String lastName) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("first_name", firstName);
        params.addValue("last_name", lastName);
        try {
            return jdbc.queryForObject("select * from authors where first_name = :first_name and last_name = :last_name;",
                    params, new AuthorMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Author(
                    resultSet.getLong("author_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
            );
        }
    }
}
