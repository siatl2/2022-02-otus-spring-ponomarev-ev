package ru.otus.homework03.domain;

public class Answer {
    private final String name;
    private final boolean correct;

    public Answer(final String name, final boolean correct){
        this.name = name;
        this.correct = correct;
    }

    public String getName() {
        return name;
    }

    public boolean isCorrect() {
        return correct;
    }
}
