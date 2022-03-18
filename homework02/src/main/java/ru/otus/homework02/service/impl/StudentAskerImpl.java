package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public String askFamily() {
        return ioService.inputStringWithPrompt("Enter your family: ");
    }

    @Override
    public String askName() {
        return ioService.inputStringWithPrompt("Enter your name: ");
    }
}
