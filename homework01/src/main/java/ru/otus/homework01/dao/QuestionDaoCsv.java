package ru.otus.homework01.dao;

import ru.otus.homework01.domain.Answer;
import ru.otus.homework01.domain.Question;
import ru.otus.homework01.service.FileReader;

import java.util.ArrayList;
import java.util.List;

public class QuestionDaoCsv implements QuestionDao {
    private final FileReader fileReader;
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
                for (int i = 1; i < column.length - 1; i++) {
                    listAnswer.add(new Answer(column[i]));
                }
                Question question = new Question(name, listAnswer);
                listQuestions.add(question);
            }
        }
        return listQuestions;
    }
}
