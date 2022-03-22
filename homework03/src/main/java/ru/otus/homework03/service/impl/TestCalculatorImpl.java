package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework03.domain.*;
import ru.otus.homework03.service.TestCalculator;

import java.util.Calendar;
import java.util.List;

@Service
public class TestCalculatorImpl implements TestCalculator {
    private final Calendar datetimeTest = Calendar.getInstance();
    private final int minimumScore;

    public TestCalculatorImpl(@Value("${questions.minimum_questions_correct_answer}") final int minimumScore) {
        this.minimumScore = minimumScore;
    }

    @Override
    public TestResult calculateResult(final Student student
                                    , final List<AnswerUserOnQuestion> answerUserOnQuestions) {
        int score = 0;
        for (AnswerUserOnQuestion answerUserOnQuestion : answerUserOnQuestions){
            Question question = answerUserOnQuestion.getQuestion();

            if (question.getCorrectAnswer().equalsIgnoreCase(answerUserOnQuestion.getAnswerUser())){
                score++;
            }
        }
        boolean passed = score >= minimumScore;

        return new TestResult(student
                            , datetimeTest
                            , answerUserOnQuestions
                            , minimumScore
                            , score
                            , passed);
    }
}
