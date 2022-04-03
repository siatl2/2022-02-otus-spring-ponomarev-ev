package ru.otus.homework04.service;

import ru.otus.homework04.domain.Question;

public interface QuestionOutput {
    void showQuestionWithVariableAnswers(int number, Question question);
}
