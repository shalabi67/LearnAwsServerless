package com.labs.FaqApiUsingFramework.repositories;

import com.labs.FaqApiUsingFramework.models.Question;
import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamoDbRepository;

public class QuestionRepository extends DynamoDbRepository<Question> {
    public QuestionRepository(IDynamoDbClientFactory dynamoDbClientFactory) {
        super(dynamoDbClientFactory);
    }
}
