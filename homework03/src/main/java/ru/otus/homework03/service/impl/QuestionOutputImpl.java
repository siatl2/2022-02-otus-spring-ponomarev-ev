package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.homework03.domain.Answer;
import ru.otus.homework03.domain.Question;
import ru.otus.homework03.exception.LanguageNotSupportException;
import ru.otus.homework03.service.IOService;
import ru.otus.homework03.service.QuestionOutput;

import java.util.List;
import java.util.Locale;

@Service
public class QuestionOutputImpl implements QuestionOutput {
    private final IOService ioService;
    private final MessageSource messageSource;
    private final Locale language;

    @Autowired
    public QuestionOutputImpl(final IOService ioService
                                , final MessageSource messageSource
                                , @Value("${questions.language}") final String language) {
        this.ioService = ioService;
        this.messageSource = messageSource;
        if (language.equalsIgnoreCase("en")||language.equalsIgnoreCase("ru")){
            this.language = Locale.forLanguageTag(language);
        } else {
            throw new LanguageNotSupportException("Language " + language + "not supported", new RuntimeException());
        }
    }

    @Override
    public void showQuestionWithVariabkeAnswers(int number, Question question) {
        ioService.outputString("=========================================");
        ioService.outputEmptyLine();


        String questionNumber = messageSource.getMessage("question_number", null, language);
        String questionText = questionNumber + "[" + (number + 1) + "]=" + question.getName();
        ioService.outputString(questionText);
        ioService.outputEmptyLine();

        String variableText = getVariableText(question);

        if (variableText.length() > 0){
            ioService.outputString(variableText);
            String enterFreeNumber = messageSource.getMessage("enter_number_answer", null, language);
            ioService.outputString(enterFreeNumber + "=");
        } else {
            String enterFreeAnswer = messageSource.getMessage("enter_free_answer", null, language);
            ioService.outputString(enterFreeAnswer + "=");
        }
    }

    private String getVariableText(Question question) {
        StringBuilder returnValue = new StringBuilder();
        List<Answer> answers = question.getAnswers();
        if (answers.size() > 0) {
            String variableAnswers = messageSource.getMessage("variable_answers", null, language);
            returnValue.append(variableAnswers);
            returnValue.append(":\n");
            for (int i = 0; i < answers.size(); i++) {
                returnValue.append((i + 1) + " - " + answers.get(i).getName() + "\n");
            }
        }
        return returnValue.toString();
    }

}
