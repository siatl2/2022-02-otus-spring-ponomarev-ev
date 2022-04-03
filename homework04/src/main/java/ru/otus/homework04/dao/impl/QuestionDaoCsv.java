package ru.otus.homework04.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.otus.homework04.dao.QuestionDao;
import ru.otus.homework04.domain.Answer;
import ru.otus.homework04.domain.Question;
import ru.otus.homework04.domain.QuestionWithFreeAnswer;
import ru.otus.homework04.domain.QuestionWithVariableAnswers;
import ru.otus.homework04.exception.InvalidFileFormatException;
import ru.otus.homework04.component.FileReader;

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
            String[] columns = line.split(",");
            validateCountColumn(columns.length);
            String name = columns[0];
            String correctAnswer = columns[1];
            boolean questionWithFreeAnswer = columns.length == 2;
            Question question = createQuestion(questionWithFreeAnswer
                                                , name
                                                , correctAnswer
                                                , columns);
            questions.add(question);
        }
        return questions;
    }

    private void validateCountColumn(int countColumn){
        if (countColumn < 2){
            throw new InvalidFileFormatException("Bad file format", new RuntimeException());
        }
    }

    private Question createQuestion(boolean questionWithFreeAnswer
                                    , String name
                                    , String correctAnswer
                                    , String[] columns) {
        if (questionWithFreeAnswer){
            return new QuestionWithFreeAnswer(name, correctAnswer);
        } else {
            List<Answer> answers = new ArrayList<>();
            for (int j = 2; j < columns.length; j++) {
                String answer = columns[j];
                boolean correct = (correctAnswer.equalsIgnoreCase(String.valueOf(j - 1)));
                answers.add(new Answer(answer, correct));
            }
            return new QuestionWithVariableAnswers(name, answers);
        }
    }
}
