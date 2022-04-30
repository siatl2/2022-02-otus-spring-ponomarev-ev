package ru.otus.homework06.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.homework06.repository.BookRepository;
import ru.otus.homework06.model.Book;
import ru.otus.homework06.exception.LibraryException;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class BookRepositoryJpa implements BookRepository {
    private final EntityManager em;

    @Autowired
    public BookRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public Book save(Book book) {
        if (book.getId() == 0){
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery("select e from Book e join fetch e.author join fetch e.genre", Book.class);
        return query.getResultList();
    }

    @Override
    public Optional<Book> findById(long id) {
        Query query = em.createQuery("select e" +
                                        " from Book e" +
                                        " join fetch e.author" +
                                        " join fetch e.genre" +
                                        " where e.id=:id");
        query.setParameter("id", id);
        return Optional.ofNullable((Book)query.getSingleResult());
    }

    @Override
    public boolean existById(long id) {
        Query query = em.createQuery("select count(e.id) from Book e where e.id=:id");
        query.setParameter("id", id);
        Long result = (Long)query.getSingleResult();
        return result > 0;
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book e where e.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
