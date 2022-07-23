package ru.otus.homework18.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.otus.homework18.model.Counter;
import ru.otus.homework18.service.SequenceGenerator;

@Service
public class SequenceGeneratorImpl implements SequenceGenerator {
    private static final long INC = 1L;
    private final ReactiveMongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorImpl(
            ReactiveMongoOperations mongoOperations
    ) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public Mono<Counter> getNextCounter(String sequenceName) {
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("sequenceNumber", INC);
        return mongoOperations.findAndModify(query,
                update,
                Counter.class);
    }
}
