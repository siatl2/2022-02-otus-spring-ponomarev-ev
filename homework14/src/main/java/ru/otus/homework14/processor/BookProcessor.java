package ru.otus.homework14.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework14.domain.AuthorMongo;
import ru.otus.homework14.domain.BookMongo;
import ru.otus.homework14.domain.GenreMongo;
import ru.otus.homework14.exception.LibraryException;
import ru.otus.homework14.model.BookJpa;
import ru.otus.homework14.repository.AuthorMongoRepository;
import ru.otus.homework14.repository.GenreMongoRepository;

@Service
public class BookProcessor implements ItemProcessor<BookJpa, BookMongo> {
    private final AuthorMongoRepository authorMongoRepository;
    private final GenreMongoRepository genreMongoRepository;

    @Autowired
    public BookProcessor(AuthorMongoRepository authorMongoRepository, GenreMongoRepository genreMongoRepository) {
        this.authorMongoRepository = authorMongoRepository;
        this.genreMongoRepository = genreMongoRepository;
    }

    @Override
    public BookMongo process(BookJpa bookJpa) throws Exception {
        AuthorMongo authorMongo = authorMongoRepository.findById(bookJpa.getAuthorJpa().getId()).orElseThrow(() -> new LibraryException());
        GenreMongo genreMongo = genreMongoRepository.findById(bookJpa.getGenreJpa().getId()).orElseThrow(() -> new LibraryException());

        BookMongo bookMongo = new BookMongo();
        bookMongo.setId(bookJpa.getId());
        bookMongo.setName(bookJpa.getName());
        bookMongo.setAuthorMongo(authorMongo);
        bookMongo.setGenreMongo(genreMongo);

        return bookMongo;
    }
}
