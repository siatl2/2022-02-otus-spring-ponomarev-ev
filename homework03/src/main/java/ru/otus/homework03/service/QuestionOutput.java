package ru.otus.homework03.service;

import ru.otus.homework03.domain.Question;

public interface QuestionOutput {
    void showQuestionWithVariabkeAnswers(int number, Question question);
}
