package ru.otus.homework17.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import ru.otus.homework17.domain.AuthorMongo;
import ru.otus.homework17.domain.BookMongo;
import ru.otus.homework17.domain.CommentMongo;
import ru.otus.homework17.domain.GenreMongo;

@Configuration
@EnableBatchProcessing
public class WritersConfig {
    @Bean
    public MongoItemWriter<AuthorMongo> writerAuthor(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<AuthorMongo>()
                .template(mongoTemplate)
                .collection("author")
                .build();
    }

    @Bean
    public MongoItemWriter<BookMongo> writerBook(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<BookMongo>()
                .template(mongoTemplate)
                .collection("book")
                .build();
    }

    @Bean
    public MongoItemWriter<GenreMongo> writerGenre(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<GenreMongo>()
                .template(mongoTemplate)
                .collection("genre")
                .build();
    }

    @Bean
    public MongoItemWriter<CommentMongo> writerComment(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<CommentMongo>()
                .template(mongoTemplate)
                .collection("comment")
                .build();
    }
}
