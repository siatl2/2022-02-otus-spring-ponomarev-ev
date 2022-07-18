package ru.otus.homework16.actuators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;
import ru.otus.homework16.repository.BookRepository;

@Component
public class BookLibraryIndicator implements HealthIndicator {
    private final long maximumBooks;
    private final BookRepository bookRepository;

    @Autowired
    public BookLibraryIndicator(@Value("${books.maximum}") long maximumBooks,
                                BookRepository bookRepository) {
        this.maximumBooks = maximumBooks;
        this.bookRepository = bookRepository;
    }

    @Override
    public Health health() {
        long countBooks = bookRepository.count();
        if (countBooks > maximumBooks) {
            return Health.down()
                    .status(Status.DOWN)
                    .withDetail("message", "Let's go some book to archive!")
                    .build();
        } else {
            return Health.up().status(Status.UP).
                    withDetail("message", "librarian, sleep longer!").build();
        }
    }
}
