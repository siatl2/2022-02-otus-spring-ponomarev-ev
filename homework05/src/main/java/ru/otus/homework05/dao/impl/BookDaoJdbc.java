package ru.otus.homework05.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework05.dao.AuthorDao;
import ru.otus.homework05.dao.BookDao;
import ru.otus.homework05.dao.GenreDao;
import ru.otus.homework05.domain.Author;
import ru.otus.homework05.domain.Book;
import ru.otus.homework05.domain.Genre;
import ru.otus.homework05.exception.LibraryException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Autowired
    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDao authorDao, GenreDao genreDao) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    @Override
    public long insertByNameAuthorIdGenreId(String name, long authorId, long genreId) {
        if (!authorDao.existById(authorId)){
            throw new LibraryException("Author not exist", new RuntimeException());
        }

        if (!genreDao.existById(genreId)){
            throw new LibraryException("Genre not exist", new RuntimeException());
        }

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("author_id", authorId);
        params.addValue("genre_id", genreId);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into book(name, author_id, genre_id) values(:name, :author_id, :genre_id)", params, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query("select * from book", new BookDaoJdbc.BookMapper());
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from book where id=:id"
                ,params
                , new BookDaoJdbc.BookMapper()
        );
    }

    @Override
    public boolean existById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Integer returnCount = namedParameterJdbcOperations.queryForObject(
                "select count(*) from book where id=:id"
                ,params
                , Integer.class
        );
        return (returnCount > 0);
    }

    @Override
    public void update(Book newBook) {
        long id = newBook.getId();
        String name = newBook.getName();
        long authorId = newBook.getAuthor().getId();
        long genreId = newBook.getGenre().getId();

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("author_id", authorId);
        params.put("genre_id", genreId);

        namedParameterJdbcOperations.update(
                "update book " +
                        "set name=:name " +
                        " , author_id=:author_id " +
                        " , genre_id=:genre_id " +
                        "where id=:id"
                , params
        );
    }

    @Override
    public void delete(Book book) {
        if (!existById(book.getId())){
            throw new LibraryException("Cant't delete book. Id not exists/", new RuntimeException());
        }
        Map<String, Object> params = Collections.singletonMap("id", book.getId());
        namedParameterJdbcOperations.update(
                "delete from book " +
                        "where id=:id"
                , params);
    }

    private class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            long authorId = resultSet.getLong("author_id");
            Author author = authorDao.getById(authorId);
            long genreId = resultSet.getLong("genre_id");
            Genre genre = genreDao.getById(genreId);
            return new Book(id, name, author, genre);
        }
    }
}
