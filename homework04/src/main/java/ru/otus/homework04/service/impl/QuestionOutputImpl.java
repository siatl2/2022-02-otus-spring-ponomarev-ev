package ru.otus.homework04.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework04.domain.Answer;
import ru.otus.homework04.domain.Question;
import ru.otus.homework04.component.IOService;
import ru.otus.homework04.component.MessageOutput;
import ru.otus.homework04.service.QuestionOutput;

import java.util.List;

@Service
public class QuestionOutputImpl implements QuestionOutput {
    private final IOService ioService;
    private final MessageOutput messageOutput;

    @Autowired
    public QuestionOutputImpl(final IOService ioService
                                , final MessageOutput messageOutput) {
        this.ioService = ioService;
        this.messageOutput = messageOutput;
    }

    @Override
    public void showQuestionWithVariableAnswers(int number, Question question) {
        ioService.outputString("=========================================");
        ioService.outputEmptyLine();

        String questionNumber = messageOutput.getMessage("question_number");
        String questionText = questionNumber + "[" + (number + 1) + "]=" + question.getName();
        ioService.outputString(questionText);
        ioService.outputEmptyLine();

        String variableText = getVariableText(question);

        if (variableText.length() > 0){
            ioService.outputString(variableText);
            String enterFreeNumber = messageOutput.getMessage("enter_number_answer");
            ioService.outputString(enterFreeNumber + "=");
        } else {
            String enterFreeAnswer = messageOutput.getMessage("enter_free_answer");
            ioService.outputString(enterFreeAnswer + "=");
        }
    }

    private String getVariableText(Question question) {
        StringBuilder returnValue = new StringBuilder();
        List<Answer> answers = question.getAnswers();
        if (answers.size() > 0) {
            String variableAnswers = messageOutput.getMessage("variable_answers");
            returnValue.append(variableAnswers);
            returnValue.append(":\n");
            for (int i = 0; i < answers.size(); i++) {
                returnValue.append((i + 1) + " - " + answers.get(i).getName() + "\n");
            }
        }
        return returnValue.toString();
    }

}
