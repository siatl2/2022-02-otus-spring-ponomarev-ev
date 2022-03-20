package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Student;
import ru.otus.homework02.service.IOService;
import ru.otus.homework02.service.StudentAsker;

@Service
public class StudentAskerImpl implements StudentAsker {
    private final IOService ioService;

    @Autowired
    public StudentAskerImpl(final IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public Student askStudentInfo() {
        String family = ioService.inputStringWithPrompt("Enter your family: ");
        String name = ioService.inputStringWithPrompt("Enter your name: ");

        return new Student(family, name);
    }
}
