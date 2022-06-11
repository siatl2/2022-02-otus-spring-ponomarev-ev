package ru.otus.homework13.repository;

import org.springframework.data.repository.CrudRepository;
import ru.otus.homework13.model.Reader;

public interface ReaderRepository extends CrudRepository<Reader, Long> {
    Reader findByLogin(String userName);
}
