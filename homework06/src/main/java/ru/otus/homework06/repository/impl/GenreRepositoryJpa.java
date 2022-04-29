package ru.otus.homework06.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.homework06.repository.GenreRepository;
import ru.otus.homework06.model.Genre;
import ru.otus.homework06.exception.LibraryException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.*;

@Repository
public class GenreRepositoryJpa implements GenreRepository {
    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public GenreRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public Genre save(Genre genre) {
        if (genre.getId() == 0){
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery("select e from Genre e", Genre.class);
        return query.getResultList();
    }

    @Override
    public Optional<Genre> findById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public boolean existById(long id) {
        Query query = em.createQuery("select count(e.id) from Genre e where e.id=:id");
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

        Query query = em.createQuery("delete from Genre e where e.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
