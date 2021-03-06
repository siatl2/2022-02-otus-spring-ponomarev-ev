package ru.otus.homework02.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.homework02.dao.QuestionDao;
import ru.otus.homework02.domain.Answer;
import ru.otus.homework02.domain.Question;
import ru.otus.homework02.service.FileReader;

import java.util.ArrayList;
import java.util.List;
@Repository
public class QuestionDaoCsv implements QuestionDao {
    private final FileReader fileReader;
    @Autowired
    public QuestionDaoCsv(final FileReader fileReader){
        this.fileReader = fileReader;
    }

    @Override
    public List<Question> getQuestions(){
        List<String> strings = fileReader.getContentFile();
        List<Question> questions = new ArrayList<>();

        for (String line : strings){
            String[] column = line.split(",");
            if (column.length > 0){
                String name = column[0];
                String correctAnswer = column[1];
                List<Answer> answers = new ArrayList<>();
                for (int j = 2; j < column.length; j++) {
                    answers.add(new Answer(column[j]));
                }
                Question question = new Question(name, answers, correctAnswer);
                questions.add(question);
            }
        }
        return questions;
    }
}
