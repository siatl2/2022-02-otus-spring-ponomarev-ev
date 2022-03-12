package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.dao.QuestionDao;
import ru.otus.homework02.domain.Question;
import ru.otus.homework02.service.ExamAskerOutput;
import ru.otus.homework02.service.IOService;
import ru.otus.homework02.service.QuestionConverter;
import ru.otus.homework02.service.VariableAnswerConverter;

@Service
public class ExamAskerOutputImpl implements ExamAskerOutput {
    private final QuestionDao questionDao;
    private final QuestionConverter questionConverter;
    private final VariableAnswerConverter variableAnswerConverter;
    private final IOService ioService;

    @Autowired
    public ExamAskerOutputImpl(QuestionDao questionDao
                                , IOService ioService
                                , QuestionConverter questionConverter
                                , VariableAnswerConverter variableAnswerConverter) {
        this.questionDao = questionDao;
        this.questionConverter = questionConverter;
        this.variableAnswerConverter = variableAnswerConverter;
        this.ioService = ioService;
    }

    @Override
    public void showQuestionWithVariabkeAnswers(int number) {
        Question question = questionDao.getQuestions().get(number);

        ioService.outputString("=========================================");
        ioService.newLine();

        String questionText = questionConverter.convertQuestionToString((number + 1), question);
        ioService.outputString(questionText);
        ioService.newLine();

        String variableText = variableAnswerConverter.convertVariableAnswerToString(question);
        if (variableText.length() > 0){
            ioService.outputString(variableText);
            ioService.outputString("Enter number answer=");
        } else {
            ioService.outputString("Enter free answer=");
        }
    }
}
