package ru.otus.homework02.domain;

import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.List;

public class TestResult {
    private final Student student;
    private final Calendar datetimeTest;
    private final List<AnswerUserOnQuestion> answersUserOnQuestion;
    private final int minimumScore;
    private final int userScore;
    private final boolean passed;

    public TestResult(final Student student
            , final Calendar datetimeTest
            , final List<AnswerUserOnQuestion> answersUserOnQuestion
            , final int minimumScore
            , final int userScore
            , final boolean passed) {
        this.student = student;
        this.datetimeTest = datetimeTest;
        this.answersUserOnQuestion = answersUserOnQuestion;
        this.minimumScore = minimumScore;
        this.userScore = userScore;
        this.passed = passed;
    }

    public Student getStudent() {
        return student;
    }

    public Calendar getDatetimeTest() {
        return datetimeTest;
    }

    public List<AnswerUserOnQuestion> getAnswersUserOnQuestion() {
        return answersUserOnQuestion;
    }

    public int getMinimumScore() {
        return minimumScore;
    }

    public int getUserScore() {
        return userScore;
    }

    public boolean isPassed() {
        return passed;
    }
}
