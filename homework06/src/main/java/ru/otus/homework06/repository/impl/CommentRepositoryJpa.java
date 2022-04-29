package ru.otus.homework06.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.homework06.exception.LibraryException;
import ru.otus.homework06.model.Comment;
import ru.otus.homework06.repository.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentRepositoryJpa implements CommentRepository {
    @PersistenceContext
    private final EntityManager em;

    @Autowired
    public CommentRepositoryJpa(EntityManager em) {
        this.em = em;
    }

    @Transactional
    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0){
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("select e from Comment e join fetch e.book b join fetch b.author join fetch b.genre", Comment.class);
        return query.getResultList();
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(em.find(Comment.class, id));
    }

    @Override
    public boolean existById(long id) {
        Query query = em.createQuery("select count(e.id) from Comment e where e.id=:id");
        query.setParameter("id", id);
        Long result = (Long)query.getSingleResult();
        return result > 0;
    }

    @Transactional
    @Override
    public void deleteById(long id) {
        if (!existById(id)){
            throw new LibraryException("Cant't delete non existing comment");
        }

        Query query = em.createQuery("delete from Comment e where e.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
