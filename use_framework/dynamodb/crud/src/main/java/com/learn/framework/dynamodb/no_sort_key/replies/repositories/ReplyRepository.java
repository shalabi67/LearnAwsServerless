package com.learn.framework.dynamodb.no_sort_key.replies.repositories;

import com.learn.framework.dynamodb.no_sort_key.product_catalog.models.Book;
import com.learn.framework.dynamodb.no_sort_key.replies.models.Reply;
import com.serverless.framework.dynamodb.factories.IDynamoDbClientFactory;
import com.serverless.framework.dynamodb.repository.DynamoDbRepository;

public class ReplyRepository extends DynamoDbRepository<Reply> {
    public ReplyRepository(IDynamoDbClientFactory dynamoDbClientFactory) {
        super(dynamoDbClientFactory);
    }
}
