package ru.otus.homework04.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.otus.homework04.bean.IOServiceTestBean;
import ru.otus.homework04.bean.MessageOutputTestBean;
import ru.otus.homework04.domain.Student;
import ru.otus.homework04.service.StudentAsker;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {StudentAskerImpl.class, IOServiceTestBean.class, MessageOutputTestBean.class})
class StudentAskerImplTest {
    private static final String FAMILY = "Ivanov";
    private static final String NAME = "Vasya";
    private static final String INPUT_TEXT = FAMILY + "\n" + NAME;
    private static final String OUTPUT_TEXT = "" +
            "Enter your family: " + FAMILY + "\n" +
            "Enter your name: " + NAME + "\n";

    private final StudentAsker studentAsker;
    private final IOServiceTestBean ioServiceTestBean;

    @Autowired
    public StudentAskerImplTest(
            StudentAsker studentAsker
            , IOServiceTestBean ioServiceTestBean) {
        this.studentAsker = studentAsker;
        this.ioServiceTestBean = ioServiceTestBean;
    }

    @Test
    void askStudentInfo() {
        ioServiceTestBean.setInputText(INPUT_TEXT);

        Student expectedStudent = new Student(FAMILY, NAME);
        String expectedOutput = OUTPUT_TEXT;

        Student actualStudent = studentAsker.askStudentInfo();
        String actualOutput = ioServiceTestBean.getOutoutText();

        assertAll(
                () -> assertEquals(expectedStudent, actualStudent),
                () -> assertEquals(expectedOutput, actualOutput)
        );
    }
}