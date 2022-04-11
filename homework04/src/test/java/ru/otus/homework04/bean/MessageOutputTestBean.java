package ru.otus.homework04.bean;

import ru.otus.homework04.component.MessageOutput;

import java.util.HashMap;
import java.util.Map;

public class MessageOutputTestBean implements MessageOutput {
    private final Map<String, String> mapMessage = new HashMap<>();
    {
        mapMessage.put("question_number", "Question #");
        mapMessage.put("enter_number_answer", "Enter number answer");
        mapMessage.put("enter_free_answer", "Enter free answer");
        mapMessage.put("variable_answers", "Variable answers");
        mapMessage.put("enter_your_family", "Enter your family");
        mapMessage.put("enter_your_name", "Enter your name");
        mapMessage.put("student", "Student");
        mapMessage.put("get_score", "Get Score");
        mapMessage.put("minimum_score", "Minimum Score");
        mapMessage.put("result", "Result");
        mapMessage.put("passed", "passed");
        mapMessage.put("failed", "failed");

    }
    @Override
    public String getMessage(String parameter) {
        return mapMessage.get(parameter);
    }
}
