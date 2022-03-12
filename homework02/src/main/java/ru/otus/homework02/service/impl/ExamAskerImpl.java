package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.dao.QuestionDao;
import ru.otus.homework02.service.*;

@Service
public class ExamAskerImpl implements ExamAsker {
    private final QuestionDao questionDao;
    private final IOService ioService;

    @Autowired
    public ExamAskerImpl(QuestionDao questionDao
                        , IOService ioService) {
        this.questionDao = questionDao;
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

    @Override
    public int getCountQuestions() {
        return questionDao.getQuestions().size();
    }

    @Override
    public String askAnswer(int number) {
        return ioService.inputStringWithPrompt("");
    }
}
