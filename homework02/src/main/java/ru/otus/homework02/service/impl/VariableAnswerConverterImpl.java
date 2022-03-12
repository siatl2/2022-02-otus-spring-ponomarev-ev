package ru.otus.homework02.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Answer;
import ru.otus.homework02.domain.Question;
import ru.otus.homework02.service.VariableAnswerConverter;

import java.util.List;

@Service
public class VariableAnswerConverterImpl implements VariableAnswerConverter {
    @Override
    public String convertVariableAnswerToString(Question question) {
        String returnValue = "";
        List<Answer> listAnswer = question.getListAnswer();
        if (listAnswer.size() > 0) {
            returnValue = "Variable answers:\n";
            for (int i = 0; i < listAnswer.size(); i++) {
                returnValue += (i + 1) + " - " + listAnswer.get(i).getName() + "\n";
            }
        }
        return returnValue;
    }
}
