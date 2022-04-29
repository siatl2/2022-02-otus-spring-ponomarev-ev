package ru.otus.homework06.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.homework06.repository.AuthorRepository;
import ru.otus.homework06.model.Author;
import ru.otus.homework06.exception.LibraryException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class AuthorRepositoryJpa implements AuthorRepository {
    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public AuthorRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public Author save(Author author) {
        if (author.getId() == 0){
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery("select e from Author e", Author.class);
        return query.getResultList();
    }

    @Override
    public Optional<Author> findById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public boolean existById(long id) {
        Query query = em.createQuery("select count(e.id) from Author e where e.id=:id");
        query.setParameter("id", id);
        Long result = (Long)query.getSingleResult();
        return result > 0;
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        if (!existById(id)){
            throw new LibraryException("Cant't delete non existing author");
        }

        Query query = em.createQuery("delete from Author e where e.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
