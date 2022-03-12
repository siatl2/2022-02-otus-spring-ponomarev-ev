package ru.otus.homework02.service;

import org.springframework.stereotype.Service;
import ru.otus.homework02.domain.Question;

@Service
public interface QuestionConverter {
    String convertQuestionToString(int number, Question question);
}
