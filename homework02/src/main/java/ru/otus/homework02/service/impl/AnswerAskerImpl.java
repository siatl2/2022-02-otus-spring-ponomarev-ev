package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.service.AnswerAsker;
import ru.otus.homework02.service.IOService;

@Service
public class AnswerAskerImpl implements AnswerAsker {
    private final IOService ioService;

    @Autowired
    public AnswerAskerImpl(final IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public String askAnswer() {
        return ioService.inputStringWithPrompt("");
    }
}
