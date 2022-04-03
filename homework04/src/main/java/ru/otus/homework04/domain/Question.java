package ru.otus.homework04.domain;

import java.util.List;

public abstract class Question {
    protected final String name;

    public Question(final String name){
        this.name = name;
    }

    public abstract List<Answer> getAnswers();

    public final String getName() {
        return name;
    }

    public abstract String getCorrectAnswer();
}
