package ru.otus.homework03.domain;

import java.util.List;

public abstract class Question {
    private final String name;

    public Question(final String name){
        this.name = name;
    }

    public abstract List<Answer> getAnswers();

    public final String getName() {
        return name;
    }

    public abstract String getCorrectAnswer();
}
