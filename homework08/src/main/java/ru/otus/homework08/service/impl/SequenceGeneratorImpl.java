package ru.otus.homework08.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.otus.homework08.model.Counter;
import ru.otus.homework08.service.SequenceGenerator;


@Service
public class SequenceGeneratorImpl implements SequenceGenerator {
    private static final long INC = 1L;
    private final MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public long getSequenceNumber(String sequenceName) {
        Query query = new Query(Criteria.where("_id").is(sequenceName));
        Update update = new Update().inc("sequenceNumber", INC);
        Counter counter = mongoOperations.findAndModify(query,
                update,
                Counter.class);
        return counter.getSequenceNumber();
    }
}
