package ru.otus.homework04.service;

import ru.otus.homework04.domain.*;

import java.util.List;

public interface TestCalculator {
    TestResult calculateResult(Student student
                                , List<AnswerUserOnQuestion> answerUserOnQuestions);
}
