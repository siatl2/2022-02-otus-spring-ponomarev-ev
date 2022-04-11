package ru.otus.homework04.service.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.homework04.bean.IOServiceTestBean;
import ru.otus.homework04.service.AnswerAsker;

@SpringBootTest(classes = {AnswerAskerImpl.class,IOServiceTestBean.class})
class AnswerAskerImplTest {
    private static final String TEST_INPUT = "Vasya";

    private final AnswerAsker answerAsker;
    private final IOServiceTestBean ioServiceTestBean;

    @Autowired
    public AnswerAskerImplTest(
            AnswerAsker answerAsker
            ,IOServiceTestBean ioServiceTestBean) {
        this.answerAsker = answerAsker;
        this.ioServiceTestBean = ioServiceTestBean;
    }

    @Test
    void askAnswer(){
        String expectedValue = TEST_INPUT;

        ioServiceTestBean.setInputText(TEST_INPUT);
        String actualValue = answerAsker.askAnswer();

        assertEquals(expectedValue, actualValue);
    }
}