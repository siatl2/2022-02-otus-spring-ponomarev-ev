package ru.otus.homework13.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.homework13.model.Reader;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {
    Reader findByLogin(String login);
}
