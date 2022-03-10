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
        List<String> listString = fileReader.getContentFile();
        List<Question> listQuestions = new ArrayList<>();

        for (String row: listString) {
            String[] column = row.split(",");
            if (column.length > 0){
                String name = column[0];
                List<Answer> listAnswer = new ArrayList<>();
                for (int i = 1; i < column.length; i++) {
                    listAnswer.add(new Answer(column[i]));
                }
                Question question = new Question(name, listAnswer);
                listQuestions.add(question);
            }
        }
        return listQuestions;
    }
}
