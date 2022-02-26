package ru.otus.homework01.domain;

import java.util.List;

public class Question {
    private List<Answer> listAnswer;
    private String name;

    public Question(String name){
        this.name = name;
    }

    public void setListAnswer(List<Answer> listAnswer) {
        this.listAnswer = listAnswer;
    }

    public List<Answer> getListAnswer() {
        return listAnswer;
    }

    public String getName() {
        return name;
    }
}
