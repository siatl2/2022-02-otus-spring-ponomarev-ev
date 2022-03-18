package ru.otus.homework02.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.homework02.dao.CorrectAnswerDao;

@Repository
public class CorrectAnswerDaoProperties implements CorrectAnswerDao {
    private final String correctAnswers;

    public CorrectAnswerDaoProperties(@Value("${questions.correct_answers}") final String correctAsswers) {
        this.correctAnswers = correctAsswers;
    }

    @Override
    public String[] getCorrectAnswers() {
        return correctAnswers.split(",");
    }
}
