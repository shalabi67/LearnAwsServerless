package com.labs.FaqApiUsingFramework.models;

import com.serverless.framework.dynamodb.repository.BaseModule;
import com.serverless.framework.dynamodb.repository.DynamodbAttributes;
import lombok.Data;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.lang.reflect.AnnotatedArrayType;
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
    public void read(Map<String, AttributeValue> map) {
        DynamodbAttributes attributes = new DynamodbAttributes(map);
        id = attributes.getString(ID);
        question = attributes.getString(QUESTION);
        answer = attributes.getString(ANSWER);
        /*
        AttributeValue value = map.get("question");
        if(value != null) {
           question = value.s();
        }
        */
    }

    @Override
    public Map<String, AttributeValue> save() {
        DynamodbAttributes attributes = new DynamodbAttributes();
        attributes.putString(ID, id);
        //questionMap.put(ID, AttributeValue.builder().s(id).build());
        if(question != null) {
            //questionMap.put(QUESTION, AttributeValue.builder().s(question).build());
            attributes.putString(QUESTION, question);
        }
        if(answer != null) {
            //questionMap.put(ANSWER, AttributeValue.builder().s(answer).build());
            attributes.putString(ANSWER, answer);
        }

        return attributes.getAttributesMap();
    }


}
