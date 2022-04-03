package ru.otus.homework04.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework04.domain.Student;
import ru.otus.homework04.component.IOService;
import ru.otus.homework04.component.MessageOutput;
import ru.otus.homework04.service.StudentAsker;

@Service
public class StudentAskerImpl implements StudentAsker {
    private final IOService ioService;
    private final MessageOutput messageOutput;

    @Autowired
    public StudentAskerImpl(final IOService ioService
                            , final MessageOutput messageOutput) {
        this.ioService = ioService;
        this.messageOutput = messageOutput;
    }

    @Override
    public Student askStudentInfo() {
        String enterYourFamily= messageOutput.getMessage("enter_your_family");
        String family = ioService.inputStringWithPrompt(enterYourFamily + ": ");
        String enterYourName = messageOutput.getMessage("enter_your_name");
        String name = ioService.inputStringWithPrompt(enterYourName + ": ");

        return new Student(family, name);
    }
}
