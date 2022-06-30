package ru.otus.homework14.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework14.domain.BookMongo;
import ru.otus.homework14.domain.CommentMongo;
import ru.otus.homework14.exception.LibraryException;
import ru.otus.homework14.model.CommentJpa;
import ru.otus.homework14.repository.BookMongoRepository;

@Service
public class CommentProcessor implements ItemProcessor<CommentJpa, CommentMongo> {
    private final BookMongoRepository bookMongoRepository;

    @Autowired
    public CommentProcessor(BookMongoRepository bookMongoRepository) {
        this.bookMongoRepository = bookMongoRepository;
    }

    @Override
    public CommentMongo process(CommentJpa commentJpa) throws Exception {
        CommentMongo commentMongo = new CommentMongo();

        commentMongo.setId(commentJpa.getId());
        commentMongo.setName(commentJpa.getName());

        BookMongo bookMongo = bookMongoRepository.findById(commentJpa.getBookJpa().getId()).orElseThrow(() -> new LibraryException());

        commentMongo.setBookMongo(bookMongo);

        return commentMongo;
    }
}
