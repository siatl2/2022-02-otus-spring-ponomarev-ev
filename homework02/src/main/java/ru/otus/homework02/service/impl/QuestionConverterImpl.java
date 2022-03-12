package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Question;
import ru.otus.homework02.service.QuestionConverter;

@Service
public class QuestionConverterImpl implements QuestionConverter {

    @Override
    public String convertQuestionToString(int number, Question question) {
        return "Question #[" + number + "]=" + question.getName();
    }
}
