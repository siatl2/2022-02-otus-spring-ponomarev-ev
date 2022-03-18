package ru.otus.homework02.service;

import ru.otus.homework02.domain.Question;

public interface QuestionOutput {
    void showQuestionWithVariabkeAnswers(int number, Question question);
}
