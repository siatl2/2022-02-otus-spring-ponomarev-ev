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
    public void insert(Book book) {
        long authorId = book.getAuthor().getId();
        long genreId = book.getGenre().getId();
        String name = book.getName();

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
        long id = keyHolder.getKey().longValue();
        book.setId(id);
    }

    @Override
    public List<Book> getAll() {
        String query = "select " +
                            " book.id id " +
                            ", book.name name " +
                            ", author_id " +
                            ", author.name author_name " +
                            ", genre_id " +
                            ", genre.name genre_name " +
                        " from " +
                            " book inner join author on book.author_id = author.id " +
                            " inner join genre on book.genre_id = genre.id";
        return namedParameterJdbcOperations.query(query, new BookDaoJdbc.BookMapper());
    }

    @Override
    public Book getById(long id) {
        String query = "select " +
                    " book.id id " +
                    ", book.name name " +
                    ", author_id " +
                    ", author.name author_name " +
                    ", genre_id " +
                    ", genre.name genre_name " +
                " from " +
                    " book inner join author on book.author_id = author.id " +
                    " inner join genre on book.genre_id = genre.id" +
                " where " +
                    " book.id=:id";
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                query
                ,params
                , new BookDaoJdbc.BookMapper()
        );
    }

    @Override
    public boolean existById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        Integer returnCount = namedParameterJdbcOperations.queryForObject(
                "select count(id) from book where id=:id"
                ,params
                , Integer.class
        );
        return (returnCount > 0);
    }

    @Override
    public void update(Book book) {
        long id = book.getId();
        String name = book.getName();
        long authorId = book.getAuthor().getId();
        long genreId = book.getGenre().getId();

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
    public void deleteById(long id) {
        if (!existById(id)){
            throw new LibraryException("Cant't delete non existing book.", new RuntimeException());
        }
        Map<String, Object> params = Collections.singletonMap("id", id);
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
            String authorName = resultSet.getString("author_name");
            Author author = new Author(authorId, authorName);
            long genreId = resultSet.getLong("genre_id");
            String genreName = resultSet.getString("genre_name");
            Genre genre = new Genre(genreId, genreName);
            return new Book(id, name, author, genre);
        }
    }
}
