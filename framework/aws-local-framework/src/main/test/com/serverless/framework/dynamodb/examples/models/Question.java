package com.serverless.framework.dynamodb.examples.models;

import com.serverless.framework.dynamodb.repository.BaseModule;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public void read(Map<String, AttributeValue> stringAttributeValueMap) {

    }

    @Override
    public Map<String, AttributeValue> save() {
        Map<String, AttributeValue> questionMap = new HashMap<>();
        if(id == null || id.isEmpty()) {
            id = UUID.randomUUID().toString();
        }
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
