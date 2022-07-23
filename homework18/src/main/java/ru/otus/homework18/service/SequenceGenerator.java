package ru.otus.homework18.service;

import reactor.core.publisher.Mono;
import ru.otus.homework18.model.Counter;

public interface SequenceGenerator {
    public Mono<Counter> getNextCounter(String sequenceName);
}
