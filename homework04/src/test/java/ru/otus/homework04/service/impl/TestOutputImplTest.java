package ru.otus.homework04.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homework04.bean.IOServiceTestBean;
import ru.otus.homework04.bean.MessageOutputTestBean;
import ru.otus.homework04.domain.*;
import ru.otus.homework04.service.TestOutput;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {TestOutputImpl.class, IOServiceTestBean.class, MessageOutputTestBean.class})
class TestOutputImplTest {
    public static final String OUPUT_STUDENT = "\n" +
            "===========================\n" +
            "Student Ivanov Vasya\n" +
            "===========================\n" +
            "Get Score: %1\n" +
            "Minimum Score: %2\n" +
            "===========================\n" +
            "Result: %3\n";

    private final TestOutput testOutput;
    private final IOServiceTestBean ioServiceTestBean;

    @Autowired
    public TestOutputImplTest(
            TestOutput testOutput
            , IOServiceTestBean ioServiceTestBean) {
        this.testOutput = testOutput;
        this.ioServiceTestBean = ioServiceTestBean;
    }

    @BeforeEach
    void initTest(){
        ioServiceTestBean.clearOutput();
    }

    @ParameterizedTest
    @CsvSource({"2,2,true","2,0,false"})
    void showResults(
            int minimumScore
            , int userScore
            , boolean passed) {
        Student student = new Student("Ivanov", "Vasya");
        List<AnswerUserOnQuestion> answerUserOnQuestions = new ArrayList<>();

        List<Answer> answersOnQuestionOne = new ArrayList<>();
        answersOnQuestionOne.add(new Answer("Answer1-1", true));
        answersOnQuestionOne.add(new Answer("Answer1-2", false));

        Question questionOne = new QuestionWithVariableAnswers("Question1", answersOnQuestionOne);
        Question questionTwo = new QuestionWithFreeAnswer("Question2", "1");

        answerUserOnQuestions.add(new AnswerUserOnQuestion(questionOne, "1"));
        answerUserOnQuestions.add(new AnswerUserOnQuestion(questionTwo, "1"));

        TestResult testResult = new TestResult(
                student
                , Calendar.getInstance()
                , answerUserOnQuestions
                , minimumScore
                , userScore
                ,passed);

        String textPassed;
        if (passed == true){
            textPassed = "passed";
        } else {
            textPassed = "failed";
        }

        String expectedValue = OUPUT_STUDENT
                .replace("%1", String.valueOf(userScore))
                .replace("%2", String.valueOf(minimumScore))
                .replace("%3", textPassed);
        testOutput.showResults(testResult);
        String actualValue = ioServiceTestBean.getOutoutText();

        assertEquals(expectedValue, actualValue);
    }
}