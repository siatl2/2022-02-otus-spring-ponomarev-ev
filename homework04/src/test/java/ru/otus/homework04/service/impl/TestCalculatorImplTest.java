package ru.otus.homework04.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework04.domain.*;
import ru.otus.homework04.service.TestCalculator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestCalculatorImpl.class})
class TestCalculatorImplTest {
    private final TestCalculator testCalculator;

    @Autowired
    public TestCalculatorImplTest(TestCalculator testCalculator) {
        this.testCalculator = testCalculator;
    }

    @Test
    void calculateResult() {
        Student student = new Student("Ivanov", "Vasya");
        List<AnswerUserOnQuestion> answerUserOnQuestions = new ArrayList<>();

        List<Answer> answersOnQuestionOne = new ArrayList<>();
        answersOnQuestionOne.add(new Answer("Answer1-1", true));
        answersOnQuestionOne.add(new Answer("Answer1-2", false));

        Question questionOne = new QuestionWithVariableAnswers("Question1", answersOnQuestionOne);
        Question questionTwo = new QuestionWithFreeAnswer("Question2", "1");

        answerUserOnQuestions.add(new AnswerUserOnQuestion(questionOne, "1"));
        answerUserOnQuestions.add(new AnswerUserOnQuestion(questionTwo, "1"));

        int minimumScore = 2;
        int userScore = 2;
        boolean passed = true;

        TestResult expendedResult = new TestResult(
                student
                , Calendar.getInstance()
                , answerUserOnQuestions
                , minimumScore
                , userScore
                , passed);

        TestResult actualResult = testCalculator.calculateResult(
                student
                , answerUserOnQuestions
                );
        assertEquals(expendedResult, actualResult);
    }
}