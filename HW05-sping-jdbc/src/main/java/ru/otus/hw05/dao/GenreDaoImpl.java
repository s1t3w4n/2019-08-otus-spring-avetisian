package ru.otus.hw05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.hw05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

@Repository
@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
public class GenreDaoImpl implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;

    public GenreDaoImpl(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long insert(Genre genre) {
        final MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("genre", genre);
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update("insert into genre ('genre')", params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Genre getById(long id) {
        final MapSqlParameterSource params = new MapSqlParameterSource("genre_id", id);
        return jdbc.queryForObject("select * from genre where genre_id = :genre_id", params, new GenreMapper());
    }

    @Override
    public Genre getByGenre(String genre) {
        final MapSqlParameterSource params = new MapSqlParameterSource("genre", genre);
        return jdbc.queryForObject("select * from genre where genre = :genre", params, new GenreMapper());
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Genre(
                    resultSet.getLong("genre_id"),
                    resultSet.getString("genre"));
        }
    }
}
