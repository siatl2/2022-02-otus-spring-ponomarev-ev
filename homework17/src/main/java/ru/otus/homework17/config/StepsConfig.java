package ru.otus.homework17.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework17.domain.AuthorMongo;
import ru.otus.homework17.domain.BookMongo;
import ru.otus.homework17.domain.CommentMongo;
import ru.otus.homework17.domain.GenreMongo;
import ru.otus.homework17.model.AuthorJpa;
import ru.otus.homework17.model.BookJpa;
import ru.otus.homework17.model.CommentJpa;
import ru.otus.homework17.model.GenreJpa;
import ru.otus.homework17.processor.AuthorProcessor;
import ru.otus.homework17.processor.BookProcessor;
import ru.otus.homework17.processor.CommentProcessor;
import ru.otus.homework17.processor.GenreProcessor;

@Configuration
@EnableBatchProcessing
public class StepsConfig {
    private static final int CHUNK_SIZE = 5;
    private final StepBuilderFactory stepBuilderFactory;

    private final AuthorProcessor authorProcessor;
    private final GenreProcessor genreProcessor;
    private final BookProcessor bookProcessor;
    private final CommentProcessor commentProcessor;

    @Autowired
    public StepsConfig(StepBuilderFactory stepBuilderFactory
            , AuthorProcessor authorProcessor
            , GenreProcessor genreProcessor
            , BookProcessor bookProcessor
            , CommentProcessor commentProcessor
    ) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.authorProcessor = authorProcessor;
        this.genreProcessor = genreProcessor;
        this.bookProcessor = bookProcessor;
        this.commentProcessor = commentProcessor;
    }

    @Bean
    public Step stepOneImportAuthors(JpaPagingItemReader<AuthorJpa> itemReader
            , MongoItemWriter<AuthorMongo> itemWriter)
            throws Exception {
        return this
                .stepBuilderFactory.get("stepOneImportAuthors")
                .<AuthorJpa, AuthorMongo>chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(authorProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Step stepTwoImportGenres(JpaPagingItemReader<GenreJpa> itemReader
            , MongoItemWriter<GenreMongo> itemWriter)
            throws Exception {
        return this
                .stepBuilderFactory.get("stepTwoImportGenres")
                .<GenreJpa, GenreMongo>chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(genreProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Step stepThreeImportBooks(JpaPagingItemReader<BookJpa> itemReader
            , MongoItemWriter<BookMongo> itemWriter)
            throws Exception {
        return this
                .stepBuilderFactory.get("stepThreeImportBooks")
                .<BookJpa, BookMongo>chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(bookProcessor)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Step stepFourImportComments(JpaPagingItemReader<CommentJpa> itemReader
            , MongoItemWriter<CommentMongo> itemWriter)
            throws Exception {
        return this
                .stepBuilderFactory.get("stepFourImportComments")
                .<CommentJpa, CommentMongo>chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(commentProcessor)
                .writer(itemWriter)
                .build();
    }
}
