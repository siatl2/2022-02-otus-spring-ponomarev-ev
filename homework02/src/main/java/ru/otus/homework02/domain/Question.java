package ru.otus.homework02.domain;

import java.util.List;

public class Question {
    private final List<Answer> answers;
    private final String name;
    private final String correctAnswer;

    public Question(final String name
                    , final List<Answer> answers
                    , final String correctAnswer){
        this.name = name;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public String getName() {
        return name;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
