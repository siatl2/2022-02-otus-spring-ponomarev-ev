package ru.otus.homework02.service;

import ru.otus.homework02.domain.*;

import java.util.List;

public interface TestCalculator {
    TestResult calculateResult(Student student
                                , List<AnswerUserOnQuestion> answerUserOnQuestions);
}
