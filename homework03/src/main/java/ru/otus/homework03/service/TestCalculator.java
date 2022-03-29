package ru.otus.homework03.service;

import ru.otus.homework03.domain.*;

import java.util.List;

public interface TestCalculator {
    TestResult calculateResult(Student student
                                , List<AnswerUserOnQuestion> answerUserOnQuestions);
}
