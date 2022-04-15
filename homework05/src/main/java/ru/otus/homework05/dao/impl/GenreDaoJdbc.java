package ru.otus.homework05.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework05.dao.BookDao;
import ru.otus.homework05.dao.GenreDao;
import ru.otus.homework05.domain.Genre;
import ru.otus.homework05.exception.LibraryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insertByName(String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into genre(name) values(:name)", params, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Genre> getAll() {
        return namedParameterJdbcOperations.query("select * from genre", new GenreDaoJdbc.GenreMapper());
    }

    @Override
    public Genre getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from genre where id=:id"
                ,params
                , new GenreDaoJdbc.GenreMapper()
        );
    }

    @Override
    public boolean existById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Integer returnCount = namedParameterJdbcOperations.queryForObject(
                "select count(*) from genre where id=:id"
                ,params
                , Integer.class
        );
        return (returnCount > 0);
    }

    @Override
    public void update(Genre newGenre) {
        long id = newGenre.getId();
        String name = newGenre.getName();

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update(
                "update genre " +
                        "set name=:name " +
                        "where id=:id"
                , params
        );
    }

    @Override
    public void delete(Genre genre) {
        if (existBookByGenre(genre)){
            throw new LibraryException("Cant't delete genre. Genre use in book", new RuntimeException());
        }
        Map<String, Object> params = Collections.singletonMap("id", genre.getId());
        namedParameterJdbcOperations.update(
                "delete from genre " +
                        "where id=:id"
                , params);
    }

    private boolean existBookByGenre(Genre genre){
        Map<String, Object> params = Collections.singletonMap("id", genre.getId());
        Integer returnCount = namedParameterJdbcOperations.queryForObject(
                "select count(*) from book where genre_id=:id"
                ,params
                , Integer.class
        );
        return (returnCount > 0);
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Genre(id, name);
        }
    }
}
