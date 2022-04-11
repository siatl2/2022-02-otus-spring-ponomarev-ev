package ru.otus.homework04.dao.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homework04.dao.QuestionDao;
import ru.otus.homework04.domain.Answer;
import ru.otus.homework04.domain.Question;
import ru.otus.homework04.domain.QuestionWithFreeAnswer;
import ru.otus.homework04.domain.QuestionWithVariableAnswers;
import ru.otus.homework04.component.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = {QuestionDaoCsv.class})
class QuestionDaoCsvTest {
    private static final List<String> CONTENT_FILE = Arrays.asList(new String[]{"Question1,1,Answer1-1,Answer1-2", "Question2,otvet"});

    private final QuestionDao questionDao;

    @MockBean
    private FileReader fileReader;

    @Autowired
    public QuestionDaoCsvTest(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Test
    void getQuestions() {
        when(fileReader.getContentFile()).thenReturn(CONTENT_FILE);
        List<Question> expectedQuestions = new ArrayList<>();
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Answer1-1", true));
        answers.add(new Answer("Answer1-2", false));
        expectedQuestions.add(new QuestionWithVariableAnswers("Question1", answers));
        expectedQuestions.add(new QuestionWithFreeAnswer("Question2", "otvet"));

        List<Question> actualQuestions = questionDao.getQuestions();

        assertEquals(expectedQuestions, actualQuestions);
    }
}
