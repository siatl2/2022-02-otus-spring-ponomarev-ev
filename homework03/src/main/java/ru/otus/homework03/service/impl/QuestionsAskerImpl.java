package ru.otus.homework03.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.homework03.domain.AnswerUserOnQuestion;
import ru.otus.homework03.domain.Question;
import ru.otus.homework03.service.AnswerAsker;
import ru.otus.homework03.service.QuestionOutput;
import ru.otus.homework03.service.QuestionsAsker;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionsAskerImpl implements QuestionsAsker {
    private final QuestionOutput questionOutput;
    private final AnswerAsker answerAsker;

    @Autowired
    public QuestionsAskerImpl(final QuestionOutput questionOutput
                                , final AnswerAsker answerAsker) {
        this.questionOutput = questionOutput;
        this.answerAsker = answerAsker;
    }

    @Override
    public List<AnswerUserOnQuestion> askQuestions(List<Question> questions) {
        List<AnswerUserOnQuestion> result = new ArrayList<>();
        int count = questions.size();

        for (int i = 0; i < count; i++) {
            Question question = questions.get(i);
            questionOutput.showQuestionWithVariabkeAnswers(i,question);
            String answer = answerAsker.askAnswer();
            result.add(new AnswerUserOnQuestion(question, answer));
        }

        return result;
    }
}
