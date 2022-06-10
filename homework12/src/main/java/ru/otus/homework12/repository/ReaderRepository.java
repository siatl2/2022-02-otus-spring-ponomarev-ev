package ru.otus.homework12.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework12.model.Reader;

public interface ReaderRepository extends CrudRepository<Reader, Long> {
    Reader findByLogin(String userName);
}
