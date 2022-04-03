package ru.otus.homework04.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.stereotype.Service;
import ru.otus.homework04.dao.QuestionDao;
import ru.otus.homework04.domain.*;
import ru.otus.homework04.service.*;

import java.util.List;

@Service
@ShellComponent
public class ExamRunnerImpl implements ExamRunner {
    private final TestOutput testOutput;
    private final StudentAsker studentAsker;
    private final QuestionDao questionDao;
    private final QuestionsAsker questionsAsker;
    private final TestCalculator testCalculator;
    private Student student;
    private List<AnswerUserOnQuestion> answerUserOnQuestions;

    @Autowired
    public ExamRunnerImpl(
            final StudentAsker studentAsker
            , final QuestionDao questionDao
            , final QuestionsAsker questionsAsker
            , final TestCalculator testCalculator
            , final TestOutput testOutput) {
        this.studentAsker = studentAsker;
        this.questionDao = questionDao;
        this.questionsAsker = questionsAsker;
        this.testCalculator = testCalculator;
        this.testOutput = testOutput;
    }

    @Override
    @ShellMethod("Take a test")
    public void takeTest(){
        student = studentAsker.askStudentInfo();
        List<Question> questions = questionDao.getQuestions();
        answerUserOnQuestions = questionsAsker.askQuestions(questions);
    }

    @Override
    @ShellMethod("Display results")
    @ShellMethodAvailability("displayResultAvailability")
    public void displayResult(){
        TestResult testResult = testCalculator.calculateResult(student, answerUserOnQuestions);
        testOutput.showResults(testResult);
    }

    public Availability displayResultAvailability(){
        if (student != null){
            return Availability.available();
        } else {
            return Availability.unavailable("not take a test");
        }
    }
}
