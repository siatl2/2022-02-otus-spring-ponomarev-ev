package ru.otus.homework04.domain;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestResult)) return false;

        TestResult that = (TestResult) o;

        if (minimumScore != that.minimumScore) return false;
        if (userScore != that.userScore) return false;
        if (passed != that.passed) return false;
        if (!student.equals(that.student)) return false;
        return answersUserOnQuestion.equals(that.answersUserOnQuestion);
    }

    @Override
    public int hashCode() {
        int result = student.hashCode();
        result = 31 * result + minimumScore;
        result = 31 * result + userScore;
        result = 31 * result + (passed ? 1 : 0);
        return result;
    }
}
