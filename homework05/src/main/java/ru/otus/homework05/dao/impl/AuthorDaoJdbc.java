package ru.otus.homework05.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework05.dao.AuthorDao;
import ru.otus.homework05.domain.Author;
import ru.otus.homework05.exception.LibraryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    @Autowired
    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public void insert(Author author) {
        String name = author.getName();
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into author(name) values(:name)", params, keyHolder);
        long id = keyHolder.getKey().longValue();
        author.setId(id);
    }

    @Override
    public List<Author> getAll() {
        return namedParameterJdbcOperations.query("select id, name from author", new AuthorMapper());
    }

    @Override
    public Author getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select id, name from author where id=:id"
                ,params
                , new AuthorMapper()
                );
    }

    @Override
    public boolean existById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Integer returnCount = namedParameterJdbcOperations.queryForObject(
                "select count(id) from author where id=:id"
                ,params
                , Integer.class
                );
        return (returnCount > 0);
    }

    @Override
    public void update(Author author) {
        long id = author.getId();
        String name = author.getName();

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update(
                "update author " +
                        "set name=:name " +
                        "where id=:id"
                , params
                );
    }

    @Override
    public void deleteById(long id) {
        if (!existById(id)){
            throw new LibraryException("Cant't delete non existing author", new RuntimeException());
        }
        Author author = getById(id);

        if (existBookByAuthor(author)){
            throw new LibraryException("Cant't delete author. Author use in book", new RuntimeException());
        }
        Map<String, Object> params = Collections.singletonMap("id", author.getId());
        namedParameterJdbcOperations.update(
                "delete from author " +
                        "where id=:id"
                    , params);
    }

    private boolean existBookByAuthor(Author author){
        Map<String, Object> params = Collections.singletonMap("id", author.getId());
        Integer returnCount = namedParameterJdbcOperations.queryForObject(
                "select count(id) from book where author_id=:id"
                ,params
                , Integer.class
        );
        return (returnCount > 0);
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            return new Author(id, name);
        }
    }
}
