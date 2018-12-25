package com.serverless.framework.dynamodb.examples;

import com.serverless.framework.dynamodb.examples.models.Question;
import com.serverless.framework.dynamodb.examples.repositories.QuestionRepository;
import com.serverless.framework.dynamodb.factories.DynamodbClientFactory;

public class QuestionsExample {
    public static void main(String[] args) {
        QuestionRepository questionRepository = new QuestionRepository(new DynamodbClientFactory());

        Question question = new Question();
        //question.set
        Question newQuestion = questionRepository.save(question);
    }
}
