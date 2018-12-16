package com.labs.faq.repositories;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.labs.faq.models.Question;

public class QuestionRepository {
    private static AmazonDynamoDB dynamoDb = AmazonDynamoDBClientBuilder.standard().build();

    public Question save(Question item) {
        DynamoDBMapper mapper = new DynamoDBMapper(dynamoDb);
        mapper.save(item);
        return item;
    }
}
