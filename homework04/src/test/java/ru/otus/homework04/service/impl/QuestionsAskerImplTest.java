package ru.otus.homework04.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.homework04.domain.*;
import ru.otus.homework04.service.AnswerAsker;
import ru.otus.homework04.service.QuestionOutput;
import ru.otus.homework04.service.QuestionsAsker;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest(classes = {QuestionsAskerImpl.class})
@ExtendWith(MockitoExtension.class)
class QuestionsAskerImplTest {

    @MockBean
    public QuestionOutput questionOutput;

    @MockBean
    public AnswerAsker answerAsker;

    private final QuestionsAsker questionsAsker;

    @Autowired
    public QuestionsAskerImplTest(QuestionsAsker questionsAsker) {
        this.questionsAsker = questionsAsker;
    }

    @Test
    void askQuestions() {
        List<Answer> answersOnQuestionOne = new ArrayList<>();
        answersOnQuestionOne.add(new Answer("Answer1-1", true));
        answersOnQuestionOne.add(new Answer("Answer1-2", false));

        Question questionOne = new QuestionWithVariableAnswers("Question1", answersOnQuestionOne);
        Question questionTwo = new QuestionWithFreeAnswer("Question2", "1");
        List<Question> questions = new ArrayList<>();
        questions.add(questionOne);
        questions.add(questionTwo);

        List<AnswerUserOnQuestion> expectedValue = new ArrayList<>();
        expectedValue.add(new AnswerUserOnQuestion(questionOne, "1"));
        expectedValue.add(new AnswerUserOnQuestion(questionTwo, "1"));

        Mockito.doNothing().when(questionOutput).showQuestionWithVariableAnswers(anyInt(), any());
        Mockito.when(answerAsker.askAnswer()).thenReturn("1");

        List<AnswerUserOnQuestion> actualValue = questionsAsker.askQuestions(questions);

        assertEquals(expectedValue, actualValue);
    }
}