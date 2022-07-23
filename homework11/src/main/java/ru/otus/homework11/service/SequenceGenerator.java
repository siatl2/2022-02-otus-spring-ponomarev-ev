package ru.otus.homework11.service;

import reactor.core.publisher.Mono;
import ru.otus.homework11.model.Counter;

public interface SequenceGenerator {
    public Mono<Counter> getNextCounter(String sequenceName);
}
