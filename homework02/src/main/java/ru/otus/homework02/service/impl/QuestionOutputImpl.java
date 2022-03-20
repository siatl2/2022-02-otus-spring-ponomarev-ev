package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Answer;
import ru.otus.homework02.domain.Question;
import ru.otus.homework02.service.IOService;
import ru.otus.homework02.service.QuestionOutput;

import java.util.List;

@Service
public class QuestionOutputImpl implements QuestionOutput {
    private final IOService ioService;

    @Autowired
    public QuestionOutputImpl(final IOService ioService) {
        this.ioService = ioService;
    }

    @Override
    public void showQuestionWithVariabkeAnswers(int number, Question question) {
        ioService.outputString("=========================================");
        ioService.outputEmptyLine();

        String questionText = "Question #[" + (number + 1) + "]=" + question.getName();
        ioService.outputString(questionText);
        ioService.outputEmptyLine();

        String variableText = getVariableText(question);

        if (variableText.length() > 0){
            ioService.outputString(variableText);
            ioService.outputString("Enter number answer=");
        } else {
            ioService.outputString("Enter free answer=");
        }
    }

    private String getVariableText(Question question) {
        StringBuilder returnValue = new StringBuilder();
        List<Answer> answers = question.getAnswers();
        if (answers.size() > 0) {
            returnValue.append("Variable answers:\n");
            for (int i = 0; i < answers.size(); i++) {
                returnValue.append((i + 1) + " - " + answers.get(i).getName() + "\n");
            }
        }
        return returnValue.toString();
    }

}
