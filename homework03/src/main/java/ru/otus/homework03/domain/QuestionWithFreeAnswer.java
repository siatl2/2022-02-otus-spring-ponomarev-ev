package ru.otus.homework03.domain;

import java.util.ArrayList;
import java.util.List;

public class QuestionWithFreeAnswer extends Question {
    private final String correctAnswer;
    public QuestionWithFreeAnswer(String name, String correctAnswer) {
        super(name);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public List<Answer> getAnswers() {
        return new ArrayList<>();
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }
}
