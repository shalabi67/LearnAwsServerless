package com.labs.FaqApiUsingFramework.models;

import com.serverless.framework.dynamodb.repository.BaseModule;
import lombok.Data;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Data
public class Question extends BaseModule<String> {
    private static final String QUESTION = "question";
    private static final String ANSWER = "answer";

    private String question;
    private String answer;

    public Question() {
        tableName = "questions";
    }

    @Override
    protected String createId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void read() {

    }

    @Override
    public Map<String, AttributeValue> save() {
        Map<String, AttributeValue> questionMap = new HashMap<>();
        questionMap.put(ID, AttributeValue.builder().s(id).build());
        if(question != null) {
            questionMap.put(QUESTION, AttributeValue.builder().s(question).build());
        }
        if(answer != null) {
            questionMap.put(ANSWER, AttributeValue.builder().s(answer).build());
        }

        return questionMap;
    }


}
