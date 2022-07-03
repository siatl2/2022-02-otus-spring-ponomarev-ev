package ru.otus.homework14.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;
import ru.otus.homework14.domain.AuthorMongo;
import ru.otus.homework14.model.AuthorJpa;

@Service
public class AuthorProcessor implements ItemProcessor<AuthorJpa, AuthorMongo> {
    @Override
    public AuthorMongo process(AuthorJpa authorJpa) throws Exception {
        AuthorMongo authorMongo = new AuthorMongo();
        authorMongo.setId(authorJpa.getId());
        authorMongo.setName(authorJpa.getName());
        return authorMongo;
    }
}
