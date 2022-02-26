package ru.otus.homework01.service;

import ru.otus.homework01.domain.Answer;
import ru.otus.homework01.domain.Question;

import java.util.ArrayList;
import java.util.List;

public class ParserList implements Parser {
    private Reader reader;
    public ParserList(Reader reader){
        this.reader = reader;
    }

    @Override
    public List<Question> getQuestions(){
        List<String> listString = reader.getContentFile();
        List<Question> listQuestions = new ArrayList<>();

        for (String row: listString) {
            String[] column = row.split(",");
            if (column.length > 0){
                Question question = new Question(column[0]);
                List<Answer> listAnswer = new ArrayList<>();
                for (int i = 1; i < column.length - 1; i++) {
                    listAnswer.add(new Answer(column[i]));
                }
                question.setListAnswer(listAnswer);
                listQuestions.add(question);
            }
        }
        return listQuestions;
    }
}
