package ru.otus.homework14.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.homework14.model.AuthorJpa;
import ru.otus.homework14.model.BookJpa;
import ru.otus.homework14.model.CommentJpa;
import ru.otus.homework14.model.GenreJpa;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableBatchProcessing
public class ReadersConfig {
    private static final int PAGE_SIZE = 1_000;
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public ReadersConfig(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public JpaPagingItemReader<AuthorJpa> readerAuthor() {
        return new JpaPagingItemReaderBuilder<AuthorJpa>()
                .name("AuthorJpa")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select a from AuthorJpa a")
                .pageSize(PAGE_SIZE)
                .build();
    }

    @Bean
    public JpaPagingItemReader<GenreJpa> readerGenre() {
        return new JpaPagingItemReaderBuilder<GenreJpa>()
                .name("GenreJpa")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select g from GenreJpa g")
                .pageSize(PAGE_SIZE)
                .build();
    }

    @Bean
    public JpaPagingItemReader<BookJpa> readerBook() {
        return new JpaPagingItemReaderBuilder<BookJpa>()
                .name("BookJpa")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select b from BookJpa b")
                .pageSize(PAGE_SIZE)
                .build();
    }

    @Bean
    public JpaPagingItemReader<CommentJpa> readerComment() {
        return new JpaPagingItemReaderBuilder<CommentJpa>()
                .name("CommentJpa")
                .entityManagerFactory(entityManagerFactory)
                .queryString("select c from CommentJpa c")
                .pageSize(PAGE_SIZE)
                .build();
    }
}
