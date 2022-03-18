package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.dao.CorrectAnswerDao;
import ru.otus.homework02.dao.QuestionDao;
import ru.otus.homework02.domain.*;
import ru.otus.homework02.service.*;

import java.util.List;

@Service
public class ExamRunnerImpl implements ExamRunner {
    private final TestOutput testOutput;
    private final StudentAsker studentAsker;
    private final QuestionDao questionDao;
    private final QuestionsAsker questionsAsker;
    private final CorrectAnswerDao correctAnswerDao;
    private final TestCalculator testCalculator;

    @Autowired
    public ExamRunnerImpl(
            final StudentAsker studentAsker
            , final QuestionDao questionDao
            , final QuestionsAsker questionsAsker
            , final CorrectAnswerDao correctAnswerDao
            , final TestCalculator testCalculator
            , final TestOutput testOutput) {
        this.studentAsker = studentAsker;
        this.correctAnswerDao = correctAnswerDao;
        this.questionDao = questionDao;
        this.questionsAsker = questionsAsker;
        this.testCalculator = testCalculator;
        this.testOutput = testOutput;
    }

    @Override
    public void run() {
        String family = studentAsker.askFamily();
        String name = studentAsker.askName();
        Student student = new Student(family, name);

        String[] correctAnswers = correctAnswerDao.getCorrectAnswers();
        List<Question> questions = questionDao.getQuestions(correctAnswers);
        List<AnswerUserOnQuestion> answerUserOnQuestions = questionsAsker.askQuestions(questions);
        TestResult testResult = testCalculator.calculateResult(student, answerUserOnQuestions);
        testOutput.showResults(testResult);
    }
}
