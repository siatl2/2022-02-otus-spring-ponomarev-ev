package ru.otus.homework02.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homework02.service.TestResult;

import java.util.HashMap;
import java.util.Map;

@Service
public class TestResultImpl implements TestResult {
    private final int minimumScore;
    private final String rigthAnswers;

    private Map<Integer, String> answers = new HashMap<>();

    @Autowired
    public TestResultImpl(@Value("${questions.score_minimum}") int minimumScore
                            ,@Value("${questions.right_answers}") String rigthAnswers) {
        this.minimumScore = minimumScore;
        this.rigthAnswers = rigthAnswers;
    }

    @Override
    public void add(int numberQuestion, String answer) {
        answers.put(numberQuestion, answer);
    }

    @Override
    public int calculateResult() {
        int score = 0;
        String[] rightAnswers = this.rigthAnswers.split(",");

        for (Map.Entry<Integer, String> userAnswer : answers.entrySet()){
            String userAnswerText = userAnswer.getValue();
            String rightAnswerText = rightAnswers[userAnswer.getKey()];

            if (userAnswerText.equalsIgnoreCase(rightAnswerText)){
                score++;
            }
        }

        return score;
    }

    @Override
    public int getMinimumScore(){
        return minimumScore;
    }
}
