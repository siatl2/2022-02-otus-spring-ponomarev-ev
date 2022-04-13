package ru.otus.homework04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import org.springframework.stereotype.Controller;
import ru.otus.homework04.dao.QuestionDao;
import ru.otus.homework04.domain.*;
import ru.otus.homework04.service.*;

import java.util.List;

@Controller
@ShellComponent
public class ExamRunnerImpl{
    private final TestOutput testOutput;
    private final QuestionDao questionDao;
    private final QuestionsAsker questionsAsker;
    private final TestCalculator testCalculator;
    private Student student;
    private List<AnswerUserOnQuestion> answerUserOnQuestions;

    @Autowired
    public ExamRunnerImpl(
            final QuestionDao questionDao
            , final QuestionsAsker questionsAsker
            , final TestCalculator testCalculator
            , final TestOutput testOutput) {
        this.questionDao = questionDao;
        this.questionsAsker = questionsAsker;
        this.testCalculator = testCalculator;
        this.testOutput = testOutput;
    }

    @ShellMethod("Take a test")
    public void takeTest(@ShellOption String family, @ShellOption String name){
        student = new Student(family, name);
        List<Question> questions = questionDao.getQuestions();
        answerUserOnQuestions = questionsAsker.askQuestions(questions);
    }

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
