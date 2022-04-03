package ru.otus.homework04.domain;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionWithFreeAnswer)) return false;

        QuestionWithFreeAnswer that = (QuestionWithFreeAnswer) o;

        return (name.equals(that.name)) &&
                (correctAnswer.equals(that.correctAnswer));
    }

    @Override
    public int hashCode() {
        int result = correctAnswer != null ? correctAnswer.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
