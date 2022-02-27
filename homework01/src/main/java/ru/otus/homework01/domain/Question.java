package ru.otus.homework01.domain;

import java.util.List;

public class Question {
    private final List<Answer> listAnswer;
    private final String name;

    public Question(final String name, final List<Answer> listAnswer){
        this.name = name;
        this.listAnswer = listAnswer;
    }

    public List<Answer> getListAnswer() {
        return listAnswer;
    }

    public String getName() {
        return name;
    }
}
