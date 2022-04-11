package ru.otus.homework04.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.homework04.bean.IOServiceTestBean;
import ru.otus.homework04.bean.MessageOutputTestBean;
import ru.otus.homework04.domain.Answer;
import ru.otus.homework04.domain.Question;
import ru.otus.homework04.domain.QuestionWithFreeAnswer;
import ru.otus.homework04.domain.QuestionWithVariableAnswers;
import ru.otus.homework04.service.QuestionOutput;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = {QuestionOutputImpl.class, IOServiceTestBean.class, MessageOutputTestBean.class})
class QuestionOutputImplTest {
    private static final int NUMBER_QUESTION = 0;
    private static final String EXPECTED_TEXT_FREE_ANSWER = "" +
            "=========================================\n" +
            "Question #[1]=Question\n" +
            "Enter free answer=";
    private static final String EXPECTED_TEXT_VARIABLE_ANSWER = "" +
            "=========================================\n" +
            "Question #[1]=Question\n" +
            "Variable answers:\n" +
            "1 - Answer1-1\n" +
            "2 - Answer1-2\n" +
            "Enter number answer=";

    private final QuestionOutput questionOutput;
    private final IOServiceTestBean ioServiceTestBean;

    @Autowired
    public QuestionOutputImplTest(
            QuestionOutput questionOutput
            , IOServiceTestBean ioServiceTestBean
            ){
        this.questionOutput = questionOutput;
        this.ioServiceTestBean = ioServiceTestBean;
    }

    @BeforeEach
    void clearBuffer(){
        ioServiceTestBean.clearOutput();
    }

    @Test
    void showQuestionWithVariableAnswers_VariableAnswers() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1-1", true));
        answers.add(new Answer("Answer1-2", false));
        Question question = new QuestionWithVariableAnswers("Question", answers);

        questionOutput.showQuestionWithVariableAnswers(NUMBER_QUESTION, question);

        String expectedValue=EXPECTED_TEXT_VARIABLE_ANSWER;
        String actualValue = ioServiceTestBean.getOutoutText();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void showQuestionWithVariableAnswers_FreeAnswer() {
        Question question = new QuestionWithFreeAnswer("Question", "otvet");
        questionOutput.showQuestionWithVariableAnswers(NUMBER_QUESTION, question);

        String expectedValue=EXPECTED_TEXT_FREE_ANSWER;
        String actualValue = ioServiceTestBean.getOutoutText();
        assertEquals(expectedValue, actualValue);
    }
}